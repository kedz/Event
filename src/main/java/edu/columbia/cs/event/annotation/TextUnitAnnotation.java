package edu.columbia.cs.event.annotation;

import edu.columbia.cs.event.text.TextUnit;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/4/13
 * Time: 3:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class TextUnitAnnotation {

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


}
