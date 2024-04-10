package io.vertx.jfr.impl;

import io.vertx.core.spi.metrics.EventBusMetrics;
import io.vertx.core.spi.metrics.VertxMetrics;

public class VertxMetricsImpl implements VertxMetrics {
    @Override
    public EventBusMetrics<?> createEventBusMetrics() {
        return new VertxEventBusMetrics();
    }
    @Override
    public boolean isMetricsEnabled() {
        return true;
    }
}
