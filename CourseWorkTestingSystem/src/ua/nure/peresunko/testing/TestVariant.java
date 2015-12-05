package ua.nure.peresunko.testing;

import ua.nure.peresunko.answers.Answer;
import ua.nure.peresunko.questions.Question;

public class TestVariant {
	
	private int id;
	private String theme;
	private Question[] questions;
	private Answer[] answers;
	private int countOfQuestions;
	private double maxCountPointsInTheTest = 0;
	
	public TestVariant() {
		this.id = 0;
		this.theme = "not specified";
		this.questions = null;
		this.answers = null;
	}
	
	public TestVariant(int id, String theme, Question[] questions, Answer[] answers) {
		this.id = id;
		this.theme = theme;
		this.questions = questions;
		this.answers = answers;
	}
	
	public int getCountOfQuestions() {
		return countOfQuestions;
	}

	public void setCountOfQuestions(int countOfQuestions) {
		this.countOfQuestions = countOfQuestions;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTheme() {
		return theme;
	}
	public void setTheme(String theme) {
		this.theme = theme;
	}
	public Question[] getQuestions() {
		return questions;
	}
	public void setQuestions(Question[] questions) {
		this.questions = questions;
	}
	public Answer[] getAnswers() {
		return answers;
	}
	public void setAnswers(Answer[] answers) {
		this.answers = answers;
	}

	public double getMaxCountPointsInTheTest() {
		return maxCountPointsInTheTest;
	}

	public void setMaxCountPointsInTheTest(double maxCountPointsInTheTest) {
		this.maxCountPointsInTheTest = maxCountPointsInTheTest;
	}
	
	public double calculateMaxCountOfPointsInTheTest() {
		for(int i = 0; i < questions.length; i++) {
			this.maxCountPointsInTheTest += questions[i].getNumberOfPoints();
		}
		return this.maxCountPointsInTheTest;
	}
}
