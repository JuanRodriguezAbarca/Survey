package firstProgram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Survey {

	XmlFileLoader loader = new XmlFileLoader();
	XmlQuestionsParser parser = new XmlQuestionsParser();
	XmlEditor editor = new XmlEditor();
	XmlAnswerProducer producer = new XmlAnswerProducer();
	
	int correctAnswers;
	int wrongAnswers;
	Question question;
	List<Answer> answersSelected = new ArrayList<Answer>();
	private Scanner scan = new Scanner(System.in);

	public void questionExecuter() throws ParserConfigurationException,
			SAXException, IOException, TransformerException, InterruptedException {
		
		
		Document doc = loader.loadXML("QandA.xml");
		List<Question> questions = parser.parseQuestionsXML(doc);
		
		
		// questions.stream().forEach(q -> {
		// System.out.println(q);
		// q.getAnswers().forEach(System.out::println);
		// });

		for (Question question : questions) {
			System.out.println(question);
			this.question = question;

			List<Answer> answers = question.getAnswers();
			for (Answer answer : answers) {
				System.out.println(answer.getAnswer());
			}
			Answer answer = getAnswerFromuser(answers);
			
//			answersSelected.add(new Answer(answer.getAnswer(),answer.isCorrect(), answer.getId()));
			answersSelected.add(answer);
			
			if(answer.isCorrect()){
				correctAnswers++;
			} else{
				wrongAnswers++;
			}

			

		}
		

		producer.xmlRestltsProducer(answersSelected);
		System.out.println("Generating results...\n");
		Thread.sleep(8000);
		Document docA = loader.loadXML("temp.xml");
		List<Answer> answFromXLM = parser.parseAnswerResultsXML(docA);
		System.out.println("\nFinal Results: ");
		for(Answer answerEnd:answFromXLM){
			System.out.println("Answer: "+answerEnd.getAnswer() +"\n\tResult: "+ String.valueOf(answerEnd.isCorrect()+"\n"));
			
		}
		
		finalScore();

	}

	private Answer getAnswerFromuser(List<Answer> answers) throws ParserConfigurationException, SAXException, IOException, TransformerException, InputMismatchException {
		System.out.println("Choose an answer: ");
		Integer answerByUser = 0;
		while (true) {
			try {
				answerByUser = scan.nextInt();
				if ((answerByUser >= (answers.size() + 1) || answerByUser <= 0)) {
					System.out
							.println("Please provide an answer between 1 and 5");
				} else {
					break;
				}
			} catch (InputMismatchException e) {
				answerByUser=0;
				System.out.println("Answer must be an integer");
				continue;
			}
		}
		
		
		return answers.get(answerByUser - 1);
	}

	private void finalScore() {
		String finalScore;
		if (correctAnswers > wrongAnswers) {
			finalScore = "pass";
		} else
			finalScore = "fail";
		System.out.println("The final scoring was: " + finalScore);
		System.out.println("Correct Answered: " + correctAnswers);
		System.out.println("Wrong Answered: " + wrongAnswers);

	}


}
