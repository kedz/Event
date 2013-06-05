package edu.columbia.cs.event.annotation;

import edu.columbia.cs.event.text.TextUnit;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/4/13
 * Time: 8:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextUnitAnnotationTest {

    /**
     * Constructors should always throw an exception if a null TextUnit instance is passed as an argument
     * */
    @Test(expected = IllegalArgumentException.class)
    public void testNullConstructor1() throws Exception {
        TextUnitAnnotation textUnitAnnotation = new TextUnitAnnotation(null,"lemma","NN","O");
    }

    /**
     * Constructors should always throw an exception if a null String instance is passed as an argument
     * */
    @Test(expected = IllegalArgumentException.class)
    public void testNullConstructor2() throws Exception {
        TextUnit textUnit = new TextUnit("token","docId",1,1,0,5);
        TextUnitAnnotation textUnitAnnotation = new TextUnitAnnotation(textUnit,null,"NN","O");
    }

    /**
     * Constructors should always throw an exception if a null String instance is passed as an argument
     * */
    @Test(expected = IllegalArgumentException.class)
    public void testNullConstructor3() throws Exception {
        TextUnit textUnit = new TextUnit("token","docId",1,1,0,5);
        TextUnitAnnotation textUnitAnnotation = new TextUnitAnnotation(textUnit,"token",null,"O");
    }

    /**
     * Constructors should always throw an exception if a null String instance is passed as an argument
     * */
    @Test(expected = IllegalArgumentException.class)
    public void testNullConstructor4() throws Exception {
        TextUnit textUnit = new TextUnit("token","docId",1,1,0,5);
        TextUnitAnnotation textUnitAnnotation = new TextUnitAnnotation(textUnit,"lemma","NN",null);
    }

    /**
     * Test getLemma getter
     * @throws Exception
     */
    @Test
    public void testGetLemma() throws Exception {
        TextUnit textUnit = new TextUnit("token","docId",1,1,0,5);
        TextUnitAnnotation textUnitAnnotation = new TextUnitAnnotation(textUnit,"lemma","NN","O");
        Assert.assertEquals("lemma", textUnitAnnotation.getLemma());

    }

    /**
     * Test getPos getter
     * @throws Exception
     */
    @Test
    public void testGetPos() throws Exception {
        TextUnit textUnit = new TextUnit("token","docId",1,1,0,5);
        TextUnitAnnotation textUnitAnnotation = new TextUnitAnnotation(textUnit,"lemma","NN","O");
        Assert.assertEquals("NN", textUnitAnnotation.getPos());
    }

    /**
     * Test getNe getter
     * @throws Exception
     */
    @Test
    public void testGetNe() throws Exception {
        TextUnit textUnit = new TextUnit("token","docId",1,1,0,5);
        TextUnitAnnotation textUnitAnnotation = new TextUnitAnnotation(textUnit,"lemma","NN","O");
        Assert.assertEquals("O", textUnitAnnotation.getNe());
    }

    /**
     * Test getTextUnit getter
     * @throws Exception
     */
    @Test
    public void testGetTextUnit() throws Exception {
        TextUnit textUnit = new TextUnit("token","docId",1,1,0,5);
        TextUnit textUnitNotEqual = new TextUnit("token","docId",1,1,1,6);
        TextUnitAnnotation textUnitAnnotation = new TextUnitAnnotation(textUnit,"lemma","NN","O");
        Assert.assertEquals(textUnit, textUnitAnnotation.getTextUnit());
        Assert.assertNotEquals(textUnitNotEqual, textUnitAnnotation.getTextUnit());
    }

    /**
     * Test getToken getter
     * @throws Exception
     */
    @Test
    public void testGetToken() throws Exception {
        TextUnit textUnit = new TextUnit("token","docId",1,1,0,5);
        TextUnitAnnotation textUnitAnnotation = new TextUnitAnnotation(textUnit,"lemma","NN","O");
        Assert.assertEquals(textUnit.getToken(), textUnitAnnotation.getToken());
    }

    /**
     * Test getDocumentId getter
     * @throws Exception
     */
    @Test
    public void testGetDocumentId() throws Exception {
        TextUnit textUnit = new TextUnit("token","docId",1,1,0,5);
        TextUnitAnnotation textUnitAnnotation = new TextUnitAnnotation(textUnit,"lemma","NN","O");
        Assert.assertEquals(textUnit.getDocumentId(), textUnitAnnotation.getDocumentId());
    }

    /**
     * Test getLineNumber getter
     * @throws Exception
     */
    @Test
    public void testGetLineNumber() throws Exception {
        TextUnit textUnit = new TextUnit("token","docId",1,1,0,5);
        TextUnitAnnotation textUnitAnnotation = new TextUnitAnnotation(textUnit,"lemma","NN","O");
        Assert.assertEquals(textUnit.getLineNumber(), textUnitAnnotation.getLineNumber());
    }

    /**
     * Test getWordIndex getter
     * @throws Exception
     */
    @Test
    public void testGetWordIndex() throws Exception {
        TextUnit textUnit = new TextUnit("token","docId",1,1,0,5);
        TextUnitAnnotation textUnitAnnotation = new TextUnitAnnotation(textUnit,"lemma","NN","O");
        Assert.assertEquals(textUnit.getWordIndex(), textUnitAnnotation.getWordIndex());
    }

    /**
     * Test getStartPosition getter
     * @throws Exception
     */
    @Test
    public void testGetStartPosition() throws Exception {
        TextUnit textUnit = new TextUnit("token","docId",1,1,0,5);
        TextUnitAnnotation textUnitAnnotation = new TextUnitAnnotation(textUnit,"lemma","NN","O");
        Assert.assertEquals(textUnit.getStartPosition(), textUnitAnnotation.getStartPosition());
    }

    /**
     * Test getEndPosition getter
     * @throws Exception
     */
    @Test
    public void testGetEndPosition() throws Exception {
        TextUnit textUnit = new TextUnit("token","docId",1,1,0,5);
        TextUnitAnnotation textUnitAnnotation = new TextUnitAnnotation(textUnit,"lemma","NN","O");
        Assert.assertEquals(textUnit.getEndPosition(), textUnitAnnotation.getEndPosition());
    }

    /**
     * Test compareTo
     * @throws Exception
     */
    @Test
    public void testCompareTo() throws Exception {

        TextUnit textUnitA = new TextUnit("token","docId",1,1,0,5);
        TextUnitAnnotation textUnitAnnotationA = new TextUnitAnnotation(textUnitA,"lemma","NN","O");
        TextUnitAnnotation textUnitAnnotationB = new TextUnitAnnotation(textUnitA, "lemma","NN", "PERSON");
        TextUnitAnnotation textUnitAnnotationC = new TextUnitAnnotation(textUnitA, "lemma","VRB", "O");
        TextUnitAnnotation textUnitAnnotationD = new TextUnitAnnotation(textUnitA, "Lemma","NN", "O");

        TextUnit textUnitB = new TextUnit("token","docId",1,6,20,25);
        TextUnitAnnotation textUnitAnnotationE = new TextUnitAnnotation(textUnitB, "lemma","NN", "O");


        Assert.assertTrue(textUnitAnnotationA.compareTo(textUnitAnnotationB) < 0); // identical except for ne
        Assert.assertTrue(textUnitAnnotationA.compareTo(textUnitAnnotationC) < 0); // identical except for pos
        Assert.assertTrue(textUnitAnnotationA.compareTo(textUnitAnnotationD) > 0); // identical except for lemma
        Assert.assertEquals(0, textUnitAnnotationA.compareTo(textUnitAnnotationA)); // equal
        Assert.assertTrue(textUnitAnnotationA.compareTo(textUnitAnnotationE) < 0); // identical except for textUnit

    }

    /**
     * Test equals
     * @throws Exception
     */
    public void testEquals() throws Exception {
        TextUnit textUnitA = new TextUnit("token","docId",1,1,0,5);

        TextUnitAnnotation textUnitAnnotationA = new TextUnitAnnotation(textUnitA,"lemma","NN","O"); // same
        TextUnitAnnotation textUnitAnnotationB = new TextUnitAnnotation(textUnitA, "lemma","NN", "PERSON"); // different
        TextUnitAnnotation textUnitAnnotationC = new TextUnitAnnotation(textUnitA, "lemma","NN", "O"); // same

        Assert.assertNotEquals(textUnitAnnotationA,textUnitAnnotationB);
        Assert.assertEquals(textUnitAnnotationA,textUnitAnnotationC);
    }

    /**
     * Test hashCode
     * @throws Exception
     */
    public void testHashCode() throws Exception {

        HashSet<TextUnitAnnotation> set = new HashSet<TextUnitAnnotation>();
        TextUnit textUnitA = new TextUnit("token","docId",1,1,0,5);
        TextUnitAnnotation textUnitAnnotationA = new TextUnitAnnotation(textUnitA,"lemma","NN","O"); //same
        TextUnitAnnotation textUnitAnnotationB = new TextUnitAnnotation(textUnitA, "lemma","NN", "PERSON"); //different
        TextUnitAnnotation textUnitAnnotationC = new TextUnitAnnotation(textUnitA, "lemma","NN", "O"); //same
        set.add(textUnitAnnotationA);
        set.add(textUnitAnnotationB);
        set.add(textUnitAnnotationC);

        Assert.assertEquals(2,set.size());

    }
}
