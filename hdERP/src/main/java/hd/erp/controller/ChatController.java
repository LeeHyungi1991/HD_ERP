package hd.erp.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import hd.erp.dto.ChatMessageDTO;

@Controller
public class ChatController {

	@MessageMapping("/chat.sendMessage")
	@SendTo("/topic/public")
	public ChatMessageDTO sendMessage(@Payload ChatMessageDTO chatMessage) {
		return chatMessage;
	}
	
	@MessageMapping("/chat.addUser")
	@SendTo("/topic/public")
	public ChatMessageDTO addUser(@Payload ChatMessageDTO chatMessage,SimpMessageHeaderAccessor headerAccess) {
		headerAccess.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}
}
