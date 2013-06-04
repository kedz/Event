package edu.columbia.cs.event;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: chris
 * Date: 6/3/13
 * Time: 5:42 PM
 * Empty constructor reads event.properties resource and holds configuration info, e.g. important directory locations.
 */
public class EventConfiguration {

    private File rawXmlDirectory;
    private File processedXmlDirectory;
    private boolean intern;

    private static EventConfiguration EVENT_CONFIGURATION;

    public static EventConfiguration getInstance() {
        if(EVENT_CONFIGURATION == null)
            EVENT_CONFIGURATION = new EventConfiguration();
        return EVENT_CONFIGURATION;
    }


    private EventConfiguration() {

        Properties prop = new Properties();
        System.out.println("[ LOADING CONFIGURATION ]");

        try {
            prop.load(EventConfiguration.class.getClassLoader().getResourceAsStream("event.properties"));

            rawXmlDirectory = new File(prop.getProperty("raw.xml.directory"));
            System.out.println("[ RAW XML DIRECTORY = "+rawXmlDirectory+" ]");
            processedXmlDirectory = new File(prop.getProperty("processed.xml.directory"));
            System.out.println("[ PROCESSED XML DIRECTORY = "+processedXmlDirectory+" ]");
            intern = Boolean.getBoolean(prop.getProperty("use.string.intern"));


        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    public File getRawXmlDirectory() {return rawXmlDirectory;}
    public File getProcessedXmlDirectory() {return processedXmlDirectory;}
    public boolean getUseStringIntern() {return intern;}


}
