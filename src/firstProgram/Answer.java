package firstProgram;

public class Answer {
	private String answer;
	private boolean correct;
	private String id;
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public Answer(String answer, boolean isCorrect, String id){
		this.answer=answer;
		this.correct=isCorrect;
		this.id = id;
	}

	@Override
	public String toString() {
		return answer;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public boolean isCorrect() {
		return correct;
	}

	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	
}
