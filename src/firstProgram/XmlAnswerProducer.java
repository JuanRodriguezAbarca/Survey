package firstProgram;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlAnswerProducer  {

	XmlEditor editor = new XmlEditor();
	Document docEdited;
	private String tempXml = "temp";
	private Scanner scan = new Scanner(System.in);

	public void xmlRestltsProducer(List<Answer> answers) throws TransformerException,
			ParserConfigurationException, SAXException, IOException, InterruptedException {

		System.out.println("Give me your name: ");
		String theName = scan.nextLine();

		editor.xmlEditor(answers, theName);
		editor.xmlEditor(answers, tempXml);

	}

}
