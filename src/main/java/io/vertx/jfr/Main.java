package io.vertx.jfr;

import io.vertx.core.Vertx;
import io.vertx.jfr.impl.VertxMetricsFactoryImpl;

public class Main {
    public static void main(String[] args) {
        // Create a Vert.x instance
        Vertx vertx = Vertx.builder().withMetrics(new VertxMetricsFactoryImpl()).build();

        // Define the message handler
        String address = "my.address";
        vertx.eventBus().<String>consumer(address, message -> {
            System.out.println("Received message: " + message.body());
        });

        // Send a message to the event bus
        String messageToSend = "Hello from Vert.x!";
        vertx.eventBus().<String>send(address, messageToSend);

        // Close the Vert.x instance
        vertx.close();
    }
}