# Example Java Application with Jetty Webserver and htmx Website

Example application showing how to configure an embedded Jetty server to use in combination with an htmx web user
interface
via:

* Static files included in the resources
* REST APIs
* WebSocket communication

Full information is available here:

* [Blog post on webtechie.be](https://webtechie.be/post/2024-03-28-java-jetty-htmx-websocket-example/)
* [YouTube video with code-walk-through](https://www.youtube.com/watch?v=ko-tIacI-u8)

## Technical Info

### Jetty

See https://eclipse.dev/jetty/.

_"Jetty provides a web server and servlet container, additionally providing support for HTTP/2, WebSocket, OSGi, JMX,
JNDI, JAAS and many other integrations. These components are open source and are freely available for commercial use and
distribution."_

### htmx

See https://htmx.org/ and https://htmx.org/extensions/web-sockets/.

_"htmx gives you access to AJAX, CSS Transitions, WebSockets and Server Sent Events directly in HTML, using attributes,
so you can build modern user interfaces with the simplicity and power of hypertext"_

### Other

* Random text output is generated with
  the [Datafaker library](https://www.datafaker.net/documentation/usage/#default-usage).

## Running the Application

### In IntelliJIDEA

* Open the project in your IDE as Maven project.
* Open `be.webtechie.javajettyhtmx.Application`.
* Click on run on the `main` method.
* Open the browser on one of these pages:
    * http://localhost:9999/index.html
    * http://localhost:9999/websocket.html
* Test the REST APIs:
    * http://localhost:9999/rest/text
    * http://localhost:9999/rest/list
* You can test the WebSocket on this endpoint: `ws://localhost:9999/websocket/ws`

### From JAR

* Build the application with `mvn package`.
* Start it with `java -jar jetty-htmx-demo.jar`
