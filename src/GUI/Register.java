package GUI;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DatabaseConnect.Connect;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Register extends JFrame implements ActionListener {
	private JTextField firstnameField;
	private JTextField lastnameField;
	private JTextField emailField;
	private JTextField numberField;
	private JTextField usernameField;

	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;

	private JButton backButton;
	private JButton registerButton;

	private JComboBox levelBox;
	private JComboBox semesterBox;
	private JComboBox coursesBox;
	

	private String course;
	private String level;
	private String semester;
	private String username;

	private ArrayList<String> list;
	private ArrayList<String> modules;

	public ArrayList<String> getCourses() {
		list = new ArrayList<String>();
		Connect con = new Connect();

		String query = "SELECT * FROM courses";
		try {
			ResultSet rs = con.st.executeQuery(query);
			
			while (rs.next()) {
				list.add(rs.getString("course_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	Register() {
		Font myFont = new Font("Calibri", Font.BOLD, 17);
		Font myFont1 = new Font("Calibri", Font.PLAIN, 15);

		firstnameField = new JTextField();
		lastnameField = new JTextField();
		usernameField = new JTextField();
		emailField = new JTextField();
		numberField = new JTextField();
		passwordField = new JPasswordField();
		confirmPasswordField = new JPasswordField();

		String[] courses = getCourses().toArray(new String[getCourses().size()]);
		System.out.println(courses);
		coursesBox = new JComboBox<String>(courses);
		coursesBox.addActionListener(this);

		String[] level = { "4", "5", "6" };
		levelBox = new JComboBox<String>(level);
		levelBox.addActionListener(this);

		String[] semester = { "1", "2" };
		semesterBox = new JComboBox<String>(semester);
		semesterBox.addActionListener(this);

		JLabel topic = new JLabel("REGISTER");
		JLabel firstnameLabel = new JLabel("First Name:", SwingConstants.RIGHT);
		JLabel lastnameLabel = new JLabel("Last Name:", SwingConstants.RIGHT);
		JLabel usernameLabel = new JLabel("Username:", SwingConstants.RIGHT);
		JLabel passwordLabel = new JLabel("Password:", SwingConstants.RIGHT);
		JLabel confirmPasswordLabel = new JLabel("Confirm Password:", SwingConstants.RIGHT);
		JLabel emailLabel = new JLabel("Email:", SwingConstants.RIGHT);
		JLabel numberLabel = new JLabel("Phone Number:", SwingConstants.RIGHT);
		JLabel coursesLabel = new JLabel("Courses: ", SwingConstants.RIGHT);
		JLabel levelLabel = new JLabel("Level:", SwingConstants.RIGHT);
		JLabel semesterLabel = new JLabel("Semester:", SwingConstants.RIGHT);

		topic.setBounds(145, 25, 125, 30);
		topic.setFont(new Font("Calibri", Font.BOLD, 30));
		topic.setForeground(new Color(0xDEDEDE));
		
		firstnameLabel.setBounds(65, 85, 80, 30);
		firstnameLabel.setFont(myFont);
		firstnameLabel.setForeground(new Color(0xDEDEDE));

		lastnameLabel.setBounds(65, 140, 80, 30);
		lastnameLabel.setFont(myFont);
		lastnameLabel.setForeground(new Color(0xDEDEDE));
		
		usernameLabel.setBounds(65, 195, 80, 30);
		usernameLabel.setFont(myFont);
		usernameLabel.setForeground(new Color(0xDEDEDE));
		
		emailLabel.setBounds(95, 250, 50, 30);
		emailLabel.setFont(myFont);
		emailLabel.setForeground(new Color(0xDEDEDE));
		
		numberLabel.setBounds(35, 305, 110, 30);
		numberLabel.setFont(myFont);
		numberLabel.setForeground(new Color(0xDEDEDE));
		
		passwordLabel.setBounds(65, 360, 80, 30);
		passwordLabel.setFont(myFont);
		passwordLabel.setForeground(new Color(0xDEDEDE));
		
		confirmPasswordLabel.setBounds(15, 415, 130, 30);
		confirmPasswordLabel.setFont(myFont);
		confirmPasswordLabel.setForeground(new Color(0xDEDEDE));
		
		coursesLabel.setBounds(35, 470, 110, 30);
		coursesLabel.setFont(myFont);
		coursesLabel.setForeground(new Color(0xDEDEDE));
		
		levelLabel.setBounds(35, 525, 110, 30);
		levelLabel.setFont(myFont);
		levelLabel.setForeground(new Color(0xDEDEDE));
		
		semesterLabel.setBounds(75, 580, 70, 30);
		semesterLabel.setFont(myFont);
		semesterLabel.setForeground(new Color(0xDEDEDE));
		
		firstnameField.setBounds(165, 82, 225, 35);
		firstnameField.setFont(myFont1);
		firstnameField.setBackground(new Color(0xDEDEDE));

		lastnameField.setBounds(165, 137, 225, 35);
		lastnameField.setFont(myFont1);
		lastnameField.setBackground(new Color(0xDEDEDE));

		usernameField.setBounds(165, 192, 225, 35);
		usernameField.setFont(myFont1);
		usernameField.setBackground(new Color(0xDEDEDE));

		emailField.setBounds(165, 247, 225, 35);
		emailField.setFont(myFont1);
		emailField.setBackground(new Color(0xDEDEDE));

		numberField.setBounds(165, 302, 225, 35);
		numberField.setFont(myFont1);
		numberField.setBackground(new Color(0xDEDEDE));
		numberField.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
              char c = e.getKeyChar();
              if (!(Character.isDigit(c) ||
                 (c == KeyEvent.VK_BACK_SPACE) ||
                 (c == KeyEvent.VK_DELETE))) {
                   e.consume();
                 }
            }
          });
		
		passwordField.setBounds(165, 357, 225, 35);
		passwordField.setFont(myFont1);
		passwordField.setBackground(new Color(0xDEDEDE));

		confirmPasswordField.setBounds(165, 412, 225, 35);
		confirmPasswordField.setFont(myFont1);
		confirmPasswordField.setBackground(new Color(0xDEDEDE));

		coursesBox.setBounds(165, 467, 225, 35);
		coursesBox.setBackground(new Color(0xDEDEDE));
		coursesBox.setFont(myFont1);
		coursesBox.addActionListener(this);

		levelBox.setBounds(165, 522, 225, 35);
		levelBox.setBackground(new Color(0xDEDEDE));
		levelBox.setFont(myFont1);
		levelBox.addActionListener(this);

		semesterBox.setBounds(165, 577, 225, 35);
		semesterBox.setBackground(new Color(0xDEDEDE));
		semesterBox.setFont(myFont1);
		semesterBox.addActionListener(this);

		backButton = new JButton("Back");
		backButton.setBounds(80, 650, 100, 30);
		backButton.setFocusable(false);
		backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backButton.addActionListener(this);
		backButton.setBackground(new Color(141, 91, 233));
		backButton.setBorder(BorderFactory.createLineBorder(Color.black));

		registerButton = new JButton("Register");
		registerButton.setBounds(240, 650, 100, 30);
		registerButton.setFocusable(false);
		registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		registerButton.addActionListener(this);
		registerButton.setBackground(new Color(141, 91, 233));
		registerButton.setBorder(BorderFactory.createLineBorder(Color.black));

		this.setTitle("Course Management System");
		this.add(topic);
		this.add(firstnameLabel);
		this.add(lastnameLabel);
		this.add(usernameLabel);
		this.add(passwordLabel);
		this.add(confirmPasswordLabel);
		this.add(emailLabel);
		this.add(numberLabel);
		this.add(coursesLabel);
		this.add(levelLabel);
		this.add(semesterLabel);
		this.add(firstnameField);
		this.add(lastnameField);
		this.add(usernameField);
		this.add(emailField);
		this.add(passwordField);
		this.add(confirmPasswordField);
		this.add(numberField);
		this.add(coursesBox);
		this.add(levelBox);
		this.add(semesterBox);
		this.add(backButton);
		this.add(registerButton);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(31, 41, 91));
		this.setSize(420, 750);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		level = (String) levelBox.getSelectedItem();
		semester = (String) semesterBox.getSelectedItem();
		course = (String) coursesBox.getSelectedItem();

		String first_name = firstnameField.getText();
		String last_name = lastnameField.getText();
		String username = usernameField.getText();
		String email = emailField.getText();
		String number = numberField.getText();

		String password = String.valueOf(passwordField.getPassword());
		String confirmPassword = String.valueOf(confirmPasswordField.getPassword());

		if (e.getSource() == backButton) {
			this.dispose();
			new Choice();
		}
		if (e.getSource() == registerButton) {

			try {
				Connect conn = new Connect();

				String query = "SELECT * FROM studentinfo WHERE username = '" + username + "'";
				ResultSet rs = conn.st.executeQuery(query);

				if (first_name.isEmpty() || last_name.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()
						|| username.isEmpty() || number.isEmpty() || email.isEmpty()) {
					JOptionPane.showMessageDialog(null, "None of the fields can be left empty!");
				} else if (email.indexOf("@") == -1 || email.indexOf(".") == -1) {
					JOptionPane.showMessageDialog(null, "Invalid email. Please try again.");
				} else if (password.length() < 7) {
					JOptionPane.showMessageDialog(null, "The length of the password must be greater than 7!");
				} else if (!confirmPassword.equals(password)) {
					JOptionPane.showMessageDialog(null, "Passwords do not match!");
				} else if (number.length() != 10) {
					JOptionPane.showMessageDialog(null, "Number length should be 10!");
				} else if (rs.next()) {
					JOptionPane.showMessageDialog(null, "The given user already exists!");
				}

				else {
					try {
						query = String.format(
								"INSERT INTO studentinfo(first_name, last_name, username, password, email, number, course, level, semester) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s','%s')",
								first_name, last_name, username, password, email, number, course, level, semester);
						conn.st.executeUpdate(query);

						query = String.format(
								"SELECT * FROM modules WHERE course = '%s' AND level = '%s' AND semester = '%s' AND mandatory = 'Yes'",
								course, level, semester);
						rs = conn.st.executeQuery(query);

						modules = new ArrayList<String>();
						
						while (rs.next()) {
							modules.add(rs.getString("module_name"));
						}

						String[] moduleArray = modules.toArray(new String[modules.size()]);
						
						if (level.equals("6")) {
							query = String.format(
									"INSERT INTO level6(name, username, course, semester, module1, module2) VALUES ('%s', '%s', '%s', '%s', '%s', 'TBD')",
									first_name + " " + last_name, username, course, semester, moduleArray[0]);
						} else if (level.equals("4") || level.equals("5")) {
							query = String.format(
									"INSERT INTO level4and5(name, username, course, level, semester, module1, module2, module3, module4) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s')",
									first_name + " " + last_name, username, course, level, semester, moduleArray[0], moduleArray[1], moduleArray[2], moduleArray[3]);
						}

						conn.st.executeUpdate(query);
						JOptionPane.showMessageDialog(null, "Successfully registered! Redirecting to Login!");
						
						this.dispose();
						new Login();
					} catch (Exception er) {
						er.printStackTrace();
					}
				}
			} catch (Exception er) {
				er.printStackTrace();
			}
		}
	}

}
