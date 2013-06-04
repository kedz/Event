package edu.columbia.cs.event;

import edu.columbia.cs.event.nlp.RawArticlePreProcessor;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/4/13
 * Time: 2:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class SplitClassifierLabeler {

    public static void main(String[] args) {


        RawArticle rawArticle = RawArticleFactory.getArticleFromId("1125550030");
        RawArticlePreProcessor preProcessor = new RawArticlePreProcessor();

        preProcessor.process(rawArticle);




    }


}
