package be.webtechie.javajettyhtmx.webserver;

import be.webtechie.javajettyhtmx.rest.ListService;
import be.webtechie.javajettyhtmx.rest.TextService;
import org.eclipse.jetty.ee10.servlet.ServletContextHandler;
import org.eclipse.jetty.ee10.websocket.jakarta.server.config.JakartaWebSocketServletContainerInitializer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.util.resource.ResourceFactory;

import java.net.URL;
import java.nio.file.Paths;

public class MyWebServer implements Runnable {

    private final Server server;

    public MyWebServer(int port) {
        server = new Server();

        try {
            ServerConnector connector = new ServerConnector(server);
            connector.setPort(port);
            server.addConnector(connector);

            ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
            server.setHandler(contextHandlerCollection);

            System.out.println("Initializing the static file handler");
            initStaticFileHandler(contextHandlerCollection);
            System.out.println("Initializing the REST endpoints");
            initRest(contextHandlerCollection);
            System.out.println("Initializing the websocket");
            initWebsocket(contextHandlerCollection);
        } catch (Exception e) {
            System.err.println("Problem initializing the Jetty server: " + e.getMessage());
        }
    }

    private void initStaticFileHandler(ContextHandlerCollection contextHandlerCollection) throws Exception {
        URL url = MyWebServer.class.getClassLoader().getResource("web");

        if (url == null) {
            throw new Exception("Could not find the web files");
        }

        ResourceFactory.Closeable resourceFactory = ResourceFactory.closeable();
        Resource resource = resourceFactory.newResource(url.toExternalForm());

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setBaseResource(resource);

        ContextHandler contactHandler = new ContextHandler();
        contactHandler.setContextPath("/");
        contactHandler.setBaseResourceAsPath(Paths.get(url.toURI()));
        contactHandler.setHandler(resourceHandler);

        contextHandlerCollection.addHandler(contactHandler);
    }

    private void initRest(ContextHandlerCollection contextHandlerCollection) {
        ServletContextHandler apiHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        apiHandler.setContextPath("/rest");
        contextHandlerCollection.addHandler(apiHandler);
        apiHandler.addServlet(ListService.class, "/list");
        apiHandler.addServlet(TextService.class, "/text");
    }

    private void initWebsocket(ContextHandlerCollection contextHandlerCollection) {
        ServletContextHandler webserviceContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        webserviceContextHandler.setContextPath("/websocket");
        contextHandlerCollection.addHandler(webserviceContextHandler);
        JakartaWebSocketServletContainerInitializer.configure(webserviceContextHandler, (servletContext, websocketContainer) -> {
            websocketContainer.setDefaultMaxTextMessageBufferSize(1024);
            websocketContainer.addEndpoint(MyEventSocket.class);
        });
    }

    public void start() throws Exception {
        server.start();
    }

    public void join() throws InterruptedException {
        server.join();
    }

    @Override
    public void run() {
        try {
            start();
            join();
        } catch (Exception e) {
            System.err.println("Could not start server: " + e.getMessage());
        }
    }
}
