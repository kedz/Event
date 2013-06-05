package edu.columbia.cs.event.text;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/4/13
 * Time: 2:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextUnit implements Comparable<TextUnit> {

    private final String token;
    private final String documentId;
    private final int lineNumber;
    private final int tokenIndex;
    private final int startPosition;
    private final int endPosition;

    public TextUnit(String token, String documentId, int lineNumber, int tokenIndex, int startPosition, int endPosition) {
        this(token, documentId, lineNumber, tokenIndex, startPosition, endPosition, false);
    }

    public TextUnit(String token, String documentId, int lineNumber, int tokenIndex, int startPosition, int endPosition, boolean intern) {

        if (token == null)
            throw new IllegalArgumentException("Argument 'token' cannot be null.");
        if (documentId == null)
            throw new IllegalArgumentException("Argument 'documentId' cannot be null.");

        if (intern) {
            this.token = token.intern();
            this.documentId = documentId.intern();
        } else {
            this.token = token;
            this.documentId = documentId;
        }

        this.lineNumber = lineNumber;
        this.tokenIndex = tokenIndex;
        this.startPosition = startPosition;
        this.endPosition = endPosition;

        if (getEndPosition() - getStartPosition() != getToken().length()
                || getStartPosition() < 0
                || getEndPosition() < 0)
            throw new IllegalArgumentException("Invalid Start or End Position.");
    }

    public String getToken() {return token;}
    public String getDocumentId() {return documentId;}
    public int getLineNumber() {return lineNumber;}
    public int getTokenIndex() {return tokenIndex;}
    public int getStartPosition() {return startPosition;}
    public int getEndPosition() {return endPosition;}

    @Override
    public int compareTo(TextUnit textUnit) {
        if (textUnit == null)
            return 1;
        int docResult = this.getDocumentId().compareTo(textUnit.getDocumentId());

        if (docResult!=0) {
            return docResult;
        } else if (this.getLineNumber() < textUnit.getLineNumber()) {
            return -1;
        } else if (this.getLineNumber() > textUnit.getLineNumber()) {
            return 1;
        } else if (this.getTokenIndex() < textUnit.getTokenIndex()) {
            return -1;
        } else if (this.getTokenIndex() > textUnit.getTokenIndex()) {
            return 1;
        } else {
            return getToken().compareTo(textUnit.getToken());
        }

    }

    @Override
    public String toString() {
        return "[ TextUnit: \""+getToken()+"\" DocId: "+documentId+" LineNum: "+lineNumber+" TokenIndex: "+ tokenIndex +" StartPos: "+startPosition+" EndPos: "+endPosition+" ]";
    }

    @Override
    public boolean equals(Object other) {
        if (other==null)
            return false;
        if (!(other instanceof TextUnit))
            return false;
        TextUnit otherText = (TextUnit) other;
        return getToken().equals(otherText.getToken())
                && getDocumentId().equals(otherText.getDocumentId())
                && getLineNumber() == otherText.getLineNumber()
                && getTokenIndex() == otherText.getTokenIndex()
                && getStartPosition() == otherText.getStartPosition()
                && getEndPosition() == otherText.getEndPosition();

    }

    @Override
    public int hashCode() {
        return (3359*getToken().hashCode())^(3361*getDocumentId().hashCode())^(3371*getLineNumber())^(3373* getTokenIndex())^(3389*getStartPosition())^(3391*getEndPosition());
    }

}
