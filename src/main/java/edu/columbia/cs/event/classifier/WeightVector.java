package edu.columbia.cs.event.classifier;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/5/13
 * Time: 6:36 PM
 * To change this template use File | Settings | File Templates.
 */
public class WeightVector {

    double[] weights;

    public WeightVector(int numWeights) {
        weights = new double[numWeights];
        for(int i = 0; i < numWeights; i++)
            weights[i] = 10;

    }

    public void setWeight(int index, double newWeight) {
        weights[index] = newWeight;
    }
    public double getWeight(int index) {return weights[index];}

    public int numWeights() {return weights.length;}

}
