package edu.columbia.cs.event.annotation;

import edu.columbia.cs.event.annotation.TextUnitAnnotation;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: wojo
 * Date: 6/5/13
 * Time: 3:56 PM
 * To change this template use File | Settings | File Templates.
 */

public class SentenceAnnotation implements Comparable<SentenceAnnotation> {

    private ArrayList<TextUnitAnnotation> textUnitAnnotationList;

    private final String documentId;
    private final int lineNumber;
    private final int sentenceIndex;

    public SentenceAnnotation (ArrayList<TextUnitAnnotation> textUnitAntList, String documentId, int lineNumber, int sentenceIndex) {

        if (textUnitAntList == null)
            throw new IllegalArgumentException("Argument 'textUnitAntList' cannot be null.");

        if (documentId == null)
            throw new IllegalArgumentException("Argument 'documentId' cannot be null.");

        this.textUnitAnnotationList = textUnitAntList;
        this.documentId = documentId;
        this.lineNumber = lineNumber;
        this.sentenceIndex = sentenceIndex;
    }

    public String getDocumentId() {return documentId;}
    public int getLineNumber() {return lineNumber;}
    public int getSentenceIndex() {return sentenceIndex;}
    public ArrayList<TextUnitAnnotation> getTextUnitAntList() {return textUnitAnnotationList;}

    public int length() {return textUnitAnnotationList.size();}

    @Override
    public int compareTo (SentenceAnnotation other) {

        if (other==null)
            return 1;

        int docResult = this.getDocumentId().compareTo(other.getDocumentId());
        if (docResult != 0) {
            return docResult;
        } else if (this.getLineNumber() < other.getLineNumber()) {
            return -1;
        } else if (this.getLineNumber() > other.getLineNumber()) {
            return 1;
        } else if (this.getSentenceIndex() < other.getSentenceIndex()) {
            return -1;
        } else if (this.getSentenceIndex() > other.getSentenceIndex()) {
            return 1;
        } else if (this.length() < other.length()) {
            return -1;
        } else if (this.length() > other.length()) {
            return 1;
        } else {
            for (int i=0; i<this.length(); i++) {
                int textUnitResult = this.getTextUnitAntList().get(i).compareTo(other.getTextUnitAntList().get(i));
                if (textUnitResult != 0)
                    return textUnitResult;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        String output =  "[ SentenceAnnotation: DocId: "+documentId+" LineNum: "+lineNumber+" SentenceIndex: "+sentenceIndex+"]\n";
        for (TextUnitAnnotation textUnitAnnotation : textUnitAnnotationList) {
            output += "\t"+textUnitAnnotation.toString()+"\n";
        }
        return output;
    }




}
