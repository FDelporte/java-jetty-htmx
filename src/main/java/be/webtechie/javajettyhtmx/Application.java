package be.webtechie.javajettyhtmx;

import be.webtechie.javajettyhtmx.webserver.MyWebServer;

public class Application {

    public static void main(String[] args) {
        var server = new MyWebServer(9999);
        (new Thread(server)).start();
    }
}
