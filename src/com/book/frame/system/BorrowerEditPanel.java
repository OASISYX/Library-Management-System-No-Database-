package com.book.frame.system;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import com.book.dao.BorrowerDAO;
import com.book.util.SystemConstants;


public class BorrowerEditPanel extends JInternalFrame {

	public BorrowerEditPanel(Object id) {
		super("Add User", true, true, true, true);
		this.setSize(SystemConstants.FRAME_WIDTH - 20, SystemConstants.FRAME_HEIGHT - 50);

		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());

		this.setContentPane(panel);

		// Create base and left boxes for labels
		Box boxBase = Box.createHorizontalBox();
		Box boxLeft = Box.createVerticalBox();

		// Add labels to the left box
		boxLeft.add(new JLabel("Student ID"));
		boxLeft.add(Box.createVerticalStrut(8));
		boxLeft.add(new JLabel("Name"));
		boxLeft.add(Box.createVerticalStrut(8));
		boxLeft.add(new JLabel("Gender"));
		boxLeft.add(Box.createVerticalStrut(8));
		boxLeft.add(new JLabel("Major"));
		boxLeft.add(Box.createVerticalStrut(8));
		boxLeft.add(new JLabel("User Name"));
		boxLeft.add(Box.createVerticalStrut(8));
		boxLeft.add(new JLabel("Password"));
		boxLeft.add(Box.createVerticalStrut(30));

		// Create right box for input fields
		Box boxRight = Box.createVerticalBox();

		// Create and add text fields to the right box
		JTextField field1 = new JTextField(10);
		boxRight.add(field1);
		boxRight.add(Box.createVerticalStrut(5));
		JTextField field2 = new JTextField(10);
		boxRight.add(field2);
		boxRight.add(Box.createVerticalStrut(5));

		// Create and configure the gender combo box
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.addItem("Male");
		comboBox.addItem("Female");
		boxRight.add(comboBox);
		boxRight.add(Box.createVerticalStrut(5));

		// Add more text fields to the right box
		JTextField field4 = new JTextField(10);
		boxRight.add(field4);
		boxRight.add(Box.createVerticalStrut(5));
		JTextField field5 = new JTextField(10);
		boxRight.add(field5);
		boxRight.add(Box.createVerticalStrut(5));
		JTextField field6 = new JTextField(10);
		boxRight.add(field6);
		boxRight.add(Box.createVerticalStrut(5));

		// Create and configure the add button
		JButton btn = new JButton("Add");
		btn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Get the data from the input fields
				String[] data = new String[] { field1.getText(), field2.getText(), comboBox.getSelectedItem().toString(),
						field4.getText(), field5.getText(), field6.getText() };

				// Add the data using BorrowerDAO
				BorrowerDAO.add(data);
				JOptionPane.showMessageDialog(getParent(), "Successful Add", "System Prompt", JOptionPane.WARNING_MESSAGE);

				// Set the content of SystemAdminPanel to BorrowerTablePanel
				SystemAdminPanel.setContent(new BorrowerTablePanel());
			}
		});
		boxRight.add(btn);

		// Add the left and right boxes to the base box
		boxBase.add(boxLeft);
		boxBase.add(Box.createHorizontalStrut(8));
		boxBase.add(boxRight);
		panel.add(boxBase);

		this.setVisible(true);

		// If an ID is provided, populate the fields with the corresponding data
		if (id != null) {
			Object[] data = BorrowerDAO.findById((String) id);
			field1.setText(data[0].toString());
			field2.setText(data[1].toString());
		}
	}
}