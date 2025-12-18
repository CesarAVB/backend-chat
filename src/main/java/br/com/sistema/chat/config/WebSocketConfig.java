package br.com.sistema.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	/**
	 * Registra o endpoint onde os clientes irão se conectar
	 * 
	 * Exemplo: O frontend conecta em http://localhost:8080/ws
	 * 
	 * @param registry - Registro de endpoints STOMP
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
 		
		// Define o endpoint "/ws" para conexão
		registry.addEndpoint("/ws")
 		
 		// Permite conexões de qualquer origem (desenvolvimento)
 		// Em produção, substitua "*" pelo endereço específico do frontend
 		.setAllowedOriginPatterns("*")
 		
 		// Habilita SockJS como alternativa caso WebSocket não funcione
 		.withSockJS();
	}
	
	
	/**
	 * Configura como as mensagens serão roteadas no servidor
	 * 
	 * Define os prefixos que o servidor vai usar para:
	 * 1. Enviar mensagens para os clientes (/topic e /queue)
	 * 2. Receber mensagens dos clientes (/app)
	 * 
	 * @param registry - Registro do broker de mensagens
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		
		// Habilita broker simples para enviar mensagens aos clientes
		// /topic - usado para broadcast (várias pessoas)
		// /queue - usado para mensagens privadas (uma pessoa)
		registry.enableSimpleBroker("/topic", "/queue");
		
		// Define que mensagens vindas do cliente devem começar com /app
		// Exemplo: cliente envia para "/app/chat.send/geral"
		registry.setApplicationDestinationPrefixes("/app");
	}
	
}
