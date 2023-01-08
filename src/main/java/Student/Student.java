package Student;

import java.time.LocalDate;

public class Student {
	private int sid;
	private String sname;
	private String gender;
	private LocalDate enterYear;
	private String deptName;
	
	Student(){}
	
	Student(int sid) {
		this.sid = sid;
	}
	Student(int sid, String sname, String gender, LocalDate enterYear, String deptName) {
		this.sid = sid;
		this.sname = sname;
		this.gender = gender;
		this.enterYear = enterYear;
		this.deptName = deptName;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public LocalDate getEnterYear() {
		return enterYear;
	}
	public void setEnterYear(LocalDate enterYear) {
		this.enterYear = enterYear;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	@Override
	public String toString() {
		return "Student [sid=" + sid + ", sname=" + sname + ", gender=" + gender + ", enterYear=" + enterYear
				+ ", deptName=" + deptName + "]";
	}

}
