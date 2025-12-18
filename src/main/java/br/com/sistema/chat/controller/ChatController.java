package br.com.sistema.chat.controller;

import java.time.LocalDateTime;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import br.com.sistema.chat.model.ChatMessage;

@Controller
public class ChatController {

	/**
	 * Recebe uma mensagem do cliente e envia para todos da mesma sala
	 * 
	 * Fluxo:
	 * 1. Cliente envia mensagem para: /app/chat.send/geral
	 * 2. Servidor adiciona o timestamp (data/hora)
	 * 3. Servidor envia para: /topic/chat/geral
	 * 4. Todos inscritos em /topic/chat/geral recebem a mensagem
	 * 
	 * @param room - Nome da sala (extraído da URL)
	 * @param message - Mensagem recebida do cliente
	 * @return Mensagem com timestamp atualizado
	 */
	@MessageMapping("/chat.send/{room}")				// Escuta mensagens em /app/chat.send/{room}
	@SendTo("/topic/chat/{room}")						// Envia resposta para /topic/chat/{room}
	public ChatMessage sendToRoom(@DestinationVariable String room, ChatMessage message) {
		message.setTimestamp(LocalDateTime.now());		// Adiciona a data e hora atual na mensagem
		return message;									// Retorna a mensagem que será enviada para todos da sala
	}
	
}
