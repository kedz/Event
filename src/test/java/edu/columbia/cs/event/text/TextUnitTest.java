package edu.columbia.cs.event.text;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/4/13
 * Time: 2:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextUnitTest {

    @Test
    public void testGetToken() throws Exception {
        TextUnit tUnit = new TextUnit("token","docId",1,1,0,5);
        assertEquals("token", tUnit.getToken());
    }

    @Test
    public void testGetDocumentId() throws Exception {
        TextUnit tUnit = new TextUnit("token","docId",1,2,0,5);
        assertEquals("docId",tUnit.getDocumentId());
    }

    @Test
    public void testGetLineNumber() throws Exception {
        TextUnit tUnit = new TextUnit("token","docId",1,2,0,5);
        assertEquals(1,tUnit.getLineNumber());
    }

    @Test
    public void testGetWordIndex() throws Exception {
        TextUnit tUnit = new TextUnit("token","docId",1,2,0,5);
        assertEquals(2,tUnit.getWordIndex());
    }

    @Test
    public void testGetStartPosition() throws Exception {
        TextUnit tUnit = new TextUnit("token","docId",1,1,0,5);
        assertEquals(0,tUnit.getStartPosition());
    }

    @Test
    public void testGetEndPosition() throws Exception {
        TextUnit tUnit = new TextUnit("token","docId",1,1,0,5);
        assertEquals(5,tUnit.getEndPosition());
    }

    @Test
    public void testEquals() throws Exception {


        TextUnit tUnitA = new TextUnit("token","docId",1,1,0,5);
        TextUnit tUnitB = new TextUnit("TOKEN","docId",1,1,0,5);
        TextUnit tUnitC = new TextUnit("token","DiffId",1,1,0,5);
        TextUnit tUnitD = new TextUnit("token","docId",2,1,0,5);
        TextUnit tUnitE = new TextUnit("token","docId",1,2,0,5);
        TextUnit tUnitF = new TextUnit("token","docId",1,1,1,6);


        String aToken = "toke";
        aToken += "n";
        TextUnit tUnitG = new TextUnit(aToken,"docId",1,1,0,5);

        assertEquals(false,tUnitA.equals(tUnitB));
        assertEquals(false,tUnitA.equals(tUnitC));
        assertEquals(false,tUnitA.equals(tUnitD));
        assertEquals(false,tUnitA.equals(tUnitE));
        assertEquals(false,tUnitA.equals(tUnitF));


        assertEquals(tUnitA,tUnitG);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullConstructor1() throws Exception {
        TextUnit tUnit = new TextUnit(null,"docId",1,1,0,5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullConstructor2() throws Exception {
        TextUnit tUnit = new TextUnit("token",null,1,1,0,5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullConstructor3() throws Exception {
        TextUnit tUnit = new TextUnit(null,"docId",1,1,0,5,true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullConstructor4() throws Exception {
        TextUnit tUnit = new TextUnit("token",null,1,1,0,5,true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidStartStopRange1() {
        TextUnit tUnit = new TextUnit("token","docId",1,1,-1,4);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidStartStopRange2() throws Exception {
        TextUnit tUnit = new TextUnit("token","docId",1,1,5,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidStartStopRange3() throws Exception {
        TextUnit tUnit = new TextUnit("token","docId",1,1,0,8);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidStartStopRange4() throws Exception {
        TextUnit tUnit = new TextUnit(null,"docId",1,1,4,5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidStartStopRange5() {
        TextUnit tUnit = new TextUnit("token","docId",1,1,-1,4,true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidStartStopRange6() throws Exception {
        TextUnit tUnit = new TextUnit("token","docId",1,1,5,0,true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidStartStopRange7() throws Exception {
        TextUnit tUnit = new TextUnit("token","docId",1,1,0,8,true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidStartStopRange8() throws Exception {
        TextUnit tUnit = new TextUnit(null,"docId",1,1,4,5,true);
    }


    @Test
    public void testCompareTo() throws Exception {

        TextUnit[] orderedArray = new TextUnit[9];

        orderedArray[0] = new TextUnit("This", "0112235", 1,1,0,4);
        orderedArray[1] = new TextUnit("one", "0112235",1,2,6,9);
        orderedArray[2] = new TextUnit("That", "0112235",2,1,0,4);

        orderedArray[3] = new TextUnit("one", "0112235",2,2,6,9);
        orderedArray[4] = new TextUnit("alone", "1112232",1,1,0,5);
        orderedArray[5] = new TextUnit("A", "1112234",1,1,0,1);

        orderedArray[6] = new TextUnit("longer", "1112234",1,2,2,8);
        orderedArray[7] = new TextUnit("sentence", "1112234",1,3,11,19);
        orderedArray[8] = new TextUnit("fragment", "1112234",1,4,21,29);


        List<TextUnit> orderedList = new ArrayList<TextUnit>();

        orderedList.add(orderedArray[2]);
        orderedList.add(orderedArray[8]);
        orderedList.add(orderedArray[6]);
        orderedList.add(orderedArray[5]);
        orderedList.add(orderedArray[7]);
        orderedList.add(orderedArray[1]);
        orderedList.add(orderedArray[3]);
        orderedList.add(orderedArray[0]);
        orderedList.add(orderedArray[4]);

        Collections.sort(orderedList);

        for(int i = 0; i < orderedList.size(); i++) {
            assertEquals(orderedList.get(i), orderedArray[i]);
        }

    }

}
