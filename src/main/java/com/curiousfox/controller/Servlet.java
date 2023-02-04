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
import com.curiousfox.model.Comment;
import com.curiousfox.model.User;

@WebServlet(urlPatterns = {"/profile", "/send"})
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
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String action = req.getServletPath();
		if (action.equals("/send")) {
			sendComment(req, res);
		}else {
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
}
