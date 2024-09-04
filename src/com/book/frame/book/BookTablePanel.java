package com.book.frame.book;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.book.dao.BookDAO;
import com.book.util.SystemConstants;


public class BookTablePanel extends JInternalFrame {
	public BookTablePanel() {
		super("Book List", true, true, true, true);
		this.setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);

		// Create a table for displaying book data
		JTable table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Set the table model with book data retrieved from BookDAO
		TableModel tableModel = new DefaultTableModel(BookDAO.toList(BookDAO.data), BookDAO.columnNames);
		table.setModel(tableModel);

		JPanel topPanel = new JPanel();

		// Create "Add" button
		JButton addBtn = new JButton("Add");
		topPanel.add(addBtn);

		// Create "Lending and Returning Books" button
		JButton editBtn = new JButton("Lending and Returning Books");
		topPanel.add(editBtn);

		JPanel panel = new JPanel(new BorderLayout());
		panel.setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);

		// Add mouse listener to "Add" button
		addBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Open the BookEditPanel for adding a book
				BookAdminPanel.setContent(new BookEditPanel());
			}
		});

		// Add mouse listener to "Lending and Returning Books" button
		editBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowNum = table.getSelectedRow();
				System.out.println("The row is " + rowNum);
				if (rowNum <= -1) {
					return;
				}
				// Open the BookManagePanel for managing the selected book
				BookAdminPanel.setContent(new BookManagePanel((String) table.getValueAt(rowNum, 0)));
			}
		});

		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		this.add(topPanel, BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		this.add(panel, BorderLayout.CENTER);

		this.setVisible(true);
	}
}
