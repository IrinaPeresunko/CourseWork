package ua.nure.peresunko.main;

import ua.nure.peresunko.UI.Authentication;
import ua.nure.peresunko.containerOfUsers.ContainerOfUsers;
import ua.nure.peresunko.user.User;
import ua.nure.peresunko.user.UsersListThatContainsInTheSchoolBase;

public class Demo {
	
	private static ContainerOfUsers users = ContainerOfUsers.getInstance();
	
	private static void initialization() {
		User[] usersList = UsersListThatContainsInTheSchoolBase.getPupilsList();
		
		for(int i=0; i < usersList.length;i++){
			users.add(usersList[i]);
		}
		
		usersList = UsersListThatContainsInTheSchoolBase.getTeachersList();
		
		for(int i=0; i < usersList.length;i++){
			//System.out.println(usersList[i].getClass().getSimpleName());
			users.add(usersList[i]);
		}
		//users.print();
	}
	
	public static void main(String[] args) {
		initialization();
		new Authentication();
		
		users.print();
	}
	
	public static ContainerOfUsers getUsersList() {
		return users;
	}
}
