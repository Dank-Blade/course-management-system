package Users;

import java.sql.ResultSet;

import DatabaseConnect.Connect;

public class Teacher extends Users{
	private String course;
	private String module1;
	private String module2;
	private String module3;
	private String module4;
	private Connect con;
	

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
	public String getModule1() {
		return module1;
	}
	
	public void setModule1(String module1) {
		this.module1 = module1;
	}
	
	public String getModule2() {
		return module2;
	}
	
	public void setModule2(String module2) {
		this.module2 = module2;
	}
	
	public String getModule3() {
		return module3;
	}
	
	public void setModule3(String module3) {
		this.module3 = module3;
	}
	
	public String getModule4() {
		return module4;
	}
	
	public void setModule4(String module4) {
		this.module4 = module4;
	}
	
	public void setTeacherInfo(String username) {
		try {
			con = new Connect();

			String query = String.format("SELECT * FROM teacherinfo where username='%s'", username);
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
				setModule1(rs.getString("module1"));
				setModule2(rs.getString("module2"));
				setModule3(rs.getString("module3"));
				setModule4(rs.getString("module4"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}