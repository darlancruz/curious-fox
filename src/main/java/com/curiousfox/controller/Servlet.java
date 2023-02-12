package com.curiousfox.controller;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.curiousfox.DAO.CommentDAO;
import com.curiousfox.DAO.UserDAO;
import com.curiousfox.exception.ValidationException;
import com.curiousfox.model.Comment;
import com.curiousfox.model.User;
import com.curiousfox.utils.Validation;

@WebServlet(urlPatterns = {"/profile", "/send","/sign-up"})
public class Servlet extends HttpServlet {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String action = req.getServletPath();
		if (action.equals("/profile")) {
			getUser(req, res);
		}
		if(action.equals("/sign-up")) {
			RequestDispatcher rd = req.getRequestDispatcher("sign-up.jsp");
			rd.forward(req, res);
		}
		else {
			res.sendRedirect("404");
		}
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String action = req.getServletPath();
		if (action.equals("/send")) {
			sendComment(req, res);
		}
		if(action.equals("/sign-up")) {
			SignUp(req, res);
		}
		else {
			res.sendRedirect("404");
		}
	}
	
	protected void getUser(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username = req.getParameter("username");
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUser(username);
		
		req.setAttribute("id", user.getId());
		req.setAttribute("name", user.getName());
		req.setAttribute("username", user.getUsername());
		req.setAttribute("picture", user.getPictureUrl());
		req.setAttribute("bio", user.getBio());
		
		CommentDAO commentDAO = new CommentDAO();
		ArrayList<Comment> commentArr = commentDAO.getAllComments(user.getId());
	
		ArrayList<Entry<User, Comment>> commentsByUser = new ArrayList<Entry<User, Comment>>();
		for(Comment comment: commentArr) {
			User sender = userDAO.getUserById(comment.getSenderId());
			Entry<User, Comment> entry = Map.entry(sender, comment);
			commentsByUser.add(entry);
		}
		req.setAttribute("comments", commentsByUser);
		
		RequestDispatcher rd = req.getRequestDispatcher("profile.jsp");
		rd.forward(req, res);
	}
	
	
	protected void sendComment(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String receiverId = req.getParameter("receiver_id");
		String receiverUsername = req.getParameter("receiver_username");
		String text = req.getParameter("text");
		
		Comment comment = new Comment();
		comment.setReceiverId(receiverId);
		comment.setText(text);
		comment.setCreatedAt(Instant.now());
		
		CommentDAO dao = new CommentDAO();
		dao.addComment(comment);
		
		res.sendRedirect(req.getContextPath() + "/profile?username="+receiverUsername);
	}
	
	protected void SignUp(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		User user = new User();
		user.setName(req.getParameter("name"));
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		
		String confirmPassword = req.getParameter("confirm-password");
		
		try {
			Validation.isNameValid(user.getName());
			Validation.isUsernameValid(user.getUsername());
			Validation.isPasswordValid(user.getPassword(), confirmPassword);
			
			UserDAO dao = new UserDAO();
			
			if(dao.checkUserExists(user.getUsername())) {
				throw new ValidationException("Username already in use");
			}
			
			dao.createUser(user);
			res.sendRedirect(req.getContextPath() + "/profile?username="+user.getUsername());
		}catch (ValidationException e) {
			//Keeps filled form field values after form errors
			req.setAttribute("name", user.getName());
			req.setAttribute("username", user.getUsername());
			req.setAttribute("password", user.getPassword());
			req.setAttribute("confirm-password", user.getPassword());
			
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/sign-up.jsp");
			rd.forward(req, res);
		}
	}
}
