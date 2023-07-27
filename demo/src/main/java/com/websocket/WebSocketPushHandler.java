package com.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class WebSocketPushHandler extends TextWebSocketHandler {
    private static final List<WebSocketSession> userList = new ArrayList<>();


    /**
     * 用户进入 系统监听
     *
     * @param session
     * @throws Exception
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info(session.getAttributes() + "用户进入系统。。。");
        log.info("用户信息:" + session.getAttributes());
        Map<String, Object> map = session.getAttributes();
        for (String key : map.keySet()) {
            log.info("key:" + key + "and value: " + map.get(key));
        }
        userList.add(session);
        sendMessagesToUsers(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("系统处理[" + session.getAttributes().get("userId") + "]用户的请求信息==>" + message.getPayload());
        sendMessageToUser((String) session.getAttributes().get("userId"), message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        userList.remove(session);
        log.info("xxx用户退出系统");
    }

    public void sendMessagesToUsers(WebSocketSession session) {
        String msg = "欢迎'" + session.getAttributes().get("userId");
        TextMessage message = new TextMessage(msg);
        for (WebSocketSession user : userList) {
            try {
                // isOpen() 在线就发送
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getLocalizedMessage());
            }
        }
    }

    public void sendMessageToUser(String userId, TextMessage message) {
        for (WebSocketSession user : userList) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
                log.error(e.getLocalizedMessage());
            }
        }
    }


}
