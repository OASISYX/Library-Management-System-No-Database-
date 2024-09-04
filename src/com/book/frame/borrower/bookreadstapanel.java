package com.book.frame.borrower;

import com.book.dao.BorrowedBookDAO;
import com.book.frame.MainFrame;
import com.book.util.SystemConstants;

import javax.swing.*;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;

public class bookreadstapanel extends JInternalFrame {
    public bookreadstapanel() {
        super("Book Reads", true, true, true, true);
        this.setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        // Retrieve book category count data for the current user
        Object[][] categoryCountData = BorrowedBookDAO.getBookCategoryCount(MainFrame.user.getUsername());
        Arrays.sort(categoryCountData, Comparator.comparingInt(o -> (int) o[1]));


        if (categoryCountData.length > 0) {
            // Get the highest borrowed category and its count
            String highestCategory = (String) categoryCountData[categoryCountData.length - 1][0];
            int highestCount = (int) categoryCountData[categoryCountData.length - 1][1];
            // Create labels to display the highest borrowed category and its count
            JLabel highestCategoryLabel = new JLabel("Most Borrowed Category:");
            JLabel highestCategoryNameLabel = new JLabel(highestCategory);
            JLabel highestCountLabel = new JLabel("Borrow Count:");
            JLabel highestCountValueLabel = new JLabel(String.valueOf(highestCount));

            highestCategoryLabel.setFont(highestCategoryLabel.getFont().deriveFont(Font.BOLD));
            highestCategoryNameLabel.setFont(highestCategoryNameLabel.getFont().deriveFont(Font.BOLD));
            highestCountLabel.setFont(highestCountLabel.getFont().deriveFont(Font.BOLD));
            highestCountValueLabel.setFont(highestCountValueLabel.getFont().deriveFont(Font.BOLD));

            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.WEST;
            this.add(highestCategoryLabel, gbc);

            gbc.gridx = 1;
            this.add(highestCategoryNameLabel, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            this.add(highestCountLabel, gbc);

            gbc.gridx = 1;
            this.add(highestCountValueLabel, gbc);

            gbc.gridy = 2;
        }

        // Iterate over category count data and display each category and its count
        for (int i = 0; i < categoryCountData.length; i++) {
            String category = (String) categoryCountData[i][0];
            int count = (int) categoryCountData[i][1];

            JLabel categoryLabel = new JLabel(category);
            JTextField countTextField = new JTextField(String.valueOf(count));
            countTextField.setEditable(false);

            categoryLabel.setFont(categoryLabel.getFont().deriveFont(Font.BOLD));

            gbc.gridx = 0;
            gbc.anchor = GridBagConstraints.EAST;
            this.add(categoryLabel, gbc);

            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST;
            this.add(countTextField, gbc);

            gbc.gridy++;
        }

        this.setVisible(true);
    }
}