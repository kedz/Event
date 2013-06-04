package edu.columbia.cs.event;

import org.joda.time.DateTime;
import org.w3c.dom.Document;

import java.io.File;


/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 3/8/13
 * Time: 6:07 PM
 * To change this template use File | Settings | File Templates.
 */
public class DocumentMeta {

    private String fileId;

    private String title;
    private String byline;
    private String publication;
    private String url;

    private File srcFile;

    private DateTime date;

    public DocumentMeta(Document doc, File xmlFile) {

        srcFile = xmlFile;
        fileId = doc.getElementsByTagName("article").item(0).getAttributes().getNamedItem("id").getNodeValue();

        title = doc.getElementsByTagName("title").item(0).getTextContent();
        byline = doc.getElementsByTagName("byline").item(0).getTextContent();
        publication = doc.getElementsByTagName("publication").item(0).getTextContent();
        url = doc.getElementsByTagName("article").item(0).getAttributes().getNamedItem("url").getNodeValue();

        int year = Integer.parseInt(doc.getElementsByTagName("date").item(0).getAttributes().getNamedItem("year").getNodeValue());
        int month = Integer.parseInt(doc.getElementsByTagName("date").item(0).getAttributes().getNamedItem("month").getNodeValue());
        int day = Integer.parseInt(doc.getElementsByTagName("date").item(0).getAttributes().getNamedItem("day").getNodeValue());

        date = new DateTime(year, month, day, 0, 0);

    }

    public String getFileId() { return fileId; }

    public void setTitle(String newTitle) { title = newTitle; }
    public String getTitle() { return title; }

    public void setByline(String newByline) { byline = newByline; }
    public String getByline() { return byline; }

    public void setPublication(String newPublication) { publication = newPublication; }
    public String getPublication() { return publication; }

    public void setUrl(String newUrl) { url = newUrl; }
    public String getUrl() { return url; }

    public File getSrcFile() { return srcFile; }

    public DateTime getDate() { return date; }

}