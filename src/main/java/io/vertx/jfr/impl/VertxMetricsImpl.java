package io.vertx.jfr.impl;

import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.net.NetServerOptions;
import io.vertx.core.net.SocketAddress;
import io.vertx.core.spi.metrics.EventBusMetrics;
import io.vertx.core.spi.metrics.HttpServerMetrics;
import io.vertx.core.spi.metrics.TCPMetrics;
import io.vertx.core.spi.metrics.VertxMetrics;

public class VertxMetricsImpl implements VertxMetrics {
    @Override
    public EventBusMetrics<?> createEventBusMetrics() {
        return new VertxEventBusMetrics();
    }

    @Override
    public HttpServerMetrics<?, ?, ?> createHttpServerMetrics(HttpServerOptions options, SocketAddress localAddress) {
        return new VertxHttpServerMetrics();
    }

    @Override
    public TCPMetrics<?> createNetServerMetrics(NetServerOptions options, SocketAddress localAddress) {
        return new VertxNetServerMetrics();
    }

    @Override
    public boolean isMetricsEnabled() {
        return true;
    }
}
