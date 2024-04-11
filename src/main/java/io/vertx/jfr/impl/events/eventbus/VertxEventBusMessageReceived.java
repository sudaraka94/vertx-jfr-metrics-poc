package io.vertx.jfr.impl.events.eventbus;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

@Name("io.vertx.eventbus.VertxEventBusMessageReceived")
@Label("EventBus Message Received")
@Description("Signals when a message is being received via Vertx Event Bus")
@Category({ "Vertx", "VertxEventBus" })
public class VertxEventBusMessageReceived extends Event {
    @Label("The address")
    public String address;
    @Label("If the message was published")
    public boolean publish;
    @Label("If the message is received locally")
    public boolean local;
    @Label("Number of handlers that process the message")
    public int handlers;
}
