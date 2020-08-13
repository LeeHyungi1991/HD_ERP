package hd.erp.dto;

import hd.erp.config.MessageType;
import lombok.Data;
@Data
public class ChatMessageDTO {

	private MessageType type;
	private String content;
	private String sender;
}
