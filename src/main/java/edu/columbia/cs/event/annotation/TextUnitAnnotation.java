package edu.columbia.cs.event.annotation;

import edu.columbia.cs.event.text.TextUnit;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/4/13
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextUnitAnnotation implements Comparable<TextUnitAnnotation> {

    private final String lemma;
    private final String pos;
    private final String ne;
    private final TextUnit textUnit;

    public TextUnitAnnotation(TextUnit textUnit, String lemma, String pos, String ne) {
        this(textUnit,lemma,pos,ne,false);
    }

    public TextUnitAnnotation(TextUnit textUnit, String lemma, String pos, String ne, boolean intern) {

        if (textUnit == null)
            throw new IllegalArgumentException("Argument 'textUnit' cannot be null.");
        if (lemma == null)
            throw new IllegalArgumentException("Argument 'lemma' cannot be null.");
        if (pos == null)
            throw new IllegalArgumentException("Argument 'pos' cannot be null.");
        if (ne == null)
            throw new IllegalArgumentException("Argument 'ne' cannot be null.");

        if (intern) {
            this.lemma = lemma.intern();
            this.pos = pos.intern();
            this.ne = ne.intern();
        } else {
            this.lemma = lemma;
            this.pos = pos;
            this.ne = ne;
        }

        this.textUnit = textUnit;

    }

    public String getLemma() {return lemma;}
    public String getPos() {return pos;}
    public String getNe() {return ne;}
    public TextUnit getTextUnit() {return textUnit;}
    public String getToken() {return getTextUnit().getToken();}
    public String getDocumentId() {return getTextUnit().getDocumentId();}
    public int getLineNumber() {return getTextUnit().getLineNumber();}
    public int getTokenIndex() {return getTextUnit().getTokenIndex();}
    public int getStartPosition() {return getTextUnit().getStartPosition();}
    public int getEndPosition() {return getTextUnit().getEndPosition();}

    @Override
    public int compareTo(TextUnitAnnotation other) {
        if (other==null)
            return 1;

        int textUnitResult = this.getTextUnit().compareTo(other.getTextUnit());
        if (textUnitResult != 0)
            return textUnitResult;

        int lemmaResult = this.getLemma().compareTo(other.getLemma());
        if (lemmaResult != 0)
            return lemmaResult;

        int posResult = this.getPos().compareTo(other.getPos());
        if (posResult != 0)
            return posResult;

        return this.getNe().compareTo(other.getNe());

    }


    @Override
    public String toString() {
        return "[ TextUnitAnnotation: "+textUnit.toString()+" Lemma: "+lemma+" Pos: "+pos+" Ne: "+ne+" ]";
    }

    @Override
    public boolean equals(Object other) {
        if (other==null)
            return false;
        if (!(other instanceof TextUnitAnnotation))
            return false;
        TextUnitAnnotation otherAnnotation = (TextUnitAnnotation) other;

        return getLemma().equals(otherAnnotation.getLemma())
                && getPos().equals(otherAnnotation.getPos())
                && getNe().equals(otherAnnotation.getNe())
                && getTextUnit().equals(otherAnnotation.getTextUnit());

    }

    @Override
    public int hashCode() {
        return (6229*getLemma().hashCode())^(6247*getPos().hashCode())^(6257*getNe().hashCode())^(6263*getTextUnit().hashCode());
    }

}
