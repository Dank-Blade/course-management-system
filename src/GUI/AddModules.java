package GUI;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DatabaseConnect.Connect;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddModules extends JFrame implements ActionListener {
	private JButton addButton;

	private ArrayList<String> list;
	private ArrayList<String> modules;

	private JComboBox<String> coursesBox;

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

	AddModules() {
		Font myFont = new Font("Calibri", Font.BOLD, 17);
		Font myFont1 = new Font("Calibri", Font.PLAIN, 15);

		String[] courses = getCourses().toArray(new String[getCourses().size()]);
		System.out.println(courses);
		coursesBox = new JComboBox<String>(courses);
		coursesBox.addActionListener(this);

		JLabel topic = new JLabel("ADD MODULES");

		JLabel coursesLabel = new JLabel("Courses:", SwingConstants.RIGHT);
		JLabel levelLabel = new JLabel("Level:", SwingConstants.RIGHT);
		JLabel semesterLabel = new JLabel("Semester:", SwingConstants.RIGHT);
		JLabel moduleCodeLabel = new JLabel("Module Code:", SwingConstants.RIGHT);
		JLabel moduleNameLabel = new JLabel("Module Name:", SwingConstants.RIGHT);
		JLabel mandatoryLabel = new JLabel("Mandatory:", SwingConstants.RIGHT);

		String[] level = { "Level 4", "Level 5", "Level 6" };
		String[] semester = { "1", "2" };
		String[] mandatory = { "Yes" };

		JComboBox<String> levelField = new JComboBox<String>(level);
		JComboBox<String> semesterField = new JComboBox<String>(semester);
		JComboBox<String> mandatoryField = new JComboBox<String>(mandatory);

		JTextField moduleCodeField = new JTextField();
		JTextField moduleNameField = new JTextField();

		topic.setBounds(115, 25, 255, 30);
		topic.setFont(new Font("Calibri", Font.BOLD, 30));
		topic.setForeground(new Color(0xDEDEDE));

		coursesLabel.setBounds(65, 85, 80, 30);
		coursesLabel.setFont(myFont);
		coursesLabel.setForeground(new Color(0xDEDEDE));

		levelLabel.setBounds(65, 140, 80, 30);
		levelLabel.setFont(myFont);
		levelLabel.setForeground(new Color(0xDEDEDE));

		semesterLabel.setBounds(65, 195, 80, 30);
		semesterLabel.setFont(myFont);
		semesterLabel.setForeground(new Color(0xDEDEDE));

		moduleCodeLabel.setBounds(35, 250, 110, 30);
		moduleCodeLabel.setFont(myFont);
		moduleCodeLabel.setForeground(new Color(0xDEDEDE));

		moduleNameLabel.setBounds(35, 305, 110, 30);
		moduleNameLabel.setFont(myFont);
		moduleNameLabel.setForeground(new Color(0xDEDEDE));

		mandatoryLabel.setBounds(35, 360, 110, 30);
		mandatoryLabel.setFont(myFont);
		mandatoryLabel.setForeground(new Color(0xDEDEDE));

		coursesBox.setBounds(165, 82, 225, 35);
		coursesBox.setFont(myFont1);
		coursesBox.setBackground(new Color(0xDEDEDE));
		coursesBox.addActionListener(this);

		levelField.setBounds(165, 137, 225, 35);
		levelField.setFont(myFont1);
		levelField.setBackground(new Color(0xDEDEDE));
		levelField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (levelField.getSelectedIndex() == 2) {
					mandatoryField.removeAllItems();
					mandatoryField.addItem("Yes");
					mandatoryField.addItem("No");
				} else {
					mandatoryField.removeAllItems();
					mandatoryField.addItem("Yes");
				}

			}

		});

		semesterField.setBounds(165, 192, 225, 35);
		semesterField.setFont(myFont1);
		semesterField.setBackground(new Color(0xDEDEDE));

		moduleCodeField.setBounds(165, 247, 225, 35);
		moduleCodeField.setFont(myFont1);
		moduleCodeField.setBackground(new Color(0xDEDEDE));

		moduleNameField.setBounds(165, 302, 225, 35);
		moduleNameField.setFont(myFont1);
		moduleNameField.setBackground(new Color(0xDEDEDE));

		mandatoryField.setBounds(165, 357, 225, 35);
		mandatoryField.setFont(myFont1);
		mandatoryField.setBackground(new Color(0xDEDEDE));

		addButton = new JButton("Add");
		addButton.setBounds(130, 425, 150, 50);
		addButton.setFocusable(false);
		addButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addButton.setBackground(new Color(0xE2E2E2));
		addButton.setBorder(BorderFactory.createLineBorder(Color.black));
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String coursesText = (String) coursesBox.getSelectedItem();
				String levelText = ((String) levelField.getSelectedItem()).split(" ")[1];
				String semesterText = (String) semesterField.getSelectedItem();
				String codeText = moduleCodeField.getText();
				String nameText = moduleNameField.getText();
				String mandatoryText = (String) mandatoryField.getSelectedItem();

				Connect con = new Connect();
				String query = String.format("SELECT * FROM modules WHERE course='%s' AND level='%s' AND semester='%s'",
						coursesText, levelText, semesterText);
				int size = 0;
				try {
					ResultSet rs = con.st.executeQuery(query);
					while (rs.next()) {
						size += 1;
					}
				} catch (SQLException er) {
					er.printStackTrace();
				}

				try {

					String check = String.format("SELECT * FROM modules WHERE module_code='%s'", codeText);
					ResultSet rs = con.st.executeQuery(check);

					if (codeText.isEmpty() || nameText.isEmpty()) {
						JOptionPane.showMessageDialog(null, "None of the fields can be left empty!");
					} else if (rs.next()) {
						JOptionPane.showMessageDialog(null, "The given module already exists!");
					} else {
						if (size >= 4 && levelText.equals("4") && semesterText.equals("1")) {
							JOptionPane.showMessageDialog(null, "Max Modules reached for Level 4 Semester 1");
						} else if (size >= 4 && levelText.equals("4") && semesterText.equals("2")) {
							JOptionPane.showMessageDialog(null, "Max Modules reached for Level 4 Semester 2");
						} else if (size >= 4 && levelText.equals("5") && semesterText.equals("1")) {
							JOptionPane.showMessageDialog(null, "Max Modules reached for Level 5 Semester 1");
						} else if (size >= 4 && levelText.equals("5") && semesterText.equals("2")) {
							JOptionPane.showMessageDialog(null, "Max Modules reached for Level 5 Semester 2");
						} else if (size >= 2 && levelText.equals("6") && semesterText.equals("1")) {
							JOptionPane.showMessageDialog(null, "Max Modules reached for Level 6 Semester 1");
						} else if (size >= 2 && levelText.equals("6") && semesterText.equals("2")) {
							JOptionPane.showMessageDialog(null, "Max Modules reached for Level 6 Semester 2");
						} else {
							query = String.format(
									"INSERT INTO modules (module_code, module_name, course, level, semester, mandatory) VALUES ('%s', '%s', '%s', '%s', '%s', '%s')",
									codeText, nameText, coursesText, levelText, semesterText, mandatoryText);
							try {
								Connect conn = new Connect();
								conn.st.executeUpdate(query);
								JOptionPane.showMessageDialog(null, "Module Added!");
							} catch (SQLException er) {
								er.printStackTrace();
							}
						}
					}

				} catch (SQLException er) {
					er.printStackTrace();
				}

			}

		});

		this.setTitle("Course Management System");
		this.add(topic);
		this.add(coursesLabel);
		this.add(levelLabel);
		this.add(semesterLabel);
		this.add(moduleCodeLabel);
		this.add(moduleNameLabel);
		this.add(mandatoryLabel);
		this.add(coursesBox);
		this.add(levelField);
		this.add(semesterField);
		this.add(moduleCodeField);
		this.add(moduleNameField);
		this.add(mandatoryField);
		this.add(addButton);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.getContentPane().setBackground(new Color(31, 41, 91));
		this.setSize(420, 550);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

	}

}
