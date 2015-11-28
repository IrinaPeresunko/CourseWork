package ua.nure.peresunko.questions;

import ua.nure.peresunko.answers.Answer;

public class ChoiceQuestion extends Question {
	
	public ChoiceQuestion() {
		super();
		super.setNumberOfPoints(0);
	}
	
	public ChoiceQuestion(String question) {
		super(question);
		super.setNumberOfPoints(2);
	}
	
	public ChoiceQuestion(int id, String question, Answer answer, String[] variants) {
		super(id, question, answer);
		super.setNumberOfPoints(2);
	}
	
}
