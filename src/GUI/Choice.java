package GUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Choice extends JFrame implements ActionListener {
	private JButton loginButton;
	private JButton registerButton;
	
	Choice() {
		JLabel label = new JLabel("Do you want to login or register?");
		label.setFont(new Font("Calibri", Font.PLAIN, 16));
		label.setBounds(35, 50, 300, 50);
		label.setForeground(new Color(0xDEDEDE));

		loginButton = new JButton("Login");
		loginButton.setBounds(50, 100, 75, 25);
		loginButton.setFocusable(false);
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginButton.addActionListener(this);
		loginButton.setBackground(new Color(141, 91, 233));
		loginButton.setBorder(BorderFactory.createLineBorder(Color.black));

		registerButton = new JButton("Register");
		registerButton.setBounds(150, 100, 85, 25);
		registerButton.setFocusable(false);
		registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		registerButton.addActionListener(this);
		registerButton.setBackground(new Color(141, 91, 233));
		registerButton.setBorder(BorderFactory.createLineBorder(Color.black));

		this.setTitle("Course Management System");
		this.getContentPane().setBackground(new Color(31, 41, 91));
		this.add(label);
		this.add(loginButton);
		this.add(registerButton);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setSize(300, 250);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			this.dispose();
			new Login();
		}
		if (e.getSource() == registerButton) {
			this.dispose();
			new Register();
		}
	}
}
