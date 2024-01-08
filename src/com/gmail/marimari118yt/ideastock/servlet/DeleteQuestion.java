package com.gmail.marimari118yt.ideastock.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gmail.marimari118yt.ideastock.beans.QuestionBean;
import com.gmail.marimari118yt.ideastock.beans.UserBean;
import com.gmail.marimari118yt.ideastock.dao.QuestionDAO;
import com.gmail.marimari118yt.ideastock.dto.ValidationException;

@WebServlet("/delete/question")
public class DeleteQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("serial")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		Map<String, List<String>> err = new HashMap<>();
		int questionId = -1;
		
		try {
			UserBean user = (UserBean)session.getAttribute("user");
			questionId = Integer.parseInt((String)request.getParameter("id"));
			QuestionBean question = QuestionDAO.find(questionId);
			
			if (question.getAuthor().getId() == user.getId()) {
				QuestionDAO.delete(questionId);
				
			} else {
				err.put("other", new ArrayList<String>(){{
					add("質問の削除に失敗しました。");
				}});
			}
		
		} catch (ValidationException e) {
			err.putAll(e.getDetails());
		
		} catch (Exception e) {
			err.put("other", new ArrayList<String>(){{
				add("不明な例外が発生しました。");
			}});
		}
		
		if (err.isEmpty()) {
			response.sendRedirect("/index");
			
		} else {
			response.sendRedirect("/details?id=" + questionId);
		}
	}

}
