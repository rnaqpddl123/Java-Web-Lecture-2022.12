package Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StudentDao {
	
	public static Connection getConnection() {
		Connection conn;
		try {
			//Servers/contex.xml에 Resource에 정보를 입력해놔야함
			Context initcontext = new InitialContext();
			DataSource ds = (DataSource) initcontext.lookup("java:comp/env/jdbc/world");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}
	// 테이블 생성
	public void createTable() {
		Connection conn = getConnection();
		String sql = "CREATE TABLE if NOT EXISTS student"
				+ "(sid int(255) NOT NULL,"
				+ "	sname varchar(5) NOT NULL,"
				+ "	gender varchar(4) NOT NULL,"
				+ "	enterYear datetime,"
				+ "	deptName varchar(20),"
				+ "	PRIMARY KEY (sid));";
		try {
			Statement stmt = conn.createStatement();
			
			stmt.execute(sql);
			stmt.close(); conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertStudent(Student s) {
		Connection conn = getConnection();
		String sql = "INSERT INTO student VALUES (?, ?, ?, ?, ?);";
		
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, s.getSid());	
			pStmt.setString(2, s.getSname());	
			pStmt.setString(3, s.getGender());	
			pStmt.setString(4, s.getEnterYear().toString());	
			pStmt.setString(5, s.getDeptName());	
			
			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateStudent(Student s) {
		Connection conn = getConnection();
		String sql = "UPDATE student set sname=?, gender=?, enteryear=?, deptName=? where sid=? ;";
		
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, s.getSname());	
			pStmt.setString(2, s.getGender());	
			pStmt.setString(3, s.getEnterYear().toString());	
			pStmt.setString(4, s.getDeptName());	
			pStmt.setInt(5, s.getSid());	
			
			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Student> studentInfo() {
		Connection conn = getConnection();
		String sql = "SELECT * FROM student;";
		List<Student> list = new ArrayList<>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Student s = new Student();
				s.setSid(rs.getInt(1));
				s.setSname(rs.getString(2));
				s.setGender(rs.getString(3));
				s.setEnterYear(LocalDate.parse(rs.getString(4).substring(0,10)));
				s.setDeptName(rs.getString(5));
				list.add(s);
			}
			rs.close();	
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public void delete(int sid) {
		Connection conn = getConnection();
		String sql = "DELETE FROM student WHERE sid=?;";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, sid);
			
			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}
