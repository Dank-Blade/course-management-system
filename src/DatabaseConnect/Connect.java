package DatabaseConnect;

import java.sql.*;

public class Connect {
	private final String url = "jdbc:mysql://localhost/";
	private final String user = "root";
	private final String password = "";
	private final String dbName = "course_management_system";

	public Connection c;
	public Statement st;

	public Connect() {
		try {
			c = DriverManager.getConnection(url, user, password);
//        establishing connection
			st = c.createStatement();
//        helps execute mySQL queries
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Failed to connect to database");
		}
		try {
			String query = "CREATE DATABASE " + dbName;
			st.executeUpdate(query);

			System.out.println("Database has been created. Filling in dummy datas.");

			String query1 = "USE " + dbName;
			st.executeUpdate(query1);

			createTables();
		} catch (SQLException e) {
			System.out.println("Database already exists!");
			String useDbQuery = "USE " + dbName;
			try {
				st.executeUpdate(useDbQuery);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	private void createTables() {
		adminInfo();
		courses();
		modules();
		studentInfo();
		level4And5();
		level6();
		teacherInfo();
		cancelledCourses();
		cancelledModules();
	}

	private void adminInfo() {
		String query = "CREATE TABLE IF NOT EXISTS `admininfo` (\n" + "  `ID` int(11) NOT NULL AUTO_INCREMENT,\n"
				+ "  `first_name` varchar(100) NOT NULL,\n" + "  `last_name` varchar(100) NOT NULL,\n"
				+ "  `username` varchar(100) NOT NULL,\n" + "  `password` varchar(100) NOT NULL,\n"
				+ "  `email` varchar(100) NOT NULL,\n" + "  `number` varchar(100) NOT NULL,\n"
				+ "  PRIMARY KEY (`ID`)\n" + ") ENGINE=InnoDB AUTO_INCREMENT=1003 DEFAULT CHARSET=utf8mb4\n";

		try {
			st.executeUpdate(query);
			fillAdmin();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void fillAdmin() {
		String query = "INSERT INTO `admininfo` (`ID`, `first_name`, `last_name`, `username`, `password`, `email`, `number`) VALUES"
				+ "(NULL, 'Aashish', 'Tuladhar', 'admin', 'admin', 'admin@gmail.com', '9852732212')";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void studentInfo() {
		String query = "CREATE TABLE IF NOT EXISTS studentinfo (\n" + "  ID int(7) NOT NULL AUTO_INCREMENT,\n"
				+ "  first_name varchar(100) NOT NULL,\n" + "  last_name varchar(100) NOT NULL,\n"
				+ "  username varchar(100) NOT NULL,\n" + "  password varchar(100) NOT NULL,\n"
				+ "  email varchar(100) NOT NULL,\n" + "  number varchar(100) NOT NULL,\n"
				+ "  course varchar(100) NOT NULL,\n" + "  level varchar(100) NOT NULL,\n"
				+ "  semester varchar(100) NOT NULL,\n" + "  PRIMARY KEY (`ID`),\n" + "  KEY username (username)\n"
				+ ") ENGINE=InnoDB AUTO_INCREMENT=2050000 DEFAULT CHARSET=utf8mb4";

		try {
			st.executeUpdate(query);
			fillStudent();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void fillStudent() {
		String query = "INSERT INTO `studentinfo` (`ID`, `first_name`, `last_name`, `username`, `password`, `email`, `number`, `course`, `level`, `semester`) VALUES"
				+ "(NULL, 'Pallaw', 'Magaw', 'pallawm', 'pallawpallaw', 'p@gmail.com', '9876227671', 'BIT', '4', '1'),"
				+ "(NULL, 'Sujen', 'Shrestha', 'sujens', 'sujensujen', 's@gmail.com', '9823441231', 'BIT', '4', '2'),"
				+ "(NULL, 'Safal', 'Maharjan', 'safalm', 'safalsafal', 'sa@gmail.com', '9822102311', 'BIT', '5', '1'),"
				+ "(NULL, 'Ashim', 'Maharjan', 'ashimm', 'ashimashim', 'ashim@gmail.com', '9823122211', 'BIT', '5', '2'),"
				+ "(NULL, 'Rahul', 'Nakarmi', 'rahuln', 'rahulrahul', 'r@gmail.com', '9832123312', 'BIT', '6', '1'),"
				+ "(NULL, 'Samyak', 'Maharjan', 'samyakm', 'samyaksamyak', 'sa@gmail.com', '9823112312', 'BIT', '6', '2'),"
				+ "(NULL, 'Kayisu', 'Gurung', 'kayisug', 'kayisukayisu', 'k@gmail.com', '9832132211', 'BIT', '4', '1'),"
				+ "(NULL, 'Aayush', 'Dangol', 'aayushd', 'dangoldangol', 'aay@gmail.com', '9832341221', 'BIT', '4', '2'),"
				+ "(NULL, 'Sohit', 'Shrestha', 'sohits', 'sohitsohit', 'so@gmail.com', '9823112311', 'BIT', '5', '2'),"
				+ "(NULL, 'Kuber', 'Shakya', 'kubers', 'kuberkuber', 'ku@gmail.com', '9876427772', 'BIT', '5', '2'),"
				+ "(NULL, 'Shreeyog', 'Shrestha', 'shreeyogs', 'shreeyog', 'sh@gmail.com', '9823122312', 'BIT', '6', '1'),"
				+ "(NULL, 'Rahul', 'Shrestha', 'rahuls', 'rahulsrahuls', 'rahs@gmail.com', '9847262312', 'BIT', '6', '2')";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void courses() {
		String query = "CREATE TABLE IF NOT EXISTS `courses` (\n" + "  `ID` int(11) NOT NULL AUTO_INCREMENT,\n"
				+ "  `course_name` varchar(100) NOT NULL,\n" + "  PRIMARY KEY (`ID`)\n"
				+ ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4";

		try {
			st.executeUpdate(query);
			fillCourses();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void fillCourses() {
		String query = "INSERT INTO `courses` (`ID`, `course_name`) VALUES (NULL, 'BIT'), (NULL, 'BIBM')";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void level4And5() {
		String query = "CREATE TABLE IF NOT EXISTS `level4and5` (\n" + "  `name` varchar(100) NOT NULL,\n"
				+ "  `username` varchar(100) NOT NULL,\n" + "  `course` varchar(100) NOT NULL,\n"
				+ "  `level` varchar(100) NOT NULL,\n" + "  `semester` varchar(100) NOT NULL,\n"
				+ "  `module1` varchar(100) NOT NULL,\n" + "  `marks1` varchar(100) DEFAULT NULL,\n"
				+ "  `remarks1` varchar(100) DEFAULT NULL,\n" + "  `module2` varchar(100) NOT NULL,\n"
				+ "  `marks2` varchar(100) DEFAULT NULL,\n" + "  `remarks2` varchar(100) DEFAULT NULL,\n"
				+ "  `module3` varchar(100) NOT NULL,\n" + "  `marks3` varchar(100) DEFAULT NULL,\n"
				+ "  `remarks3` varchar(100) DEFAULT NULL,\n" + "  `module4` varchar(100) DEFAULT NULL,\n"
				+ "  `marks4` varchar(100) DEFAULT NULL,\n" + "  `remarks4` varchar(100) DEFAULT NULL\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
		try {
			st.executeUpdate(query);
			fillLevel4And5();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void fillLevel4And5() {
		String query = "INSERT INTO `level4and5` (`name`, `username`, `course`, `level`, `semester`, `module1`, `marks1`, `remarks1`, `module2`, `marks2`, `remarks2`, `module3`, `marks3`, `remarks3`, `module4`, `marks4`, `remarks4`) VALUES\n"
				+ "('Pallaw Magaw', 'pallawm', 'BIT', '4', '1', 'Academic Skills and Team Based Learning', '67', 'PASSED', 'Introductory Programming and Problem Solving', NULL, NULL, 'Fundamentals of Computing', NULL, NULL, 'Server Management and Virtualisation', NULL, NULL),\n"
				+ "('Sujen Shrestha', 'sujens', 'BIT', '4', '2', 'Embedded System Programming', NULL, NULL, 'Internet Software Architecture', NULL, NULL, 'Computational Mathematics', NULL, NULL, 'Web Development', NULL, NULL),\n"
				+ "('Safal Maharjan', 'safalm', 'BIT', '5', '1', 'Object-Oriented Design and Programming', NULL, NULL, 'Concepts and Technologies of AI', NULL, NULL, 'Numerical Methods and Concurrency', NULL, NULL, 'Databases', NULL, NULL),\n"
				+ "('Ashim Maharjan', 'ashimm', 'BIT', '5', '2', 'Distributed and Cloud System Programming', NULL, NULL, 'Collaborative Development', NULL, NULL, 'Human Computer Interaction', NULL, NULL, 'Advanced Databases', NULL, NULL),\n"
				+ "('Kayisu Gurung', 'kayisug', 'BIT', '4', '1', 'Academic Skills and Team Based Learning', '87', 'PASSED', 'Introductory Programming and Problem Solving', NULL, NULL, 'Fundamentals of Computing', NULL, NULL, 'Server Management and Virtualisation', NULL, NULL),\n"
				+ "('Aayush Dangol', 'aayushd', 'BIT', '4', '2', 'Embedded System Programming', NULL, NULL, 'Internet Software Architecture', NULL, NULL, 'Computational Mathematics', NULL, NULL, 'Web Development', NULL, NULL),\n"
				+ "('Sohit Shrestha', 'sohits', 'BIT', '5', '2', 'Distributed and Cloud System Programming', NULL, NULL, 'Collaborative Development', NULL, NULL, 'Human Computer Interaction', NULL, NULL, 'Advanced Databases', NULL, NULL),\n"
				+ "('Kuber Shakya', 'kubers', 'BIT', '5', '2', 'Distributed and Cloud System Programming', NULL, NULL, 'Collaborative Development', NULL, NULL, 'Human Computer Interaction', NULL, NULL, 'Advanced Databases', NULL, NULL)";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void level6() {
		String query = "CREATE TABLE IF NOT EXISTS `level6` (\n" + "  `name` varchar(100) NOT NULL,\n"
				+ "  `username` varchar(100) NOT NULL,\n" + "  `course` varchar(100) NOT NULL,\n"
				+ "  `semester` varchar(100) NOT NULL,\n" + "  `module1` varchar(100) NOT NULL,\n"
				+ "  `marks1` varchar(100) DEFAULT NULL,\n" + "  `remarks1` varchar(100) DEFAULT NULL,\n"
				+ "  `module2` varchar(100) NOT NULL,\n" + "  `marks2` varchar(100) DEFAULT NULL,\n"
				+ "  `remarks2` varchar(100) DEFAULT NULL\n" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";

		try {
			st.executeUpdate(query);
			fillLevel6();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void fillLevel6() {
		String query = "INSERT INTO `level6` (`name`, `username`, `course`, `semester`, `module1`, `marks1`, `remarks1`, `module2`, `marks2`, `remarks2`) VALUES\n"
				+ "('Rahul Nakarmi', 'rahuln', 'BIT', '1', 'Project and Professionalism', NULL, NULL, 'TBD', NULL, NULL),\n"
				+ "('Samyak Maharjan', 'samyakm', 'BIT', '2', 'High Performance Computing', NULL, NULL, 'TBD', NULL, NULL),\n"
				+ "('Shreeyog Shrestha', 'shreeyogs', 'BIT', '1', 'Project and Professionalism', NULL, NULL, 'TBD', NULL, NULL),\n"
				+ "('Rahul Shrestha', 'rahuls', 'BIT', '2', 'High Performance Computing', NULL, NULL, 'TBD', NULL, NULL)";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void teacherInfo() {
		String query = "CREATE TABLE IF NOT EXISTS `teacherinfo` (\n" + "  `ID` int(11) NOT NULL AUTO_INCREMENT,\n"
				+ "  `first_name` varchar(100) NOT NULL,\n" + "  `last_name` varchar(100) NOT NULL,\n"
				+ "  `username` varchar(100) NOT NULL,\n" + "  `password` varchar(100) NOT NULL,\n"
				+ "  `email` varchar(100) NOT NULL,\n" + "  `number` varchar(100) NOT NULL,\n"
				+ "  `course` varchar(100) NOT NULL,\n" + "  `module1` varchar(100) DEFAULT NULL,\n"
				+ "  `module2` varchar(100) DEFAULT NULL,\n" + "  `module3` varchar(100) DEFAULT NULL,\n"
				+ "  `module4` varchar(100) DEFAULT NULL,\n" + "  PRIMARY KEY (`ID`)\n"
				+ ") ENGINE=InnoDB AUTO_INCREMENT=400001 DEFAULT CHARSET=utf8mb4";

		try {
			st.executeUpdate(query);
			fillTeacherInfo();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void fillTeacherInfo() {
		String query = "INSERT INTO `teacherinfo` (`ID`, `first_name`, `last_name`, `username`, `password`, `email`, `number`, `course`, `module1`, `module2`, `module3`, `module4`) VALUES\n"
				+ "(NULL, 'Shalin', 'Devkota', 'shalind', 'shalinshalin', 'sha@gmail.com', '9802312331', 'BIT', 'Academic Skills and Team Based Learning', 'Numerical Methods and Concurrency', 'Complex System', 'Fundamentals of Computing'),\n"
				+ "(NULL, 'Saurav', 'Parajulee', 'sayravp', 'sauravsaurav', 'sau@gmail.com', '9802332134', 'BIT', 'Introductory Programming and Problem Solving', 'Object-Oriented Design and Programming', 'Project and Professionalism', 'Concepts and Technologies of AI'),\n"
				+ "(NULL, 'Ricky', 'Rana', 'rickr', 'rickyricky', 'rick@gmail.com', '9802314123', 'BIT', 'Server Management and Virtualisation', 'Databases', 'High Performance Computing', 'Embedded System Programming'),\n"
				+ "(NULL, 'Daen', 'Basnet', 'daenb', 'daendaen', 'daen@gmail.com', '9783213213', 'BIT', 'Internet Software Architecture', 'Distributed and Cloud System Programming', 'Artificial Intelligence and Machine Learning', 'Big Data')";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void modules() {
		String query = "CREATE TABLE IF NOT EXISTS modules (\n" + "  `S.N.` int(7) NOT NULL AUTO_INCREMENT,\n"
				+ "  module_code varchar(100) NOT NULL,\n" + "  module_name varchar(100) NOT NULL,\n"
				+ "  course varchar(100) NOT NULL,\n" + "  level varchar(100) NOT NULL,\n"
				+ "  semester varchar(100) NOT NULL,\n" + "  mandatory varchar(100) NOT NULL,\n"
				+ "  PRIMARY KEY (`S.N.`)\n" + ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4";
		try {
			st.executeUpdate(query);
			fillModules();

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void fillModules() {
		String query = "INSERT INTO modules (`S.N.`, module_code, module_name, course, level, semester, mandatory) VALUES"
				+ "(NULL, '4CI018', 'Academic Skills and Team Based Learning', 'BIT', '4', '1', 'Yes'),"
				+ "(NULL, '4CS001', 'Introductory Programming and Problem Solving', 'BIT', '4', '1', 'Yes'),"
				+ "(NULL, '4CS015', 'Fundamentals of Computing', 'BIT', '4', '1', 'Yes'),"
				+ "(NULL, '4CS012', 'Server Management and Virtualisation', 'BIT', '4', '1', 'Yes'),"
				+ "(NULL, '4CS016', 'Embedded System Programming', 'BIT', '4', '2', 'Yes'),"
				+ "(NULL, '4CS017', 'Internet Software Architecture', 'BIT', '4', '2', 'Yes'),"
				+ "(NULL, '4MM013', 'Computational Mathematics', 'BIT', '4', '2', 'Yes'),"
				+ "(NULL, '4CS023', 'Web Development', 'BIT', '4', '2', 'Yes'),"
				+ "(NULL, '5CS019', 'Object-Oriented Design and Programming', 'BIT', '5', '1', 'Yes'),"
				+ "(NULL, '5CS037', 'Concepts and Technologies of AI', 'BIT', '5', '1', 'Yes'),"
				+ "(NULL, '5CS021', 'Numerical Methods and Concurrency', 'BIT', '5', '1', 'Yes'),"
				+ "(NULL, '5CI022', 'Databases', 'BIT', '5', '1', 'Yes'),"
				+ "(NULL, '5CS022', 'Distributed and Cloud System Programming', 'BIT', '5', '2', 'Yes'),"
				+ "(NULL, '5CS024', 'Collaborative Development', 'BIT', '5', '2', 'Yes'),"
				+ "(NULL, '5CS020', 'Human Computer Interaction', 'BIT', '5', '2', 'Yes'),"
				+ "(NULL, '5CI023', 'Advanced Databases', 'BIT', '5', '2', 'Yes'),"
				+ "(NULL, '6CS007', 'Project and Professionalism', 'BIT', '6', '1', 'Yes'),"
				+ "(NULL, '6CS014', 'Complex System', 'BIT', '6', '1', 'No'),"
				+ "(NULL, '6CS005', 'High Performance Computing', 'BIT', '6', '2', 'Yes'),"
				+ "(NULL, '6CS012', 'Artificial Intelligence and Machine Learning', 'BIT', '6', '1', 'No'),"
				+ "(NULL, '6CS030', 'Big Data', 'BIT', '6', '2', 'No'),"
				+ "(NULL, '4BU015', 'The Responsible Business', 'BIBM', '4', '1', 'Yes'),"
				+ "(NULL, '4BU016', 'The Sustainable Business', 'BIBM', '4', '1', 'Yes'),"
				+ "(NULL, '4BU002', '21st Century Management', 'BIBM', '4', '1', 'Yes'),"
				+ "(NULL, '4GK012', 'Preparing for Success at University', 'BIBM', '4', '1', 'Yes'),"
				+ "(NULL, '4BU003', 'Principles of Business', 'BIBM', '4', '2', 'Yes'),"
				+ "(NULL, '4BE002', 'The Innovative Business', 'BIBM', '4', '2', 'Yes'),"
				+ "(NULL, '4GK013', 'Project Based Learning', 'BIBM', '4', '2', 'Yes'), "
				+ "(NULL, '4BU0017', 'The Digital Business', 'BIBM', '4', '2', 'Yes'),"
				+ "(NULL, '5IB006', 'Contemporary Issues in International Business', 'BIBM', '5', '1', 'Yes'),"
				+ "(NULL, '5HR009', 'The International HR Professional', 'BIBM', '5', '1', 'Yes'),"
				+ "(NULL, '5BU017', 'Operation and Project Planning', 'BIBM', '5', '1', 'Yes'),"
				+ "(NULL, '5FC004', 'Managing Finance and Accounts', 'BIBM', '5', '1', 'Yes'),"
				+ "(NULL, '5BE005', 'The Strategic Business', 'BIBM', '5', '2', 'Yes'),"
				+ "(NULL, '5BU024', 'Global Context for Multinational Enterprises', 'BIBM', '5', '2', 'Yes'),"
				+ "(NULL, '5BU020', 'The Professional Project', 'BIBM', '5', '2', 'Yes'),"
				+ "(NULL, '5MK014', 'The Marketing Consultant', 'BIBM', '5', '2', 'Yes'),"
				+ "(NULL, '6BU018', 'Customer Acquisiton & Retention', 'BIBM', '6', '1', 'Yes'),"
				+ "(NULL, '6FC004', 'Operations and Project Planning', 'BIBM', '6', '1', 'No'),"
				+ "(NULL, '6BU017', 'Managing Finance and Accounts', 'BIBM', '6', '2', 'Yes'),"
				+ "(NULL, '6MG001', 'The Professional Manager and Leadership', 'BIBM', '6', '2', 'No');";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	private void cancelledCourses() {
		String query = "CREATE TABLE `cancelledcourses` (\n" + "  `ID` int(11) NOT NULL,\n"
				+ "  `course_name` varchar(100) NOT NULL\n" + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	private void cancelledModules() {
		String query = "CREATE TABLE `cancelledmodules` (\n" + "  `S.N.` int(11) NOT NULL,\n"
				+ "  `module_code` varchar(100) NOT NULL,\n" + "  `module_name` varchar(100) NOT NULL,\n"
				+ "  `course` varchar(100) NOT NULL,\n" + "  `level` varchar(100) NOT NULL,\n"
				+ "  `semester` varchar(100) NOT NULL,\n" + "  `mandatory` varchar(100) NOT NULL\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4";
		try {
			st.executeUpdate(query);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
