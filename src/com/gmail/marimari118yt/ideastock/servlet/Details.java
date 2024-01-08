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

import com.gmail.marimari118yt.ideastock.beans.AnswerBean;
import com.gmail.marimari118yt.ideastock.beans.QuestionBean;
import com.gmail.marimari118yt.ideastock.dao.AnswerDAO;
import com.gmail.marimari118yt.ideastock.dao.QuestionDAO;
import com.gmail.marimari118yt.ideastock.dto.ValidationException;

@WebServlet("/details")
public class Details extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("serial")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		
		Map<String, List<String>> err = new HashMap<>();
		QuestionBean question = null;
		List<AnswerBean> answers = null;
		
		try {
			question = QuestionDAO.find(Integer.parseInt(request.getParameter("id")));
			
			if (question != null) {
				answers = AnswerDAO.getByQuestionId(question.getId());
				
			} else {
				err.put("other", new ArrayList<String>(){{
					add("質問が存在しません。");
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
			request.setAttribute("question", question);
			request.setAttribute("answers", answers);
			request.getRequestDispatcher("/details.jsp").forward(request, response);
			
		} else {
			response.sendRedirect("/index");
		}
	}

}
