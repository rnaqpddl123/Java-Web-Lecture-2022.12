package ch07;

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


public class CustomerDao {
	// Servlet의 연결하는방법 
	// 기존방법은 계속 커넥션을 연결했다 끊었다 하는방식인데 이 방식은 한번연결하면 계속 연결해놓음 
	public static Connection getConnection() {
		Connection conn;
		try {
			Context initcontext = new InitialContext();
			DataSource ds = (DataSource) initcontext.lookup("java:comp/env/jdbc/world");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return conn;
	}

	public void deleteCustomer(String uid) {
		Connection conn = getConnection();
		String sql = "UPDATE customer SET isdeleted=1 WHERE uid =?;";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, uid);
			
			//Delete 대신에 isDeleted 필드를 1로 변경
			pStmt.executeUpdate();
			pStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void updateCustomer(Customer c){
		Connection conn = getConnection();
		String sql = "UPDATE customer SET name=? WHERE uid = ?;";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, c.getUname());
			pStmt.setString(2, c.getUid());
			
			//Update 실행
			pStmt.executeUpdate();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public Customer getCustomer(String uid) {
		Connection conn = getConnection();
		String sql = "SELECT *FROM customer WHERE  uid =?;";
		Customer c = new Customer();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uid);
			
			//Select 실행
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				c.setUid(rs.getString(1));
				c.setUname(rs.getString(2));
				c.setRegDate(LocalDate.parse(rs.getString(3)));		// LocalDate타입이라 앞에 붙여줘야함
				c.setIsDeleted(rs.getInt(4));
			}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return c;
	}
		
	public List<Customer> getCustomers(){
		Connection conn = getConnection();
		Statement stmt = null;
		List<Customer> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM customer WHERE isDeleted=0 ORDER BY regDate;";

			//Select 실행
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Customer c = new Customer();
				c.setUid(rs.getString(1));
				c.setUname(rs.getString(2));
				c.setRegDate(LocalDate.parse(rs.getString(3)));		// LocalDate타입이라 앞에 붙여줘야함
				c.setIsDeleted(rs.getInt(4));
				list.add(c);
			}
			rs.close();	
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}
	
	public void insertCustomer(Customer c) {
		Connection conn = getConnection();
		String sql = "INSERT INTO customer(uid, `name`) VALUES (?, ?);";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, c.getUid());	//sql문장 첫번째?에 값입력
			pStmt.setString(2, c.getUname()); //sql문장 두번째?에 값입력
			
			pStmt.executeUpdate();
			pStmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}
	}
	
}
