package io.vertx.jfr.impl.event;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

@Name("io.vertx.VertxEventBusHandlerUnregistered")
@Label("EventBus Handler Unregistered")
@Description("Signals when a handler gets unregistered on the eventbus")
@Category({ "VertxEventBus" })
public class VertxEventBusHandlerUnregistered extends Event {
    @Label("Address of the unregistered Handler")
    public String address;
}
