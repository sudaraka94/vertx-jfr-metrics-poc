package io.vertx.jfr.impl.events.server;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

@Name("io.vertx.server.VertxHttpServerRequestBegin")
@Label("Http Server Request Begin")
@Description("Signals when a request begins in HTTP Server")
@Category({ "Vertx", "VertxHttpServer" })
public class VertxHttpServerRequestBegin extends Event {
    @Label("The uri")
    public String uri;
    @Label("The request method")
    public String method;
}
