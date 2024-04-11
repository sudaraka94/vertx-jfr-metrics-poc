package io.vertx.jfr.impl;

import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.TCPMetrics;
import io.vertx.jfr.impl.events.server.VertxNetServerConnected;
import io.vertx.jfr.impl.events.server.VertxNetServerDisconnected;

import java.util.concurrent.atomic.LongAdder;

public class VertxNetServerMetrics implements TCPMetrics<VertxNetServerMetrics.NetServerSocketMetric> {

    @Override
    public NetServerSocketMetric connected(SocketAddress remoteAddress, String remoteName) {
        VertxNetServerConnected event = new VertxNetServerConnected();
        event.remoteName = remoteName;
        event.host = remoteAddress.host();
        event.path = remoteAddress.path();
        event.port = remoteAddress.port();
        event.commit();
        return new NetServerSocketMetric(remoteName);
    }

    @Override
    public void disconnected(NetServerSocketMetric socketMetric, SocketAddress remoteAddress) {
        VertxNetServerDisconnected event = new VertxNetServerDisconnected();
        event.remoteName = socketMetric.remoteName;
        event.host = remoteAddress.host();
        event.path = remoteAddress.path();
        event.port = remoteAddress.port();
        event.bytesRead = socketMetric.bytesReceived.longValue();
        event.bytesWritten = socketMetric.bytesSent.longValue();
        event.commit();
    }

    @Override
    public void bytesRead(NetServerSocketMetric socketMetric, SocketAddress remoteAddress, long numberOfBytes) {
        socketMetric.bytesReceived.add(numberOfBytes);
    }

    @Override
    public void bytesWritten(NetServerSocketMetric socketMetric, SocketAddress remoteAddress, long numberOfBytes) {
        socketMetric.bytesSent.add(numberOfBytes);
    }

    public static class NetServerSocketMetric {
        LongAdder bytesReceived;
        LongAdder bytesSent;
        LongAdder errors;
        String remoteName;

        public NetServerSocketMetric(String remoteName) {
            this.remoteName = remoteName;
            this.bytesReceived = new LongAdder();
            this.bytesSent = new LongAdder();
            this.errors = new LongAdder();
        }
    }
}
