package com.book.dao;

import java.util.*;

public class BorrowedBookDAO {
	// Column names for the transactions data
	public static final Object[] columnNames = { "Book Number", "Book Name", "Categories", "Year of publication", "prices","status","Borrowing time", "Returning time" };
	// transactions data stored in a list of object arrays
	public static Map<String, List<Object[]>> BOOKS = new HashMap<>();


	static {
		modifier("yangxi",new Object[] { "001", "The Great Gatsby", "novels", "2000", "9.99", "on-site" },"2023-4-21","2023-5-20");
		modifier("yangxi",new Object[] { "003", "1984", "novels", "2002", "11.99", "on-site" },"2023-4-25","2023-5-5");
		modifier("yangxi",new Object[] { "005", "The Catcher in the Rye", "novels", "2004", "13.99", "on-site" },"2023-6-1","2023-6-30");
		modifier("emily",new Object[] { "003", "1984", "novels", "2002", "11.99", "on-site" },"2023-4-21","2023-5-20");
		modifier("emily",new Object[] { "005", "The Catcher in the Rye", "novels", "2004", "13.99", "on-site" },"2023-4-21","2023-6-20");
		modifier("emily",new Object[] { "001", "The Great Gatsby", "novels", "2000", "9.99", "on-site" },"2023-6-20","2023-8-30");
		modifier("emily",new Object[] { "008", "Moby-Dick", "novels", "2007", "16.99", "on-site"},"2023-2-20","2023-3-30");
		modifier("emily",new Object[] { "010", "Frankenstein", "novels", "2009", "18.99", "on-site"},"2023-5-20","2023-5-30");
		modifier("john",new Object[] { "010", "Frankenstein", "novels", "2009", "18.99", "on-site"},"2023-5-20","2023-5-30");
		modifier("john",new Object[] { "003", "1984", "novels", "2002", "11.99", "on-site"},"2023-5-20","2023-5-30");
		modifier("john",new Object[] { "014", "The Autobiography of Malcolm X", "biographies", "2013", "17.99", "on-site" },"2023-5-30","2023-6-30");
		modifier("john",new Object[] { "015", "Sapiens: A Brief History of Humankind", "science", "2014", "18.99", "on-site"},"2023-7-20","2023-7-30");
	}
	public static void modifier(String name,Object[] book,String borrowTime,String returnTime){
		BookDAO.borrowbook((String) book[0]);
		add(name,book,borrowTime);
		update(name,(String)book[0],returnTime);
		BookDAO.returnbook((String) book[0]);
	}
	// Convert a list of object arrays to a 2D object array
	public static Object[][] toList(String user) {
		List<Object[]> list = BOOKS.get(user);
		if (list != null) {
			Object[][] result = new Object[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				result[i] = list.get(i);
			}
			return result;
		}
		return new Object[0][columnNames.length];
	}


	//Used to update data when a book is borrowed
	public static int add(String user, Object[] book,String time) {
		List<Object[]> books = BOOKS.get(user);
		if (books == null) {
			books = new ArrayList();
			BOOKS.put(user, books);
		}
		Object[] updatedBook = new Object[8];
		System.arraycopy(book, 0, updatedBook, 0, book.length);
		updatedBook[6] = time;
		books.add(updatedBook);
		return 1;
	}
	//Find borrowed books by username
	public static Object[] findById(String searchKey) {
		for (List<Object[]> bookList : BOOKS.values()) {
			for (Object[] book : bookList) {
				if (book.length > 0 && book[0].equals(searchKey)) {
					return book;
				}
			}
		}
		return null;
	}
	//Update the return time and book status when returning books
	public static int update(String user, String bookNo,String time) {
		List<Object[]> books = BOOKS.get(user);
		if (books == null) {
			return 0;
		}
		for (Object[] book : books) {
			if (book[0].equals(bookNo)) {
				book[7] = time;
				return 1;
            }
		}
		return 0;
	}
	//Counting the number of loans from the same category
	public static Object[][] getBookCategoryCount(String user) {
		List<Object[]> books = BOOKS.get(user);
		if (books != null) {
			Map<String, Integer> categoryCountMap = new HashMap<>();
			for (Object[] book : books) {
				String category = (String) book[2];
				int count = categoryCountMap.getOrDefault(category, 0);
				categoryCountMap.put(category, count + 1);
			}
			Object[][] categoryCountArray = new Object[categoryCountMap.size()][2];
			int index = 0;
			for (Map.Entry<String, Integer> entry : categoryCountMap.entrySet()) {
				String category = entry.getKey();
				int count = entry.getValue();
				categoryCountArray[index][0] = category;
				categoryCountArray[index][1] = count;
				index++;
			}
			return categoryCountArray;
		}
		return new Object[0][2];
	}
	//Counting the number of loans from the same student
	public static Map<String, Integer> getStudentBorrowCount() {
		Map<String, Integer> borrowCountMap = new HashMap<>();

		for (String user : BOOKS.keySet()) {
			List<Object[]> borrowedBooks = BOOKS.get(user);
			int count = borrowedBooks.size();
			borrowCountMap.put(user, count);
		}

		return borrowCountMap;
	}
}
