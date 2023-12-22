package com.bs.spring.websocket;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChattingServer extends TextWebSocketHandler{
	
	@Autowired
	private ObjectMapper mapper;
	
	private Map<String,WebSocketSession> clients=new HashMap();
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		attendMessage();
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, 
			TextMessage message) throws Exception {
		Message msg=mapper.readValue(message.getPayload(), Message.class);
		switch(msg.getType()) {
			case "open" : addClient(session,msg);break; 
			case "msg" : sendMessage(msg);break;
		}		
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		log.debug("채팅서버 나갔다!");
//		String userId="";
//		for(Map.Entry<String, WebSocketSession> client:clients.entrySet()) {
//			if(Objects.equals(session,client.getValue())) {
//				userId=client.getKey();
//				break;
//			}
//		}
		deleteClient(session);
		
	}
	
	private void addClient(WebSocketSession session,Message msg) {
		clients.put(msg.getSender(), session);
		sendMessage(msg);
		attendMessage();
	}
	
	private void attendMessage() {
		try {
			Message attendClient=Message.builder().type("attend")
				.msg(mapper.writeValueAsString(clients.keySet())).build();
			sendMessage(attendClient);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	private void sendMessage(Message msg) {
		for(Map.Entry<String, WebSocketSession> client : clients.entrySet()) {
			WebSocketSession session=client.getValue();
			try {
				String message=mapper.writeValueAsString(msg);
				session.sendMessage(new TextMessage(message));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void deleteClient(WebSocketSession delSession) {
				
		/*
		 * clients.forEach((key,value)->{ if(!value.isOpen()) clients.remove(key); });
		 */
		
		Iterator<Map.Entry<String, WebSocketSession>> itclient
			=this.clients.entrySet().iterator();
		var userId="";
		while(itclient.hasNext()) {
			Map.Entry<String, WebSocketSession> client=itclient.next();
			if(client.getValue().equals(delSession)) {
				userId=client.getKey();
				itclient.remove();
			}
		}
		
		//접속종료된 사용자 알림메세지 전송!
		var msg=Message.builder().type("close").sender(userId).build();
		
		sendMessage(msg);
		attendMessage();
	}
}
