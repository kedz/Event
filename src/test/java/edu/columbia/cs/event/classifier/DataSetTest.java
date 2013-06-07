package edu.columbia.cs.event.classifier;

import edu.columbia.cs.event.EventConfiguration;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/5/13
 * Time: 5:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataSetTest {


    public void testConstructor() throws Exception {

        EventConfiguration config = EventConfiguration.getInstance();

        File trainingFile = new File(config.getTestDirectory()+File.separator+"maxent"+File.separator+"train.csv");
        File labelsFile = new File(config.getTestDirectory()+File.separator+"maxent"+File.separator+"labels.csv");

        DataSet dataSet = new DataSet(trainingFile,labelsFile);

        for(DataPoint dataPoint : dataSet.getDataPointList()) {
            System.out.println(dataPoint);
        }

        System.out.println("Expected Feature Count");
        for(int featureIndex = 0; featureIndex < dataSet.getNumFeatures(); featureIndex++) {
            double expected = dataSet.getExpectedFeatureCount(featureIndex);
            System.out.println("Feature "+featureIndex+": "+expected);
        }

        System.out.println();
        System.out.println("Empirical Joint Probability");
        for(DataPoint dataPoint : dataSet.getEmpiricalJointDistribution().keySet()) {
            double prob = dataSet.getEmpiricalJointProbability(dataPoint);
            System.out.println(dataPoint+": "+prob);
        }

        System.out.println();
        System.out.println("Empirical Marginal Probability");
        for(SparseBinaryFeatureVector featureVector : dataSet.getEmpiricalMarginalDistribution().keySet()) {
            double prob = dataSet.getEmpiricalMarginalProbability(featureVector);
            System.out.println(featureVector+": "+prob);
        }

    }


    @Test
    public void testEmpiricalJointProbability() throws Exception {

        EventConfiguration config = EventConfiguration.getInstance();

        File trainingFile = new File(config.getTestDirectory()+File.separator+"maxent"+File.separator+"train.csv");
        File labelsFile = new File(config.getTestDirectory()+File.separator+"maxent"+File.separator+"labels.csv");

        DataSet dataSet = new DataSet(trainingFile,labelsFile);

        for(DataPoint aPoint : dataSet.getEmpiricalJointDistribution().keySet()) {

            if (aPoint.getLabel() == 0) {
                Assert.assertEquals(.2,dataSet.getEmpiricalJointProbability(aPoint),0.0);
            } else
                Assert.assertEquals(.05,dataSet.getEmpiricalJointProbability(aPoint),0.0);
        }

    }

    @Test
    public void testEmpiricalMarginalProbability() throws Exception {

        EventConfiguration config = EventConfiguration.getInstance();

        File trainingFile = new File(config.getTestDirectory()+File.separator+"maxent"+File.separator+"train.csv");
        File labelsFile = new File(config.getTestDirectory()+File.separator+"maxent"+File.separator+"labels.csv");

        DataSet dataSet = new DataSet(trainingFile,labelsFile);

        for(SparseBinaryFeatureVector featureVector : dataSet.getEmpiricalMarginalDistribution().keySet()) {
            System.out.println(featureVector+ " : "+dataSet.getEmpiricalMarginalProbability(featureVector));
            Assert.assertEquals(.25,dataSet.getEmpiricalMarginalProbability(featureVector),0.0);

        }

    }

    @Test
    public void testFeatureExpectedValue() throws Exception {

        EventConfiguration config = EventConfiguration.getInstance();

        File trainingFile = new File(config.getTestDirectory()+File.separator+"maxent"+File.separator+"train.csv");
        File labelsFile = new File(config.getTestDirectory()+File.separator+"maxent"+File.separator+"labels.csv");

        DataSet dataSet = new DataSet(trainingFile,labelsFile);

        for(int i = 0; i < dataSet.getNumFeatures(); i++) {
            System.out.println("Feature "+i+": "+dataSet.getExpectedFeatureCount(i));
            Assert.assertEquals(.5,dataSet.getExpectedFeatureCount(i),0.0);
        }


    }



}
