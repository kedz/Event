package edu.columbia.cs.event.classifier;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/5/13
 * Time: 12:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class SparseBinaryFeatureVector {

    private Set<Integer> activeFeatures = new TreeSet<Integer>();

    public SparseBinaryFeatureVector activateFeature(Integer featureIndex) {
        activeFeatures.add(featureIndex);
        return this;
    }

    public boolean isActive(Integer featureIndex) {
        if (featureIndex == null)
            return false;
        return activeFeatures.contains(featureIndex);
    }
    public Set<Integer> getActiveFeatures() {return activeFeatures;}
    public int getNumActiveFeatures() {return activeFeatures.size();}

    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();
        for(Integer featureIndex : activeFeatures) {
            buffer.append(featureIndex+" ");
        }

        return "[ SparseBinaryFeatureVector: "+buffer.toString()+"]";
    }

    @Override
    public boolean equals(Object other) {
        if (other==null)
            return false;
        if (!(other instanceof SparseBinaryFeatureVector))
            return false;
        SparseBinaryFeatureVector otherVector = (SparseBinaryFeatureVector) other;
        if (this.getNumActiveFeatures() != otherVector.getNumActiveFeatures())
            return false;

        return this.toString().equals(otherVector.toString());

    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

}
