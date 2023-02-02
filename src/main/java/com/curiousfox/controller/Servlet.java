package com.curiousfox.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curiousfox.DAO.UserDAO;
import com.curiousfox.model.User;

@WebServlet(urlPatterns = {"/profile"})
public class Servlet extends HttpServlet {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String action = req.getServletPath();
		if (action.equals("/profile")) {
			getUser(req, res);
		}else {
			res.sendRedirect("404");
		}
	}
	
	protected void getUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username = req.getParameter("username");
		UserDAO dao = new UserDAO();
		User user = dao.getUser(username);
		
		req.setAttribute("id", user.getId());
		req.setAttribute("name", user.getName());
		req.setAttribute("username", user.getUsername());
		req.setAttribute("picture", user.getPicture_url());
		req.setAttribute("bio", user.getBio());
		
		RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
		rd.forward(req, res);
	}
}
