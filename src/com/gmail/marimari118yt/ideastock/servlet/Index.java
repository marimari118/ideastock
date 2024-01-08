package com.gmail.marimari118yt.ideastock.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gmail.marimari118yt.ideastock.beans.QuestionBean;
import com.gmail.marimari118yt.ideastock.dao.QuestionDAO;

@WebServlet("/index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String search = request.getParameter("search");
		
		List<QuestionBean> questions = QuestionDAO.search(search);
		request.setAttribute("questions", questions);
		
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
