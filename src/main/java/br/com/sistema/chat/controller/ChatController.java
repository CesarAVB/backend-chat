package br.com.sistema.chat.controller;

import java.time.LocalDateTime;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import br.com.sistema.chat.model.ChatMessage;

@Controller
public class ChatController {

	@MessageMapping("/chat.send/{room}")
	@SendTo("/topic/chat/{room}")
	public ChatMessage sendToRoom(@DestinationVariable String room, ChatMessage message) {
		message.setTimestamp(LocalDateTime.now());
		return message;
	}
	
}
