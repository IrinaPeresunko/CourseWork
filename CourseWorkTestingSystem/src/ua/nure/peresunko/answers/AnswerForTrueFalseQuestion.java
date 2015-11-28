package ua.nure.peresunko.answers;

public class AnswerForTrueFalseQuestion extends AnswerForChoiceQuestion {
	
	private final String[] variantsOfAnswer = {"Да", "Нет"};
	
	public AnswerForTrueFalseQuestion() {
		super();
	}
	
	public AnswerForTrueFalseQuestion(String correctAnswer) {
		super(correctAnswer);
	}
	
	public String[] getVariantsOfAnswer() {
		return variantsOfAnswer;
	}

	@Override
	public String[] getVariantsOfAnswers() {
		return this.variantsOfAnswer;
	}
	
	
}
