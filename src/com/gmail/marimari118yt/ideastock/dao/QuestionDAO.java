package com.gmail.marimari118yt.ideastock.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

import com.gmail.marimari118yt.ideastock.beans.QuestionBean;
import com.gmail.marimari118yt.ideastock.dto.QuestionInfoDTO;
import com.gmail.marimari118yt.ideastock.dto.QuestionPostDTO;
import com.gmail.marimari118yt.ideastock.dto.QuestionUpdateDTO;
import com.gmail.marimari118yt.ideastock.dto.ValidationException;

public class QuestionDAO {

	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
	
	private static final String SQL_CREATE = new StringJoiner("\n")
			.add("INSERT INTO questions(")
			.add("user_id, title, content")
			.add(")VALUES(")
			.add("?, ?, ?")
			.add(")").toString();
	
	private static final String SQL_FIND = new StringJoiner("\n")
			.add("SELECT * FROM questions")
			.add("WHERE id = ?").toString();
	
	private static final String SQL_GET_ALL = new StringJoiner("\n")
			.add("SELECT * FROM questions_info").toString();
	
	private static final String SQL_SEARCH = new StringJoiner("\n")
			.add("SELECT * FROM questions_info")
			.add("WHERE MATCH (title, content)")
			.add("AGAINST (? IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION)").toString();
	
	private static final String SQL_UPDATE = new StringJoiner("\n")
			.add("UPDATE questions SET")
			.add("title = ?,")
			.add("content = ?")
			.add("WHERE id = ?").toString();
	
	private static final String SQL_DELETE = new StringJoiner("\n")
			.add("UPDATE questions SET")
			.add("is_deleted = ?")
			.add("WHERE id = ?").toString();

	public static boolean create(QuestionPostDTO questionData) throws ValidationException {
		QuestionBean question = questionData.build();
		
		try (Connection con = DriverManager.getConnection(Const.URL, Const.USER, Const.PASSWORD)) {
			con.setAutoCommit(false);
			try (PreparedStatement stmt = con.prepareStatement(SQL_CREATE)) {
				stmt.setInt(1, question.getAuthor().getId());
				stmt.setString(2, question.getPost().getTitle());
				stmt.setString(3, question.getPost().getContent());
				
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
	
	public static QuestionBean find(int id) {
		try (
				Connection con = DriverManager.getConnection(Const.URL, Const.USER, Const.PASSWORD);
				PreparedStatement stmt = con.prepareStatement(SQL_FIND);
		) {
			stmt.setInt(1, id);
			
			try (ResultSet rs = stmt.executeQuery()) {
				return getQuestionByResultSet(rs);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return null;
	}
	
	public static List<QuestionBean> getAll() throws ValidationException {
		try (
			Connection con = DriverManager.getConnection(Const.URL, Const.USER, Const.PASSWORD);
			PreparedStatement stmt = con.prepareStatement(SQL_GET_ALL);
		) {
			try (ResultSet rs = stmt.executeQuery()) {
				return getQuestionsByResultSet(rs);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return null;
	}
	
	public static List<QuestionBean> search(String keyword) throws ValidationException {
		if (keyword != null && !keyword.equals("")) {
			try (
					Connection con = DriverManager.getConnection(Const.URL, Const.USER, Const.PASSWORD);
					PreparedStatement stmt = con.prepareStatement(SQL_SEARCH);
			) {
				stmt.setString(1, keyword);
				
				try (ResultSet rs = stmt.executeQuery()) {
					return getQuestionsByResultSet(rs);
				}
				
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		
		} else {
			return getAll();
		}
		
		return null;
	}
	
	public static boolean update(QuestionUpdateDTO questionData) throws ValidationException {
		QuestionBean question = questionData.build();
		
		try (Connection con = DriverManager.getConnection(Const.URL, Const.USER, Const.PASSWORD)) {
			con.setAutoCommit(false);
			try (PreparedStatement stmt = con.prepareStatement(SQL_UPDATE)) {
				stmt.setInt(1, question.getAuthor().getId());
				stmt.setString(2, question.getPost().getTitle());
				stmt.setString(3, question.getPost().getContent());
				stmt.setInt(4, question.getId());
				
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
	
	public static boolean delete(QuestionUpdateDTO questionData) throws ValidationException {
		QuestionBean question = questionData.build();
		
		try (Connection con = DriverManager.getConnection(Const.URL, Const.USER, Const.PASSWORD)) {
			con.setAutoCommit(false);
			try (PreparedStatement stmt = con.prepareStatement(SQL_DELETE)) {
				stmt.setInt(1, 1);
				stmt.setInt(2, question.getId());
				
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
	
	protected static List<QuestionBean> getQuestionsByResultSet(ResultSet rs) throws SQLException {
		List<QuestionBean> questions = new LinkedList<>();
		
		while (rs.next()) {
			questions.add(new QuestionInfoDTO()
					.id(rs.getInt("id"))
					.authorId(rs.getInt("author_id"))
					.authorName(rs.getString("author_name"))
					.title(rs.getString("title"))
					.content(rs.getString("content"))
					.createdAt(formatter.format(rs.getTimestamp("created_at")))
					.build());
		}
		
		return questions;
	}
	
	protected static QuestionBean getQuestionByResultSet(ResultSet rs) throws SQLException {
		QuestionBean question = null;
		
		if (rs.next()) {
			question = new QuestionInfoDTO()
					.id(rs.getInt("id"))
					.authorId(rs.getInt("author_id"))
					.authorName(rs.getString("author_name"))
					.title(rs.getString("title"))
					.content(rs.getString("content"))
					.createdAt(formatter.format(rs.getTimestamp("created_at")))
					.build();
		}
		
		return question;
	}

}
