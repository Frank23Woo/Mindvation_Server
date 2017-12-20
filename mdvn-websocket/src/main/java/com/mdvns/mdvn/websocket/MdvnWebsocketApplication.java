package com.mdvns.mdvn.websocket;


import com.mdvns.mdvn.websocket.service.WebSocketService;
import com.mdvns.mdvn.websocket.service.impl.MyWebSocket;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class MdvnWebsocketApplication {

	@Bean
	public WebSocketService webSocketService() {
		return new MyWebSocket();
	}

	public static void main(String[] args) {
		SpringApplication.run(MdvnWebsocketApplication.class, args);
	}
}
