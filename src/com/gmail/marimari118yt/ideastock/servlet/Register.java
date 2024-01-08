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

import com.gmail.marimari118yt.ideastock.dao.UserDAO;
import com.gmail.marimari118yt.ideastock.dto.UserRegisterDTO;
import com.gmail.marimari118yt.ideastock.dto.ValidationException;

@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("serial")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Map<String, List<String>> err = new HashMap<>();
		boolean created = false;
		
		try {
			UserRegisterDTO userData = new UserRegisterDTO()
					.name(request.getParameter("name"))
					.loginId(request.getParameter("loginId"))
					.password(request.getParameter("password"));
		
			created = UserDAO.create(userData);
			
			if (!created) {
				err.put("other", new ArrayList<String>(){{
					add("ユーザー登録に失敗しました。");
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
			response.sendRedirect("/login");
			
		} else {
			request.setAttribute("errors", err);
			request.getRequestDispatcher("/signup").forward(request, response);
		}
	}

}
