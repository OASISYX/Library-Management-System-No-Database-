package com.book.dao;

import java.util.HashMap;

import java.util.Map;

import com.book.pojo.User;



public class UserDAO {
	//Stores information about System_Admins, Book_Admins and borrowers, respectively.
	public static Map<String, User> SYSTEM_ADMINS = new HashMap<>();
	public static Map<String, User> BOOK_ADMINS = new HashMap<>();
	public static Map<String, User> BORROWERS = new HashMap<>();

	static {

		User admin1 = new User("admin1", "123456");
		User admin2 = new User("admin2", "123456");
		SYSTEM_ADMINS.put(admin1.getUsername(), admin1);
		SYSTEM_ADMINS.put(admin2.getUsername(), admin2);


		User user1 = new User("user1", "000000");
		User user2 = new User("user2", "000000");
		BOOK_ADMINS.put(user1.getUsername(), user1);
		BOOK_ADMINS.put(user2.getUsername(), user2);


		User borrower1 = new User("yangxi","000000");
		User borrower2 = new User("emily","000000");
		User borrower3 = new User("john","970432");
		User borrower4 = new User("sophia","731053");
		User borrower5 = new User("emma","480500");
		User borrower6 = new User("james","248493");
		User borrower7 = new User("ava","317972");
		User borrower8 = new User("benjamin","508073");
		User borrower9 = new User("william","530706");

		BORROWERS.put(borrower1.getUsername(), borrower1);
		BORROWERS.put(borrower2.getUsername(), borrower2);
		BORROWERS.put(borrower3.getUsername(), borrower3);
		BORROWERS.put(borrower4.getUsername(), borrower4);
		BORROWERS.put(borrower5.getUsername(), borrower5);
		BORROWERS.put(borrower6.getUsername(), borrower6);
		BORROWERS.put(borrower7.getUsername(), borrower7);
		BORROWERS.put(borrower8.getUsername(), borrower8);
		BORROWERS.put(borrower9.getUsername(), borrower9);
	}
}
