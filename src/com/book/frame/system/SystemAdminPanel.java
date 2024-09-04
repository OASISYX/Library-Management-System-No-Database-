package com.book.frame.system;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import com.book.frame.LoginPanel;
import com.book.frame.MainFrame;
import com.book.util.SystemConstants;

public class SystemAdminPanel extends JPanel {
	private static JDesktopPane contentPanel = new JDesktopPane();

	public SystemAdminPanel() {
		// Set the size and layout of the panel
		this.setBounds(0, 0, SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
		contentPanel.setBounds(0, 20, SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT - 50);
		this.add(contentPanel, BorderLayout.CENTER);

		// Create a menu bar
		JMenuBar menuBar = new JMenuBar();

		// Create parentMenu for Reader List
		JMenu parentMenu = new JMenu("Reader List");

		// Create RoomMenu for Room List
		JMenu RoomMenu = new JMenu("Room List");

		// Create systemMenu for Log out
		JMenu systemMenu = new JMenu("Log out");

		// Add menus to the menu bar
		menuBar.add(parentMenu);
		menuBar.add(RoomMenu);
		menuBar.add(systemMenu);

		// Add a mouse listener to parentMenu
		parentMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Set the content panel to display BorrowerTablePanel
				setContent(new BorrowerTablePanel());
			}
		});

		// Add a mouse listener to RoomMenu
		RoomMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Set the content panel to display userRoom with the current user
				setContent(new userRoom(MainFrame.user));
			}
		});

		// Add a mouse listener to systemMenu
		systemMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				// Remove all components from the content panel
				contentPanel.removeAll();
				contentPanel.repaint();
				// Set the content panel to display LoginPanel
				MainFrame.setContent(new LoginPanel());
			}
		});

		// Set the layout of the panel
		this.setLayout(new BorderLayout());

		// Set the bounds of the menu bar
		menuBar.setBounds(0, 0, SystemConstants.FRAME_WIDTH, 50);

		// Add the menu bar to the panel
		this.add(menuBar, BorderLayout.NORTH);
	}

	// Set the content of the internal frame
	public static void setContent(JInternalFrame internalFrame) {
		internalFrame.setSize(SystemConstants.FRAME_WIDTH - 100, SystemConstants.FRAME_HEIGHT - 100);
		internalFrame.setVisible(true);
		contentPanel.removeAll();
		contentPanel.repaint();
		contentPanel.add(internalFrame);
	}

	// Set the content of the frame
	public static void setContent(JFrame internalFrame) {
		internalFrame.setSize(SystemConstants.FRAME_WIDTH - 100, SystemConstants.FRAME_HEIGHT - 100);
		internalFrame.setVisible(true);
		contentPanel.removeAll();
		contentPanel.repaint();
		contentPanel.add(internalFrame);
	}
}
