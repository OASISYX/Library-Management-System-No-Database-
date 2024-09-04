package com.book.dao;

import com.book.util.CommonUtil;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

public class BookDAO {
	// Column names for the book data
	public static final Object[] columnNames = { "Book Number", "Book Name", "Categories", "Year of publication", "Prices", "Status" };

	// Book data stored in a list of object arrays
	public static final List<Object[]> data = new ArrayList<>();

	static {
		// Adding sample book data to the list
		data.add(new Object[] { "001", "The Great Gatsby", "novels", "2000", "9.99", "on-site" });
		data.add(new Object[] { "002", "To Kill a Mockingbird", "novels", "2001", "10.99", "on-site" });
		data.add(new Object[] { "003", "1984", "novels", "2002", "11.99", "on-site" });
		data.add(new Object[] { "004", "Pride and Prejudice", "novels", "2003", "12.99", "on-site" });
		data.add(new Object[] { "005", "The Catcher in the Rye", "novels", "2004", "13.99", "on-site" });
		data.add(new Object[] { "006", "To the Lighthouse", "novels", "2005", "14.99", "on-site" });
		data.add(new Object[] { "007", "Brave New World", "novels", "2006", "15.99", "on-site" });
		data.add(new Object[] { "008", "Moby-Dick", "novels", "2007", "16.99", "on-site" });
		data.add(new Object[] { "009", "The Odyssey", "novels", "2008", "17.99", "on-site" });
		data.add(new Object[] { "010", "Frankenstein", "novels", "2009", "18.99", "on-site" });

		data.add(new Object[] { "011", "Steve Jobs", "biographies", "2010", "14.99", "on-site" });
		data.add(new Object[] { "012", "The Diary of a Young Girl", "biographies", "2011", "15.99", "on-site" });
		data.add(new Object[] { "013", "Long Walk to Freedom", "biographies", "2012", "16.99", "on-site" });
		data.add(new Object[] { "014", "The Autobiography of Malcolm X", "biographies", "2013", "17.99", "on-site" });

		data.add(new Object[] { "015", "Sapiens: A Brief History of Humankind", "science", "2014", "18.99", "on-site" });
		data.add(new Object[] { "016", "The Gene: An Intimate History", "science", "2015", "19.99", "on-site" });
		data.add(new Object[] { "017", "The Emperor of All Maladies", "science", "2016", "20.99", "on-site" });

		data.add(new Object[] { "018", "oiadjoad", "history", "2016", "20.99", "on-site" });
		data.add(new Object[] { "019", "ljadlsadqe", "history", "2016", "20.99", "on-site" });
		data.add(new Object[] { "020", "dasd asdca", "history", "2016", "20.99", "on-site" });
		data.add(new Object[] { "021", " dadavdadv ", "history", "2016", "20.99", "on-site" });
		data.add(new Object[] { "022", "areavacsd", "history", "2016", "20.99", "on-site" });
		data.add(new Object[] { "024", "areavacsd", "history", "2016", "20.99", "on-site" });
		data.add(new Object[] { "025", "areavacsd", "history", "2016", "20.99", "on-site" });
		data.add(new Object[] { "026", "areavacsd", "history", "2016", "20.99", "on-site" });
	}

	// Convert a list of object arrays to a 2D object array
	public static Object[][] toList(List<Object[]> list) {
		Object[][] result = new Object[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}

	// Find a book by its ID
	public static Object[] findById(String id) {
		for (Object[] d : data) {
			if (d[0].equals(id)) {
				return d;
			}
		}
		return new Object[columnNames.length];
	}

	// Add a new book to the data
	public static void add(Object[] obj) {
		data.add(obj);
	}

	// Borrow a book by changing its status to "out-site"
	public static void borrowbook(String id) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i)[0].equals(id)) {
				if (data.get(i)[5].equals("on-site")) {
					data.get(i)[5] = "out-site";
				} else {
					JOptionPane.showMessageDialog(null, "The book has been loaned", "System Prompt",
							JOptionPane.WARNING_MESSAGE);
				}
				break;
			}
		}
	}

	// Return a borrowed book by changing its status to "on-site"
	public static void returnbook(String id) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i)[0].equals(id)) {
				if(data.get(i)[5].equals("out-site")) {
					data.get(i)[5] = "on-site";
				} else {
					JOptionPane.showMessageDialog(null, "The book has not been borrowed", "System Prompt",
							JOptionPane.WARNING_MESSAGE);
				}
				break;
			}
		}
	}

	// Search for books based on multiple criteria
	public static Object[][] searchMulti(int[] cols, String[] texts) {
		List<Object[]> result = new ArrayList<>();
		for (Object[] d : data) {
			boolean hit = true;
			for (int i = 0; i < cols.length; i++) {
				if (CommonUtil.isNotEmpty(texts[i])) {
					if (!d[cols[i]].toString().equals(texts[i])) {
						hit = false;
						break;
					}
				}
			}
			if (hit) {
				result.add(d);
			}
		}
		return CommonUtil.toArray(result);
	}
}