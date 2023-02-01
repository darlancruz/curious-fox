package com.curiousfox.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.curiousfox.jdbc.ConnectionFactory;
import com.curiousfox.model.User;

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
				user.setPicture_url(rs.getString("picture_url"));	
			}
			
			rs.close();
			stmt.close();
			return user;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
