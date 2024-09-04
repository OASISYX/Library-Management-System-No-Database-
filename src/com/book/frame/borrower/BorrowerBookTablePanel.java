package com.book.frame.borrower;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.book.dao.BorrowedBookDAO;
import com.book.frame.MainFrame;
import com.book.util.SystemConstants;


public class BorrowerBookTablePanel extends JInternalFrame {

	public BorrowerBookTablePanel() {
		super("Data List", true, true, true, true);
		this.setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);

		// Create a non-editable JTable
		JTable table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		// Create the table model using the borrowed book data for the current user
		TableModel tableModel = new DefaultTableModel(BorrowedBookDAO.toList(MainFrame.user.getUsername()),
				BorrowedBookDAO.columnNames);
		table.setModel(tableModel);

		JPanel panel = new JPanel(new BorderLayout());
		panel.setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);

		// Add the table header to the panel
		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		// Add the table to the panel
		panel.add(table, BorderLayout.CENTER);
		this.add(panel, BorderLayout.CENTER);

		this.setVisible(true);
	}
}
