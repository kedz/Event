package edu.columbia.cs.event.text;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;


/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/3/13
 * Time: 10:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class RawLineTest {


    @Test
    public void testOrdering() {

        RawLine[] orderedArray = new RawLine[9];

        orderedArray[0] = new RawLine("Raw line A.", "0112235", 1);
        orderedArray[1] = new RawLine("Raw line B.", "0112235", 8);
        orderedArray[2] = new RawLine("Raw line C.", "1112232", 2);

        orderedArray[3] = new RawLine("Raw line D.", "1112232", 4);
        orderedArray[4] = new RawLine("Raw line E.", "1112232", 6);
        orderedArray[5] = new RawLine("Raw line F.", "1112234", 3);

        orderedArray[6] = new RawLine("Raw line G.", "1112234", 7);
        orderedArray[7] = new RawLine("Raw line H.", "1112235", 9);
        orderedArray[8] = new RawLine("Raw line I.", "1112254", 5);


        List<RawLine> orderedList = new ArrayList<RawLine>();

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
            Assert.assertEquals(orderedList.get(i), orderedArray[i]);
        }

    }


    @Test(expected = IllegalArgumentException.class)
    public void testNullTextString() {
        new RawLine(null, "1234", 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullDocumentId() {
        new RawLine("A line.", null, 1);
    }

    @Test
    public void testStringIntern() {

        String lineText = "This will be internalized";
        lineText += ".";
        RawLine rawLine = new RawLine(lineText,"1234",1,true);
        String same = new String("This will be internalized");
        same += ".";
        same = same.intern();

        Assert.assertSame(same, rawLine.getLineText());

    }

    @Test
    public void testIdentity() {
        HashSet<RawLine> rawLineSet = new HashSet<RawLine>();

        RawLine lineA = new RawLine("This is a line.", "1234",1, true);

        String lineText = "This is a line";
        lineText += ".";

        RawLine lineB = new RawLine(lineText,"1234",1,false);
        RawLine lineC = new RawLine("Some other line.","4321",1,false);


        rawLineSet.add(lineA);
        rawLineSet.add(lineB);
        rawLineSet.add(lineC);

        Assert.assertEquals(2,rawLineSet.size(),0);


    }


}
