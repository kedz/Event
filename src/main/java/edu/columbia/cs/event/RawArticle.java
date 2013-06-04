package edu.columbia.cs.event;

import edu.columbia.cs.event.text.RawLine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 3/8/13
 * Time: 8:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class RawArticle {

    private DocumentMeta documentMeta;
    private Document document;

    private String instanceId;
    private String contentString;
    private List<RawLine> rawLines;

    public RawArticle(Document document, DocumentMeta documentMeta) {
        this.documentMeta = documentMeta;
        this.document = document;
        instanceId = document.getElementsByTagName("article").item(0).getAttributes().getNamedItem("id").getNodeValue();
    }

    public String getContentString() {

        if (contentString==null) {
            StringBuilder buffer = new StringBuilder();

            NodeList rawElements = document.getElementsByTagName("raw");
            for(int i = 0; i < rawElements.getLength(); i++) {

                Element rawElement = (Element) rawElements.item(i);
                if (i != 0)
                    buffer.append("\n\n");
                buffer.append(rawElement.getTextContent());

            }

            contentString = buffer.toString();
            buffer = null;
        }

        return contentString;

    }

    public List<RawLine> getRawLines() {
        if (rawLines == null) {
            boolean intern = EventConfiguration.getInstance().getUseStringIntern();
            rawLines = new LinkedList<RawLine>();
            int lineNum = 1;
            String[] lines = getContentString().split("\n");
            for(String line : lines) {
                if (!line.trim().isEmpty())
                    rawLines.add(new RawLine(line.trim(),getDocumentId(),lineNum++,intern));
            }
        }

        return rawLines;
    }


    public Document getDocument() { return document; }
    public String getInstanceId() { return instanceId; }
    public String getDocumentId() { return getDocumentMeta().getFileId(); }
    public DocumentMeta getDocumentMeta() { return documentMeta; }


    @Override
    public String toString() {

        return "[INST_ID: "+getInstanceId()+" DOC_ID: "+getDocumentId()+ " CONTENT: "+getContentString()+"]";
    }

}
