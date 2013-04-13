package org.francho.sample.eltiempo.aemet;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * 
 * @author francho
 * 
 */
public class AemetRss {
	public ArrayList<AemetDayInfo> getLogronoPrediction() {
		return getPrediction("http://www.aemet.es/xml/municipios/localidad_26089.xml");
	}
	
	private ArrayList<AemetDayInfo> getPrediction(String aemetFeed) {
		ArrayList<AemetDayInfo> prediction = new ArrayList<AemetDayInfo>();
		try {
			URL url = new URL(aemetFeed);

			/* Create a new ContentHandler and apply it to the XML-Reader */
			AemetRssHandler aemetHandler = new AemetRssHandler();
			
			/* Get a SAXParser from the SAXPArserFactory. */
			SAXParserFactory saxFactory = SAXParserFactory.newInstance();
			SAXParser saxParse = saxFactory.newSAXParser();
			
			/* Get the XMLReader of the SAXParser we created. */
			XMLReader xmlReader = saxParse.getXMLReader();
			xmlReader.setContentHandler(aemetHandler);
			
			/* Parse the xml-data from our URL. */
			InputSource inputStream = new InputSource(url.openStream());
			inputStream.setEncoding("ISO-8859-1");
			xmlReader.parse(inputStream);

			/* Parsing has finished. */
			prediction = aemetHandler.getPrediction();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return prediction;
	}
}
