package com.book.frame.borrower;

import com.book.dao.BookDAO;

import com.book.util.SystemConstants;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;


public class BookSearchTablePanel extends JInternalFrame {
    JTable table = new JTable() {
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTextField field1 = new JTextField(10);
    private final JTextField field2 = new JTextField(10);

    public BookSearchTablePanel() {
        super("Book Search", true, true, true, true);
        this.setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);

        // Create the table model using the book data retrieved from BookDAO
        TableModel tableModel = new DefaultTableModel(BookDAO.toList(BookDAO.data), BookDAO.columnNames);
        table.setModel(tableModel);
        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Book Name:"));
        topPanel.add(field1);
        topPanel.add(new JLabel("Categories:"));
        topPanel.add(field2);

        JButton searchBtn = new JButton("Search");
        topPanel.add(searchBtn);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);

        // Add an ActionListener to the search button for performing the search
        searchBtn.addActionListener(e -> search());
        search();

        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        this.add(topPanel, BorderLayout.NORTH);
        panel.add(table, BorderLayout.CENTER);
        this.add(panel, BorderLayout.CENTER);

        this.setVisible(true);
    }

    private void search() {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        // Perform a multi-field search using the text entered in field1 and field2
        Object[][] searchData = BookDAO.searchMulti(new int[]{1, 2}, new String[]{field1.getText(), field2.getText()});
        for (Object[] row : searchData) {
            tableModel.addRow(row);
        }
    }
}
