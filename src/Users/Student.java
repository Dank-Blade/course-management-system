package Users;

import java.sql.ResultSet;
import java.util.ArrayList;

import DatabaseConnect.Connect;

public class Student extends Users{
	private String course;
	private String level;
	private String semester;
	private String[] modules;
	private String[] marks;
	private String[] remarks;

	private Connect con;


	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String[] getModules() {
		return modules;
	}

	public String[] getMarks() {
		return marks;
	}

	public String[] getRemarks() {
		return remarks;
	}

	public void setStudentInfo(String username) {
		try {
			ArrayList<String> modulesArrayList = new ArrayList<String>();
			ArrayList<String> marksArrayList = new ArrayList<String>();
			ArrayList<String> remarksArrayList = new ArrayList<String>();
			
			con = new Connect();

			String query = String.format("SELECT * FROM studentinfo where username='%s'", username);
			ResultSet rs = con.st.executeQuery(query);

			while (rs.next()) {
				setId(rs.getInt("ID"));
				setFirstName(rs.getString("first_name"));
				setLastName(rs.getString("last_name"));
				setUsername(rs.getString("username"));
				setPassword(rs.getString("password"));
				setEmail(rs.getString("email"));
				setNumber(rs.getString("number"));
				setCourse(rs.getString("course"));
				setLevel(rs.getString("level"));
				setSemester(rs.getString("semester"));
			}


			if (getLevel().equals("4") || getLevel().equals("5")) {
				query = String.format("SELECT * FROM level4and5 WHERE username = '%s' and semester = '%s'", username,
						getSemester());
				rs = con.st.executeQuery(query);

				while (rs.next()) {
					modulesArrayList.add(rs.getString("module1"));
					modulesArrayList.add(rs.getString("module2"));
					modulesArrayList.add(rs.getString("module3"));
					modulesArrayList.add(rs.getString("module4"));
					
					marksArrayList.add(rs.getString("marks1"));
					marksArrayList.add(rs.getString("marks2"));
					marksArrayList.add(rs.getString("marks3"));
					marksArrayList.add(rs.getString("marks4"));

					remarksArrayList.add(rs.getString("remarks1"));
					remarksArrayList.add(rs.getString("remarks2"));
					remarksArrayList.add(rs.getString("remarks3"));
					remarksArrayList.add(rs.getString("remarks4"));
				}
			} else if (getLevel().equals("6")) {
				query = String.format("SELECT * FROM level6 WHERE username = '%s' and semester = '%s'", username,
						getSemester());
				rs = con.st.executeQuery(query);
				
				while (rs.next()) {
					modulesArrayList.add(rs.getString("module1"));
					modulesArrayList.add(rs.getString("module2"));
				
					marksArrayList.add(rs.getString("marks1"));
					marksArrayList.add(rs.getString("marks2"));
				
					remarksArrayList.add(rs.getString("remarks1"));
					remarksArrayList.add(rs.getString("remarks2"));
				}
			}
			modules = modulesArrayList.toArray(new String[modulesArrayList.size()]);
			marks = marksArrayList.toArray(new String[marksArrayList.size()]);
			remarks = remarksArrayList.toArray(new String[remarksArrayList.size()]);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}