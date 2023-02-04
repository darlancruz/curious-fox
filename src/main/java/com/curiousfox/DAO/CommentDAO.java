package com.curiousfox.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

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
	
	public ArrayList<Comment> getAllComments(String userId) {
		String sql = "SELECT * FROM comments WHERE receiver_id = ? ORDER BY created_at DESC";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setObject(1, userId, Types.OTHER);
			ResultSet rs = stmt.executeQuery();
			
			ArrayList<Comment> commentsArr = new ArrayList<Comment>();
			while (rs.next()) {
				String commentId = rs.getString("comment_id");
				String senderId = rs.getString("sender_id");
				String receiverId = rs.getString("receiver_id");
				String text = rs.getString("comment_text");
				Timestamp createdAt = rs.getTimestamp("created_at");
				
				Comment comment = new Comment();
				comment.setId(commentId);
				comment.setSenderId(senderId);
				comment.setReceiverId(receiverId);
				comment.setText(text);
				comment.setCreatedAt(createdAt.toInstant());
				
				commentsArr.add(comment);
			}
			
			stmt.close();
			return commentsArr;
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
