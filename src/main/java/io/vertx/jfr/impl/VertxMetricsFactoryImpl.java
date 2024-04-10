package io.vertx.jfr.impl;

import io.vertx.core.VertxOptions;
import io.vertx.core.spi.VertxMetricsFactory;
import io.vertx.core.spi.metrics.VertxMetrics;

public class VertxMetricsFactoryImpl implements VertxMetricsFactory {
    @Override
    public VertxMetrics metrics(VertxOptions vertxOptions) {
        return new VertxMetricsImpl();
    }
}
