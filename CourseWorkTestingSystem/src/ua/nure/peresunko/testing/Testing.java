package ua.nure.peresunko.testing;

public class Testing {
	
	private int id;
	private int userId;
	private TestVariant testVariant;
	private int timeForWorking;
	private int countOfCorrectAnswers;
	private double mark;
	
	public Testing() {
		this.id = 0;
		this.userId = 0;
		this.testVariant = null;
		this.timeForWorking = 0;
		this.countOfCorrectAnswers = 0;
		this.mark = 0;
	}
	
	public Testing(int id, int userId, TestVariant testVariant, int timeForWorking) {
		this.id = id;
		this.userId = userId;
		this.testVariant = testVariant;
		this.timeForWorking = timeForWorking;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getTimeForWorking() {
		return timeForWorking;
	}
	public void setTimeForWorking(int timeForWorking) {
		this.timeForWorking = timeForWorking;
	}
	public int getCountOfCorrectAnswers() {
		return countOfCorrectAnswers;
	}
	public void setCountOfCorrectAnswers(int countOfCorrectAnswers) {
		this.countOfCorrectAnswers = countOfCorrectAnswers;
	}
	public double getMark() {
		return mark;
	}
	public void setMark(double mark) {
		this.mark = mark;
	}
	public TestVariant getTest() {
		return testVariant;
	}
	public void setTest(TestVariant testVariant) {
		this.testVariant = testVariant;
	}
	
}
