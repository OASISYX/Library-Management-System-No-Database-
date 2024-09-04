package com.book.frame.borrower;

import com.book.dao.BorrowedBookDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.*;

public class RankingTablePanel extends JPanel {
    private JTable rankingTable;

    public RankingTablePanel() {
        initializeUI();
        updateRankingTable();
    }

    private void initializeUI() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create the ranking table and add it to a JScrollPane for scrolling
        rankingTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(rankingTable);
        this.add(scrollPane);
    }

    private void updateRankingTable() {
        // Create a new DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();

        // Add columns to the model
        model.addColumn("Rank");
        model.addColumn("Student");
        model.addColumn("Borrow Count");

        // Get the student borrow count data from BorrowedBookDAO
        Map<String, Integer> studentBorrowCountMap = BorrowedBookDAO.getStudentBorrowCount();

        // Sort the student borrow count map based on the borrow count in descending order
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(studentBorrowCountMap.entrySet());
        sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // Set the initial rank to 1
        int rank = 1;

        // Iterate over the sorted list and add rows to the model
        for (Map.Entry<String, Integer> entry : sortedList) {
            String student = entry.getKey();
            int borrowCount = entry.getValue();
            model.addRow(new Object[]{rank, student, borrowCount});
            rank++;
        }

        // Set the model to the ranking table
        rankingTable.setModel(model);
    }
}