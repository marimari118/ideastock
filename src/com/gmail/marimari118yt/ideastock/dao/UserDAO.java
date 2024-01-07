package com.gmail.marimari118yt.ideastock.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.StringJoiner;

import com.gmail.marimari118yt.ideastock.beans.UserBean;
import com.gmail.marimari118yt.ideastock.dto.UserAuthDTO;
import com.gmail.marimari118yt.ideastock.dto.UserRegisterDTO;
import com.gmail.marimari118yt.ideastock.dto.UserUpdateDTO;
import com.gmail.marimari118yt.ideastock.dto.ValidationException;

public class UserDAO {
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 h:m");
	
	private static final String SQL_CREATE = new StringJoiner("\n")
			.add("INSERT INTO users(")
			.add("name, login_id, password")
			.add(")VALUES(")
			.add("?, ?, ?")
			.add(")").toString();
	
	private static final String SQL_FIND = new StringJoiner("\n")
			.add("SELECT * FROM users")
			.add("WHERE id = ?").toString();
	
	private static final String SQL_LOGIN = new StringJoiner("\n")
			.add("SELECT * FROM users")
			.add("WHERE login_id = ?").toString();
	
	private static final String SQL_UPDATE = new StringJoiner("\n")
			.add("UPDATE users SET")
			.add("name = ?,")
			.add("login_id = ?,")
			.add("password = ?")
			.add("WHERE id = ?").toString();
	
	private static final String SQL_DELETE = new StringJoiner("\n")
			.add("UPDATE users SET")
			.add("is_deleted = ?")
			.add("WHERE id = ?").toString();

	public static boolean create(UserRegisterDTO userData) throws ValidationException, NoSuchAlgorithmException {
		UserBean user = userData.build();
		
		try (Connection con = DriverManager.getConnection(Const.URL, Const.USER, Const.PASSWORD)) {
			con.setAutoCommit(false);
			try (PreparedStatement stmt = con.prepareStatement(SQL_CREATE)) {
				stmt.setString(1, user.getName());
				stmt.setString(2, user.getLoginId());
				stmt.setBytes(3, user.getPassword());
				
				stmt.executeUpdate();
				con.commit();
				return true;
				
			} catch (SQLException e) {
				con.rollback();
				throw e;
			}

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return false;
	}
	
	public static UserBean find(int id) {
		try (
				Connection con = DriverManager.getConnection(Const.URL, Const.USER, Const.PASSWORD);
				PreparedStatement stmt = con.prepareStatement(SQL_FIND);
		) {
			stmt.setInt(1, id);
			
			try (ResultSet rs = stmt.executeQuery()) {
				return getUserByResultSet(rs);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return null;
	}
	
	public static UserBean login(UserAuthDTO userData) throws ValidationException, NoSuchAlgorithmException {
		UserBean user = userData.build();
		
		try (
				Connection con = DriverManager.getConnection(Const.URL, Const.USER, Const.PASSWORD);
				PreparedStatement stmt = con.prepareStatement(SQL_LOGIN);
		) {
			stmt.setString(1, user.getLoginId());
			
			try (ResultSet rs = stmt.executeQuery()) {
				UserBean auth = getUserByResultSet(rs);
				if (auth(user, auth)) {
					return auth;
				}
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return null;
	}
	
	public static boolean update(UserUpdateDTO userData) throws ValidationException, NoSuchAlgorithmException {
		UserBean user = userData.build();
		
		try (Connection con = DriverManager.getConnection(Const.URL, Const.USER, Const.PASSWORD)) {
			con.setAutoCommit(false);
			try (PreparedStatement stmt = con.prepareStatement(SQL_UPDATE)) {
				stmt.setString(1, user.getName());
				stmt.setString(2, user.getLoginId());
				stmt.setBytes(3, user.getPassword());
				stmt.setInt(4, user.getId());
				
				stmt.executeUpdate();
				con.commit();
				return true;
				
			} catch (Exception e) {
				con.rollback();
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return false;
	}
	
	public static boolean delete(UserUpdateDTO userData) throws ValidationException, NoSuchAlgorithmException {
		UserBean user = userData.build();
		
		try (Connection con = DriverManager.getConnection(Const.URL, Const.USER, Const.PASSWORD)) {
			con.setAutoCommit(false);
			try (PreparedStatement stmt = con.prepareStatement(SQL_DELETE)) {
				stmt.setInt(1, 1);
				stmt.setInt(2, user.getId());
				
				stmt.executeUpdate();
				con.commit();
				return true;
				
			} catch (Exception e) {
				con.rollback();
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return false;
	}
	
	protected static boolean auth(UserBean user, UserBean auth) {
		return Arrays.equals(user.getPassword(), auth.getPassword());
	}
	
	protected static UserBean getUserByResultSet(ResultSet rs) throws SQLException {
		UserBean user = null;
		
		if (rs.next()) {
			user = new UserBean();
			user.setId(rs.getInt("id"));
			user.setName(rs.getString("name"));
			user.setLoginId(rs.getString("login_id"));
			user.setPassword(rs.getBytes("password"));
			user.setCreatedAt(formatter.format(rs.getTime("created_at")));
		}
		
		return user;
	}

}
