package ua.nure.peresunko.user;

public class Teacher extends User {
	
	private static final boolean isTeacher = true;
	
	public Teacher() {
		super("not specified", "not specified", "not specified", "not specified");
	}
	
	public Teacher(String name, String lastName, String login, String password) {
		super(name, lastName, login, password);
	}

	public static boolean isTeacher() {
		return isTeacher;
	}

}
