package ua.nure.peresunko.answers;

public class AnswerForChoiceQuestion extends Answer {
	
	private String[] variantsOfAnswer;
	
	public AnswerForChoiceQuestion() {
		super();
		this.variantsOfAnswer = null;
	}
	
	public AnswerForChoiceQuestion(String[] variantsOfAnswers, String correctAnswer) {
		super(correctAnswer);
		this.variantsOfAnswer = variantsOfAnswers;
	}
	
	public AnswerForChoiceQuestion(int id, String[] variantsOfAnswers, String correctAnswer) {
		super(id, correctAnswer);
		this.variantsOfAnswer = variantsOfAnswers;
	}

	public AnswerForChoiceQuestion(String correctAnswer) {
		super(correctAnswer);
	}
	
	public void setVariantsOfAnswer(String[] variantsOfAnswer) {
		this.variantsOfAnswer = variantsOfAnswer;
	}

	@Override
	public String[] getVariantsOfAnswers() {
		return this.variantsOfAnswer;
	}	
}
