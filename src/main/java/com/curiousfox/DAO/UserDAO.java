package com.curiousfox.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.curiousfox.jdbc.ConnectionFactory;
import com.curiousfox.model.User;
import com.curiousfox.utils.DiceBearUrlGenerator;

public class UserDAO {
	private Connection connection;
	
	public UserDAO() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public User getUser(String username){
		String sql = "select * from accounts where username = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			User user = new User();
			while(rs.next()) {
				user.setId(rs.getString("user_id"));
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setBio(rs.getString("bio"));
				user.setEmail(rs.getString("email"));
				user.setPictureUrl(rs.getString("picture_url"));	
			}
			
			rs.close();
			stmt.close();
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public User getUserById(String userId){
		String sql = "select * from accounts where user_id = ?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setObject(1, userId, Types.OTHER);
			ResultSet rs = stmt.executeQuery();
			
			User user = new User();
			while(rs.next()) {
				user.setId(rs.getString("user_id"));
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setBio(rs.getString("bio"));
				user.setEmail(rs.getString("email"));
				user.setPictureUrl(rs.getString("picture_url"));	
			}
			
			rs.close();
			stmt.close();
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void createUser(User user) {
		String sql = "INSERT INTO accounts (name, username, password, picture_url) VALUES (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getUsername());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, DiceBearUrlGenerator.getUrl());
			
			stmt.execute();
			stmt.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	} 
	
	public boolean checkUserExists(String username) {
		boolean userExists;
		
		String sql = "SELECT * FROM accounts WHERE username=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				userExists = true;
			}else {
				userExists = false;
			}
			
			rs.close();
			stmt.close();
			return userExists;
		}catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ArrayList<User> getListOfRandomUsers() {
		String sql = "SELECT * FROM accounts ORDER BY random() LIMIT 6";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			ArrayList<User> users = new ArrayList<User>();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getString("user_id"));
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setBio(rs.getString("bio"));
				user.setEmail(rs.getString("email"));
				user.setPictureUrl(rs.getString("picture_url"));	
				
				users.add(user);
			}
			rs.close();
			stmt.close();
			return users;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ArrayList<User> searchUser(String name){
		String sql = "SELECT * FROM accounts WHERE name LIKE '%"+name+"%' OR username LIKE '%"+name+"%' LIMIT 6";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();

			ArrayList<User> users = new ArrayList<User>();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getString("user_id"));
				user.setName(rs.getString("name"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setBio(rs.getString("bio"));
				user.setEmail(rs.getString("email"));
				user.setPictureUrl(rs.getString("picture_url"));	
				
				users.add(user);
			}
			rs.close();
			stmt.close();
			return users;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
