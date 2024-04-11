package io.vertx.jfr.impl;

import io.vertx.core.spi.metrics.EventBusMetrics;
import io.vertx.jfr.impl.VertxEventBusMetrics.HandlerMetric;
import io.vertx.jfr.impl.events.eventbus.VertxEventBusHandlerRegistered;
import io.vertx.jfr.impl.events.eventbus.VertxEventBusHandlerUnregistered;
import io.vertx.jfr.impl.events.eventbus.VertxEventBusMessageReceived;
import io.vertx.jfr.impl.events.eventbus.VertxEventBusMessageSent;

public class VertxEventBusMetrics implements EventBusMetrics<HandlerMetric> {
    @Override
    public HandlerMetric handlerRegistered(String address) {
        VertxEventBusHandlerRegistered event = new VertxEventBusHandlerRegistered();
        event.begin();
        HandlerMetric handlerMetric = new HandlerMetric(address);
        event.address = address;
        event.commit();

        return handlerMetric;
    }

    @Override
    public void handlerUnregistered(HandlerMetric handler) {
        VertxEventBusHandlerUnregistered event = new VertxEventBusHandlerUnregistered();
        event.begin();
        if (handler != null) {
            event.address = handler.address;
        }
        event.commit();
    }

    @Override
    public void messageSent(String address, boolean publish, boolean local, boolean remote) {
        if (isNotInternal(address)) {
            VertxEventBusMessageSent event = new VertxEventBusMessageSent();
            event.address = address;
            event.publish = publish;
            event.local = local;
            event.remote = remote;
            event.commit();
        }
    }

    @Override
    public void messageReceived(String address, boolean publish, boolean local, int handlers) {
        if (isNotInternal(address)) {
            VertxEventBusMessageReceived event = new VertxEventBusMessageReceived();
            event.address = address;
            event.publish = publish;
            event.local = local;
            event.handlers = handlers;
            event.commit();
        }
    }

    private static boolean isNotInternal(String address) {
        return !address.startsWith("__vertx.");
    }

    public record HandlerMetric(String address) {

    }
}
