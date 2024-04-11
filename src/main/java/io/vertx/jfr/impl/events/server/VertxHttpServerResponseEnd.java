package io.vertx.jfr.impl.events.server;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

@Name("io.vertx.server.VertxHttpServerResponseEnd")
@Label("Http Server Response End")
@Description("Signals when a response ends in HTTP Server")
@Category({ "Vertx", "VertxHttpServer" })
public class VertxHttpServerResponseEnd extends Event {
    @Label("The uri")
    public String uri;
    @Label("The request method")
    public String method;
    @Label("The total number of bytes read")
    public int statusCode;
    @Label("The total number of bytes read")
    public long bytesWritten;
}
