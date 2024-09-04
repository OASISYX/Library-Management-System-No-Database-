package com.book.frame.book;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.book.dao.BookDAO;
import com.book.util.SystemConstants;


public class BookEditPanel extends JInternalFrame {

	public BookEditPanel() {
		super("Add Book", true, true, true, true);
		this.setSize(SystemConstants.FRAME_WIDTH - 20, SystemConstants.FRAME_HEIGHT - 50);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		this.setContentPane(panel);
		Box boxBase = Box.createHorizontalBox();
		Box boxLeft = Box.createVerticalBox();
		boxLeft.add(new JLabel("Book Number"));
		boxLeft.add(Box.createVerticalStrut(8));
		boxLeft.add(new JLabel("Book Name"));
		boxLeft.add(Box.createVerticalStrut(8));
		boxLeft.add(new JLabel("Categories"));
		boxLeft.add(Box.createVerticalStrut(8));
		boxLeft.add(new JLabel("Year of publication"));
		boxLeft.add(Box.createVerticalStrut(8));
		boxLeft.add(new JLabel("Prices"));
		boxLeft.add(Box.createVerticalStrut(8));

		Box boxRight = Box.createVerticalBox();
		JTextField field1 = new JTextField(10);
		boxRight.add(field1);
		boxRight.add(Box.createVerticalStrut(5));
		JTextField field2 = new JTextField(10);
		boxRight.add(field2);
		boxRight.add(Box.createVerticalStrut(5));
		JTextField field3 = new JTextField(10);
		boxRight.add(field3);
		boxRight.add(Box.createVerticalStrut(5));
		JTextField field4 = new JTextField(10);
		boxRight.add(field4);
		boxRight.add(Box.createVerticalStrut(5));
		JTextField field5 = new JTextField(10);
		boxRight.add(field5);
		boxRight.add(Box.createVerticalStrut(5));

		JButton btn = new JButton("Submit");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Create an object array with the input data
				Object[] data = new Object[] {
						field1.getText(),
						field2.getText(),
						field3.getText(),
						field4.getText(),
						field5.getText(),
						"on-site"
				};
				BookDAO.add(data); // Add the book using the BookDAO class

				BookAdminPanel.setContent(new BookTablePanel()); // Set the content of the BookAdminPanel to the BookTablePanel
			}
		});
		boxRight.add(btn);
		boxBase.add(boxLeft);
		boxBase.add(Box.createHorizontalStrut(8));
		boxBase.add(boxRight);
		panel.add(boxBase);

		this.setVisible(true);
	}
}