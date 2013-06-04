package edu.columbia.cs.event;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/3/13
 * Time: 5:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class RawArticleFactory {

    private static DocumentBuilder documentBuilder;
    static {
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        } catch (ParserConfigurationException pce) {
            System.out.println("XML Parser config failed!");
            pce.printStackTrace();
            System.exit(-1);
        }
    }

    public static RawArticle getArticleFromFile(File articleFile) {

        RawArticle rawArticleInstance = null;

        try {

            Document document = documentBuilder.parse(articleFile);
            DocumentMeta dm = new DocumentMeta(document, articleFile);
            rawArticleInstance = new RawArticle(document, dm);

        } catch (SAXException saxe) {
            System.out.println("Error parsing xml document: "+articleFile);
            saxe.printStackTrace();
            System.exit(-1);
        } catch (IOException ioe) {
            System.out.println("IO Error while parsing xml document: "+articleFile);
            ioe.printStackTrace();
            System.exit(-1);
        }

        return rawArticleInstance;

    }

    public static RawArticle getArticleFromId(String id) {

        EventConfiguration eventConfiguration = EventConfiguration.getInstance();
        File xmlFile = new File(eventConfiguration.getRawXmlDirectory()+File.separator+id+".xml");
        return getArticleFromFile(xmlFile);

    }

}

