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

import com.gmail.marimari118yt.ideastock.beans.UserBean;
import com.gmail.marimari118yt.ideastock.dao.QuestionDAO;
import com.gmail.marimari118yt.ideastock.dto.QuestionPostDTO;
import com.gmail.marimari118yt.ideastock.dto.ValidationException;

@WebServlet("/post/question")
public class PostQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("serial")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		Map<String, List<String>> err = new HashMap<>();
		boolean created = false;
		
		try {
			QuestionPostDTO questionData = new QuestionPostDTO()
					.authorId(((UserBean)session.getAttribute("user")).getId())
					.title(request.getParameter("title"))
					.content(request.getParameter("content"));
		
			created = QuestionDAO.create(questionData);
			
			if (!created) {
				err.put("other", new ArrayList<String>(){{
					add("質問の投稿に失敗しました。");
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
			request.setAttribute("errors", err);
			request.getRequestDispatcher("/ask").forward(request, response);
		}
	}

}
