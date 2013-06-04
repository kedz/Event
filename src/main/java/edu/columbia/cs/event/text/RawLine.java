package edu.columbia.cs.event.text;

import com.sun.org.apache.bcel.internal.generic.INSTANCEOF;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/3/13
 * Time: 10:03 PM
 * This class represents a raw line extracted from an article as it was found in the wild. For example all of the text
 * found between a {@literal <p>} tag would be encapsulated in a RawLine object.
 */
public class RawLine implements Comparable<RawLine> {

    private final String documentId;
    private final String lineText;
    private final int lineNumber;

    /**
     * If intern == true, use String.intern() method for storing line text.
     * */
    public RawLine(String lineText, String documentId, int lineNumber, boolean intern) {
        if (lineText==null)
            throw new IllegalArgumentException("Argument 'lineText' cannot be null.");
        if (documentId==null)
            throw new IllegalArgumentException("Argument 'documentId' cannot be null.");

        if (intern) {
            this.documentId = documentId.intern();
            this.lineText = lineText.intern();
        } else {
            this.documentId = documentId;
            this.lineText = lineText;
        }

        this.lineNumber = lineNumber;
    }

    public RawLine(String lineText, String documentId, int lineNumber) {
        if (lineText==null)
            throw new IllegalArgumentException("Argument 'lineText' cannot be null.");
        if (documentId==null)
            throw new IllegalArgumentException("Argument 'documentId' cannot be null.");
        this.lineText = lineText;
        this.lineNumber = lineNumber;
        this.documentId = documentId;
    }

    public String getDocumentId() {return documentId;}
    public String getLineText() {return lineText;}
    public int getLineNumber() {return lineNumber;}

    @Override
    public int compareTo(RawLine otherLine) {
        if (otherLine == null)
            return 1;
        int docResult = this.getDocumentId().compareTo(otherLine.getDocumentId());

        if (docResult!=0) {
            return docResult;
        } else if (this.getLineNumber() < otherLine.getLineNumber()) {
            return -1;
        } else if (this.getLineNumber() > otherLine.getLineNumber()) {
            return 1;
        }

        return 0;
    }

    @Override
    public String toString() {

        return "[ RawLine: \""+getLineText()+"\" DocId: "+getDocumentId()+" LineNum: "+getLineNumber()+" ]";

    }

    @Override
    public boolean equals(Object other) {
        if (other == null)
            return false;
        if (!(other instanceof RawLine))
            return false;
        RawLine otherLine = (RawLine) other;

        return getLineText().equals(otherLine.getLineText())
                && getLineNumber() == otherLine.getLineNumber()
                && getDocumentId().equals(otherLine.getDocumentId());

    }

    @Override
    public int hashCode() {
        return (9091*getLineText().hashCode())^(9103*getLineNumber())^(9109*getDocumentId().hashCode());
    }
}
