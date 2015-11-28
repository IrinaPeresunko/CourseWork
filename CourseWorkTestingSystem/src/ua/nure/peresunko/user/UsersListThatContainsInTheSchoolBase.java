package ua.nure.peresunko.user;

public class UsersListThatContainsInTheSchoolBase {
	
	private static int countOfPupilsInTheClass = 4;
	private static Pupil[] pupilsList = new Pupil[countOfPupilsInTheClass];
	
	private static int countOfTeachersInTheSchool = 2;
	private static Teacher[] teachersList = new Teacher[countOfTeachersInTheSchool];
	
	private static void initializationPupilsList() {
		pupilsList[0] = new Pupil("Ирина","Пересунько","iperesunko","peresunko95");
		pupilsList[1] = new Pupil("Екатерина","Удовиченко","kudovichenko","udovichenko96");
		pupilsList[2] = new Pupil("Илья","Каплаушенко","ikaplaushenko","kaplaushenko95");
		pupilsList[3] = new Pupil("Галина","Ключко","gkluchko","kluchko96");
	}
	
	public static User[] getPupilsList() {
		initializationPupilsList();
		return pupilsList;
	}
	
	private static void initializationTeachersList() {
		teachersList[0] = new Teacher("Ирина","Кучеренко","ikucherenko","kucherenko75");
		teachersList[1] = new Teacher("Ольга","Бандуровская","obandurovskaya","obandurovskaya78");
	}
	
	public static User[] getTeachersList() {
		initializationTeachersList();
		return teachersList;
	}
	
	public static boolean isTeacher(User user) {
		for(int i = 0; i < teachersList.length; i++) {
			if(teachersList[i].equals(user)) {
				return true;
			}
		}
		return false;
	}
	
}
