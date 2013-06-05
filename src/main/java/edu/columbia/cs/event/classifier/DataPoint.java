package edu.columbia.cs.event.classifier;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/5/13
 * Time: 2:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class DataPoint {

    private final int numFeatures;
    private Integer label = null;
    private SparseBinaryFeatureVector featureVector = new SparseBinaryFeatureVector();

    public DataPoint(int numFeatures) {
        this.numFeatures = numFeatures;
    }

    public void activateFeature(Integer featureNum) {
        if (featureNum >= numFeatures || featureNum < 0)
            throw new IllegalArgumentException("Feature indexes must be >= 0 and < 'numFeatures'.");
        featureVector.activateFeature(featureNum);
    }

    public boolean isActive(Integer featureNum) {
        return featureVector.isActive(featureNum);
    }

    public DataPoint setLabel(Integer label) {
        this.label = label;
        return this;
    }
    public Integer getLabel() {return label;}

    public SparseBinaryFeatureVector getFeatureVector() {return featureVector;}
    public Set<Integer> getActiveFeatures() {return featureVector.getActiveFeatures();}
    public int getNumFeatures() {return numFeatures;}


    @Override
    public String toString() {

        return "[ DataPoint: Label: "+((label==null) ? "?" : label)+" TotalFeatures: "+numFeatures+" ActiveFeatures: "+getFeatureVector()+" ]";
    }

    @Override
    public boolean equals(Object other) {
        if (other==null)
            return false;
        if (!(other instanceof DataPoint))
            return false;
        DataPoint otherDataPoint = (DataPoint) other;
        if (this.getLabel() != otherDataPoint.getLabel())
            return false;
        if (this.getNumFeatures() != otherDataPoint.getNumFeatures())
            return false;

        return this.getFeatureVector().equals(otherDataPoint.getFeatureVector());

    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

}
