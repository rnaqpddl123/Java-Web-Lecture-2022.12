package ch09.users;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.mindrot.jbcrypt.BCrypt;


public class UserDao {
	private String host;
	private String user;
	private String password;
	private String database; 
	private String port;

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
	UserDao(){
		try {
			InputStream is = new FileInputStream("C://workspace/mysql.properties");
			Properties props = new Properties();
			props.load(is);
			is.close();
			
			host = props.getProperty("host");
			user = props.getProperty("user");
			password = props.getProperty("password");
			database = props.getProperty("database");
			port = props.getProperty("port", "3306"); // key값이 없으면 디폴트값 3316을 준다
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//유저 정보 삭제
	public void deleteCustomer(String uid) {
		Connection conn = getConnection();
		String sql = "DELETE FROM users WHERE uid=?;";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, uid);
			
			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	// 유저 정보 수정
	public void updateUser(User u){
		Connection conn = getConnection();
		String sql = "UPDATE users SET pwd=?, uname=?, email=?, regDate=? WHERE uid=?;";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, u.getPwd());
			pStmt.setString(2, u.getUname());	//데이터 타입맞춰주기
			pStmt.setString(3, u.getEmail());
			pStmt.setString(4, u.getRegDate().toString());
			pStmt.setString(5, u.getUid());
			
			//Update 실행
			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// case1 : uid가 없는경우
	// case2 : pwd가 틀린경우
	// case3 : uid, pwd 일치 ==> login
	// 로그인
	public User login(String uid, String pwd) {
		Connection conn = getConnection();
		String sql = "SELECT * FROM Users WHERE  uid =?;";
		User u = new User();
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			ResultSet rs = pstmt.executeQuery();
			System.out.println(rs.getString(1));
			
//			if(rs.getString(1) == null) {
//				System.out.println("아이디가 틀렸습니다.");
//			}
//			if(rs.getString(2) == null) {
//				System.out.println("비밀번호가 틀렸습니다");
//			}
//			if (uid == rs.getString(1) & pwd == rs.getString(2)) {
//				System.out.println("로그인되었습니다.");
//			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	// 유저 정보 보여주기
	public User getUserinfo(String uid) {
		Connection conn = getConnection();
		String sql = "SELECT * FROM Users WHERE  uid =?;";
		User u = new User();
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			
			//Select 실행
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				u.setUid(rs.getString(1));
				u.setPwd(rs.getString(2));
				u.setUname(rs.getString(3));
				u.setEmail(rs.getString(4));		
				u.setRegDate(LocalDate.parse(rs.getString(5)));
			}
			rs.close();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return u;
	}
		
	public List<User> listUsers(){
		Connection conn = getConnection();
		String sql = "SELECT * FROM Users  ORDER BY regDate, uid;";
		List<User> list = new ArrayList<>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				User u = new User();
				u.setUid(rs.getString(1));
				u.setPwd(rs.getString(2));
				u.setUname(rs.getString(3));
				u.setEmail(rs.getString(4));
				u.setRegDate(LocalDate.parse(rs.getString(5)));	
				list.add(u);
			}
			rs.close();	
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	// 유저목록 및 내용보여주기
	public void registerUser(User u) {
		Connection conn = getConnection();
		String sql = "INSERT INTO users VALUES (?, ?, ?, ?, DEFAULT);";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, u.getUid());	
			String cryptedPwd = BCrypt.hashpw(u.getPwd(), BCrypt.gensalt());
			pStmt.setString(2, cryptedPwd); 
			pStmt.setString(3, u.getUname()); 
			pStmt.setString(4, u.getEmail());

			pStmt.executeUpdate();
			pStmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
}
