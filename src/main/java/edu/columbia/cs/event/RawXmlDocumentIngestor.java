package edu.columbia.cs.event;

import edu.columbia.cs.event.text.RawLine;
import org.apache.commons.cli.*;

import java.io.File;
import java.io.FilenameFilter;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/3/13
 * Time: 4:56 PM
 * Reads in unprocessed XML documents, cleans them up, and writes them to file.
 */
public class RawXmlDocumentIngestor {


    public static void main(String[] args) {

        EventConfiguration eventConfig = EventConfiguration.getInstance();

        File[] rawFiles = eventConfig.getRawXmlDirectory().listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".xml");
            }
        });


        RawArticle rawArticle = RawArticleFactory.getArticleFromId("1125550030");

        //System.out.println(rawArticle.getContentString());


        for(RawLine line : rawArticle.getRawLines()) {
            System.out.println(line);
        }

    }



}
