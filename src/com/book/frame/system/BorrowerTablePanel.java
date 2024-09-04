package com.book.frame.system;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.book.dao.BorrowedBookDAO;
import com.book.dao.BorrowerDAO;
import com.book.dao.UserDAO;
import com.book.frame.MainFrame;
import com.book.util.SystemConstants;


public class BorrowerTablePanel extends JInternalFrame {
	JTable table = new JTable() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTextField field1 = new JTextField(10);
	private JTextField field2 = new JTextField(10);

	public BorrowerTablePanel() {
		super("User Management", true, true, true, true);
		this.setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);

		// Set the table model with data from BorrowedBookDAO and column names from BorrowerDAO
		TableModel tableModel = new DefaultTableModel(BorrowedBookDAO.toList(MainFrame.user.getId()), BorrowerDAO.columnNames);
		table.setModel(tableModel);

		// Create the top panel with labels, text fields, and buttons
		JPanel topPanel = new JPanel();
		topPanel.add(new JLabel("Student ID:"));
		topPanel.add(field1);
		topPanel.add(new JLabel("Name:"));
		topPanel.add(field2);

		JButton searchBtn = new JButton("Search");
		topPanel.add(searchBtn);
		JButton addBtn = new JButton("Add");
		topPanel.add(addBtn);
		JButton delBtn = new JButton("Delete");
		topPanel.add(delBtn);
		JButton editBtn = new JButton("Change");
		topPanel.add(editBtn);

		JPanel panel = new JPanel(new BorderLayout());
		panel.setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);

		// Add ActionListener to the search button
		searchBtn.addActionListener(e -> search());
		search();

		// Add MouseAdapter to the add button
		addBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Open the BorrowerEditPanel with null parameter
				SystemAdminPanel.setContent(new BorrowerEditPanel(null));
			}
		});

		// Add MouseAdapter to the edit button
		editBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Get the selected row number
				int rowNum = table.getSelectedRow();
				if (rowNum <= -1) {
					return;
				}
				// Open the BorrowerEditPanel with the value from the selected row's first column
				SystemAdminPanel.setContent(new BorrowerEditPanel(table.getValueAt(rowNum, 0)));
			}
		});

		// Add MouseAdapter to the delete button
		delBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Get the selected row number
				int rowNum = table.getSelectedRow();
				if (rowNum <= -1) {
					return;
				}
				// Remove the borrower from the BorrowerDAO and UserDAO.BORROWERS
				BorrowerDAO.remove(table.getValueAt(rowNum, 0));
				UserDAO.BORROWERS.remove(table.getValueAt(rowNum, 1));
				search();
			}
		});

		// Add the table header to the panel
		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Add the top panel, table, and panel to the internal frame
		this.add(topPanel, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		this.add(panel, BorderLayout.CENTER);

		this.setVisible(true);
	}

	// Perform a search based on the values in the text fields
	private void search() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);

		// Perform a multi-field search using BorrowerDAO and update the table model
		Object[][] searchData = BorrowerDAO.searchMulti(new int[]{0, 1}, new String[]{field1.getText(), field2.getText()});
		for (Object[] row : searchData) {
			tableModel.addRow(row);
		}
	}
}