package com.book.frame.borrower;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import com.book.frame.LoginPanel;
import com.book.frame.MainFrame;
import com.book.frame.system.userRoom;
import com.book.util.SystemConstants;


public class BorrowerPanel extends JPanel {
	private static JDesktopPane contentPanel = new JDesktopPane();

	public BorrowerPanel() {
		this.setBounds(0, 0, SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
		contentPanel.setBounds(0, 20, SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT - 50);
		this.add(contentPanel, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		JMenu parentMenu = new JMenu("Borrowing Records");
		JMenu bookMenu = new JMenu("Book Search");
		JMenu readMenu = new JMenu("Borrowing and reading statistics");
		JMenu rankingMenu = new JMenu("Student Reading List");
		JMenu roomMenu = new JMenu("Room Request");
		JMenu logoutMenu = new JMenu("Log out");
		menuBar.add(parentMenu);
		menuBar.add(bookMenu);
		menuBar.add(readMenu);
		menuBar.add(rankingMenu);
		menuBar.add(roomMenu);
		menuBar.add(logoutMenu);

		parentMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setContent(new BorrowerBookTablePanel());
			}
		});

		bookMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setContent(new BookSearchTablePanel());
			}
		});

		readMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setContent(new bookreadstapanel());
			}
		});

		roomMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setContent(new userRoom(MainFrame.user));
			}
		});

		logoutMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				contentPanel.removeAll();
				contentPanel.repaint();
				MainFrame.setContent(new LoginPanel());
			}
		});

		rankingMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setContent(new RankingTablePanel());
			}
		});

		this.setLayout(new BorderLayout());
		menuBar.setBounds(0, 0, SystemConstants.FRAME_WIDTH, 50);
		this.add(menuBar, BorderLayout.NORTH);
	}

	public static void setContent(JInternalFrame internalFrame) {
		// Set the size and visibility of the internal frame
		internalFrame.setSize(SystemConstants.FRAME_WIDTH - 100, SystemConstants.FRAME_HEIGHT - 100);
		internalFrame.setVisible(true);

		// Remove all components from the content panel, repaint, and add the internal frame
		contentPanel.removeAll();
		contentPanel.repaint();
		contentPanel.add(internalFrame);
	}

	public static void setContent(RankingTablePanel panel) {
		// Set the size and visibility of the ranking table panel
		panel.setSize(SystemConstants.FRAME_WIDTH - 100, SystemConstants.FRAME_HEIGHT - 100);
		panel.setVisible(true);

		// Remove all components from the content panel, add the ranking table panel, and refresh the layout
		contentPanel.removeAll();
		contentPanel.add(panel);
		contentPanel.revalidate();
		contentPanel.repaint();
	}

	public static void setContent(JFrame internalFrame) {
		// Set the size and visibility of the JFrame
		internalFrame.setSize(SystemConstants.FRAME_WIDTH - 100, SystemConstants.FRAME_HEIGHT - 100);
		internalFrame.setVisible(true);

		// Remove all components from the content panel, repaint, and add the JFrame
		contentPanel.removeAll();
		contentPanel.repaint();
		contentPanel.add(internalFrame);
	}
}
