package be.webtechie.javajettyhtmx.webserver;

import be.webtechie.javajettyhtmx.rest.ListService;
import be.webtechie.javajettyhtmx.rest.TextService;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Represents a WebSocket endpoint for handling events.
 */
@ClientEndpoint
@ServerEndpoint(value = "/ws")
public class MyEventSocket {

    private final ScheduledExecutorService scheduler;
    private final ListService listService;
    private final TextService textService;

    public MyEventSocket() {
        scheduler = Executors.newScheduledThreadPool(10);
        listService = new ListService();
        textService = new TextService();
    }

    @OnOpen
    public void onWebSocketConnect(Session session) {
        System.out.println("Socket connected: " + session);
        scheduler.scheduleAtFixedRate(new StatusSender(session), 1, 1, TimeUnit.SECONDS);
    }

    @OnMessage
    public void onWebSocketText(Session session, String message) {
        System.out.println("Received: " + message);

        if (message.contains("replaceBelow")) {
            session.getAsyncRemote().sendText("<p id=\"toReplace\">" + textService.getRandomParagraph(5) + "</li>");
        } else if (message.contains("replaceThis")) {
            session.getAsyncRemote().sendText("<p id=\"replaceThis\">" + textService.getRandomParagraph(10) + "</p>");
        } else {
            System.out.println("Sorry, I don't know how to handle this request...");
        }
    }

    @OnClose
    public void onWebSocketClose(CloseReason reason) {
        System.out.println("Socket closed: " + reason);
    }

    @OnError
    public void onWebSocketError(Throwable cause) {
        System.err.println("Websocket error: " + cause.getMessage());
    }

    public void awaitClosure() throws InterruptedException {
        System.out.println("Awaiting closure");
    }
}
