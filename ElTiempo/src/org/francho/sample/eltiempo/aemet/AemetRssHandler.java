package org.francho.sample.eltiempo.aemet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * @author francho
 *
 */
public class AemetRssHandler extends DefaultHandler {
	
	private ArrayList<AemetDayInfo> prediction = new ArrayList<AemetDayInfo>();
	private AemetDayInfo currentDayInfo;
	
	
	private boolean in_prediccion = false;
	private boolean in_dia = false;
	private boolean in_temperatura = false;
	private boolean in_maxima = false;
	private boolean in_minima = false;
	
	
	private interface AemetXml {
		public static final String PREDICCION = "prediccion";
		public static final String DIA = "dia";
		public static final String FECHA = "fecha";
		public static final String TEMPERATURA = "temperatura";
		public static final String MAXIMA = "maxima";
		public static final String MINIMA = "minima";
	}

	/**
	 *  The date format used in the feed
	 */
	SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");


    public ArrayList<AemetDayInfo> getPrediction() {
		return prediction;
	}

	@Override
    public void startDocument() throws SAXException {
         // this.feedDataSet = new AemetRssDataSet();
    }

    @Override
    public void endDocument() throws SAXException {
       //  this.feedDataSet.sortNews();
    }
    
    /** Gets be called on opening tags like:
     * <tag>
     * Can provide attribute(s), when xml was like:
     * <tag attribute="attributeValue">*/
    @Override
    public void startElement(String namespaceURI, String localName,
                    String qName, Attributes atts) throws SAXException {
    	if(localName.equalsIgnoreCase(AemetXml.PREDICCION)) {
    		in_prediccion = true;
    		
    		prediction = new ArrayList<AemetDayInfo>();
    		
    	} else if(localName.equalsIgnoreCase(AemetXml.DIA)) {
    		in_dia = true;
    		currentDayInfo = new AemetDayInfo();
    		
			try {
				Date theDate = dateFormatter.parse(atts.getValue(AemetXml.FECHA));
				currentDayInfo.setDate(theDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
    		
    	} else if(localName.equalsIgnoreCase(AemetXml.TEMPERATURA)) {
    		in_temperatura = true;
    	} else if(localName.equalsIgnoreCase(AemetXml.MAXIMA)) {
    		in_maxima = true;
    	} else if(localName.equalsIgnoreCase(AemetXml.MINIMA)) {
    		in_minima = true;
    	}   	
    }
    
    /** Gets be called on closing tags like:
     * </tag> */
    @Override
    public void endElement(String namespaceURI, String localName, String qName)
                    throws SAXException {
    	
    	if(localName.equalsIgnoreCase(AemetXml.PREDICCION)) {
    		in_prediccion = false;
    	} else if(localName.equalsIgnoreCase(AemetXml.DIA)) {
    		in_dia = false;
    		prediction.add(currentDayInfo);
    		currentDayInfo = null;
    	} else if(localName.equalsIgnoreCase(AemetXml.TEMPERATURA)) {
    		in_temperatura = false;
    	} else if(localName.equalsIgnoreCase(AemetXml.MAXIMA)) {
    		in_maxima = false;
    	} else if(localName.equalsIgnoreCase(AemetXml.MINIMA)) {
    		in_minima = false;
    	}
    	
    }
    
    /** Gets be called on the following structure:
     * <tag>characters</tag> */
    @Override
    public void characters(char ch[], int start, int length) {
            if(in_prediccion && in_dia && in_temperatura){
            	if(in_maxima) {
            		int temperature = Integer.parseInt(new String(ch, start, length));
            		currentDayInfo.setMaxTemp(temperature);
            	} else if(in_minima) {
            		int temperature = Integer.parseInt(new String(ch, start, length));
            		currentDayInfo.setMinTemp(temperature);
            	} 
            }
    }

}
