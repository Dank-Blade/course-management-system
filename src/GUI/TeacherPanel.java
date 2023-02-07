package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DatabaseConnect.Connect;
import Users.Teacher;

public class TeacherPanel extends JFrame implements ActionListener {
	private JButton myDetails;
	private JButton setMarks;
	
	private JButton logout;

	private JPanel mainPanel;

	private Font myFont = new Font("Calibri", Font.BOLD, 17);
	private Font myFont1 = new Font("Calibri", Font.PLAIN, 15);

	private Container container;

	private String selectedModule;
	private String selectedName;

	private Teacher teacher;
	private Connect con;

	TeacherPanel(String username) {
		teacher = new Teacher();
		teacher.setTeacherInfo(username);

		container = this.getContentPane();

		container.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		mainPanel = new JPanel();

		JLabel welcome = new JLabel(String.format("Welcome, %s!", teacher.getFirstName()));
		welcome.setFont(myFont);
		welcome.setBounds(15, 15, 150, 50);
		welcome.setForeground(new Color(214, 214, 214));

		myDetails = new JButton("My Details");
		myDetails.setPreferredSize(new Dimension(175, 60));
		myDetails.setFocusable(false);
		myDetails.setBackground(new Color(141, 91, 233));
		myDetails.setCursor(new Cursor(Cursor.HAND_CURSOR));
		myDetails.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showDetailsPanel();
			}

		});

		setMarks = new JButton("Set Marks");
		setMarks.setPreferredSize(new Dimension(175, 60));
		setMarks.setFocusable(false);
		setMarks.setBackground(new Color(141, 91, 233));
		setMarks.setCursor(new Cursor(Cursor.HAND_CURSOR));
		setMarks.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setMarksPanel();

			}

		});	

		logout = new JButton("Logout");
		logout.setFocusable(false);
		logout.setBounds(930, 21, 80, 30);
		logout.setBackground(new Color(141, 91, 233));
		logout.setCursor(new Cursor(Cursor.HAND_CURSOR));
		logout.addActionListener(this);

		topPanel.setLayout(null);
		topPanel.add(welcome);
		topPanel.setBackground(new Color(47, 55, 99));
		topPanel.setPreferredSize(new Dimension(900, 75));
		topPanel.add(logout);

		leftPanel.setLayout(new FlowLayout(1, 1, 0));
		leftPanel.setBackground(new Color(47, 55, 99));
		leftPanel.setPreferredSize(new Dimension(175, 825));
		leftPanel.add(myDetails);
		leftPanel.add(setMarks);
		
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(31, 41, 91));

		container.add(topPanel, BorderLayout.NORTH);
		container.add(leftPanel, BorderLayout.WEST);
		container.add(mainPanel);

		this.setTitle("Course Management System");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(1050, 700);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	private void showDetailsPanel() {
		mainPanel.removeAll();

		JLabel id = new JLabel("ID:", SwingConstants.RIGHT);
		JLabel firstName = new JLabel("First Name:", SwingConstants.RIGHT);
		JLabel lastName = new JLabel("Last Name:", SwingConstants.RIGHT);
		JLabel username = new JLabel("Username:", SwingConstants.RIGHT);
		JLabel password = new JLabel("Password:", SwingConstants.RIGHT);
		JLabel email = new JLabel("Email:", SwingConstants.RIGHT);
		JLabel number = new JLabel("Number:", SwingConstants.RIGHT);

		JLabel idField = new JLabel(String.format("%s", teacher.getId()));
		JTextField firstNameField = new JTextField(teacher.getFirstName());
		JTextField lastNameField = new JTextField(teacher.getLastName());
		JLabel usernameField = new JLabel(teacher.getUsername());
		JPasswordField passwordField = new JPasswordField(teacher.getPassword());
		JTextField emailField = new JTextField(teacher.getEmail());
		JTextField numberField = new JTextField(teacher.getNumber());
		JButton updateButton = new JButton("Update");

		firstName.setFont(myFont);
		firstName.setBounds(90, 60, 80, 30);
		firstName.setForeground(new Color(214, 214, 214));

		lastName.setFont(myFont);
		lastName.setBounds(500, 60, 80, 30);
		lastName.setForeground(new Color(214, 214, 214));

		id.setFont(myFont);
		id.setBounds(150, 140, 20, 30);
		id.setForeground(new Color(214, 214, 214));
		
		username.setFont(myFont);
		username.setBounds(500, 140, 80, 30);
		username.setForeground(new Color(214, 214, 214));
		
		password.setFont(myFont);
		password.setBounds(90, 220, 80, 30);
		password.setForeground(new Color(214, 214, 214));
		
		email.setFont(myFont);
		email.setBounds(530, 220, 50, 30);
		email.setForeground(new Color(214, 214, 214));
		
		number.setFont(myFont);
		number.setBounds(100, 300, 70, 30);
		number.setForeground(new Color(214, 214, 214));
		
		firstNameField.setFont(myFont1);
		firstNameField.setBounds(190, 60, 200, 30);

		lastNameField.setFont(myFont1);
		lastNameField.setBounds(600, 60, 200, 30);

		idField.setFont(myFont1);
		idField.setBounds(190, 140, 105, 30);
		idField.setForeground(new Color(214, 214, 214));
		
		usernameField.setFont(myFont1);
		usernameField.setBounds(600, 140, 105, 30);
		usernameField.setForeground(new Color(214, 214, 214));
		
		passwordField.setFont(myFont1);
		passwordField.setBounds(190, 220, 200, 30);

		emailField.setFont(myFont1);
		emailField.setBounds(600, 220, 200, 30);

		numberField.setFont(myFont1);
		numberField.setBounds(190, 300, 200, 30);
		numberField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});

		updateButton.setFont(myFont);
		updateButton.setFocusable(false);
		updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		updateButton.setBounds(725, 500, 90, 30);
		updateButton.setBackground(new Color(141, 91, 233));
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connect conn = new Connect();

				String first_name = firstNameField.getText();
				String last_name = lastNameField.getText();
				String username = usernameField.getText();
				String email = emailField.getText();
				String number = numberField.getText();

				String password = String.valueOf(passwordField.getPassword());
				if (e.getSource() == updateButton) {

					if (first_name.isEmpty() || last_name.isEmpty() || password.isEmpty() || username.isEmpty()
							|| number.isEmpty() || email.isEmpty()) {
						JOptionPane.showMessageDialog(null, "None of the fields can be left empty!");
					} else if (email.indexOf("@") == -1 || email.indexOf(".com") == -1) {
						JOptionPane.showMessageDialog(null, "Invalid email. Please try again.");
					} else if (password.length() < 7) {
						JOptionPane.showMessageDialog(null, "The length of the password must be greater than 7!");
					} else if (number.length() != 10) {
						JOptionPane.showMessageDialog(null, "Number length should be 10!");
					}

					else {
						try {
							String query = String.format(
									"UPDATE teacherinfo SET first_name = '%s', last_name = '%s', password = '%s', email = '%s', number = '%s' WHERE username = '%s'",
									first_name, last_name, password, email, number, username);
							conn.st.executeUpdate(query);

							JOptionPane.showMessageDialog(null, "Credentials Updated!");

						} catch (Exception er) {
							er.printStackTrace();
						}
					}
				}

			}

		});

		mainPanel.add(firstName);
		mainPanel.add(lastName);
		mainPanel.add(id);
		mainPanel.add(username);
		mainPanel.add(password);
		mainPanel.add(email);
		mainPanel.add(number);

		mainPanel.add(firstNameField);
		mainPanel.add(lastNameField);
		mainPanel.add(idField);
		mainPanel.add(usernameField);
		mainPanel.add(passwordField);
		mainPanel.add(emailField);
		mainPanel.add(numberField);

		mainPanel.add(updateButton);

		container.repaint();
		validate();
	}

	private void setMarksPanel() {
		mainPanel.removeAll();
		con = new Connect();

		JLabel topic = new JLabel("Set Marks");
		JLabel coursesLabel = new JLabel("Course:", SwingConstants.RIGHT);
		JLabel studentName = new JLabel("Student Name:", SwingConstants.RIGHT);
		JLabel marks = new JLabel("Marks:", SwingConstants.RIGHT);
		JLabel remarks = new JLabel("Remarks: ", SwingConstants.RIGHT);

		JLabel coursesField = new JLabel(teacher.getCourse());
		JComboBox<String> studentNameField = new JComboBox<String>();
		JTextField marksField = new JTextField();
		JLabel remarksField = new JLabel();

		JComboBox<String> currentModules;
		JLabel module = new JLabel("Modules:", SwingConstants.RIGHT);

		JButton update = new JButton("Update");

		String[] modules = { teacher.getModule1(), teacher.getModule2(), teacher.getModule3(), teacher.getModule4() };

		topic.setFont(new Font("Calibri", Font.BOLD, 22));
		topic.setBounds(385, 20, 170, 40);
		topic.setForeground(new Color(214, 214, 214));

		coursesLabel.setFont(myFont);
		coursesLabel.setBounds(70, 80, 80, 30);
		coursesLabel.setForeground(new Color(214, 214, 214));
		
		module.setFont(myFont);
		module.setBounds(70, 160, 80, 30);
		module.setForeground(new Color(214, 214, 214));
		
		studentName.setFont(myFont);
		studentName.setBounds(0, 240, 150, 30);
		studentName.setForeground(new Color(214, 214, 214));
		
		marks.setFont(myFont);
		marks.setBounds(70, 320, 80, 30);
		marks.setForeground(new Color(214, 214, 214));
		
		remarks.setFont(myFont);
		remarks.setBounds(70, 400, 80, 30);
		remarks.setForeground(new Color(214, 214, 214));
		
		coursesField.setFont(myFont);
		coursesField.setBounds(200, 80, 80, 30);
		coursesField.setForeground(new Color(214, 214, 214));
		
		currentModules = new JComboBox<String>(modules);
		currentModules.setFont(myFont1);
		currentModules.setBounds(200, 160, 350, 30);
		currentModules.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				studentNameField.removeAllItems();

				selectedModule = (String) currentModules.getSelectedItem();

				String level = "";

				String check = String.format("SELECT level FROM modules where module_name = '%s'", selectedModule);

				try {
					ResultSet rs1 = con.st.executeQuery(check);
					while (rs1.next()) {
						level = rs1.getString(1);
					}
				} catch (Exception er) {
					er.printStackTrace();
				}

				String query1 = String.format(
						"SELECT * FROM level4and5 where module1 = '%s' OR module2 = '%s' OR module3 = '%s' OR module4 = '%s'",
						selectedModule, selectedModule, selectedModule, selectedModule);

				String query2 = String.format("SELECT * FROM level6 where module1 = '%s' OR module2 = '%s'",
						selectedModule, selectedModule);

				if (level.equals("4") || level.equals("5")) {

					try {
						ResultSet rs2 = con.st.executeQuery(query1);

						while (rs2.next()) {
							studentNameField.addItem(rs2.getString("name"));
						}

					} catch (SQLException er) {
						er.printStackTrace();
					}

				}

				else if (level.equals("6")) {
					try {
						ResultSet rs3 = con.st.executeQuery(query2);

						while (rs3.next()) {
							studentNameField.addItem(rs3.getString("name"));
						}
					} catch (SQLException er) {
						er.printStackTrace();
					}

				}
			}

		});

		studentNameField.setFont(myFont1);
		studentNameField.setBounds(200, 240, 350, 30);
		studentNameField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connect conn = new Connect();

				selectedModule = (String) currentModules.getSelectedItem();
				selectedName = (String) studentNameField.getSelectedItem();

				String level = "";
				String mark = "";
				String remark = "";

				String check = String.format("SELECT level FROM modules where module_name = '%s'", selectedModule);

				String query1 = String.format("SELECT * FROM level4and5 WHERE name = '%s'", selectedName);

				String query2 = String.format("SELECT * FROM level6 WHERE name = '%s'", selectedName);

				try {
					ResultSet rs1 = conn.st.executeQuery(check);
					while (rs1.next()) {
						level = rs1.getString(1);
					}
				} catch (Exception er) {
					er.printStackTrace();
				}

				if (level.equals("4") || level.equals("5")) {
					try {
						ResultSet rs2 = conn.st.executeQuery(query1);
						while (rs2.next()) {
							if (rs2.getString("module1").equals(selectedModule)) {
								mark = rs2.getString("marks1");
								remark = rs2.getString("remarks1");
							} else if (rs2.getString("module2").equals(selectedModule)) {
								mark = rs2.getString("marks2");
								remark = rs2.getString("remarks2");
							} else if (rs2.getString("module3").equals(selectedModule)) {
								mark = rs2.getString("marks3");
								remark = rs2.getString("remarks3");
							} else if (rs2.getString("module4").equals(selectedModule)) {
								mark = rs2.getString("marks4");
								remark = rs2.getString("remarks4");
							} else {
								marksField.setText("");
								remarksField.setText("");
							}

						}
					} catch (SQLException er) {
						er.printStackTrace();
					}

				} else if (level.equals("6")) {

					try {
						ResultSet rs3 = conn.st.executeQuery(query1);
						while (rs3.next()) {
							if (rs3.getString("module1").equals(selectedModule)) {
								mark = rs3.getString("marks1");
								remark = rs3.getString("remarks1");
							} else if (rs3.getString("module2").equals(selectedModule)) {
								mark = rs3.getString("marks2");
								remark = rs3.getString("remarks2");
							} else {
								marksField.setText("");
								remarksField.setText("");
							}
						}

					} catch (SQLException er) {
						er.printStackTrace();
					}
				}
				marksField.setText(mark);
				remarksField.setText(remark);
			}
		});

		marksField.setFont(myFont1);
		marksField.setBounds(200, 320, 350, 30);
		marksField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});

		remarksField.setFont(myFont);
		remarksField.setBounds(200, 400, 80, 30);
		remarksField.setForeground(new Color(214, 214, 214));

		update.setFont(myFont1);
		update.setBounds(390, 450, 80, 30);
		update.setFocusable(false);
		update.setBackground(new Color(141, 91, 233));
		update.setCursor(new Cursor(Cursor.HAND_CURSOR));
		update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connect con = new Connect();

				selectedModule = (String) currentModules.getSelectedItem();
				selectedName = (String) studentNameField.getSelectedItem();

				String level = "";
				String marksText = marksField.getText();
				int marksInt = Integer.parseInt(marksText);

				if (marksInt > 100) {
					JOptionPane.showMessageDialog(null, "Marks should not be greater than 100!");
				} else {
					String check = String.format("SELECT level FROM modules where module_name = '%s'", selectedModule);

					try {
						ResultSet rs1 = con.st.executeQuery(check);
						while (rs1.next()) {
							level = rs1.getString(1);
						}
					} catch (Exception er) {
						er.printStackTrace();
					}

					String query1 = String.format("SELECT * FROM level4and5 WHERE name = '%s'", selectedName);

					String query2 = String.format("SELECT * FROM level6 WHERE name = '%s'", selectedName);

					String query = "";
					if (level.equals("4") || level.equals("5")) {
						try {

							ResultSet rs2 = con.st.executeQuery(query1);
							while (rs2.next()) {

								if (rs2.getString("module1").equals(selectedModule)) {
									if (marksInt >= 40) {
										query = String.format(
												"UPDATE level4and5 SET marks1 = '%s', remarks1 = 'PASSED' WHERE name = '%s'",
												marksText, selectedName);
									} else {
										query = String.format(
												"UPDATE level4and5 SET marks1 = '%s', remarks1 = 'FAILED' WHERE name = '%s'",
												marksText, selectedName);
									}
								} else if (rs2.getString("module2").equals(selectedModule)) {
									if (marksInt >= 40) {
										query = String.format(
												"UPDATE level4and5 SET marks2 = '%s', remarks2 = 'PASSED' WHERE name = '%s'",
												marksText, selectedName);
									} else {
										query = String.format(
												"UPDATE level4and5 SET marks2 = '%s', remarks2 = 'FAILED' WHERE name = '%s'",
												marksText, selectedName);
									}
								} else if (rs2.getString("module3").equals(selectedModule)) {
									if (marksInt >= 40) {
										query = String.format(
												"UPDATE level4and5 SET marks3 = '%s', remarks3 = 'PASSED' WHERE name = '%s'",
												marksText, selectedName);
									} else {
										query = String.format(
												"UPDATE level4and5 SET marks3 = '%s', remarks3 = 'FAILED' WHERE name = '%s'",
												marksText, selectedName);
									}
								} else if (rs2.getString("module4").equals(selectedModule)) {
									if (marksInt >= 40) {
										query = String.format(
												"UPDATE level4and5 SET marks4 = '%s', remarks4 = 'PASSED' WHERE name = '%s'",
												marksText, selectedName);
									} else {
										query = String.format(
												"UPDATE level4and5 SET marks4 = '%s', remarks4 = 'FAILED' WHERE name = '%s'",
												marksText, selectedName);
									}
								}
							}
						} catch (SQLException er) {
							er.printStackTrace();
						}

					} else if (level.equals("6")) {

						try {
							ResultSet rs3 = con.st.executeQuery(query2);
							while (rs3.next()) {
								if (rs3.getString("module1").equals(selectedModule)) {
									if (marksInt >= 40) {
										query = String.format(
												"UPDATE level6 SET marks1 = '%s', remarks1 = 'PASSED' WHERE name = '%s'",
												marksText, selectedName);
									} else {
										query = String.format(
												"UPDATE level6 SET marks1 = '%s', remarks1 = 'FAILED' WHERE name = '%s'",
												marksText, selectedName);
									}
								} else if (rs3.getString("module2").equals(selectedModule)) {
									if (marksInt >= 40) {
										query = String.format(
												"UPDATE level6 SET marks2 = '%s', remarks2 = 'PASSED' WHERE name = '%s'",
												marksText, selectedName);
									} else {
										query = String.format(
												"UPDATE level6 SET marks2 = '%s', remarks2 = 'FAILED' WHERE name = '%s'",
												marksText, selectedName);
									}
								}
							}
						} catch (SQLException er) {
							er.printStackTrace();
						}
					}
					try {
						con.st.executeUpdate(query);
						JOptionPane.showMessageDialog(null, "Marks Set!");
					} catch (SQLException er) {
						er.printStackTrace();
					}
				}
			}

		});

		mainPanel.add(topic);
		mainPanel.add(coursesLabel);
		mainPanel.add(coursesField);
		mainPanel.add(module);
		mainPanel.add(currentModules);
		mainPanel.add(studentName);
		mainPanel.add(studentNameField);
		mainPanel.add(marks);
		mainPanel.add(marksField);
		mainPanel.add(remarks);
		mainPanel.add(remarksField);
		mainPanel.add(update);
		container.repaint();
		validate();
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == logout) {
			JPanel panel = new JPanel();
			panel.add(new JLabel("Are you sure you want to logout?"));
			Object[] options = { "Yes", "No" };

			int result = JOptionPane.showOptionDialog(null, panel, "Logout", JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options, null);

			if (result == JOptionPane.YES_OPTION) {
				this.dispose();
				new Login();
			}
		}
	}
}
