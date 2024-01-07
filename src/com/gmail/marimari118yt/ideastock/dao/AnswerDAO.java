package com.gmail.marimari118yt.ideastock.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.StringJoiner;

import com.gmail.marimari118yt.ideastock.beans.AnswerBean;
import com.gmail.marimari118yt.ideastock.dto.AnswerInfoDTO;
import com.gmail.marimari118yt.ideastock.dto.AnswerPostDTO;
import com.gmail.marimari118yt.ideastock.dto.AnswerUpdateDTO;
import com.gmail.marimari118yt.ideastock.dto.ValidationException;

public class AnswerDAO {
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 h:m");
	
	private static final String SQL_CREATE = new StringJoiner("\n")
			.add("INSERT INTO answers(")
			.add("question_id, user_id, title, content")
			.add(")VALUES(")
			.add("?, ?, ?, ?")
			.add(")").toString();
	
	private static final String SQL_FIND = new StringJoiner("\n")
			.add("SELECT * FROM answers")
			.add("WHERE id = ?").toString();
	
	private static final String SQL_SEARCH = new StringJoiner("\n")
			.add("SELECT * FROM answers_info")
			.add("WHERE MATCH (title, content)")
			.add("AGAINST (? IN NATURAL LANGUAGE MODE WITH QUERY EXPANSION)").toString();
	
	private static final String SQL_UPDATE = new StringJoiner("\n")
			.add("UPDATE answers SET")
			.add("title = ?,")
			.add("content = ?")
			.add("WHERE id = ?").toString();
	
	private static final String SQL_DELETE = new StringJoiner("\n")
			.add("UPDATE answers SET")
			.add("is_deleted = ?")
			.add("WHERE id = ?").toString();

	public static boolean create(AnswerPostDTO answerData) throws ValidationException, NoSuchAlgorithmException {
		AnswerBean answer = answerData.build();
		
		try (Connection con = DriverManager.getConnection(Const.URL, Const.USER, Const.PASSWORD)) {
			con.setAutoCommit(false);
			try (PreparedStatement stmt = con.prepareStatement(SQL_CREATE)) {
				stmt.setInt(1, answer.getQuestionId());
				stmt.setInt(2, answer.getAuthor().getId());
				stmt.setString(3, answer.getPost().getTitle());
				stmt.setString(4, answer.getPost().getContent());
				
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
	
	public static AnswerBean find(int id) {
		try (
				Connection con = DriverManager.getConnection(Const.URL, Const.USER, Const.PASSWORD);
				PreparedStatement stmt = con.prepareStatement(SQL_FIND);
		) {
			stmt.setInt(1, id);
			
			try (ResultSet rs = stmt.executeQuery()) {
				return getAnswerByResultSet(rs);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return null;
	}
	
	public static List<AnswerBean> search(String keyword) throws ValidationException, NoSuchAlgorithmException {
		try (
				Connection con = DriverManager.getConnection(Const.URL, Const.USER, Const.PASSWORD);
				PreparedStatement stmt = con.prepareStatement(SQL_SEARCH);
		) {
			stmt.setString(1, keyword);
			
			try (ResultSet rs = stmt.executeQuery()) {
				return getAnswersByResultSet(rs);
			}
			
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return null;
	}
	
	public static boolean update(AnswerUpdateDTO answerData) throws ValidationException, NoSuchAlgorithmException {
		AnswerBean answer = answerData.build();
		
		try (Connection con = DriverManager.getConnection(Const.URL, Const.USER, Const.PASSWORD)) {
			con.setAutoCommit(false);
			try (PreparedStatement stmt = con.prepareStatement(SQL_UPDATE)) {
				stmt.setInt(1, answer.getAuthor().getId());
				stmt.setString(2, answer.getPost().getTitle());
				stmt.setString(3, answer.getPost().getContent());
				stmt.setInt(4, answer.getId());
				
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
	
	public static boolean delete(AnswerUpdateDTO answerData) throws ValidationException, NoSuchAlgorithmException {
		AnswerBean answer = answerData.build();
		
		try (Connection con = DriverManager.getConnection(Const.URL, Const.USER, Const.PASSWORD)) {
			con.setAutoCommit(false);
			try (PreparedStatement stmt = con.prepareStatement(SQL_DELETE)) {
				stmt.setInt(1, 1);
				stmt.setInt(2, answer.getId());
				
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
	
	protected static List<AnswerBean> getAnswersByResultSet(ResultSet rs) throws SQLException {
		List<AnswerBean> answers = new LinkedList<>();
		
		while (rs.next()) {
			answers.add(new AnswerInfoDTO()
					.id(rs.getInt("id"))
					.questionId(rs.getInt("question_id"))
					.authorId(rs.getInt("author_id"))
					.authorName(rs.getString("author_name"))
					.title(rs.getString("title"))
					.content(rs.getString("content"))
					.createdAt(formatter.format(rs.getTime("created_at")))
					.build());
		}
		
		return answers;
	}
	
	protected static AnswerBean getAnswerByResultSet(ResultSet rs) throws SQLException {
		AnswerBean answer = null;
		
		if (rs.next()) {
			answer = new AnswerInfoDTO()
					.id(rs.getInt("id"))
					.questionId(rs.getInt("question_id"))
					.authorId(rs.getInt("author_id"))
					.authorName(rs.getString("author_name"))
					.title(rs.getString("title"))
					.content(rs.getString("content"))
					.createdAt(formatter.format(rs.getTime("created_at")))
					.build();
		}
		
		return answer;
	}

}
