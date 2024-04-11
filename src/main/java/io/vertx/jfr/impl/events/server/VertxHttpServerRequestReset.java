package io.vertx.jfr.impl.events.server;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

@Name("io.vertx.server.VertxHttpServerRequestReset")
@Label("Http Server Request Reset")
@Description("Signals when a request resets in HTTP Server")
@Category({ "Vertx", "VertxHttpServer" })
public class VertxHttpServerRequestReset extends Event {
    @Label("The uri")
    public String uri;
    @Label("The request method")
    public String method;
}
