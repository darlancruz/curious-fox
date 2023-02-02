package com.curiousfox.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;

import com.curiousfox.jdbc.ConnectionFactory;
import com.curiousfox.model.Comment;

public class CommentDAO {
	private Connection connection;
	
	public CommentDAO(){
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void addComment(Comment comment) {
		String sql = "INSERT INTO comments (sender_id, receiver_id, comment_text, created_at) values (?,?,?,?)";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setObject(1, comment.getSenderId(), Types.OTHER);
			stmt.setObject(2, comment.getReceiverId(), Types.OTHER);
			stmt.setString(3, comment.getText());
			stmt.setTimestamp(4, Timestamp.from(comment.getCreatedAt()));
			
			stmt.execute();
			stmt.close();
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}	
	}
}
