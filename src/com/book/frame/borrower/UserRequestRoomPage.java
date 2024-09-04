package com.book.frame.borrower;

import com.book.dao.RoomDAO;
import com.book.frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserRequestRoomPage extends JFrame {

    private String roomName;

    public UserRequestRoomPage(String roomName) {
        this.roomName = roomName;
        this.setTitle("Request Room");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Create and configure the room label
        JLabel roomLabel = new JLabel("Room Name: " + roomName);
        roomLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(roomLabel, BorderLayout.NORTH);

        // Create a panel for the center section
        JPanel centerPanel = new JPanel(new BorderLayout());

        // Create and configure the reason label
        JLabel reasonLabel = new JLabel("Reason:");
        centerPanel.add(reasonLabel, BorderLayout.WEST);

        // Create a combo box for selecting the reason
        JComboBox<String> reasonComboBox = new JComboBox<>(new String[]{"Personal Use", "Group Use"});
        centerPanel.add(reasonComboBox, BorderLayout.CENTER);

        this.add(centerPanel, BorderLayout.CENTER);

        // Create and configure the submit button
        JButton submitButton = new JButton("Submit Request");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected reason from the combo box
                String requestText = reasonComboBox.getSelectedItem().toString();

                // Update the room information using RoomDAO
                RoomDAO.updateRoom(roomName, requestText, MainFrame.user);

                // Show a dialog box with the request submission information
                JOptionPane.showMessageDialog(UserRequestRoomPage.this,
                        "Request submitted for " + roomName + " with reason: " + requestText + ".",
                        "Request Submitted",
                        JOptionPane.INFORMATION_MESSAGE);

                // Close the request room page
                UserRequestRoomPage.this.dispose();
            }
        });
        this.add(submitButton, BorderLayout.SOUTH);

        this.setVisible(true);
    }
}