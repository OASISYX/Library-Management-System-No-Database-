package com.book.frame;
import javax.swing.*;
import com.book.pojo.User;
import com.book.util.SystemConstants;

public class MainFrame {
	public static final JFrame frame = new JFrame("library management system");
	public static User user;
	public static void main(String[] args) {
		frame.setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
		frame.setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setContentPane(new LoginPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void setContent(JPanel panel) {
		frame.setContentPane(panel);
	}
}
