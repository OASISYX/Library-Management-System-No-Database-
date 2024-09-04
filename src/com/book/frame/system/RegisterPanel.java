package com.book.frame.system;

import com.book.frame.LoginPanel;
import com.book.frame.MainFrame;
import com.book.pojo.User;
import com.book.util.SystemConstants;
import com.book.util.SystemVerifier;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static com.book.dao.UserDAO.BORROWERS;

public class RegisterPanel extends JPanel {
    // Directory path for image resources
    private static final String dir = RegisterPanel.class.getClassLoader().getResource("Image").getPath();

    public RegisterPanel() {
        this.setBounds(0, 0, SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
        this.setLayout(null);

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                // Draw the background image
                g2.drawImage(new ImageIcon(dir + "/p1.png").getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel.setBounds(200, 100, 500, 300);
        this.add(panel);

        Box box = Box.createVerticalBox();
        panel.add(box);
        box.add(Box.createVerticalStrut(15));

        Box box0 = Box.createHorizontalBox();
        JLabel title = new JLabel("Library Management System");
        title.setFont(new Font("Serif", Font.BOLD, 30));
        box0.add(title);
        box.add(box0);
        box.add(Box.createVerticalStrut(20));

        Font font = new Font("Serif", Font.BOLD, 20);
        Border border = BorderFactory.createBevelBorder(1);

        Box box1 = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("username   : ");
        nameLabel.setFont(font);
        box1.add(nameLabel);
        JTextField nameField = new JTextField(15);
        nameField.setBorder(border);
        nameField.setInputVerifier(SystemVerifier.emptyVerify("username ", 4, 8));
        box1.add(nameField);
        box.add(box1);
        box.add(Box.createVerticalStrut(15));

        Box box2 = Box.createHorizontalBox();
        JLabel pwdLabel = new JLabel("password   : ");
        pwdLabel.setFont(font);
        box2.add(pwdLabel);
        JTextField pwdField = new JTextField(15);
        pwdField.setInputVerifier(SystemVerifier.emptyVerify("password", 6, 20));
        nameField.setBorder(border);
        box2.add(pwdField);
        box.add(box2);
        box.add(Box.createVerticalStrut(15));

        Box box3 = Box.createHorizontalBox();
        JLabel repwdLabel = new JLabel("verify password : ");
        pwdLabel.setFont(font);
        box3.add(repwdLabel);
        JTextField repwdField = new JTextField(15);
        pwdField.setInputVerifier(SystemVerifier.emptyVerify("Verify password ", 6, 20));
        nameField.setBorder(border);
        box3.add(Box.createHorizontalStrut(1));
        box3.add(repwdField);
        box3.add(Box.createVerticalStrut(15));
        box.add(box3);

        JButton regButton = new JButton();
        Image reg = new ImageIcon(dir + "/p3.png").getImage().getScaledInstance(110, 60, Image.SCALE_DEFAULT);
        regButton.setIcon(new ImageIcon(reg));
        regButton.setBorderPainted(false);
        regButton.setOpaque(false);
        regButton.setContentAreaFilled(false);
        regButton.setBorder(null);

        JButton loginButton = new JButton();
        Image login = new ImageIcon("%s/p2.png".formatted(dir)).getImage().getScaledInstance(110, 60, Image.SCALE_DEFAULT);
        loginButton.setIcon(new ImageIcon(login));
        loginButton.setBorderPainted(false);
        loginButton.setOpaque(false);
        loginButton.setContentAreaFilled(false);
        loginButton.setBorder(null);

        Box box4 = Box.createHorizontalBox();
        box4.add(loginButton);
        box4.add(Box.createHorizontalStrut(20));
        box4.add(regButton);
        box.add(box4);

        // Add a mouse listener to the login button
        loginButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Open the LoginPanel when the login button is clicked
                MainFrame.setContent(new LoginPanel());
            }
        });

        // Add a mouse listener to the register button
        regButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = nameField.getText();
                String password = new String(pwdField.getText());
                String repword = new String(repwdField.getText());

                if (!password.equals(repword)) {
                    // Display a warning message if the passwords do not match
                    JOptionPane.showMessageDialog(loginButton.getParent(),"Inconsistency between two password entries","System Prompt",JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Create a new User object and add it to the BORROWERS map
                User borrower1 = new User(username, password);
                BORROWERS.put(borrower1.getUsername(), borrower1);
                // Display a success message
                JOptionPane.showMessageDialog(loginButton.getParent(),"Successful registration","System Prompt",JOptionPane.WARNING_MESSAGE);
                // Open the LoginPanel
                MainFrame.setContent(new LoginPanel());
            }
        });

    }

    // Directory path for the background image
    private static final String imagePath = RegisterPanel.class.getClassLoader().getResource("Image/background.jpg").getPath();

    // Override the paintComponent method to draw the background image
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Image backgroundImage = new ImageIcon(imagePath).getImage();
        g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
    }
}