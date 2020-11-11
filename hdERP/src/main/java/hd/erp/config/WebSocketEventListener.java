
package hd.erp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import hd.erp.dto.ChatMessageDTO;


@Component
//@Slf4j ·Òº¹ ¤Ð¤Ð
public class WebSocketEventListener {

	
	private static final Logger log = LoggerFactory.getLogger(WebSocketEventListener.class);

	
	@Autowired
	private SimpMessageSendingOperations messagingTemplate;
	
	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent event) {
		log.info("Received a new web socket connection");
	}
	
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		String username =(String) headerAccessor.getSessionAttributes().get("username");
		if(username != null) {
			log.info("User Disconnected : "+username);
			
			ChatMessageDTO chatMessage =new ChatMessageDTO();
			chatMessage.setType(MessageType.LEAVE);
			chatMessage.setSender(username);
			
			messagingTemplate.convertAndSend("/topic/public",chatMessage);
		}
	}
}
