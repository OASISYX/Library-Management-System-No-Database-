package com.book.frame.system;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.*;

import com.book.dao.RoomDAO;
import com.book.dao.UserDAO;
import com.book.frame.MainFrame;
import com.book.frame.borrower.UserRequestRoomPage;
import com.book.pojo.User;
import com.book.util.SystemConstants;


public class userRoom extends JInternalFrame {
	private final User user;
	private final HashMap<String, String> roomStatusMap;
	private final HashMap<String, String> roomDataMap;
	private final HashMap<String, JLabel> roomLabels;

	public userRoom(User user) {
		super("Room List", true, true, true, true);
		this.setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
		this.user = user;
		// Initialize data structures
		roomStatusMap = new HashMap<>();
		roomLabels = new HashMap<>();
		roomDataMap = new HashMap<>();
		// Retrieve room data from RoomDAO
		Object[][] roomData = RoomDAO.toList(RoomDAO.data);
		for (Object[] room : roomData) {
			String roomName = (String) room[0];
			String roomStatus = (String) room[1];
			Object[] roomStatus0 = Arrays.copyOfRange(room, 1, room.length);
			String roomStatus01 = Arrays.toString(Arrays.copyOf(roomStatus0, roomStatus0.length, String[].class));
			// Store room status and data in the corresponding maps
			roomStatusMap.put(roomName, roomStatus);
			roomDataMap.put(roomName,roomStatus01);
		}

		// Create the main panel with a grid layout
		JPanel mainPanel = new JPanel(new GridLayout(4, 3, 10, 10));

		// Create panels for each room and add them to the main panel
		for (int i = 1; i <=8; i++) {
			String roomName = "Room " + i;


			JPanel coloredPanel = new JPanel();
			coloredPanel.setBackground(getRoomStatusColor(roomName));
			coloredPanel.setLayout(new BoxLayout(coloredPanel, BoxLayout.Y_AXIS));


			JLabel roomLabel = new JLabel(roomName);

			JLabel statusLabel = new JLabel(roomStatusMap.get(roomName) + "\n" +roomDataMap.get(roomName));
			int finalI = i;
			roomLabel.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						if (roomStatusMap.get(roomName).startsWith("Status: Unused") || roomStatusMap.get(roomName).startsWith("Status: Requested")) {

							if (UserDAO.BORROWERS.containsKey(user.getUsername())) {
								UserRequestRoomPage requestRoomPage = new UserRequestRoomPage(roomName);
								requestRoomPage.addWindowListener(new WindowAdapter() {
									@Override
									public void windowClosed(WindowEvent e) {

										roomStatusMap.put(roomName, "Status: Requested");
										coloredPanel.setBackground(getRoomStatusColor(roomName));
										statusLabel.setText(roomStatusMap.get(roomName));
										// Update RoomDAO data
										Object[] roomData = RoomDAO.findByName(roomName);
										if (roomData != null) {
											roomData[1] = "Status: Requested";
										}
									}
								});
								requestRoomPage.setVisible(true);
							} else if (UserDAO.SYSTEM_ADMINS.containsKey(user.getUsername())) {
								AdminRoomApprovalPage approvalPage = new AdminRoomApprovalPage();
								approvalPage.setVisible(true);
							}
						} else {
							JOptionPane.showMessageDialog(userRoom.this, "Room " + finalI + " is currently occupied.",
									"Room Occupied", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			});

			coloredPanel.add(roomLabel);
			coloredPanel.add(Box.createVerticalStrut(5));
			coloredPanel.add(statusLabel);
			mainPanel.add(coloredPanel);
			roomLabels.put(roomName, statusLabel);
		}

		this.add(mainPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}
	// Update the room status and colors
	public void updateRoomStatus() {
		// Retrieve the latest room data from RoomDAO
		Object[][] roomData = RoomDAO.toList(RoomDAO.data);
		for (Object[] room : roomData) {
			String roomName = (String) room[0];
			String roomStatus = (String) room[1];

			Object[] roomStatus0 = Arrays.copyOfRange(room, 1, room.length);
			roomStatus0[3] = MainFrame.user.getId();
			String roomStatus01 = Arrays.toString(Arrays.copyOf(roomStatus0, roomStatus0.length, String[].class));
			// Update the room status and data maps
			roomStatusMap.put(roomName, roomStatus);
			roomDataMap.put(roomName,roomStatus01);
			// Update the status label for the room if it exists
			JLabel statusLabel = roomLabels.get(roomName);
			if (statusLabel != null) {
				statusLabel.setText(roomStatus);
				statusLabel.setBackground(getRoomStatusColor(roomName));
			}
		}
	}
	// Get the color for a given room status
	private Color getRoomStatusColor(String roomName) {
		String status = roomStatusMap.get(roomName);
		if (status.startsWith("Status: Requested")) {
			return Color.YELLOW;
		} else if (status.startsWith("Status: Unused")) {
			return Color.GREEN;
		} else {
			return Color.RED;
		}
	}
	// Get the room status map
	public HashMap<String, String> getRoomStatusMap() {
		return roomStatusMap;
	}
}