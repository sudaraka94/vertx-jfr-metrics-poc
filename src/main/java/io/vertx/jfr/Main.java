package io.vertx.jfr;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.jfr.impl.VertxMetricsFactoryImpl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {
    public static void main(String[] args) throws IOException {
        // Create a Vert.x instance
        Vertx vertx = Vertx.builder().withMetrics(new VertxMetricsFactoryImpl()).build();

        // =========== Simulating Event Bus Behaviour ===========
        // Define the message handler
        String address = "my.address";
        vertx.eventBus().<String>consumer(address, message -> {
            System.out.println("Received message: " + message.body());
        });

        // Send a message to the event bus
        String messageToSend = "Hello from Vert.x!";
        vertx.eventBus().<String>send(address, messageToSend);

        // =========== Simulating HTTP Server Behaviour ===========
        HttpServer server = setupServer(vertx);

        // send some messages
        sendGet();

        // Close the Vert.x instance
        vertx.close();
    }

    private static HttpServer setupServer(Vertx vertx) {
        HttpServer server = vertx.createHttpServer();

        // Create a router for handling requests
        Router router = Router.router(vertx);

        // Attach a GET handler for the root path "/"
        router.get("/").handler(Main::handleGet);

        // Attach a POST handler for the path "/data"
        router.post("/").handler(Main::handlePost);

        // Start the server on port 8080
        server.requestHandler(router).listen(8080).andThen(result -> {
            System.out.println("Server started on port 8080");
        });

        return server;
    }

    private static void handleGet(RoutingContext context) {
        // Handle GET request, you can send a response or perform some logic here
        context.response().end("Hello from GET handler!");
    }

    private static void handlePost(RoutingContext context) {
        // Handle POST request, you can access the request body and perform some logic here
        String body = context.body().asString();
        System.out.println("Received POST request with body: " + body);
        context.response().end("Hello from POST handler!");
    }

    // Just focusing on the POC, this can be done way better
    private static void sendGet() throws IOException {
        URL url = new URL("http://localhost:8080");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        if (connection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code " + connection.getResponseCode());
        }

        connection.disconnect();
    }
}
