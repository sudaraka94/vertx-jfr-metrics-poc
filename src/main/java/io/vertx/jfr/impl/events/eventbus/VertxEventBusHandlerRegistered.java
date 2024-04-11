package io.vertx.jfr.impl.events.eventbus;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

@Name("io.vertx.eventbus.VertxEventBusHandlerRegistered")
@Label("EventBus Handler Registered")
@Description("Signals when a handler gets registered on the eventbus")
@Category({ "Vertx", "VertxEventBus" })
public class VertxEventBusHandlerRegistered extends Event {
    @Label("Address of the registered Handler")
    public String address;
}
