package br.com.sistema.chat.model;

import java.time.LocalDateTime;

public class ChatMessage {

	private String from;
	private String content;
	private LocalDateTime timestamp;
	
	public ChatMessage() {
		// TODO Auto-generated constructor stub
	}

	public ChatMessage(String from, String content, LocalDateTime timestamp) {
		super();
		this.from = from;
		this.content = content;
		this.timestamp = timestamp;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
}