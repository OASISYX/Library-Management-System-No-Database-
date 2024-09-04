package com.book.frame.system;

import com.book.dao.RoomDAO;
import com.book.frame.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class AdminRoomApprovalPage extends JFrame {

    private userRoom userRoom;
    private JList<String> roomList;

    public AdminRoomApprovalPage() {
        this.setTitle("Room Approval");
        this.setSize(300, 200);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());

        // Create a default list model for the room list
        DefaultListModel<String> roomListModel = new DefaultListModel<>();
        roomList = new JList<>(roomListModel);
        JScrollPane scrollPane = new JScrollPane(roomList);
        this.add(scrollPane, BorderLayout.CENTER);

        // Create and configure the refresh button
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUserRoomStatus();
            }
        });
        this.add(refreshButton, BorderLayout.SOUTH);

        // Create and configure the approve button
        JButton approveButton = new JButton("Approve");
        approveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected room from the room list
                String selectedRoom = roomList.getSelectedValue();
                if (selectedRoom != null) {
                    if (selectedRoom.contains("Status: Requested")) {
                        // Extract the room name from the selected room string
                        String roomName = selectedRoom.split(" - ")[0];
                        approveRoom(roomName);
                    } else {
                        JOptionPane.showMessageDialog(AdminRoomApprovalPage.this,
                                "You can only approve rooms with the status 'Status: Requested'.",
                                "Invalid Selection",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(AdminRoomApprovalPage.this,
                            "Please select a room to approve.",
                            "No Room Selected",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        this.add(approveButton, BorderLayout.EAST);

        // Create a userRoom object for the current user
        userRoom = new userRoom(MainFrame.user);

        // Update the user's room status
        updateUserRoomStatus();

        this.setVisible(true);
    }

    public void updateUserRoomStatus() {
        // Get the room status map for the user
        HashMap<String, String> roomStatusMap = userRoom.getRoomStatusMap();

        // Clear the existing room list model
        DefaultListModel<String> roomListModel = (DefaultListModel<String>) roomList.getModel();
        roomListModel.clear();

        // Iterate over the room status map and add room name - status pairs to the model
        for (String roomName : roomStatusMap.keySet()) {
            roomListModel.addElement(roomName + " - " + roomStatusMap.get(roomName));
        }
    }

    public void approveRoom(String roomName) {
        // Approve the room using RoomDAO
        RoomDAO.approveBookRoom(roomName);
        JOptionPane.showMessageDialog(AdminRoomApprovalPage.this,
                "Room " + roomName + " approved.",
                "Room Approved",
                JOptionPane.INFORMATION_MESSAGE);

        // Update the user's room status
        userRoom.updateRoomStatus();

        // Update the room list
        updateUserRoomStatus();
    }
}