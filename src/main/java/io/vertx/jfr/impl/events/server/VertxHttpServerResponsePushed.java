package io.vertx.jfr.impl.events.server;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

@Name("io.vertx.server.VertxHttpServerResponsePushed")
@Label("Http Server Response Pushed")
@Description("Signals when a response is pushed in HTTP Server")
@Category({ "Vertx", "VertxHttpServer" })
public class VertxHttpServerResponsePushed extends Event {
    @Label("The uri")
    public String uri;
    @Label("The request method")
    public String method;
    @Label("The total number of bytes read")
    public int statusCode;
}
