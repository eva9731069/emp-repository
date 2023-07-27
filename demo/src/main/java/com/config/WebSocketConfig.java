package com.config;

import com.websocket.MyWebSocketInterceptor;
import com.websocket.WebSocketPushHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;


@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {


    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(createWebSocketPushHandler(), "/webSocketServer")
                .addInterceptors(createHandshakeInterceptor()).setAllowedOrigins("*");
        registry.addHandler(createWebSocketPushHandler(), "/sockjs/webSocketServer")
                .addInterceptors(createHandshakeInterceptor()).withSockJS();


    }

    /**
     * 握手拦截器
     *
     * @return
     */
    @Bean
    public HandshakeInterceptor createHandshakeInterceptor() {
        return new MyWebSocketInterceptor();
    }

    @Bean
    public WebSocketHandler createWebSocketPushHandler() {
        return new WebSocketPushHandler();
    }

}
