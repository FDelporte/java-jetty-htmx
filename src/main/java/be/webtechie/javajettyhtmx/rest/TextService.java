package be.webtechie.javajettyhtmx.rest;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.datafaker.Faker;

import java.io.IOException;

public class TextService extends HttpServlet {

    private final Faker faker = new Faker();

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("GET: " + req);
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("<p>" + getRandomParagraph(10) + "</p>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("POST: " + req.getParameterMap());
        resp.setContentType("text/html");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write("<p>Hello " + req.getParameter("name") + ", thank you for using this demo.</p>");
    }

    public String getRandomParagraph(int numberOfSentences) {
        return faker.lorem().paragraph(numberOfSentences);
    }
}
