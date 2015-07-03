package firstProgram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlQuestionsParser {

	public List<Question> parseQuestionsXML(Document doc)
			throws ParserConfigurationException, SAXException, IOException {

		NodeList questionsNode = doc.getElementsByTagName("question");
		List<Question> questionList = new ArrayList<Question>();

		for (int i = 0; i < questionsNode.getLength(); i++) {

			Element element = (Element) questionsNode.item(i);
			String text = element.getElementsByTagName("text").item(0)
					.getTextContent();
			List<Answer> answers = getAnswers(element);

			questionList.add(new Question(text, answers));
		}

		return questionList;
	}

	public List<Answer> parseAnswerResultsXML(Document docA)
			throws ParserConfigurationException, SAXException, IOException {
		
		NodeList answerNode = docA.getElementsByTagName("answer");
		
		List<Answer> answers = new ArrayList<Answer>();
		

		for (int i = 0; i< answerNode.getLength();i++){
			Element element = (Element) answerNode.item(i);
			String text = element.getTextContent();
			Boolean isCorrect = Boolean.parseBoolean(element.getAttributeNode("isCorrect").getValue());
			String id = element.getAttributeNode("id").getTextContent();
			Answer tempAnswer = new Answer(text,isCorrect,id);
			answers.add(tempAnswer);
//			answers.add( element);
		}
			return answers;
		
		}

	private List<Answer> getAnswers(Element element) {
		List<Answer> answers = new ArrayList<Answer>();
		NodeList answersList = element.getElementsByTagName("answer");
		for (int i = 0; i < answersList.getLength(); i++) {
			Element answerElement = (Element) answersList.item(i);
			String text = answerElement.getTextContent();
			boolean isCorrect = Boolean.parseBoolean(answerElement
					.getAttribute("isCorrect"));
			String id = answerElement.getAttribute("id");
			answers.add(new Answer(text, isCorrect, id));
		}
		return answers;
	}
}
