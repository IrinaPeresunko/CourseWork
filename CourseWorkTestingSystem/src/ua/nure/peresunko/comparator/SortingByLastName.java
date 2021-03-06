package ua.nure.peresunko.comparator;

import java.util.Comparator;

import ua.nure.peresunko.user.User;

public class SortingByLastName implements Comparator<User>{
	/*
	 * method to sort element by last name
	 */
	public int compare(User user1, User user2) {
		return user1.getLastName().compareTo(user2.getLastName()); 
	}
}
