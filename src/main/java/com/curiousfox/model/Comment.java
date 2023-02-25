package com.curiousfox.model;

import java.time.Instant;

public class Comment {
	private String id;
	private String parentId;
	private String senderId;
	private String receiverId;
	private String text;
	private Instant createdAt;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getParentId() {
		return parentId;
	}
	
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public String getSenderId() {
		return senderId;
	}
	
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	
	public String getReceiverId() {
		return receiverId;
	}
	
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Instant getCreatedAt() {
		return createdAt;
	}
	
	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}
	
}
