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
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import DatabaseConnect.Connect;
import Users.Student;

public class StudentPanel extends JFrame implements ActionListener {
	private JButton myDetails;
	private JButton modules;
	private JButton showResult;
	private JButton teacherInfo;
	private JButton logout;

	private JPanel mainPanel;

	private Font myFont = new Font("Calibri", Font.BOLD, 17);
	private Font myFont1 = new Font("Calibri", Font.PLAIN, 15);

	private Container container;

	private Student student;
	private Connect con;

	StudentPanel(String username) {
		student = new Student();
		student.setStudentInfo(username);

		container = this.getContentPane();

		container.setLayout(new BorderLayout());

		JPanel topPanel = new JPanel();
		JPanel leftPanel = new JPanel();
		mainPanel = new JPanel();

		JLabel welcome = new JLabel(String.format("Welcome, %s!", student.getFirstName()));
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

		modules = new JButton("Enroll Modules");
		modules.setPreferredSize(new Dimension(175, 60));
		modules.setFocusable(false);
		modules.setBackground(new Color(141, 91, 233));
		modules.setCursor(new Cursor(Cursor.HAND_CURSOR));
		modules.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (student.getLevel().equals("6")) {
					showModulesPanel();
				} else {
					JOptionPane.showMessageDialog(null, "Only Level 6 students can enroll in additional modules.");
				}

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

		teacherInfo = new JButton("Teacher Info");
		teacherInfo.setPreferredSize(new Dimension(175, 60));
		teacherInfo.setFocusable(false);
		teacherInfo.setBackground(new Color(141, 91, 233));
		teacherInfo.setCursor(new Cursor(Cursor.HAND_CURSOR));
		teacherInfo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == teacherInfo) {
					showTeacherPanel();
				}

			}

		});

		logout = new JButton("Logout");
		logout.setFocusable(false);
		logout.setBackground(new Color(141, 91, 233));
		logout.setBounds(930, 21, 80, 30);
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
		leftPanel.add(modules);
		leftPanel.add(showResult);
		leftPanel.add(teacherInfo);

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
		JLabel course = new JLabel("Course:", SwingConstants.RIGHT);
		JLabel level = new JLabel("Level:", SwingConstants.RIGHT);
		JLabel semester = new JLabel("Semester:", SwingConstants.RIGHT);

		JLabel idField = new JLabel(String.format("%s", student.getId()));
		JTextField firstNameField = new JTextField(student.getFirstName());
		JTextField lastNameField = new JTextField(student.getLastName());
		JLabel usernameField = new JLabel(student.getUsername());
		JPasswordField passwordField = new JPasswordField(student.getPassword());
		JTextField emailField = new JTextField(student.getEmail());
		JTextField numberField = new JTextField(student.getNumber());
		JLabel courseField = new JLabel(student.getCourse());
		JLabel levelField = new JLabel(student.getLevel());
		JLabel semesterField = new JLabel(student.getSemester());

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
		
		course.setFont(myFont);
		course.setBounds(510, 300, 70, 30);
		course.setForeground(new Color(214, 214, 214));
		
		level.setFont(myFont);
		level.setBounds(120, 380, 50, 30);
		level.setForeground(new Color(214, 214, 214));
		
		semester.setFont(myFont);
		semester.setBounds(510, 380, 70, 30);
		semester.setForeground(new Color(214, 214, 214));
		
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

		courseField.setFont(myFont1);
		courseField.setBounds(600, 300, 105, 30);
		courseField.setForeground(new Color(214, 214, 214));

		levelField.setFont(myFont1);
		levelField.setBounds(190, 380, 105, 30);
		levelField.setForeground(new Color(214, 214, 214));

		semesterField.setFont(myFont1);
		semesterField.setBounds(600, 380, 105, 30);
		semesterField.setForeground(new Color(214, 214, 214));

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
									"UPDATE studentinfo SET first_name = '%s', last_name = '%s', password = '%s', email = '%s', number = '%s' WHERE username = '%s'",
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
		mainPanel.add(course);
		mainPanel.add(level);
		mainPanel.add(semester);

		mainPanel.add(firstNameField);
		mainPanel.add(lastNameField);
		mainPanel.add(idField);
		mainPanel.add(usernameField);
		mainPanel.add(passwordField);
		mainPanel.add(emailField);
		mainPanel.add(numberField);
		mainPanel.add(courseField);
		mainPanel.add(levelField);
		mainPanel.add(semesterField);

		mainPanel.add(updateButton);

		container.repaint();
		validate();
	}

	private void showModulesPanel() {
		mainPanel.removeAll();

		ArrayList<String> moduleName = new ArrayList<String>();
		ArrayList<String> moduleCode = new ArrayList<String>();
		ArrayList<String> course = new ArrayList<String>();

		try {
			con = new Connect();
			String query = String.format(
					"SELECT module_code, module_name, course FROM modules where level = '%s' AND semester = '%s' AND mandatory = 'No'",
					student.getLevel(), student.getSemester());
			ResultSet rs = con.st.executeQuery(query);

			while (rs.next()) {
				moduleCode.add(rs.getString(1));
				moduleName.add(rs.getString(2));
				course.add(rs.getString(3));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] modules = moduleName.toArray(new String[moduleName.size()]);
		JComboBox<String> moduleOptions = new JComboBox<String>(modules);

		JLabel moduleLabel = new JLabel("Module:", SwingConstants.RIGHT);
		JLabel moduleCodeLabel = new JLabel("Module Code:", SwingConstants.RIGHT);
		JLabel courseLabel = new JLabel("Course:", SwingConstants.RIGHT);

		JLabel moduleCodeField = new JLabel();
		JLabel courseField = new JLabel();

		moduleLabel.setBounds(90, 60, 80, 30);
		moduleLabel.setFont(myFont);
		moduleLabel.setForeground(new Color(214, 214, 214));

		moduleCodeLabel.setBounds(50, 130, 120, 30);
		moduleCodeLabel.setFont(myFont);
		moduleCodeLabel.setForeground(new Color(214, 214, 214));

		courseLabel.setBounds(90, 200, 80, 30);
		courseLabel.setFont(myFont);
		courseLabel.setForeground(new Color(214, 214, 214));

		moduleCodeField.setBounds(270, 130, 80, 30);
		moduleCodeField.setText(moduleCode.get(0));
		moduleCodeField.setFont(myFont);
		moduleCodeField.setForeground(new Color(214, 214, 214));

		courseField.setBounds(270, 200, 80, 30);
		courseField.setText(course.get(0));
		courseField.setFont(myFont);
		courseField.setForeground(new Color(214, 214, 214));

		moduleOptions.setBounds(270, 60, 280, 30);
		moduleOptions.setFont(myFont1);
		moduleOptions.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int index = moduleOptions.getSelectedIndex();
				moduleCodeField.setText(moduleCode.get(index));
				courseField.setText(course.get(index));
			}

		});

		JButton enroll = new JButton("Enroll");
		enroll.setFocusable(false);
		enroll.setCursor(new Cursor(Cursor.HAND_CURSOR));
		enroll.setBounds(400, 450, 90, 30);
		enroll.setBackground(new Color(141, 91, 233));
		enroll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connect conn = new Connect();
					String query = String.format("SELECT module2 FROM level6 where username = '%s'",
							student.getUsername());
					ResultSet rs = con.st.executeQuery(query);

					while (rs.next()) {
						if (rs.getString("module2").equals("TBD")) {
							String query1 = String.format("UPDATE level6 SET module2 = '%s' where username = '%s'",
									moduleOptions.getSelectedItem(), student.getUsername());
							try {
								conn.st.executeUpdate(query1);
								JOptionPane.showMessageDialog(null, "Successfully enrolled!");
							} catch (Exception er) {
								er.printStackTrace();
							}
						} else {
							if (rs.getString("module2").equals(moduleOptions.getSelectedItem())) {
								JOptionPane.showMessageDialog(null, "You have already enrolled in this module.");
							} else {
								JOptionPane.showMessageDialog(null,
										"You have already enrolled in another optional module.");
							}
						}
					}

				} catch (Exception er) {
					er.printStackTrace();
				}

			}

		});

		mainPanel.add(moduleLabel);
		mainPanel.add(moduleCodeLabel);
		mainPanel.add(courseLabel);
		mainPanel.add(moduleOptions);
		mainPanel.add(moduleCodeField);
		mainPanel.add(courseField);
		mainPanel.add(enroll);

		container.repaint();
		validate();
	}

	private void showResultsPanel() {
		mainPanel.removeAll();

		String query = "";

		String[] modules = student.getModules();
		String[] marks = student.getMarks();
		String[] remarks = student.getRemarks();

		ArrayList<String> moduleCode = new ArrayList<String>();

		try {
			if (student.getLevel().equals("4") || student.getLevel().equals("5")) {
				query = String.format(
						"SELECT module_code FROM modules where module_name = '%s' OR module_name = '%s' OR module_name = '%s' OR module_name = '%s'",
						modules[0], modules[1], modules[2], modules[3]);
			} else if (student.getLevel().equals("6")) {
				query = String.format("SELECT module_code FROM modules where module_name = '%s' OR module_name = '%s'",
						modules[0], modules[1]);
			}
			con = new Connect();
			ResultSet rs = con.st.executeQuery(query);

			while (rs.next()) {
				moduleCode.add(rs.getString("module_code"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		String[] moduleCodeArray = moduleCode.toArray(new String[moduleCode.size()]);

		String[] columns = { "Module Code", "Module Name", "Marks", "Remarks" };

		ArrayList<ArrayList<String>> tableData = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < moduleCodeArray.length; i++) {
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
		           tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
		           return component;
		        }
		};
		table.setEnabled(false);
		table.setFont(myFont1);
		JScrollPane scrollpane = new JScrollPane(table);

		scrollpane.setBounds(5, 10, 850, 373);
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

	private void showTeacherPanel() {
		mainPanel.removeAll();
		String query = "";

		String[] modules = student.getModules();

		ArrayList<String> moduleCode = new ArrayList<String>();
		ArrayList<String> teacherName = new ArrayList<String>();
		ArrayList<String> teacherEmail = new ArrayList<String>();
		ArrayList<String> teacherNumber = new ArrayList<String>();

		try {
			if (student.getLevel().equals("4") || student.getLevel().equals("5")) {
				query = String.format(
						"SELECT module_code FROM modules where module_name = '%s' OR module_name = '%s' OR module_name = '%s' OR module_name = '%s'",
						modules[0], modules[1], modules[2], modules[3]);
			} else if (student.getLevel().equals("6")) {
				query = String.format("SELECT module_code FROM modules where module_name = '%s' OR module_name = '%s'",
						modules[0], modules[1]);
			}
			con = new Connect();
			ResultSet rs = con.st.executeQuery(query);

			while (rs.next()) {
				moduleCode.add(rs.getString(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		String[] moduleCodeArray = moduleCode.toArray(new String[moduleCode.size()]);

		try {
			for (int i = 0; i < modules.length; i++) {
				if (student.getLevel().equals("4") || student.getLevel().equals("5")) {
					query = String.format(
							"SELECT first_name, last_name, email, number FROM teacherinfo WHERE module%s='%s' OR module%s='%s' OR module%s='%s' OR module%s='%s'",
							Integer.toString(i + 1), modules[0], Integer.toString(i + 1), modules[1],
							Integer.toString(i + 1), modules[2], Integer.toString(i + 1), modules[3]);
				} else {
					query = String.format(
							"SELECT first_name, last_name, email, number FROM teacherinfo WHERE module%s='%s' OR module%s='%s'",
							Integer.toString(i + 1), modules[0], Integer.toString(i + 1), modules[1]);
				}
				

				ResultSet rs = con.st.executeQuery(query);

				if (!rs.next()) {
					teacherName.add("TBD");
					teacherEmail.add("TBD");
					teacherNumber.add("TBD");
				} else {
					teacherName.add(rs.getString(1) + " " + rs.getString(2));
					teacherEmail.add(rs.getString(3));
					teacherNumber.add(rs.getString(4));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		String[] teacherNameArray = teacherName.toArray(new String[teacherName.size()]);
		String[] teacherEmailArray = teacherEmail.toArray(new String[teacherEmail.size()]);
		String[] teacherNumberArray = teacherNumber.toArray(new String[teacherNumber.size()]);
		String[] columns = {"Module Code", "Module Name", "Teacher", "Email", "Phone Number"};


		ArrayList<ArrayList<String>> tableData = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < moduleCodeArray.length; i++) {
			ArrayList<String> rowsData = new ArrayList<String>(
					Arrays.asList(moduleCodeArray[i], modules[i], teacherNameArray[i], teacherEmailArray[i], teacherNumberArray[i]));
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
		           tableColumn.setPreferredWidth(Math.max(rendererWidth + getIntercellSpacing().width, tableColumn.getPreferredWidth()));
		           return component;
		        }
		};
		table.setEnabled(false);
		table.setFont(myFont1);
		JScrollPane scrollpane = new JScrollPane(table);

		scrollpane.setBounds(5, 10, 850, 373);
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
