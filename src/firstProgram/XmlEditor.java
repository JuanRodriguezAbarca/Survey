package firstProgram;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XmlEditor {

	XmlFileLoader loader = new XmlFileLoader();

	public void xmlEditor(List<Answer> answers, String fileName)
			throws ParserConfigurationException, SAXException, IOException,
			TransformerException {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		Document doc = docBuilder.newDocument();

		Element rootElement = doc.createElement("answers");
		doc.appendChild(rootElement);

		for (Answer answer : answers) {

			
			Element answerTag = doc.createElement("answer");
			rootElement.appendChild(answerTag);
			Attr atributeIsCorrect = doc.createAttribute("isCorrect");
			Attr attributeID = doc.createAttribute("id");
			answerTag.setAttributeNode(atributeIsCorrect);
			answerTag.setAttributeNode(attributeID);

			atributeIsCorrect.setValue(String.valueOf(answer.isCorrect()));
			// answerTag.setAttributeNode(answer.setCorrect(atributeIsCorrect.getSpecified()));
			answerTag.setTextContent(answer.getAnswer());
			attributeID.setValue(String.valueOf(answer.getId()));

			// atributeIsCorrect.setValue(String.valueOf(answer.isCorrect()));
			// answerTag.setTextContent(answer.getAnswer());
			// attributeID.setNodeValue(answer.getId());

		}

		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
//		doc.setXmlStandalone(true);
		DOMSource source = new DOMSource(doc);

		StreamResult result = new StreamResult(new File("src\\firstProgram\\"
				+ fileName + ".xml"));
		
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount","4");
		
		transformer.transform(source, result);
		if(fileName!="temp"){
//			System.out.println("file saved!");
			JOptionPane.showMessageDialog(null,"file saved!");

		}
//		fos.flush();
//		fos.close();

//		transformer.reset();
		
	}

}
