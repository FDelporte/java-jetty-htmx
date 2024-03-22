# Example Java application with Jetty webserver and htmx web user interfaces

Example application showing how to configure an embedded Jetty server to use in combination with an htmx web interface
via:

* Static files included in the resources
* REST APIs
* Websocket

## About htmx

Read more about htmx:

* https://htmx.org/
* https://htmx.org/extensions/web-sockets/

## Running the Application

* Open the project in your IDE as Maven project.
* Open `be.webtechie.javajettyhtmx.Application.java`.
* Click on run on the main method.
* Open the browser on one of these pages:
    * http://localhost:9999/index.html
    * http://localhost:9999/websocket.html

## Technical Info

* Random text output is generated with
  the [Datafaker library](https://www.datafaker.net/documentation/usage/#default-usage).