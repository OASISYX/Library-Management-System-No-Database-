package com.book.frame.book;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import com.book.frame.LoginPanel;
import com.book.frame.MainFrame;
import com.book.frame.borrower.RankingTablePanel;
import com.book.util.SystemConstants;


public class BookAdminPanel extends JPanel {
	private static JDesktopPane contentPanel = new JDesktopPane();

	public BookAdminPanel() {
		this.setBounds(0, 0, SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
		contentPanel.setBounds(0, 20, SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT - 50);
		this.add(contentPanel, BorderLayout.CENTER);

		// Create menu bar
		JMenuBar menuBar = new JMenuBar();
		JMenu parentMenu = new JMenu("Book Management");

		JMenu logoutMenu = new JMenu("Log out");
		menuBar.add(parentMenu);
		menuBar.add(logoutMenu);

		// Mouse listener for parentMenu
		parentMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				setContent(new BookTablePanel());
			}
		});

		// Mouse listener for logoutMenu
		logoutMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				MainFrame.setContent(new LoginPanel());
			}
		});

		this.setLayout(new BorderLayout());
		menuBar.setBounds(0, 0, SystemConstants.FRAME_WIDTH, 50);
		this.add(menuBar, BorderLayout.NORTH);
	}

	public static void setContent(JInternalFrame internalFrame) {
		internalFrame.setSize(SystemConstants.FRAME_WIDTH - 100, SystemConstants.FRAME_HEIGHT - 100);

		internalFrame.setVisible(true);
		contentPanel.removeAll();
		contentPanel.repaint();
		contentPanel.add(internalFrame);
	}

	public static void setContent(RankingTablePanel panel) {
		panel.setSize(SystemConstants.FRAME_WIDTH - 100, SystemConstants.FRAME_HEIGHT - 100);

		panel.setVisible(true);
		contentPanel.removeAll();
		contentPanel.add(panel);
		contentPanel.revalidate();
		contentPanel.repaint();
	}
}
