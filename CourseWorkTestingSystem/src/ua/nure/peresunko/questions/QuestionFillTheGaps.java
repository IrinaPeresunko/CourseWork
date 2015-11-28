package ua.nure.peresunko.questions;

import ua.nure.peresunko.answers.Answer;

public class QuestionFillTheGaps extends Question{
	
	public QuestionFillTheGaps() {
		super();
		super.setNumberOfPoints(0);
	}
	
	public QuestionFillTheGaps(String question) {
		super(question);
		super.setNumberOfPoints(3);
	}
	
	public QuestionFillTheGaps(int id, String question, Answer answer) {
		super(id, question, answer);
		super.setNumberOfPoints(3);
	}
}
