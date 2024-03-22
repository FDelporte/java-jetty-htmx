package be.webtechie.javajettyhtmx.webserver;

import jakarta.websocket.Session;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StatusSender implements Runnable {

    private final Session session;

    public StatusSender(Session session) {
        this.session = session;
    }

    @Override
    public void run() {
        session.getAsyncRemote().sendText("<p id=\"connectionTimestamp\">" + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + "</p>");
    }
}
