package ua.nure.peresunko.questions;

import ua.nure.peresunko.answers.Answer;

public class QuestionTrueFalse extends ChoiceQuestion{
	
	public QuestionTrueFalse() {
		super();
		super.setNumberOfPoints(0);
	}
	
	public QuestionTrueFalse(String question) {
		super(question);
		super.setNumberOfPoints(1);
	}
	
	public QuestionTrueFalse(int id, String question, Answer answer, String[] variants) {
		super(id, question, answer, variants);
		super.setNumberOfPoints(1);
	}

}
