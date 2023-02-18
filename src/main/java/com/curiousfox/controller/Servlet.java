package com.curiousfox.controller;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.curiousfox.DAO.CommentDAO;
import com.curiousfox.DAO.UserDAO;
import com.curiousfox.exception.ValidationException;
import com.curiousfox.model.Comment;
import com.curiousfox.model.User;
import com.curiousfox.utils.Validation;

@WebServlet(urlPatterns = {"", "/profile", "/send","/sign-up","/login","/logout","/update-bio"})
public class Servlet extends HttpServlet {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		String action = req.getServletPath();
	
		if (action.equals("")) {
			setupHomepage(req, res);
		}
		if (action.equals("/profile")) {
			getUser(req, res);
		}
		if(action.equals("/sign-up")) {
			RequestDispatcher rd = req.getRequestDispatcher("sign-up.jsp");
			rd.forward(req, res);
		}
		if(action.equals("/login")) {
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.forward(req, res);
		}
		if(action.equals("/logout")) {
			HttpSession session = req.getSession();
			session.invalidate();
			
			res.sendRedirect("./");
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
		if(action.equals("/login")) {
			Login(req, res);
		}
		if(action.equals("/update-bio")) {
			UpdateUserBio(req, res);
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
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		if(Objects.nonNull(user)) {
			comment.setSenderId(user.getId());
		}
		
		CommentDAO dao = new CommentDAO();
		dao.addComment(comment);
		
		res.sendRedirect(req.getContextPath() + "/profile?username="+receiverUsername);
	}
	
	protected void SignUp(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		User user = new User();
		user.setName(req.getParameter("name"));
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		
		String confirmPassword = req.getParameter("confirm_password");
		
		try {
			Validation.isNameValid(user.getName());
			Validation.isUsernameValid(user.getUsername());
			Validation.isPasswordValid(user.getPassword(), confirmPassword);
			
			UserDAO dao = new UserDAO();
			
			if(dao.checkUserExists(user.getUsername())) {
				throw new ValidationException("Username already in use");
			}
			
			dao.createUser(user);
			User createdUser = dao.getUser(user.getUsername());
			
			HttpSession session = req.getSession();
			session.setAttribute("user", createdUser);
			
			res.sendRedirect(req.getContextPath() + "/profile?username="+user.getUsername());
		}catch (ValidationException e) {
			//Keeps filled form field values after form errors
			req.setAttribute("name", user.getName());
			req.setAttribute("username", user.getUsername());
			req.setAttribute("password", user.getPassword());
			req.setAttribute("confirm_password", user.getPassword());
			
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/sign-up.jsp");
			rd.forward(req, res);
		}
	}
	
	protected void Login(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		try {
			UserDAO dao = new UserDAO();
			User user = dao.getUser(username);
			
			
			if(user.getUsername() == null) {
				throw new ValidationException("Sorry, we could not find your account");
			}
			
			if(!user.getPassword().equals(password)) {
				throw new ValidationException("Wrong password!");
			}
			
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			
			res.sendRedirect(req.getContextPath() + "/profile?username="+user.getUsername());
		} catch (ValidationException e) {
			//Keeps filled form field values after form errors
			req.setAttribute("username", username);	
			req.setAttribute("password", password);
	
			req.setAttribute("error", e);
			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
			rd.forward(req, res);
		}
		
	}
	
	protected void setupHomepage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{ 
		UserDAO dao = new UserDAO();
		
		String search = req.getParameter("search");
		
		if(search == null || search.isBlank()) {
			ArrayList<User> listOfRadomUsers = dao.getListOfRandomUsers();
			req.setAttribute("list_users", listOfRadomUsers);
			
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, res);
			return;
		}
		
		ArrayList<User> listOfSearchedUsers = dao.searchUser(search);
		req.setAttribute("results_for", search);
		req.setAttribute("list_users", listOfSearchedUsers);
		
		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
		rd.forward(req, res);
	
	}
	
	protected void UpdateUserBio(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		String newBio = req.getParameter("new_bio");
		
		UserDAO dao = new UserDAO();
		dao.UpdateBio(user.getId(), newBio);
	
		res.sendRedirect(req.getContextPath() + "/profile?username="+user.getUsername());
	}
}
