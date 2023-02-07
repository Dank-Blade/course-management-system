package GUI;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DatabaseConnect.Connect;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {
	private JTextField username;
	private JPasswordField password;
	private JButton backButton;
	private JButton loginButton;
	private JRadioButton student;
	private JRadioButton teacher;
	private JRadioButton admin;
	private ButtonGroup group;
	private String role;

	Login() {
		Font myFont = new Font("Calibri", Font.BOLD, 17);
		Font myFont1 = new Font("Calibri", Font.PLAIN, 15);

		username = new JTextField();
		password = new JPasswordField();

		JLabel usernameLabel = new JLabel("Username:", SwingConstants.RIGHT);
		JLabel passwordLabel = new JLabel("Password:", SwingConstants.RIGHT);

		usernameLabel.setBounds(15, 40, 105, 30);
		usernameLabel.setFont(myFont);
		usernameLabel.setForeground(new Color(0xDEDEDE));

		passwordLabel.setBounds(15, 95, 105, 30);
		passwordLabel.setFont(myFont);
		passwordLabel.setForeground(new Color(0xDEDEDE));

		username.setBounds(130, 38, 225, 35);
		username.setFont(myFont1);
		username.setBackground(new Color(0xDEDEDE));

		password.setBounds(130, 93, 225, 35);
		password.setFont(myFont1);
		password.setBackground(new Color(0xDEDEDE));

		student = new JRadioButton("Student");
		teacher = new JRadioButton("Teacher");
		admin = new JRadioButton("Admin");

		student.setBounds(75, 150, 80, 29);
		student.setForeground(new Color(0xDEDEDE));
		student.setBackground(new Color(31, 41, 91));
		student.setFocusable(false);
		student.setCursor(new Cursor(Cursor.HAND_CURSOR));
		student.addActionListener(this);

		teacher.setBounds(165, 150, 80, 29);
		teacher.setForeground(new Color(0xDEDEDE));
		teacher.setBackground(new Color(31, 41, 91));
		teacher.setFocusable(false);
		teacher.setCursor(new Cursor(Cursor.HAND_CURSOR));
		teacher.addActionListener(this);

		admin.setBounds(255, 150, 80, 26);
		admin.setForeground(new Color(0xDEDEDE));
		admin.setBackground(new Color(31, 41, 91));
		admin.setFocusable(false);
		admin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		admin.addActionListener(this);

		group = new ButtonGroup();
		group.add(student);
		group.add(teacher);
		group.add(admin);

		backButton = new JButton("Back");
		backButton.setBounds(80, 200, 100, 30);
		backButton.setFocusable(false);
		backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backButton.addActionListener(this);
		backButton.setBackground(new Color(141, 91, 233));
		backButton.setBorder(BorderFactory.createLineBorder(Color.black));

		loginButton = new JButton("Login");
		loginButton.setBounds(240, 200, 100, 30);
		loginButton.setFocusable(false);
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginButton.addActionListener(this);
		loginButton.setBackground(new Color(141, 91, 233));
		loginButton.setBorder(BorderFactory.createLineBorder(Color.black));

		this.setTitle("Course Management System");
		this.add(usernameLabel);
		this.add(passwordLabel);
		this.add(username);
		this.add(password);
		this.add(student);
		this.add(teacher);
		this.add(admin);
		this.add(backButton);
		this.add(loginButton);
		this.getContentPane().setBackground(new Color(31, 41, 91));
		this.setSize(420, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Connect conn = new Connect();

		if (e.getSource() == backButton) {
			this.dispose();
			new Choice();
		}
		if (e.getSource() == loginButton) {

			if (username.getText().isEmpty() == true && password.getPassword().length == 0) {
				JOptionPane.showMessageDialog(null, "Username & Password field cannot be empty.");
			} else if (username.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Username field cannot be empty.");
			} else if (String.valueOf(password.getPassword()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "Password field cannot be empty.");
			}

			else {
				if (student.isSelected()) {
					role = "student";
				} else if (admin.isSelected()) {
					role = "admin";

				} else if (teacher.isSelected()) {
					role = "teacher";
				} else {
					JOptionPane.showMessageDialog(null, "Please select a user.");
				}

				if (role.equals("student")) {
					try {
						String query = "Select * from studentinfo where username='" + username.getText()
								+ "' and password='" + String.valueOf(password.getPassword()) + "'";
						ResultSet rs = conn.st.executeQuery(query);

						if (rs.next()) {
							this.dispose();
							JOptionPane.showMessageDialog(null, "Logged in successfully.");
							new StudentPanel(username.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Username or password doesn't match.");
						}

					} catch (Exception er) {
						System.out.println(er);
					}
				}

				else if (role.equals("teacher")) {
					try {
						String query = "Select * from teacherinfo where username='" + username.getText()
								+ "' and password='" + String.valueOf(password.getPassword()) + "'";
						ResultSet rs = conn.st.executeQuery(query);

						if (rs.next()) {
							this.dispose();
							JOptionPane.showMessageDialog(null, "Logged in successfully.");
							new TeacherPanel(username.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Username or password doesn't match.");
						}

					} catch (Exception er) {
						System.out.println(er);
					}
				}

				else if (role.equals("admin")) {
					try {
						String query = "Select * from admininfo where username='" + username.getText()
								+ "' and password='" + String.valueOf(password.getPassword()) + "'";
						ResultSet rs = conn.st.executeQuery(query);

						if (rs.next()) {
							this.dispose();
							JOptionPane.showMessageDialog(null, "Logged in successfully.");
							new AdminPanel(username.getText());
						} else {
							JOptionPane.showMessageDialog(null, "Username or password doesn't match.");
						}

					} catch (Exception er) {
						System.out.println(er);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "You need to select a user!");
				}
			}

		}

	}
}
