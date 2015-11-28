package ua.nure.peresunko.user;

public class Pupil extends User {

	private int markByMath;
	private int markByLanguage;
	
	public Pupil() {
		super("not specified", "not specified", "not specified", "not specified");
	}
	
	public Pupil(String name, String lastName, String login, String password) {
		super(name, lastName, login, password);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString()).append(" [mark by math = ").append(markByMath)
				.append(", mark by language = ").append(markByLanguage).append("]");
		return builder.toString();
	}

	public int getMarkByMath() {
		return markByMath;
	}

	public void setMarkByMath(int markByMath) {
		this.markByMath = markByMath;
	}

	public int getMarkByLanguage() {
		return markByLanguage;
	}

	public void setMarkByLanguage(int markByLanguage) {
		this.markByLanguage = markByLanguage;
	}

}
