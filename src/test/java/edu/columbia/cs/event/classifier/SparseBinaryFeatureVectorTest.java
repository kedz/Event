package edu.columbia.cs.event.classifier;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/5/13
 * Time: 3:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class SparseBinaryFeatureVectorTest {

    @Test
    public void testIsActive() throws Exception {
        SparseBinaryFeatureVector featureVector = new SparseBinaryFeatureVector();
        featureVector.activateFeature(6);
        featureVector.activateFeature(3);
        featureVector.activateFeature(10);
        featureVector.activateFeature(0);

        Assert.assertTrue(featureVector.isActive(0));
        Assert.assertFalse(featureVector.isActive(1));
        Assert.assertFalse(featureVector.isActive(2));
        Assert.assertTrue(featureVector.isActive(3));
        Assert.assertFalse(featureVector.isActive(4));
        Assert.assertFalse(featureVector.isActive(5));
        Assert.assertTrue(featureVector.isActive(6));
        Assert.assertFalse(featureVector.isActive(7));
        Assert.assertFalse(featureVector.isActive(8));
        Assert.assertFalse(featureVector.isActive(9));
        Assert.assertTrue(featureVector.isActive(10));
        Assert.assertFalse(featureVector.isActive(null));

    }

    @Test
    public void testToString() throws Exception {
        SparseBinaryFeatureVector featureVector = new SparseBinaryFeatureVector();
        featureVector.activateFeature(6);
        featureVector.activateFeature(3);
        featureVector.activateFeature(10);
        featureVector.activateFeature(0);

        Assert.assertEquals("[ SparseBinaryFeatureVector: 0 3 6 10 ]", featureVector.toString());
    }

    @Test
    public void testEquals() throws Exception {
        SparseBinaryFeatureVector featureVectorA = new SparseBinaryFeatureVector();

        featureVectorA.activateFeature(6);
        featureVectorA.activateFeature(3);
        featureVectorA.activateFeature(10);
        featureVectorA.activateFeature(0);

        SparseBinaryFeatureVector featureVectorB = new SparseBinaryFeatureVector();

        featureVectorB.activateFeature(4);
        featureVectorB.activateFeature(3);
        featureVectorB.activateFeature(10);
        featureVectorB.activateFeature(0);

        SparseBinaryFeatureVector featureVectorC = new SparseBinaryFeatureVector();

        featureVectorC.activateFeature(0);
        featureVectorC.activateFeature(3);
        featureVectorC.activateFeature(10);
        featureVectorC.activateFeature(6);

        Assert.assertNotEquals(featureVectorA,featureVectorB);
        Assert.assertNotEquals(featureVectorA, null);
        Assert.assertEquals(featureVectorA,featureVectorC);


    }

    @Test
    public void testHashCode() throws Exception {
        SparseBinaryFeatureVector featureVectorA = new SparseBinaryFeatureVector();

        featureVectorA.activateFeature(6);
        featureVectorA.activateFeature(3);
        featureVectorA.activateFeature(10);
        featureVectorA.activateFeature(0);

        SparseBinaryFeatureVector featureVectorB = new SparseBinaryFeatureVector();

        featureVectorB.activateFeature(4);
        featureVectorB.activateFeature(3);
        featureVectorB.activateFeature(10);
        featureVectorB.activateFeature(0);

        SparseBinaryFeatureVector featureVectorC = new SparseBinaryFeatureVector();

        featureVectorC.activateFeature(0);
        featureVectorC.activateFeature(3);
        featureVectorC.activateFeature(10);
        featureVectorC.activateFeature(6);

        Set<SparseBinaryFeatureVector> set = new HashSet<SparseBinaryFeatureVector>();
        set.add(featureVectorA);
        set.add(featureVectorB);
        set.add(featureVectorC);

        Assert.assertEquals(2,set.size());

    }


}
