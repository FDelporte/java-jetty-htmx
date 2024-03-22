package be.webtechie.javajettyhtmx.rest;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ListService extends HttpServlet {

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(getNewListItem());
    }

    public String getNewListItem() {
        return "<li>" + LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME) + "</li>";
    }
}
