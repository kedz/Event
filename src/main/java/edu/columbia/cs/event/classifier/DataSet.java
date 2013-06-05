package edu.columbia.cs.event.classifier;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/5/13
 * Time: 2:03 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataSet {

    private List<DataPoint> dataPointList = new ArrayList<DataPoint>();
    private Map<Integer,List<DataPoint>> activeFeatureToDataPointsMap = new HashMap<Integer, List<DataPoint>>();
    private Integer numFeatures;
    private Set<Integer> labels;

    //Empirical Distributions
    private Map<DataPoint,Double> empiricalJointDistribution = new HashMap<DataPoint, Double>();
    private Map<SparseBinaryFeatureVector,Double> empiricalMarginalDistribution = new HashMap<SparseBinaryFeatureVector, Double>();
    //Expected Value of Feature Count
    private Map<Integer,Double> expectedFeatureCount = new HashMap<Integer, Double>();

    public DataSet(File trainingData, File trainingLabels) throws Exception {
        loadTrainingData(trainingData, trainingLabels);
    }

    private void loadTrainingData(File trainingDataFile, File labelDataFile) throws Exception {

        //Empirical Counts for P~(x,y)
        Map<DataPoint,Integer> dataPointCounts = new HashMap<DataPoint, Integer>();
        //Marginal Counts for P~(x)
        Map<SparseBinaryFeatureVector,Integer> marginalFeatureSetCounts = new HashMap<SparseBinaryFeatureVector, Integer>();

        HashMap<String,DataPoint> cachedDataPoints = new HashMap<String, DataPoint>();

        try {

            BufferedReader trainReader = new BufferedReader(new FileReader(trainingDataFile));
            BufferedReader labelReader = new BufferedReader(new FileReader(labelDataFile));
            while(trainReader.ready()
                    && labelReader.ready()) {

                String label = labelReader.readLine();
                String featureString = trainReader.readLine();


                /** 2 Cases here:
                 *  1: We have seen the same context and label before, so grab it from the cache.
                 *  2: This is a unique(so far) context/label pair. Create a new DataPoint instance.
                 *
                 *  Either way, the following need to be done:
                 *      A: Add this point to the list associated with each active feature (activeFeatureToDataPointsMap
                 *      B: Increment empirical joint counts for a context/label pair (dataPointCounts)
                 *      C: Increment empirical marginal counts for a context (marginalFeatureSetCounts)
                 *      D: Add DataPoint to grand total list of DataPoints (dataPointList)
                 */

                if (cachedDataPoints.containsKey(label+":"+featureString)) {

                    //Grab cached DataPoint instance
                    DataPoint dataPoint = cachedDataPoints.get(label+":"+featureString);

                    // Part A
                    for(Integer featureIndex : dataPoint.getActiveFeatures()) {
                        activeFeatureToDataPointsMap.get(featureIndex).add(dataPoint);
                    }

                    // Part B
                    Integer dataPointCountPlusOne = dataPointCounts.get(dataPoint) + 1;
                    dataPointCounts.put(dataPoint, dataPointCountPlusOne);

                    // Part C
                    Integer marginalCountPlusOne = marginalFeatureSetCounts.get(dataPoint.getFeatureVector()) + 1;
                    marginalFeatureSetCounts.put(dataPoint.getFeatureVector(),marginalCountPlusOne);

                    // Part D
                    dataPointList.add(dataPoint);
                    System.out.println(dataPoint+":"+dataPointCountPlusOne+":"+marginalCountPlusOne);

                } else {


                    String[] features = featureString.split(",");

                    int numFeatures = features.length;
                    if (this.numFeatures == null) {
                        this.numFeatures = numFeatures;
                    } else if (this.numFeatures != numFeatures) {
                        throw new Exception("Inconsistent number of features used. All feature vectors must be of the same length.");
                    }

                    // Create a new DataPoint instance
                    DataPoint dataPoint = new DataPoint(numFeatures);
                    dataPoint.setLabel(Integer.parseInt(label));
                    labels.add(Integer.parseInt(label));

                    // Part A
                    for(int i = 0; i < features.length; i++) {
                        int feature = Integer.parseInt(features[i]);
                        if (feature==1) {
                            dataPoint.activateFeature(i);

                            if (!activeFeatureToDataPointsMap.containsKey(i)) {
                                activeFeatureToDataPointsMap.put(i,new LinkedList<DataPoint>());
                            }
                            activeFeatureToDataPointsMap.get(i).add(dataPoint);

                        }

                    }

                    // Part B
                    dataPointCounts.put(dataPoint, 1);

                    //Part C
                    if (marginalFeatureSetCounts.containsKey(dataPoint.getFeatureVector())) {
                        Integer marginalCountPlusOne = marginalFeatureSetCounts.get(dataPoint.getFeatureVector()) + 1;
                        marginalFeatureSetCounts.put(dataPoint.getFeatureVector(),marginalCountPlusOne);
                    } else {
                        marginalFeatureSetCounts.put(dataPoint.getFeatureVector(),1);
                    }

                    // Part D
                    dataPointList.add(dataPoint);

                    cachedDataPoints.put(label+":"+featureString,dataPoint);

                }
            }

            trainReader.close();
            labelReader.close();

        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(-1);
        }

        // Make empirical distributions
        for(DataPoint dataPoint : dataPointCounts.keySet()) {
            double prob = (double) dataPointCounts.get(dataPoint) / (double) getNumDataPoints();
            empiricalJointDistribution.put(dataPoint,prob);
        }
        for(SparseBinaryFeatureVector featureVector : marginalFeatureSetCounts.keySet()) {
            double prob = (double) marginalFeatureSetCounts.get(featureVector) / (double) getNumDataPoints();
            empiricalMarginalDistribution.put(featureVector,prob);
        }
        for(int featureIndex = 0; featureIndex < numFeatures; featureIndex++) {
            List<DataPoint> dataPointsForFeature = activeFeatureToDataPointsMap.get(featureIndex);
            double expectedValue = 0.0;

            for(DataPoint dataPoint : dataPointsForFeature) {
                expectedValue += empiricalJointDistribution.get(dataPoint);
            }

            expectedFeatureCount.put(featureIndex,expectedValue);
        }
    }


    public List<DataPoint> getDataPointList() {return dataPointList;}
    public int getNumFeatures() {return numFeatures;}
    public int getNumDataPoints() {return dataPointList.size();}

    public double getExpectedFeatureCount(Integer featureIndex) {return expectedFeatureCount.get(featureIndex);}

    public Map<DataPoint,Double> getEmpiricalJointDistribution() {return empiricalJointDistribution;}
    public double getEmpiricalJointProbability(DataPoint dataPoint) {
        if (empiricalJointDistribution.containsKey(dataPoint))
            return empiricalJointDistribution.get(dataPoint);
        return 0.0;
    }

    public Map<SparseBinaryFeatureVector,Double> getEmpiricalMarginalDistribution() {return empiricalMarginalDistribution;}
    public double getEmpiricalMarginalProbability(SparseBinaryFeatureVector featureVector) {
        if (empiricalMarginalDistribution.containsKey(featureVector))
            return empiricalMarginalDistribution.get(featureVector);
        return 0.0;
    }
    public double getEmpiricalMarginalProbability(DataPoint dataPoint) {
        return getEmpiricalMarginalProbability(dataPoint.getFeatureVector());
    }

    public Set<SparseBinaryFeatureVector> getContexts() {return getEmpiricalMarginalDistribution().keySet();}
    public Set<Integer> getLabels() {return labels;}

}
