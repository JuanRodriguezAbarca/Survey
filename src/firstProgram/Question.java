package firstProgram;

import java.util.List;

public class Question {
	
	private String text;
	private List<Answer> answers;
	
	public Question(String text, List<Answer> answers){
		this.text=text;
		this.answers=answers;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	@Override
	public String toString() {
		return text;
	}
	
	public Answer getAnswerByID(int i){
		return getAnswers().get(i-1);
	}
	
//	public void shotTheQuestion(){
//		System.out.println(text);
//		displayAnswers();
//	}
//	
//	private void displayAnswers(){
//		for (Answer answer:answers){
//			System.out.println(answer);
//		}
//		
//	}
}
