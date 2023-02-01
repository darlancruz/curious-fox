package com.curiousfox.model;

import java.time.Instant;
import java.util.Calendar;

public class Comment {
	String id;
	String sender_id;
	String receiver_id;
	String text;
	Instant created_at;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getSender_id() {
		return sender_id;
	}
	
	public void setSender_id(String sender_id) {
		this.sender_id = sender_id;
	}
	
	public String getReceiver_id() {
		return receiver_id;
	}
	
	public void setReceiver_id(String receiver_id) {
		this.receiver_id = receiver_id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Instant getCreated_at() {
		return created_at;
	}
	
	public void setCreated_at(Instant created_at) {
		this.created_at = created_at;
	}
	
}
