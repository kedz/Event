package edu.columbia.cs.event.nlp;

import edu.columbia.cs.event.RawArticle;
import edu.columbia.cs.event.text.RawLine;
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

        for(RawLine rawLine : unprocessedArticle.getRawLines()) {

            Annotation lineAnnotation = new Annotation(rawLine.getLineText());
            pipeline.annotate(lineAnnotation);

            List<CoreMap> sentences = lineAnnotation.get(CoreAnnotations.SentencesAnnotation.class);

            for(CoreMap sentence: sentences) {
                for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                    // this is the text of the token
                    String word = token.word();
                    // this is the POS tag of the token
                    String pos = token.tag();
                    // this is the NER label of the token
                    String ne = token.ner();
                    // this is the lemma of the token
                    String lemma = token.lemma();
                    System.out.println(rawLine.getDocumentId()+":"+rawLine.getLineNumber()+":"+token.sentIndex()+":"+word+":"+pos+":"+ne+":"+lemma+":"+token.beginPosition()+":"+token.endPosition());



                }

            }
        }


    }

}
