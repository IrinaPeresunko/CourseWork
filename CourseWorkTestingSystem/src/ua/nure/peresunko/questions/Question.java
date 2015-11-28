package ua.nure.peresunko.questions;

import ua.nure.peresunko.answers.Answer;

public class Question {
	
	private int id;
	private String question;
	private Answer answer;
	private double numberOfPoints;
	
	public Question() {
		this.id = 0;
		this.question = "not specified";
		this.answer = null;
	}
	
	public Question(String question) {
		this.question = question;
	}
	
	public Question(int id, String question, Answer answer) {
		this.id = id;
		this.question = question;
		this.answer = answer;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("Question [id=").append(id).append(", question=")
				.append(question).append(", answer=").append(answer.toString())
				.append("]");
		return buffer.toString();
	}

	public double getNumberOfPoints() {
		return numberOfPoints;
	}

	public void setNumberOfPoints(double numberOfPoints) {
		this.numberOfPoints = numberOfPoints;
	}
	
	
	
}

