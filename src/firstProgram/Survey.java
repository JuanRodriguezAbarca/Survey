package firstProgram;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import javax.swing.JOptionPane;
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
			SAXException, IOException, TransformerException,
			InterruptedException {

		Document doc = loader.loadXML("QandA.xml");
		List<Question> questions = parser.parseQuestionsXML(doc);

		// questions.stream().forEach(q -> {
		// System.out.println(q);
		// q.getAnswers().forEach(System.out::println);
		// });

		String[] questionComposer= new String[questions.get(0).getAnswers().size()+2];

		
		for (Question question : questions) {
//			System.out.println(question);
			int i = 0;
			this.question = question;
			questionComposer[i] = question.getText(); 

			
			List<Answer> answers = question.getAnswers();
			for (Answer answer : answers) {
				i++;
				questionComposer[i]="\t"+answer.getAnswer();
//				System.out.println(answer.getAnswer());
			}
			Answer answer = getAnswerFromuser(answers, questionComposer);

			// answersSelected.add(new
			// Answer(answer.getAnswer(),answer.isCorrect(), answer.getId()));
			answersSelected.add(answer);

			if (answer.isCorrect()) {
				correctAnswers++;
			} else {
				wrongAnswers++;
			}

		}

		
		String[] finalScreen = new String[questions.size()+4];
		producer.xmlRestltsProducer(answersSelected);
//		System.out.println("Generating results...\n");
		
		JOptionPane.showMessageDialog(null,"Wait for the results...");

		
		Thread.sleep(3000);
		
		JOptionPane.showMessageDialog(null,"Wait for the results...");

		Document docA = loader.loadXML("temp.xml");
		List<Answer> answFromXLM = parser.parseAnswerResultsXML(docA);
		
		finalScreen[0]="Final Results:";
		
//		System.out.println("\nFinal Results: ");
		int i =1;
		for (Answer answerEnd : answFromXLM) {
			
			finalScreen[i]= "Answer: " + answerEnd.getAnswer()
					+ "\n\tResult: "
					+ String.valueOf(answerEnd.isCorrect() + "\n");
			i++;
							
							
//			System.out.println("Answer: " + answerEnd.getAnswer()
//					+ "\n\tResult: "
//					+ String.valueOf(answerEnd.isCorrect() + "\n"));

		}

		finalScore(i, finalScreen);

	}

	private Answer getAnswerFromuser(List<Answer> answers, String[] questionComposer)
			throws ParserConfigurationException, SAXException, IOException,
			TransformerException, InputMismatchException {
//		System.out.println("Choose an answer: ");
		questionComposer[6]="Choose an answer: ";

		StringBuilder builder = new StringBuilder();
		for(String s : questionComposer){
			builder.append(s);
			builder.append(System.getProperty("line.separator"));
		}
		String questionComposed = builder.toString();
		
		Integer answerByUser;
		while (true) {
			try {
//				answerByUser = Integer.parseInt(scan.nextLine());
//				answerByUser = scan.nextInt();
				answerByUser = Integer.parseInt(JOptionPane.showInputDialog(questionComposed));
				if ((answerByUser >= (answers.size() + 1) || answerByUser <= 0)) {
//					System.out
//							.println("Please provide an answer between 1 and 5");
					JOptionPane.showMessageDialog(null,"Please provide an answer between 1 and 5");
				} else {
					break;
				}
			} catch (NumberFormatException e) {
//			} catch (InputMismatchException e) {

				answerByUser = 99;
//				System.out.println("Answer must be an integer");
				JOptionPane.showMessageDialog(null,"Answer must be an integer");
//				scan.nextLine();
				continue;
			}
		}

		return answers.get(answerByUser - 1);
	}

	private void finalScore(int index, String[] finalResult) {
		String finalScore;
		if (correctAnswers > wrongAnswers) {
			finalScore = "pass";
		} else
			finalScore = "fail";
		finalResult[index] = "The final scoring was: " + finalScore;
		finalResult[index + 1] = "Correct Answered: " + correctAnswers;
		finalResult[index + 2] = "Wrong Answered: " + wrongAnswers;
		
//		System.out.println("The final scoring was: " + finalScore);
//		System.out.println("Correct Answered: " + correctAnswers);
//		System.out.println("Wrong Answered: " + wrongAnswers);
		
		StringBuilder builder = new StringBuilder();
		for(String s : finalResult){
			builder.append(s);
			builder.append(System.getProperty("line.separator"));
		}
		
		String result = builder.toString();
		
		JOptionPane.showMessageDialog(null,result);

	}

}
