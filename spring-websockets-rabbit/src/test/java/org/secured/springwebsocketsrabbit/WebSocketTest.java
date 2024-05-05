package org.secured.springwebsocketsrabbit;

import org.junit.jupiter.api.Test;
import org.secured.springwebsocketsrabbit.controller.MessageController;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebSocketTest {

    private final WebSocketStompClient webSocketClient;

    private final SockJsClient sockJsClient;

    @MockBean
    private final MessageController messageController;

    public WebSocketTest(WebSocketStompClient webSocketClient, SockJsClient sockJsClient, MessageController messageController) {
        this.webSocketClient = webSocketClient;
        this.sockJsClient = sockJsClient;
        this.messageController = messageController;
    }

    @Test
    public void testSendMessage() throws InterruptedException, ExecutionException, TimeoutException {
        StompSession session = webSocketClient.connect("/chat", new StompSessionHandlerAdapter() {}).get(1, SECONDS);
        session.subscribe("/topic/response", new DefaultStompFrameHandler());

        String message = "Hello, WebSocket!";
        CompletableFuture<String> responseFuture = new CompletableFuture<>();
        session.send("/app/message", message.getBytes());

        String responseMessage = responseFuture.get(10, SECONDS);
        assertEquals("Hello, WebSocket!", responseMessage);
    }


}
