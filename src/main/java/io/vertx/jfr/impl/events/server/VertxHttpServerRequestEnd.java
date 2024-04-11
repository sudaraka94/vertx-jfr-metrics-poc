package io.vertx.jfr.impl.events.server;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

@Name("io.vertx.server.VertxHttpServerRequestEnd")
@Label("Http Server Request End")
@Description("Signals when a request ends in HTTP Server")
@Category({ "Vertx", "VertxHttpServer" })
public class VertxHttpServerRequestEnd extends Event {
    @Label("The uri")
    public String uri;
    @Label("The request method")
    public String method;
    @Label("The total number of bytes read")
    public long bytesRead;
}
