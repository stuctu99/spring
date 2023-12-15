package com.bs.spring.websocket;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChattingServer extends TextWebSocketHandler{

	@Autowired
	private ObjectMapper mapper;
	private Map<String,WebSocketSession> clients=new HashMap();
	
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.debug("채팅서버 접속");
		log.debug(session.getId()+" : " + session.getRemoteAddress());
		
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		log.debug("메세지 받았다!");
		log.debug(message.getPayload()); //클라이언트가 보낸 메세지 
		Message msg = mapper.readValue(message.getPayload(), Message.class);
		switch(msg.getType()) {
			case "open" :  addClient(session, msg); break;
			case "msg" : sendMessage(msg); break;
			
		}
		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.debug("채팅서버 나갔다!");
//		log.debug(status.getReason()+ " " + status.getCode());
		String userId="";
		for(Map.Entry<String,WebSocketSession> client:clients.entrySet()) {
			if(Objects.equals(session,client.getValue())) {
				userId = client.getKey();
				break;
			}
		}
//		deleteClient();
		var msg=Message.builder().type("close").sender(null).build();
		
		sendMessage(msg);
	}

	private void addClient(WebSocketSession session, Message msg) {
//		deleteClient();
		clients.put(msg.getSender(), session);
		sendMessage(msg);
		try {
			Message attendClient = Message.builder().type("attend").msg(mapper.writeValueAsString(clients.keySet())).build();
			sendMessage(attendClient);
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void sendMessage(Message msg) {
		for(Map.Entry<String, WebSocketSession> client : clients.entrySet()) {
			WebSocketSession session = client.getValue();
			try {
				String message = mapper.writeValueAsString(msg);
				session.sendMessage(new TextMessage(message));
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void deleteClient() {
		for(Map.Entry<String, WebSocketSession> client : clients.entrySet()) {
			if(!client.getValue().isOpen()) {
				clients.remove(client.getKey());
				
			}
		}
		log.warn("접속자 수 {}",clients.size());
	}
}
