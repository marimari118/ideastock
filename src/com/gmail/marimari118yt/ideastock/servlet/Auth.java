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
import com.gmail.marimari118yt.ideastock.dao.UserDAO;
import com.gmail.marimari118yt.ideastock.dto.UserAuthDTO;
import com.gmail.marimari118yt.ideastock.dto.ValidationException;

@WebServlet("/auth")
public class Auth extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("serial")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		Map<String, List<String>> err = new HashMap<>();
		UserBean user = null;
		
		try {
			UserAuthDTO userData = new UserAuthDTO()
					.loginId(request.getParameter("loginId"))
					.password(request.getParameter("password"));
		
			user = UserDAO.login(userData);
			
			if (user == null) {
				err.put("other", new ArrayList<String>(){{
					add("ログインIDかパスワードが間違っています。");
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
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("/index");
			
		} else {
			request.setAttribute("errors", err);
			request.getRequestDispatcher("/login").forward(request, response);
		}
	}

}
