package edu.columbia.cs.event.nlp;

import edu.columbia.cs.event.EventConfiguration;
import edu.columbia.cs.event.RawArticle;
import edu.columbia.cs.event.annotation.TextUnitAnnotation;
import edu.columbia.cs.event.text.RawLine;
import edu.columbia.cs.event.text.TextUnit;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.List;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/4/13
 * Time: 2:41 AM
 * To change this template use File | Settings | File Templates.
 */
public class RawArticlePreProcessor {

    private StanfordCoreNLP pipeline;

    public RawArticlePreProcessor() {

        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos, lemma, ner");
        pipeline = new StanfordCoreNLP(props);
    }

    public void process(RawArticle unprocessedArticle) {

        boolean useIntern = EventConfiguration.getInstance().getUseStringIntern();



        for(RawLine rawLine : unprocessedArticle.getRawLines()) {

            Annotation lineAnnotation = new Annotation(rawLine.getLineText());
            pipeline.annotate(lineAnnotation);

            List<CoreMap> sentences = lineAnnotation.get(CoreAnnotations.SentencesAnnotation.class);

            for(CoreMap sentence: sentences) {

                int tokenIndex = 1;

                for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {

                    TextUnit textUnit = new TextUnit(token.originalText(),rawLine.getDocumentId(),rawLine.getLineNumber(), tokenIndex, token.beginPosition(), token.endPosition(), useIntern);
                    TextUnitAnnotation textUnitAnnotation = new TextUnitAnnotation(textUnit,token.lemma(),token.tag(),token.ner(), useIntern);
                    tokenIndex++;
                    System.out.println(textUnitAnnotation);

                }

            }
        }


    }

}
