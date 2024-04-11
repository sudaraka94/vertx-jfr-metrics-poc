package io.vertx.jfr.impl.events.server;

import jdk.jfr.Category;
import jdk.jfr.Description;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

@Name("io.vertx.server.VertxNetServerConnected")
@Label("Net Server TCP  connected")
@Description("Signals when a TCP connection is established with the server")
@Category({ "Vertx", "VertxNetServer" })
public class VertxNetServerDisconnected extends Event {
    @Label("The remote name")
    public String remoteName;
    @Label("The host")
    public String host;
    @Label("The port")
    public int port;
    @Label("The path")
    public String path;
    @Label("Bytes written")
    public long bytesWritten;
    @Label("Bytes read")
    public long bytesRead;
}
