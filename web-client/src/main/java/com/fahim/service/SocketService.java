package com.fahim.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Slf4j
@Service
public class SocketService {

    private StompSession session;

    public void connect() {
        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSessionHandler sessionHandler = new SessionHandlerAdapter();
        ListenableFuture<StompSession> future = stompClient.connect("ws://localhost:8012/socket", sessionHandler);
        future.addCallback(new ListenableFutureCallback<StompSession>() {
            @Override
            public void onFailure(@NonNull Throwable throwable) {
                System.out.println("failed");
                SocketService.this.session = null;
            }

            @Override
            public void onSuccess(StompSession session) {
                System.out.println("success");
                SocketService.this.session = session;
            }
        });
    }

    public void disconnect() {
        if (session == null)
            return;
        if (session.isConnected())
            session.disconnect();
        session = null;
    }

    private static class SessionHandlerAdapter extends StompSessionHandlerAdapter {
        @Override
        public void afterConnected(StompSession session, @NonNull StompHeaders connectedHeaders) {
            log.info("AfterConnected SessionID:" + session.getSessionId());
        }

        @Override
        public void handleTransportError(@NonNull StompSession session, Throwable exception) {
            log.error(exception.getLocalizedMessage());
        }
    }
}
