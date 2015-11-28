package ua.nure.peresunko.answers;

public abstract class Answer {
	
	private int id;
	private String correctAnswer;
	
	public Answer() {
		this.id = 0;
		this.correctAnswer = "not specified";
	}
	
	public Answer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	public Answer(int id, String correctAnswer) {
		this.id = id;
		this.correctAnswer = correctAnswer;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	
	abstract public String[] getVariantsOfAnswers();
	
	public boolean isCorrectAnswer(String inputAnswer) {
		return this.correctAnswer.equals(inputAnswer);
	}

}
