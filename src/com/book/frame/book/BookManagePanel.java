package com.book.frame.book;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.text.DateFormatter;

import com.book.dao.BookDAO;
import com.book.dao.BorrowedBookDAO;
import com.book.dao.BorrowerDAO;
import com.book.util.SystemConstants;



public class BookManagePanel extends JInternalFrame {

	public BookManagePanel(String bookNo) {
		super("Book-borrow", true, true, true, true);
		this.setSize(SystemConstants.FRAME_WIDTH - 20, SystemConstants.FRAME_HEIGHT - 50);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		this.setContentPane(panel);
		Box boxBase = Box.createHorizontalBox();
		Box boxLeft = Box.createVerticalBox();
		boxLeft.add(new JLabel("Student ID"));
		boxLeft.add(Box.createVerticalStrut(8));
		boxLeft.add(new JLabel("Book ID"));
		boxLeft.add(Box.createVerticalStrut(8));
		Box boxRight = Box.createVerticalBox();
		JTextField field1 = new JTextField(10);
		boxRight.add(field1);
		boxRight.add(Box.createVerticalStrut(5));
		JTextField field2 = new JTextField(10);
		boxRight.add(field2);
		field2.setText(bookNo);
		field2.setEditable(false);
		boxRight.add(Box.createVerticalStrut(5));

		this.setContentPane(panel);

		boxLeft.add(new JLabel("Borrowing time"));
		boxLeft.add(Box.createVerticalStrut(8));

		SpinnerDateModel dateModel = new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH);
		JSpinner spinner = new JSpinner(dateModel);

		JFormattedTextField editor = ((JSpinner.DateEditor) spinner.getEditor()).getTextField();
		DateFormatter formatter = (DateFormatter) editor.getFormatter();
		formatter.setFormat(new java.text.SimpleDateFormat("yyyy-MM-dd"));
		boxRight.add(spinner);

		JButton borrowBook = new JButton("Borrow");
		borrowBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Retrieve user details by student ID
				Object[] book = BookDAO.findById(bookNo);
				if ("out-site".equals(book[5])) {
					JOptionPane.showMessageDialog(borrowBook.getParent(), "The book is out of storage", "System Prompt", JOptionPane.WARNING_MESSAGE);
					return;
				}
				// Retrieve user details by student ID
				Object[] user = BorrowerDAO.findById(field1.getText());
				if (user == null) {
					JOptionPane.showMessageDialog(borrowBook.getParent(), "User does not exist", "System Prompt", JOptionPane.WARNING_MESSAGE);
					return;
				}
				Date selectedDate = (Date) spinner.getValue();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(selectedDate);


				Object[] user0 = BorrowerDAO.findById(field1.getText());
				String userName = (String) user0[4];
				BorrowedBookDAO.add(userName, book, dateString);
				BookDAO.borrowbook(bookNo);
				Object[] book1 = BorrowedBookDAO.findById(bookNo);
				book1[5] = "out-site";

				BookAdminPanel.setContent(new BookTablePanel());
				JOptionPane.showMessageDialog(borrowBook.getParent(), "Successful borrowing", "System Prompt", JOptionPane.WARNING_MESSAGE);
			}
		});
		boxLeft.add(borrowBook);
		JButton returnBook = new JButton("Return");
		returnBook.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Date selectedDate = (Date) spinner.getValue();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(selectedDate);
				Object[] user = BorrowerDAO.findById(field1.getText());
				int result = BorrowedBookDAO.update((String) user[4], field2.getText(),dateString);
				if (user == null) {
					JOptionPane.showMessageDialog(borrowBook.getParent(), "User does not exist", "System Prompt", JOptionPane.WARNING_MESSAGE);
				} else if (result == 0) {
					JOptionPane.showMessageDialog(borrowBook.getParent(), "Users have not borrowed this book", "System Prompt", JOptionPane.WARNING_MESSAGE);
				} else{
					BookDAO.returnbook(bookNo);
					BorrowedBookDAO.update((String) user[4], field2.getText(),dateString);
					Object[] book1 = BorrowedBookDAO.findById(bookNo);
					book1[5] = "on-site";

					JOptionPane.showMessageDialog(borrowBook.getParent(), "Successful Return", "System Prompt", JOptionPane.WARNING_MESSAGE);
					BookAdminPanel.setContent(new BookTablePanel());
				}
			}
		});
		boxRight.add(returnBook);
		boxBase.add(boxLeft);
		boxBase.add(Box.createHorizontalStrut(8));
		boxBase.add(boxRight);
		panel.add(boxBase);

		this.setVisible(true);
	}
}
