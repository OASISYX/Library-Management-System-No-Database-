package com.book.frame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.Border;

import com.book.dao.UserDAO;
import com.book.frame.book.BookAdminPanel;
import com.book.frame.borrower.BorrowerPanel;
import com.book.frame.system.RegisterPanel;
import com.book.frame.system.SystemAdminPanel;
import com.book.pojo.User;
import com.book.util.SystemConstants;

public class LoginPanel extends JPanel {
	private static final String dir;

	static {
		// Directory path for image resources
		dir = LoginPanel.class.getClassLoader().getResource("Image").getPath();
	}
	public LoginPanel() {
		this.setBounds(0, 0, SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
		this.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		JMenu costofbook = new JMenu("Cost Of Books");
		JMenu visiters = new JMenu("Visiters");
		JMenu rooms = new JMenu("Rooms");
		JMenu borrowbooks = new JMenu("Borrow Books");
		menuBar.add(costofbook);
		menuBar.add(visiters);
		menuBar.add(rooms);
		menuBar.add(borrowbooks);

		this.setLayout(new BorderLayout());
		menuBar.setBounds(0, 0, SystemConstants.FRAME_WIDTH, 30);
		this.add(menuBar, BorderLayout.NORTH);

		this.setBounds(0, 0, SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
		this.setLayout(null);


		JPanel panel = new JPanel(){
			protected void paintComponent(Graphics g){
				Graphics2D g2=(Graphics2D)g;
				g2.drawImage(new ImageIcon(dir +"/p1.png").getImage(),0,0,getWidth(),getHeight(),null);
		 	}
		};
		panel.setBounds(200,100,500,300);
		this.add(panel);

		Box box = Box.createVerticalBox();
		panel.add(box);
		box.add(Box.createVerticalStrut(15));

		Box box0 = Box.createHorizontalBox();
		JLabel title = new JLabel("Library Management System");
		title.setFont(new Font("Serif",Font.BOLD,30));
		box0.add(title);
		box.add(box0);
		box.add(Box.createVerticalStrut(20));

		Font font = new Font("Serif",Font.BOLD,20);
		Border border = BorderFactory.createBevelBorder(1);

		Box box1 = Box.createHorizontalBox();
		JLabel nameLabel = new JLabel("username: ");
		nameLabel.setFont(font);
		box1.add(nameLabel);
		JTextField nameField = new JTextField(10);
		box1.add(nameField);
		box.add(box1);
		box.add(Box.createVerticalStrut(15));

		Box box2 = Box.createHorizontalBox();
		JLabel pwdLabel = new JLabel("password: ");
		pwdLabel.setFont(font);
		box2.add(pwdLabel);
		JPasswordField pwdField = new JPasswordField(15);
		nameField.setBorder(border);
		box2.add(pwdField);
		box.add(box2);

		box.add(Box.createVerticalStrut(15));


		JRadioButton systemRadio = new JRadioButton("System Admin", true);
		JRadioButton bookRadio = new JRadioButton("Book Admin");
		JRadioButton borrowRadio = new JRadioButton("Borrower");

		ButtonGroup group = new ButtonGroup();
		systemRadio.setFont(font);
		systemRadio.setOpaque(false);
		systemRadio.setFocusPainted(false);
		bookRadio.setFont(font);
		bookRadio.setOpaque(false);
		bookRadio.setFocusPainted(false);
		borrowRadio.setFont(font);
		borrowRadio.setOpaque(false);
		borrowRadio.setFocusPainted(false);
		group.add(systemRadio);
		group.add(bookRadio);
		group.add(borrowRadio);
		Box box3 = Box.createHorizontalBox();
		box3.add(systemRadio);
		box3.add(bookRadio);
		box3.add(borrowRadio);
		box.add(box3);
		box3.add(Box.createVerticalStrut(15));

		JButton loginButton = new JButton();
		Image login = new ImageIcon(dir + "/p2.png").getImage().getScaledInstance(110,60,Image.SCALE_DEFAULT);
		loginButton.setIcon(new ImageIcon(login));
		loginButton.setBorderPainted(false);
		loginButton.setOpaque(false);
		loginButton.setContentAreaFilled(false);
		loginButton.setBorder(null);

		JButton regButton = new JButton();
		Image reg = new ImageIcon(dir + "/p3.png").getImage().getScaledInstance(110,60,Image.SCALE_DEFAULT);
		regButton.setIcon(new ImageIcon(reg));
		regButton.setBorderPainted(false);
		regButton.setOpaque(false);
		regButton.setContentAreaFilled(false);
		regButton.setBorder(null);

		Box box4 = Box.createHorizontalBox();
		box4.add(loginButton);
		box4.add(regButton);
		box.add(box4);

		loginButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = nameField.getText();
				String password = new String(pwdField.getPassword());
				User user = null;
				JPanel panel = null;
				if (systemRadio.isSelected()) {
					user = UserDAO.SYSTEM_ADMINS.get(username);
					panel = new SystemAdminPanel();
				} else if (bookRadio.isSelected()) {
					user = UserDAO.BOOK_ADMINS.get(username);
					panel = new BookAdminPanel();
				} else if (borrowRadio.isSelected()) {
					user = UserDAO.BORROWERS.get(username);
					panel = new BorrowerPanel();
				}
				if (user == null || !user.getPassword().equals(password)) {
					JOptionPane.showMessageDialog(loginButton.getParent(), "Incorrect username or password", "System Prompt",
							JOptionPane.WARNING_MESSAGE);
				}else {
					MainFrame.setContent(panel);
					MainFrame.user = user;
				}

			}
		});

		regButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainFrame.setContent(new RegisterPanel());
			}
		});
	}
	protected void paintComponent(Graphics g){
		String dir = this.getClass().getClassLoader().getResource("Image").getPath();
		Graphics2D g2=(Graphics2D)g;
		g2.drawImage(new ImageIcon(dir +"/background.jpg").getImage(),0,0,getWidth(),getHeight(),null);
	}
}
