package io.vertx.jfr.impl.events.eventbus;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

@Name("io.vertx.eventbus.VertxEventBusMessageSent")
@Label("EventBus Message Sent")
@Description("Signals when a message is being sent via Vertx Event Bus")
@Category({ "Vertx", "VertxEventBus" })
public class VertxEventBusMessageSent extends Event {
    @Label("The address")
    public String address;
    @Label("If the message was published")
    public boolean publish;
    @Label("If the message is received locally")
    public boolean local;
    @Label("If the message is sent on the cluster")
    public boolean remote;
}
