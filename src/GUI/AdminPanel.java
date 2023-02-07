package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Container;
import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import DatabaseConnect.Connect;
import Users.Student;

public class AdminPanel extends JFrame implements ActionListener {
	private JButton registerTeacher;
	private JButton moduleAssign;
	private JButton showResult;
	private JButton addCourses;
	private JButton editModules;
	private JButton cancelButton;
	private JButton logout;

	private JPanel mainPanel;

	private Font myFont = new Font("Calibri", Font.BOLD, 17);
	private Font myFont1 = new Font("Calibri", Font.PLAIN, 15);

	private Container container;

	private Connect con;

	private ArrayList<String> list;
	private JComboBox<String> coursesBox;

	AdminPanel(String username) {
		container = this.getContentPane();

		container.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		mainPanel = new JPanel();

		JLabel welcome = new JLabel("Welcome, Admin!");
		welcome.setFont(myFont);
		welcome.setBounds(15, 15, 150, 50);
		welcome.setForeground(new Color(214, 214, 214));

		registerTeacher = new JButton("Register Teacher");
		registerTeacher.setPreferredSize(new Dimension(175, 60));
		registerTeacher.setFocusable(false);
		registerTeacher.setBackground(new Color(141, 91, 233));
		registerTeacher.setCursor(new Cursor(Cursor.HAND_CURSOR));
		registerTeacher.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				registerTeacherPanel();
			}

		});

		moduleAssign = new JButton("Assign/Remove Modules");
		moduleAssign.setPreferredSize(new Dimension(175, 60));
		moduleAssign.setFocusable(false);
		moduleAssign.setBackground(new Color(141, 91, 233));
		moduleAssign.setCursor(new Cursor(Cursor.HAND_CURSOR));
		moduleAssign.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				assignModulesPanel();
			}

		});

		showResult = new JButton("Show Results");
		showResult.setPreferredSize(new Dimension(175, 60));
		showResult.setFocusable(false);
		showResult.setBackground(new Color(141, 91, 233));
		showResult.setCursor(new Cursor(Cursor.HAND_CURSOR));
		showResult.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showResultsPanel();
			}

		});

		addCourses = new JButton("Add/Delete Courses");
		addCourses.setPreferredSize(new Dimension(175, 60));
		addCourses.setFocusable(false);
		addCourses.setBackground(new Color(141, 91, 233));
		addCourses.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addCourses.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addCoursesPanel();
			}

		});

		editModules = new JButton("Add/Edit Modules");
		editModules.setPreferredSize(new Dimension(175, 60));
		editModules.setFocusable(false);
		editModules.setBackground(new Color(141, 91, 233));
		editModules.setCursor(new Cursor(Cursor.HAND_CURSOR));
		editModules.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				editModulesPanel();
			}

		});

		cancelButton = new JButton("Cancel Courses");
		cancelButton.setPreferredSize(new Dimension(175, 60));
		cancelButton.setFocusable(false);
		cancelButton.setBackground(new Color(141, 91, 233));
		cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				cancelCoursesPanel();
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
		leftPanel.add(registerTeacher);
		leftPanel.add(moduleAssign);
		leftPanel.add(showResult);
		leftPanel.add(addCourses);
		leftPanel.add(editModules);
		leftPanel.add(cancelButton);

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

	private void registerTeacherPanel() {
		mainPanel.removeAll();

		String[] courses = getCourses().toArray(new String[getCourses().size()]);
		System.out.println(courses);
		coursesBox = new JComboBox<String>(courses);
		coursesBox.addActionListener(this);

		JLabel topic = new JLabel("REGISTER TEACHER");
		JLabel firstName = new JLabel("First Name:", SwingConstants.RIGHT);
		JLabel lastName = new JLabel("Last Name:", SwingConstants.RIGHT);
		JLabel username = new JLabel("Username:", SwingConstants.RIGHT);
		JLabel password = new JLabel("Password:", SwingConstants.RIGHT);
		JLabel confirmPassword = new JLabel("Confirm Password:", SwingConstants.RIGHT);
		JLabel email = new JLabel("Email:", SwingConstants.RIGHT);
		JLabel number = new JLabel("Number:", SwingConstants.RIGHT);
		JLabel course = new JLabel("Course:", SwingConstants.RIGHT);

		JTextField firstNameField = new JTextField();
		JTextField lastNameField = new JTextField();
		JTextField usernameField = new JTextField();
		JPasswordField passwordField = new JPasswordField();
		JPasswordField confirmPasswordField = new JPasswordField();
		JTextField emailField = new JTextField();
		JTextField numberField = new JTextField();
		JComboBox<String> coursesBox = new JComboBox<String>(courses);

		JButton registerButton = new JButton("Register");

		topic.setBounds(300, 25, 255, 30);
		topic.setFont(new Font("Calibri", Font.BOLD, 30));
		topic.setForeground(new Color(214, 214, 214));

		firstName.setFont(myFont);
		firstName.setBounds(90, 80, 80, 30);
		firstName.setForeground(new Color(214, 214, 214));

		lastName.setFont(myFont);
		lastName.setBounds(500, 80, 80, 30);
		lastName.setForeground(new Color(214, 214, 214));

		username.setFont(myFont);
		username.setBounds(90, 160, 80, 30);
		username.setForeground(new Color(214, 214, 214));

		password.setFont(myFont);
		password.setBounds(500, 160, 80, 30);
		password.setForeground(new Color(214, 214, 214));

		confirmPassword.setFont(myFont);
		confirmPassword.setBounds(20, 240, 150, 30);
		confirmPassword.setForeground(new Color(214, 214, 214));

		email.setFont(myFont);
		email.setBounds(500, 240, 80, 30);
		email.setForeground(new Color(214, 214, 214));

		number.setFont(myFont);
		number.setBounds(90, 320, 80, 30);
		number.setForeground(new Color(214, 214, 214));

		course.setFont(myFont);
		course.setBounds(500, 320, 80, 30);
		course.setForeground(new Color(214, 214, 214));

		firstNameField.setFont(myFont1);
		firstNameField.setBounds(190, 80, 200, 30);

		lastNameField.setFont(myFont1);
		lastNameField.setBounds(600, 80, 200, 30);

		usernameField.setFont(myFont1);
		usernameField.setBounds(190, 160, 200, 30);

		passwordField.setFont(myFont1);
		passwordField.setBounds(600, 160, 200, 30);

		confirmPasswordField.setFont(myFont1);
		confirmPasswordField.setBounds(190, 240, 200, 30);

		emailField.setFont(myFont1);
		emailField.setBounds(600, 240, 200, 30);

		numberField.setFont(myFont1);
		numberField.setBounds(190, 320, 200, 30);
		numberField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!(Character.isDigit(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
					e.consume();
				}
			}
		});

		coursesBox.setFont(myFont1);
		coursesBox.setBounds(600, 320, 200, 30);

		registerButton.setFont(myFont);
		registerButton.setFocusable(false);
		registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		registerButton.setBackground(new Color(141, 91, 233));
		registerButton.setBounds(700, 500, 120, 30);
		registerButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String first_name = firstNameField.getText();
				String last_name = lastNameField.getText();
				String username = usernameField.getText();
				String email = emailField.getText();
				String number = numberField.getText();

				String password = String.valueOf(passwordField.getPassword());
				String confirmPassword = String.valueOf(confirmPasswordField.getPassword());
				if (e.getSource() == registerButton) {

					try {
						Connect conn = new Connect();

						String query = "SELECT * FROM teacherinfo WHERE username = '" + username + "'";
						ResultSet rs = conn.st.executeQuery(query);

						if (first_name.isEmpty() || last_name.isEmpty() || password.isEmpty() || username.isEmpty()
								|| number.isEmpty() || email.isEmpty()) {
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
										"INSERT INTO teacherinfo(first_name, last_name, username, password, email, number, course, module1, module2, module3, module4) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', 'TBD', 'TBD', 'TBD', 'TBD')",
										first_name, last_name, username, password, email, number,
										coursesBox.getSelectedItem());
								conn.st.executeUpdate(query);

								JOptionPane.showMessageDialog(null, "Successfully Registered!");

							} catch (Exception er) {
								er.printStackTrace();
							}
						}
					} catch (SQLException er) {
						er.printStackTrace();
					}

				}

			}

		});

		mainPanel.add(topic);
		mainPanel.add(firstName);
		mainPanel.add(lastName);
		mainPanel.add(username);
		mainPanel.add(password);
		mainPanel.add(confirmPassword);
		mainPanel.add(email);
		mainPanel.add(number);
		mainPanel.add(course);

		mainPanel.add(firstNameField);
		mainPanel.add(lastNameField);
		mainPanel.add(usernameField);
		mainPanel.add(passwordField);
		mainPanel.add(confirmPasswordField);
		mainPanel.add(emailField);
		mainPanel.add(numberField);
		mainPanel.add(coursesBox);

		mainPanel.add(registerButton);

		container.repaint();
		validate();
	}

	private void assignModulesPanel() {
		mainPanel.removeAll();
		con = new Connect();

		JLabel topic = new JLabel("Assign Modules");
		JLabel teacherNameLabel = new JLabel("Teacher's Name:", SwingConstants.RIGHT);
		JLabel levelLabel = new JLabel("Level:", SwingConstants.RIGHT);
		JLabel moduleNameLabel = new JLabel("Module:", SwingConstants.RIGHT);

		JButton assign = new JButton("Assign");

		ArrayList<String> teacherName = new ArrayList<String>();

		String query1 = String.format("SELECT first_name, last_name FROM teacherinfo");

		try {
			ResultSet rs1 = con.st.executeQuery(query1);

			while (rs1.next()) {
				teacherName.add(rs1.getString(1) + " " + rs1.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String[] teacherNameArray = teacherName.toArray(new String[teacherName.size()]);
		String[] level = { "Level 4", "Level 5", "Level 6" };

		JComboBox<String> teacherNameField = new JComboBox<String>(teacherNameArray);
		JComboBox<String> levelField = new JComboBox<String>(level);
		JComboBox<String> moduleField = new JComboBox<String>();

		topic.setFont(new Font("Calibri", Font.BOLD, 28));
		topic.setBounds(355, 20, 300, 40);
		topic.setForeground(new Color(214, 214, 214));

		teacherNameLabel.setFont(myFont);
		teacherNameLabel.setBounds(30, 70, 130, 30);
		teacherNameLabel.setForeground(new Color(214, 214, 214));

		teacherNameField.setFont(myFont1);
		teacherNameField.setBounds(190, 70, 340, 30);

		levelLabel.setFont(myFont);
		levelLabel.setBounds(80, 130, 80, 30);
		levelLabel.setForeground(new Color(214, 214, 214));

		levelField.setFont(myFont1);
		levelField.setBounds(190, 130, 340, 30);
		levelField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				moduleField.removeAllItems();

				String selectedTeacher = (String) teacherNameField.getSelectedItem();
				String firstName = selectedTeacher.split(" ")[0];
				String selectedLevel = ((String) levelField.getSelectedItem()).split(" ")[1];

				String query = String.format("SELECT * FROM teacherinfo WHERE first_name = '%s'", firstName);
				String course = "";
				try {
					ResultSet rs = con.st.executeQuery(query);

					while (rs.next()) {
						course = (rs.getString("course"));
					}

				} catch (Exception er) {
					er.printStackTrace();
				}

				query = String.format("SELECT module_name FROM modules where course = '%s' AND level='%s'", course,
						selectedLevel);

				try {
					ResultSet rs2 = con.st.executeQuery(query);

					while (rs2.next()) {
						moduleField.addItem(rs2.getString(1));
					}
				} catch (SQLException er) {
					er.printStackTrace();
				}

			}

		});

		moduleNameLabel.setFont(myFont);
		moduleNameLabel.setBounds(80, 190, 80, 30);
		moduleNameLabel.setForeground(new Color(214, 214, 214));

		moduleField.setFont(myFont1);
		moduleField.setBounds(190, 190, 340, 30);

		assign.setFont(myFont1);
		assign.setBounds(390, 240, 80, 30);
		assign.setFocusable(false);
		assign.setBackground(new Color(141, 91, 233));
		assign.setCursor(new Cursor(Cursor.HAND_CURSOR));
		assign.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connect con = new Connect();

				String selectedTeacher = (String) teacherNameField.getSelectedItem();
				String firstName = selectedTeacher.split(" ")[0];
				String selectedModule = (String) moduleField.getSelectedItem();
				String check = String.format("SELECT * FROM teacherinfo WHERE first_name = '%s'", firstName);

				try {
					ResultSet rs = con.st.executeQuery(check);

					if (rs.next()) {
						if (rs.getString("module1").equals("TBD")) {
							if (!selectedModule.equals(rs.getString("module1"))
									&& !selectedModule.equals(rs.getString("module2"))
									&& !selectedModule.equals(rs.getString("module3"))
									&& !selectedModule.equals(rs.getString("module4"))) {
								String query = String.format(
										"UPDATE teacherinfo SET module1='%s' WHERE first_name='%s'", selectedModule,
										firstName);
								con.st.executeUpdate(query);
								JOptionPane.showMessageDialog(null, "Registered!");
							} else {
								JOptionPane.showMessageDialog(null, "Module already registered");
							}
						} else if (rs.getString("module2").equals("TBD")) {
							if (!selectedModule.equals(rs.getString("module1"))
									&& !selectedModule.equals(rs.getString("module2"))
									&& !selectedModule.equals(rs.getString("module3"))
									&& !selectedModule.equals(rs.getString("module4"))) {
								String query = String.format(
										"UPDATE teacherinfo SET module2='%s' WHERE first_name='%s'", selectedModule,
										firstName);
								con.st.executeUpdate(query);
								JOptionPane.showMessageDialog(null, "Registered!");
							} else {
								JOptionPane.showMessageDialog(null, "Module already registered");
							}
						} else if (rs.getString("module3").equals("TBD")) {
							if (!selectedModule.equals(rs.getString("module1"))
									&& !selectedModule.equals(rs.getString("module2"))
									&& !selectedModule.equals(rs.getString("module3"))
									&& !selectedModule.equals(rs.getString("module4"))) {
								String query = String.format(
										"UPDATE teacherinfo SET module3='%s' WHERE first_name='%s'", selectedModule,
										firstName);
								con.st.executeUpdate(query);
								JOptionPane.showMessageDialog(null, "Registered!");
							} else {
								JOptionPane.showMessageDialog(null, "Module already registered");
							}
						} else if (rs.getString("module4").equals("TBD")) {
							if (!selectedModule.equals(rs.getString("module1"))
									&& !selectedModule.equals(rs.getString("module2"))
									&& !selectedModule.equals(rs.getString("module3"))
									&& !selectedModule.equals(rs.getString("module4"))) {
								String query = String.format(
										"UPDATE teacherinfo SET module4='%s' WHERE first_name='%s'", selectedModule,
										firstName);
								con.st.executeUpdate(query);
								JOptionPane.showMessageDialog(null, "Registered!");
							} else {
								JOptionPane.showMessageDialog(null, "Module already registered");
							}
						} else {
							JOptionPane.showMessageDialog(null, "All modules are already registered.");
						}
					}
				} catch (Exception er) {
					er.printStackTrace();
				}
			}

		});

		JLabel removeLabel = new JLabel("Remove Modules", SwingConstants.RIGHT);
		JLabel teacherNameLabel2 = new JLabel("Teacher's Name:", SwingConstants.RIGHT);
		JLabel assignedModules = new JLabel("Assigned Modules:", SwingConstants.RIGHT);

		JComboBox<String> teacherNameField2 = new JComboBox<String>(teacherNameArray);

		JButton removeButton = new JButton("Remove");

		removeLabel.setFont(new Font("Calibri", Font.BOLD, 28));
		removeLabel.setBounds(268, 300, 280, 40);
		removeLabel.setForeground(new Color(214, 214, 214));

		teacherNameLabel2.setFont(myFont);
		teacherNameLabel2.setBounds(30, 360, 130, 30);
		teacherNameLabel2.setForeground(new Color(214, 214, 214));

		teacherNameField2.setFont(myFont1);
		teacherNameField2.setBounds(190, 360, 340, 30);

		JComboBox<String> assignedModulesField = new JComboBox<String>();
		teacherNameField2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				assignedModulesField.removeAllItems();
				String firstName = ((String) (teacherNameField2.getSelectedItem())).split(" ")[0];

				String query = String.format("SELECT * FROM teacherinfo WHERE first_name='%s'", firstName);

				try {
					ResultSet rs = con.st.executeQuery(query);

					while (rs.next()) {
						assignedModulesField.addItem(rs.getString("module1"));
						assignedModulesField.addItem(rs.getString("module2"));
						assignedModulesField.addItem(rs.getString("module3"));
						assignedModulesField.addItem(rs.getString("module4"));
					}
				} catch (SQLException er) {
					er.printStackTrace();
				}

			}

		});

		assignedModules.setFont(myFont);
		assignedModules.setBounds(30, 420, 130, 30);
		assignedModules.setForeground(new Color(214, 214, 214));

		assignedModulesField.setFont(myFont1);
		assignedModulesField.setBounds(190, 420, 340, 30);

		removeButton.setFont(myFont1);
		removeButton.setBounds(380, 480, 100, 30);
		removeButton.setFocusable(false);
		removeButton.setBackground(new Color(141, 91, 233));
		removeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connect conn = new Connect();
				String firstName = ((String) (teacherNameField2.getSelectedItem())).split(" ")[0];
				String selectedModule = (String) assignedModulesField.getSelectedItem();

				String query = String.format("SELECT * from teacherinfo WHERE first_name='%s'", firstName);

				try {
					ResultSet rs = conn.st.executeQuery(query);

					while (rs.next()) {
						if (rs.getString("module1").equals(selectedModule)) {
							String update = String.format("UPDATE teacherinfo SET module1='TBD' WHERE first_name='%s'",
									firstName);
							con.st.executeUpdate(update);
							JOptionPane.showMessageDialog(null, "Teacher removed successfully.");
						} else if (rs.getString("module2").equals(selectedModule)) {
							String update = String.format("UPDATE teacherinfo SET module2='TBD' WHERE first_name='%s'",
									firstName);
							con.st.executeUpdate(update);
							JOptionPane.showMessageDialog(null, "Teacher removed successfully.");
						} else if (rs.getString("module3").equals(selectedModule)) {
							String update = String.format("UPDATE teacherinfo SET module3='TBD' WHERE first_name='%s'",
									firstName);
							con.st.executeUpdate(update);
							JOptionPane.showMessageDialog(null, "Teacher removed successfully.");
						} else if (rs.getString("module4").equals(selectedModule)) {
							String update = String.format("UPDATE teacherinfo SET module4='TBD' WHERE first_name='%s'",
									firstName);
							con.st.executeUpdate(update);
							JOptionPane.showMessageDialog(null, "Teacher removed successfully.");
						} else {
							JOptionPane.showMessageDialog(null, "There seems to be an error.");
						}
					}
				} catch (SQLException er) {
					er.printStackTrace();
				}
			}

		});
		mainPanel.add(topic);
		mainPanel.add(teacherNameLabel);
		mainPanel.add(teacherNameField);
		mainPanel.add(levelLabel);
		mainPanel.add(levelField);
		mainPanel.add(moduleNameLabel);
		mainPanel.add(moduleField);
		mainPanel.add(assign);
		mainPanel.add(removeLabel);
		mainPanel.add(teacherNameLabel2);
		mainPanel.add(teacherNameField2);
		mainPanel.add(assignedModules);
		mainPanel.add(assignedModulesField);
		mainPanel.add(removeButton);

		container.repaint();
		validate();
	}

	private void showResultsPanel() {
		mainPanel.removeAll();
		Connect conn = new Connect();
		Student student = new Student();

		ArrayList<String> studentUsername = new ArrayList<String>();
		ArrayList<String> studentName = new ArrayList<String>();

		String query = "SELECT * FROM studentinfo";

		try {
			ResultSet rs = conn.st.executeQuery(query);

			while (rs.next()) {
				studentUsername.add(rs.getString("username"));
				studentName.add(rs.getString("first_name") + " " + rs.getString("last_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String[] studentUsernameArray = studentUsername.toArray(new String[studentUsername.size()]);
		String[] studentNameArray = studentName.toArray(new String[studentName.size()]);

		JLabel studentNameLabel = new JLabel("Student's Name:", SwingConstants.RIGHT);
		JComboBox<String> studentNameField = new JComboBox<String>(studentNameArray);

		studentNameLabel.setFont(myFont);
		studentNameLabel.setBounds(30, 30, 130, 30);
		studentNameLabel.setForeground(new Color(214, 214, 214));

		studentNameField.setFont(myFont1);
		studentNameField.setBounds(190, 30, 290, 30);
		studentNameField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Component[] components = mainPanel.getComponents();

				for (Component component : components) {
					if (component != studentNameLabel && component != studentNameField) {
						mainPanel.remove(component);
					}
				}
				int currentStudent = studentNameField.getSelectedIndex();

				student.setStudentInfo(studentUsernameArray[currentStudent]);

				String[] modules = student.getModules();
				String[] marks = student.getMarks();
				String[] remarks = student.getRemarks();

				String query = "";
				ArrayList<String> moduleCode = new ArrayList<String>();

				try {
					if (student.getLevel().equals("4") || student.getLevel().equals("5")) {
						query = String.format(
								"SELECT module_code FROM modules where module_name = '%s' OR module_name = '%s' OR module_name = '%s' OR module_name = '%s'",
								modules[0], modules[1], modules[2], modules[3]);
					} else if (student.getLevel().equals("6")) {
						query = String.format(
								"SELECT module_code FROM modules where module_name = '%s' OR module_name = '%s'",
								modules[0], modules[1]);
					}
					con = new Connect();
					ResultSet rs = con.st.executeQuery(query);

					while (rs.next()) {
						moduleCode.add(rs.getString("module_code"));
					}

				} catch (Exception er) {
					er.printStackTrace();
				}

				String[] moduleCodeArray = moduleCode.toArray(new String[moduleCode.size()]);

				String[] columns = { "Module Code", "Module Name", "Marks", "Remarks" };

				ArrayList<ArrayList<String>> tableData = new ArrayList<ArrayList<String>>();
				for (int i = 0; i < modules.length; i++) {
					ArrayList<String> rowsData = new ArrayList<String>(
							Arrays.asList(moduleCodeArray[i], modules[i], marks[i], remarks[i]));
					tableData.add(rowsData);
				}

				String[][] rows = new String[tableData.size()][];

				for (int i = 0; i < tableData.size(); i++) {
					ArrayList<String> row = tableData.get(i);
					rows[i] = row.toArray(new String[row.size()]);
				}

				JTable table = new JTable(rows, columns) {
					public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
						Component component = super.prepareRenderer(renderer, row, column);
						int rendererWidth = component.getPreferredSize().width;
						TableColumn tableColumn = getColumnModel().getColumn(column);
						tableColumn.setPreferredWidth(
								Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));

						return component;
					}
				};
				table.setEnabled(false);
				table.setFont(myFont1);
				JScrollPane scrollpane = new JScrollPane(table);

				scrollpane.setBounds(5, 90, 850, 360);
				table.setRowHeight(80);
				table.getTableHeader().setPreferredSize(new Dimension(50, 50));
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(JLabel.CENTER);

				for (int i = 0; i < columns.length; i++) {
					table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
				}
				table.setShowGrid(true);

				mainPanel.add(scrollpane);
				container.repaint();
				validate();

			}

		});

		mainPanel.add(studentNameLabel);
		mainPanel.add(studentNameField);

		container.repaint();
		validate();
	}

	private void addCoursesPanel() {
		mainPanel.removeAll();

		String[] courses = getCourses().toArray(new String[getCourses().size()]);

		JLabel topic1 = new JLabel("Add Course");
		JLabel topic2 = new JLabel("Delete Course");
		JLabel courseName1 = new JLabel("Course Name: ");
		JLabel courseName2 = new JLabel("Course Name: ");
		JTextField courseNameField = new JTextField();

		JButton updateButton = new JButton("Add");
		JButton deleteButton = new JButton("Delete");

		JComboBox<String> courseField = new JComboBox<String>(courses);

		topic1.setFont(new Font("Calibri", Font.BOLD, 28));
		topic1.setBounds(355, 20, 170, 40);
		topic1.setForeground(new Color(214, 214, 214));

		courseName1.setFont(myFont);
		courseName1.setBounds(30, 80, 130, 30);
		courseName1.setForeground(new Color(214, 214, 214));

		courseNameField.setFont(myFont1);
		courseNameField.setBounds(190, 80, 250, 30);

		updateButton.setFont(myFont1);
		updateButton.setBounds(360, 125, 80, 30);
		updateButton.setFocusable(false);
		updateButton.setBackground(new Color(141, 91, 233));
		updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Connect con = new Connect();
					String newCourse = courseNameField.getText();

					String check = String.format("SELECT * FROM courses where course_name='%s'", newCourse);
					ResultSet rs = con.st.executeQuery(check);
					if (newCourse.isEmpty()) {
						JOptionPane.showMessageDialog(null, "Course cannot be empty!");
					} else if (rs.next()) {
						JOptionPane.showMessageDialog(null, "The given course already exists!");
					} else {
						Connect conn = new Connect();
						String query = String.format("INSERT INTO courses (course_name) VALUES ('%s')", newCourse);
						try {
							conn.st.executeUpdate(query);
						} catch (SQLException er) {
							er.printStackTrace();
						}

						courseField.addItem(newCourse);
						JOptionPane.showMessageDialog(null, "Successfully Added!");
						JOptionPane.showMessageDialog(null, "Redirecting to add modules for the Course!");
						new AddModules();
					}
				} catch (SQLException er) {
					er.printStackTrace();
				}

				courseNameField.setText("");

			}

		});

		topic2.setFont(new Font("Calibri", Font.BOLD, 28));
		topic2.setBounds(333, 265, 170, 40);
		topic2.setForeground(new Color(214, 214, 214));

		courseName2.setFont(myFont);
		courseName2.setBounds(30, 320, 130, 30);
		courseName2.setForeground(new Color(214, 214, 214));

		courseField.setFont(myFont1);
		courseField.setBounds(190, 320, 250, 30);

		deleteButton.setFont(myFont1);
		deleteButton.setBounds(360, 365, 80, 30);
		deleteButton.setFocusable(false);
		deleteButton.setBackground(new Color(141, 91, 233));
		deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String courseToRemove = (String) courseField.getSelectedItem();
				Connect conn = new Connect();

				try {
					String query = String.format("DELETE FROM courses where course_name='%s'", courseToRemove);
					conn.st.executeUpdate(query);
				} catch (Exception er) {
					er.printStackTrace();
				}

				try {
					String query = String.format("DELETE FROM modules WHERE course='%s'",
							courseField.getSelectedItem());
					conn.st.executeUpdate(query);
				} catch (SQLException er) {
					er.printStackTrace();
				}

				try {
					String query = String.format(
							"UPDATE level4and5 SET course='NULL', level='NULL', semester='NULL', module1='NULL', marks1='NULL', remarks1='NULL', module2='NULL', marks2='NULL', remarks2='NULL', module3='NULL', marks3='NULL', remarks3='NULL', module4='NULL', marks4='NULL', remarks4='NULL' WHERE course='%s'",
							courseToRemove);
					conn.st.executeUpdate(query);
				} catch (SQLException er) {
					er.printStackTrace();
				}

				try {
					String query = String.format(
							"UPDATE level6 SET course='NULL', semester='NULL', module1='NULL', marks1='NULL', remarks1='NULL', module2='NULL', marks2='NULL', remarks2='NULL' WHERE course='%s'",
							courseToRemove);
					conn.st.executeUpdate(query);
				} catch (SQLException er) {
					er.printStackTrace();
				}

				try {
					String query = String.format(
							"UPDATE studentinfo SET course='NULL', level='NULL', semester='NULL' WHERE course='%s'",
							courseToRemove);
					conn.st.executeUpdate(query);
				} catch (SQLException er) {
					er.printStackTrace();
				}

				try {
					String query = String.format(
							"UPDATE teacherinfo SET course='NULL', module1='NULL', module2='NULL', module3='NULL', module4='NULL' WHERE course='%s'",
							courseToRemove);
					conn.st.executeUpdate(query);
				} catch (SQLException er) {
					er.printStackTrace();
				}

				JOptionPane.showMessageDialog(null, "Deleted Successfully!");

				courseField.removeAllItems();
				String[] updatedCourses = getCourses().toArray(new String[getCourses().size()]);

				for (int i = 0; i < updatedCourses.length; i++) {
					courseField.addItem(updatedCourses[i]);
				}
			}
		});

		mainPanel.add(topic1);
		mainPanel.add(topic2);
		mainPanel.add(updateButton);
		mainPanel.add(courseName1);
		mainPanel.add(courseNameField);
		mainPanel.add(courseName2);
		mainPanel.add(courseField);
		mainPanel.add(deleteButton);
		container.repaint();
		validate();
	}

	private void editModulesPanel() {
		mainPanel.removeAll();
		Connect con = new Connect();

		JLabel addLabel = new JLabel("Add Module:", SwingConstants.RIGHT);
		JButton addButton = new JButton("Add Module");

		addLabel.setFont(myFont);
		addLabel.setBounds(30, 30, 130, 30);
		addLabel.setForeground(new Color(214, 214, 214));

		addButton.setFont(myFont1);
		addButton.setBounds(190, 28, 130, 30);
		addButton.setFocusable(false);
		addButton.setBackground(new Color(141, 91, 233));
		addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new AddModules();
			}

		});

		JLabel topic = new JLabel("Edit Courses/Modules");
		JLabel courseLabel = new JLabel("Select Course:", SwingConstants.RIGHT);
		JLabel moduleLabel = new JLabel("Select Module:", SwingConstants.RIGHT);
		JLabel courseName = new JLabel("Course Name:", SwingConstants.RIGHT);
		JLabel moduleName = new JLabel("Module Name:", SwingConstants.RIGHT);
		JTextField courseNameField = new JTextField();
		JTextField moduleNameField = new JTextField();
		JButton updateButton = new JButton("Update All");
		JButton deleteButton = new JButton("Delete Module");

		String[] courses = getCourses().toArray(new String[getCourses().size()]);
		JComboBox<String> courseNameBox = new JComboBox<String>(courses);
		JComboBox<String> moduleNameBox = new JComboBox<String>();

		ArrayList<String> modulesArrayList = new ArrayList<String>();
		String query = String.format("SELECT module_name FROM modules WHERE course='%s'",
				courseNameBox.getSelectedItem());

		try {
			ResultSet rs = con.st.executeQuery(query);

			while (rs.next()) {
				modulesArrayList.add(rs.getString(1));
			}
		} catch (SQLException er) {
			er.printStackTrace();
		}

		String[] modules = modulesArrayList.toArray(new String[modulesArrayList.size()]);

		topic.setFont(new Font("Calibri", Font.BOLD, 26));
		topic.setBounds(325, 100, 320, 30);
		topic.setForeground(new Color(214, 214, 214));

		courseLabel.setFont(myFont);
		courseLabel.setBounds(30, 150, 130, 30);
		courseLabel.setForeground(new Color(214, 214, 214));

		moduleLabel.setFont(myFont);
		moduleLabel.setBounds(30, 210, 130, 30);
		moduleLabel.setForeground(new Color(214, 214, 214));

		courseNameBox.setFont(myFont1);
		courseNameBox.setBounds(190, 150, 340, 30);
		courseNameBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				moduleNameBox.removeAllItems();
				String courseText = (String) courseNameBox.getSelectedItem();
				courseNameField.setText(courseText);

				String query = String.format("SELECT module_name FROM modules WHERE course='%s'",
						courseNameBox.getSelectedItem());
				try {
					ResultSet rs = con.st.executeQuery(query);

					while (rs.next()) {
						moduleNameBox.addItem(rs.getString(1));
					}
				} catch (SQLException er) {
					er.printStackTrace();
				}
			}

		});

		moduleNameBox.setFont(myFont1);
		moduleNameBox.setBounds(190, 210, 340, 30);
		moduleNameBox.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String moduleText = (String) moduleNameBox.getSelectedItem();

				moduleNameField.setText(moduleText);
			}

		});

		courseName.setFont(myFont);
		courseName.setBounds(30, 270, 130, 30);
		courseName.setForeground(new Color(214, 214, 214));

		moduleName.setFont(myFont);
		moduleName.setBounds(30, 320, 130, 30);
		moduleName.setForeground(new Color(214, 214, 214));

		courseNameField.setFont(myFont1);
		courseNameField.setBounds(190, 270, 340, 30);

		moduleNameField.setFont(myFont1);
		moduleNameField.setBounds(190, 320, 340, 30);

		updateButton.setFont(myFont1);
		updateButton.setBounds(240, 400, 120, 30);
		updateButton.setFocusable(false);
		updateButton.setBackground(new Color(141, 91, 233));
		updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		updateButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connect conn = new Connect();

				try {
					String query = String.format("UPDATE courses SET course_name='%s' WHERE course_name='%s'",
							courseNameField.getText(), courseNameBox.getSelectedItem());
					conn.st.executeUpdate(query);
				} catch (SQLException er) {
					er.printStackTrace();
				}

				try {
					String query = String.format(
							"UPDATE modules SET course='%s', module_name='%s' WHERE course='%s' AND module_name='%s'",
							courseNameField.getText(), moduleNameField.getText(), courseNameBox.getSelectedItem(),
							moduleNameBox.getSelectedItem());
					conn.st.executeUpdate(query);
				} catch (SQLException er) {
					er.printStackTrace();
				}

				JOptionPane.showMessageDialog(null, "Updated Successfully!");

				moduleNameBox.removeAllItems();
				String courseText = (String) courseNameBox.getSelectedItem();
				courseNameField.setText(courseText);

				String query = String.format("SELECT module_name FROM modules WHERE course='%s'",
						courseNameBox.getSelectedItem());
				try {
					ResultSet rs = con.st.executeQuery(query);

					while (rs.next()) {
						moduleNameBox.addItem(rs.getString(1));
					}
				} catch (SQLException er) {
					er.printStackTrace();
				}

			}

		});

		deleteButton.setFont(myFont1);
		deleteButton.setBounds(380, 400, 150, 30);
		deleteButton.setFocusable(false);
		deleteButton.setBackground(new Color(141, 91, 233));
		deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		deleteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Connect conn = new Connect();
				String query = String.format("DELETE FROM modules WHERE module_name='%s'",
						moduleNameBox.getSelectedItem());
				try {
					conn.st.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Deleted Successfully!");
				} catch (SQLException er) {
					er.printStackTrace();
				}

				moduleNameBox.removeAllItems();
				String courseText = (String) courseNameBox.getSelectedItem();
				courseNameField.setText(courseText);

				query = String.format("SELECT module_name FROM modules WHERE course='%s'",
						courseNameBox.getSelectedItem());
				try {
					ResultSet rs = con.st.executeQuery(query);

					while (rs.next()) {
						moduleNameBox.addItem(rs.getString(1));
					}
				} catch (SQLException er) {
					er.printStackTrace();
				}
			}

		});

		mainPanel.add(addLabel);
		mainPanel.add(addButton);
		mainPanel.add(topic);
		mainPanel.add(courseLabel);
		mainPanel.add(moduleLabel);
		mainPanel.add(courseNameBox);
		mainPanel.add(moduleNameBox);
		mainPanel.add(courseName);
		mainPanel.add(moduleName);
		mainPanel.add(courseNameField);
		mainPanel.add(moduleNameField);
		mainPanel.add(updateButton);
		mainPanel.add(deleteButton);

		container.repaint();
		validate();
	}

	private void cancelCoursesPanel() {
		mainPanel.removeAll();
		Connect conn = new Connect();

		JLabel topic1 = new JLabel("Cancel Courses");
		JLabel cancel = new JLabel("Select Course to cancel:", SwingConstants.RIGHT);

		JLabel topic2 = new JLabel("Restore Courses");
		JLabel restore = new JLabel("Select Course to retrieve:", SwingConstants.RIGHT);

		String[] courses = getCourses().toArray(new String[getCourses().size()]);

		JComboBox<String> courseNameBox = new JComboBox<String>(courses);
		JComboBox<String> restoreBox = new JComboBox<String>();

		JButton cancelButton = new JButton("Cancel Course");

		topic1.setFont(new Font("Calibri", Font.BOLD, 28));
		topic1.setBounds(325, 30, 320, 30);
		topic1.setForeground(new Color(214, 214, 214));

		cancel.setFont(myFont);
		cancel.setBounds(30, 100, 190, 30);
		cancel.setForeground(new Color(214, 214, 214));

		courseNameBox.setFont(myFont1);
		courseNameBox.setBounds(260, 100, 290, 30);

		cancelButton.setFont(myFont1);
		cancelButton.setBounds(350, 165, 150, 30);
		cancelButton.setFocusable(false);
		cancelButton.setBackground(new Color(141, 91, 233));
		cancelButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedCourse = (String) courseNameBox.getSelectedItem();
				String query1 = String.format(
						"INSERT INTO cancelledcourses SELECT * FROM courses WHERE course_name='%s'", selectedCourse);
				String query2 = String.format("DELETE FROM courses WHERE course_name='%s'", selectedCourse);
				String query3 = String.format("INSERT INTO cancelledmodules SELECT * FROM modules WHERE course='%s'",
						selectedCourse);
				String query4 = String.format("DELETE FROM modules WHERE course='%s'", selectedCourse);

				try {
					conn.st.executeUpdate(query1);
					conn.st.executeUpdate(query2);
					conn.st.executeUpdate(query3);
					conn.st.executeUpdate(query4);
					JOptionPane.showMessageDialog(null, "Course Cancelled Successfully!");
				} catch (SQLException er) {
					er.printStackTrace();
				}

				courseNameBox.removeAllItems();
				String query = "SELECT course_name FROM courses";
				try {
					ResultSet rs = conn.st.executeQuery(query);

					while (rs.next()) {
						courseNameBox.addItem(rs.getString(1));
					}
				} catch (SQLException er) {
					er.printStackTrace();
				}

				restoreBox.removeAllItems();
				query = "SELECT DISTINCT course_name FROM cancelledcourses";
				try {
					ResultSet rs = conn.st.executeQuery(query);

					while (rs.next()) {
						restoreBox.addItem(rs.getString(1));
					}
				} catch (SQLException er) {
					er.printStackTrace();
				}
			}

		});

		String query = "SELECT DISTINCT course_name FROM cancelledcourses";
		try {
			ResultSet rs = conn.st.executeQuery(query);

			while (rs.next()) {
				restoreBox.addItem(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		JButton restoreButton = new JButton("Restore Course");

		topic2.setFont(new Font("Calibri", Font.BOLD, 28));
		topic2.setBounds(315, 270, 320, 30);
		topic2.setForeground(new Color(214, 214, 214));

		restore.setFont(myFont);
		restore.setBounds(30, 340, 190, 30);
		restore.setForeground(new Color(214, 214, 214));

		restoreBox.setFont(myFont1);
		restoreBox.setBounds(260, 340, 290, 30);

		restoreButton.setFont(myFont1);
		restoreButton.setBounds(352, 405, 150, 30);
		restoreButton.setFocusable(false);
		restoreButton.setBackground(new Color(141, 91, 233));
		restoreButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		restoreButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String selectedCourse = (String) restoreBox.getSelectedItem();
				String query1 = String.format(
						"INSERT INTO courses SELECT * FROM cancelledcourses WHERE course_name='%s'", selectedCourse);
				String query2 = String.format("DELETE FROM cancelledcourses WHERE course_name='%s'", selectedCourse);
				String query3 = String.format("INSERT INTO modules SELECT * FROM cancelledmodules WHERE course='%s'",
						selectedCourse);
				String query4 = String.format("DELETE FROM cancelledmodules WHERE course='%s'", selectedCourse);

				try {
					conn.st.executeUpdate(query1);
					conn.st.executeUpdate(query2);
					conn.st.executeUpdate(query3);
					conn.st.executeUpdate(query4);
					JOptionPane.showMessageDialog(null, "Course Restored Successfully!");
				} catch (SQLException er) {
					er.printStackTrace();
				}

				restoreBox.removeAllItems();
				String query = "SELECT DISTINCT course_name FROM cancelledcourses";
				try {
					ResultSet rs = conn.st.executeQuery(query);

					while (rs.next()) {
						restoreBox.addItem(rs.getString(1));
					}
				} catch (SQLException er) {
					er.printStackTrace();
				}

				courseNameBox.removeAllItems();
				query = "SELECT course_name FROM courses";
				try {
					ResultSet rs = conn.st.executeQuery(query);

					while (rs.next()) {
						courseNameBox.addItem(rs.getString(1));
					}
				} catch (SQLException er) {
					er.printStackTrace();
				}
			}

		});

		mainPanel.add(topic1);
		mainPanel.add(cancel);
		mainPanel.add(courseNameBox);
		mainPanel.add(cancelButton);
		mainPanel.add(topic2);
		mainPanel.add(restore);
		mainPanel.add(restoreBox);
		mainPanel.add(restoreButton);

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