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


public class PlayerDao {
	
	public static Connection myGetConnection() {
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
	
	public void createTable() {
		Connection conn = myGetConnection();
		String sql = "CREATE TABLE if NOT EXISTS player(\r\n"
				+ "	backnum INT NOT NULL PRIMARY KEY,\r\n"
				+ "	`name` VARCHAR(12) NOT NULL,\r\n"
				+ "	`position` VARCHAR(10) NOT NULL,\r\n"
				+ "	birthday DATE,\r\n"
				+ "	height INT,\r\n"
				+ "	isDeleted INT DEFAULT 0\r\n"
				+ "	);";
		try {
			Statement stmt = conn.createStatement();
			
			stmt.execute(sql);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	
	public List<Player> getPlayer(){
		Connection conn = myGetConnection();
		Statement  stmt =null;
		List<Player> list = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			String sql = "SELECT * FROM player\r\n"
					+ " WHERE isDeleted=0 "
					+ "	ORDER BY FIELD (`position`, '투수', '포수', '내야수', '외야수');";
			
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Player d = new Player();
				d.setBacknum(rs.getInt(1));
				d.setName(rs.getString(2));
				d.setPosition(rs.getString(3));
				d.setBirthday(LocalDate.parse(rs.getString(4)));
				d.setHeight(rs.getInt(5));
				d.setIsDeleted(rs.getInt(6));
				list.add(d);
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public Player getPlayer(int backnum) {
		Connection conn = myGetConnection();
		String sql = "SELECT * FROM player WHERE backnum =?;";
		Player player = new Player();
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, backnum);
			
			ResultSet rs = pStmt.executeQuery();
			while(rs.next()) {
				player.setBacknum(rs.getInt(1));
				player.setName(rs.getString(2));
				player.setPosition(rs.getString(3));
				player.setBirthday(LocalDate.parse(rs.getString(4)));
				player.setHeight(rs.getInt(5));
				player.setIsDeleted(rs.getInt(6));
			}
			rs.close();
			pStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return player;
	}
	
	public void insertPlayer(Player Player) {
		Connection conn = myGetConnection();
		String sql = "INSERT INTO player(backnum, `name`, position, birthday, height, isDeleted) VALUES(?,?,?,?,?,0);";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, Player.getBacknum());
			pStmt.setString(2, Player.getName());
			pStmt.setString(3, Player.getPosition().toString());
			pStmt.setString(4, Player.getBirthday().toString());
			pStmt.setInt(5, Player.getHeight());
			
			pStmt.executeUpdate();
			pStmt.close();	
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void updatePlayer(Player player) {
		Connection conn = myGetConnection();
		String sql = "UPDATE player SET `name`=?, `position`=?, birthday=?, height=? WHERE backnum=?;";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, player.getName());
			pStmt.setString(2, player.getPosition().toString());
			pStmt.setString(3, player.getBirthday().toString());
			pStmt.setInt(4, player.getHeight());
			pStmt.setInt(5, player.getBacknum());
			
			//업데이트 실행
			pStmt.executeUpdate();
			pStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void deletePlayer(int backnum) {
		Connection conn = myGetConnection();
		String sql = "UPDATE player SET isdeleted=1 WHERE backnum = ?;";
		try {
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, backnum);
			
			//Delete대신 isDelete필드를 1로 변경
			pStmt.executeUpdate();
			pStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

}
