package edu.columbia.cs.event.classifier;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/5/13
 * Time: 4:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class DataPointTest {

    /**
     * Data points cannot add feature indexes greater than the number specified in the constructor
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMaxFeature() {
        DataPoint dataPoint = new DataPoint(10);
        dataPoint.activateFeature(10);
    }

    /**
     * Data points cannot add feature indexes less than 0
     */
    @Test(expected = IllegalArgumentException.class)
    public void testMinFeature() {
        DataPoint dataPoint = new DataPoint(10);
        dataPoint.activateFeature(-20);
    }

    @Test
    public void testIsActive() throws Exception {
        DataPoint dataPoint = new DataPoint(11);
        dataPoint.activateFeature(6);
        dataPoint.activateFeature(3);
        dataPoint.activateFeature(10);
        dataPoint.activateFeature(0);

        Assert.assertTrue(dataPoint.isActive(0));
        Assert.assertFalse(dataPoint.isActive(1));
        Assert.assertFalse(dataPoint.isActive(2));
        Assert.assertTrue(dataPoint.isActive(3));
        Assert.assertFalse(dataPoint.isActive(4));
        Assert.assertFalse(dataPoint.isActive(5));
        Assert.assertTrue(dataPoint.isActive(6));
        Assert.assertFalse(dataPoint.isActive(7));
        Assert.assertFalse(dataPoint.isActive(8));
        Assert.assertFalse(dataPoint.isActive(9));
        Assert.assertTrue(dataPoint.isActive(10));
        Assert.assertFalse(dataPoint.isActive(null));

    }

    @Test
    public void testToString() throws Exception {
        DataPoint dataPoint = new DataPoint(10);
        dataPoint.activateFeature(6);
        dataPoint.activateFeature(3);
        dataPoint.activateFeature(9);
        dataPoint.activateFeature(0);

        Assert.assertEquals("[ DataPoint: Label: ? TotalFeatures: 10 ActiveFeatures: [ SparseBinaryFeatureVector: 0 3 6 9 ] ]", dataPoint.toString());
    }

    @Test
    public void testEquals() throws Exception {
        DataPoint dataPointA = new DataPoint(10);

        dataPointA.activateFeature(1);
        dataPointA.activateFeature(2);
        dataPointA.activateFeature(3);
        dataPointA.activateFeature(4);

        DataPoint dataPointB = new DataPoint(6);

        dataPointB.activateFeature(1);
        dataPointB.activateFeature(2);
        dataPointB.activateFeature(3);
        dataPointB.activateFeature(4);

        DataPoint dataPointC = new DataPoint(10);

        dataPointC.activateFeature(4);
        dataPointC.activateFeature(3);
        dataPointC.activateFeature(2);
        dataPointC.activateFeature(1);

        DataPoint dataPointD = new DataPoint(10);

        dataPointD.activateFeature(8);
        dataPointD.activateFeature(3);
        dataPointD.activateFeature(9);
        dataPointD.activateFeature(0);


        Assert.assertNotEquals(dataPointA, null);
        Assert.assertNotEquals(dataPointA,dataPointB);
        Assert.assertEquals(dataPointA,dataPointC);
        Assert.assertNotEquals(dataPointA, dataPointD);




    }

    @Test
    public void testHashCode() throws Exception {
        DataPoint dataPointA = new DataPoint(10);

        dataPointA.activateFeature(1);
        dataPointA.activateFeature(2);
        dataPointA.activateFeature(3);
        dataPointA.activateFeature(4);

        DataPoint dataPointB = new DataPoint(6);

        dataPointB.activateFeature(1);
        dataPointB.activateFeature(2);
        dataPointB.activateFeature(3);
        dataPointB.activateFeature(4);

        DataPoint dataPointC = new DataPoint(10);

        dataPointC.activateFeature(4);
        dataPointC.activateFeature(3);
        dataPointC.activateFeature(2);
        dataPointC.activateFeature(1);

        DataPoint dataPointD = new DataPoint(10);

        dataPointD.activateFeature(8);
        dataPointD.activateFeature(3);
        dataPointD.activateFeature(9);
        dataPointD.activateFeature(0);

        Set<DataPoint> set = new HashSet<DataPoint>();
        set.add(dataPointA);
        set.add(dataPointB);
        set.add(dataPointC);
        set.add(dataPointD);

        Assert.assertEquals(3,set.size());

    }

}
