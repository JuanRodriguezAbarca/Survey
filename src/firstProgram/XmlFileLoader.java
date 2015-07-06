package firstProgram;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlFileLoader {
	public Document loadXML(String file) throws ParserConfigurationException,
			SAXException, IOException {
		
		Document doc;
		try(InputStream in = getClass().getResourceAsStream(file)) {
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(in);
		}
		
		return doc;
	}
}
