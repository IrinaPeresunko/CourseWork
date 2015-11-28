package ua.nure.peresunko.answers;

public class AnswerForFillTheGapQuestion extends Answer{
	
	public AnswerForFillTheGapQuestion() {
		super();
	}
	
	public AnswerForFillTheGapQuestion(String correctAnswer) {
		super(correctAnswer);
	}
	
	public AnswerForFillTheGapQuestion(int id, String correctAnswer) {
		super(id, correctAnswer);
	}

	@Override
	public String[] getVariantsOfAnswers() {
		return new String[] {super.getCorrectAnswer()};
	}
}
