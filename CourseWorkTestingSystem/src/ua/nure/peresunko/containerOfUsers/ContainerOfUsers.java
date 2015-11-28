package ua.nure.peresunko.containerOfUsers;

import java.util.ArrayList;
import java.util.Comparator;

import ua.nure.peresunko.user.Pupil;
import ua.nure.peresunko.user.User;
import ua.nure.peresunko.user.UsersListThatContainsInTheSchoolBase;

public class ContainerOfUsers {
	
	private static ContainerOfUsers uniqueInstance;
	private final ArrayList<User> containerOfUsers; 
	
	private ContainerOfUsers(){
		this.containerOfUsers = new ArrayList<User>();
	}
	
	/*
	 * method to return uniqueInstance of class 
	 */
	public static ContainerOfUsers getInstance() {
			if (uniqueInstance == null) {
				uniqueInstance = new ContainerOfUsers();
			}
			return uniqueInstance;
	}
	
	/*
	 * method for adding items to the container 
	 */
	public void add(User user){
		if(!containerOfUsers.contains(user)) {
			containerOfUsers.add(user);
		}
	}
	
	/*
	 * method to get the count of items in the container
	 */
	public int count(){
		return containerOfUsers.size();
	}
	
	/*
	 * method to get element by index
	 */
	public User getElementByIndex(int index){
		return containerOfUsers.get(index);
	}
	
	/*
	 * method to get element by index
	 */
	public int getIndexByUser(User user){
		return containerOfUsers.indexOf(user);
	}
	
	/*
	 * method to sort elements in the container. 
	 * If value of comparator is null - use standard method of sorting
	 * that contained in the basic class. Otherwise - used comparator
	 */
	public void sort(Comparator<User> comparator){
//		if(comparator == null){
//			Collections.sort(containerOfUsers);
//		}
//		else{
//			containerOfUsers.sort(comparator);
//		}
	}
	
	public boolean contains(User user) {
		if(containerOfUsers.contains(user)) {
			return true;
		}
		return false;
	}
	
	public boolean isTeacher(User user) {
		if(UsersListThatContainsInTheSchoolBase.isTeacher(user)) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 * method to print all items in the container
	 */
	public void print(){
		for(User user : containerOfUsers){
			System.out.println(user);
		}
	}
	
	/*
	 * method to clear all items in the container, used for tests
	 */
	public void clear(){
		containerOfUsers.clear();
	}
	
	public void setMarkToPupilByMath(Pupil pupil, int mark) {
		int index = getIndexByUser(pupil);
		((Pupil) containerOfUsers.get(index)).setMarkByMath(mark);
	}
	
	public void setMarkToPupilByLanguage(Pupil pupil, int mark) {
		int index = getIndexByUser(pupil);
		((Pupil) containerOfUsers.get(index)).setMarkByLanguage(mark);
	}
}
