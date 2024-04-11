package io.vertx.jfr.impl;

import io.vertx.core.http.HttpMethod;
import io.vertx.core.spi.metrics.HttpServerMetrics;
import io.vertx.core.spi.observability.HttpRequest;
import io.vertx.core.spi.observability.HttpResponse;
import io.vertx.jfr.impl.events.server.VertxHttpServerRequestBegin;
import io.vertx.jfr.impl.events.server.VertxHttpServerRequestEnd;
import io.vertx.jfr.impl.events.server.VertxHttpServerRequestReset;
import io.vertx.jfr.impl.events.server.VertxHttpServerResponseEnd;
import io.vertx.jfr.impl.events.server.VertxHttpServerResponsePushed;

import java.util.concurrent.atomic.LongAdder;

public class VertxHttpServerMetrics extends VertxNetServerMetrics implements HttpServerMetrics<VertxHttpServerMetrics.RequestMetric, LongAdder, VertxNetServerMetrics.NetServerSocketMetric> {

    @Override
    public RequestMetric requestBegin(VertxNetServerMetrics.NetServerSocketMetric socketMetric, HttpRequest request) {
        VertxHttpServerRequestBegin event = new VertxHttpServerRequestBegin();
        event.method = request.method().name();
        event.uri = request.uri();
        event.commit();
        return new RequestMetric(request.method().name(), request.uri());
    }

    @Override
    public void requestEnd(RequestMetric requestMetric, HttpRequest request, long bytesRead) {
        if (!requestMetric.reset) {
            VertxHttpServerRequestEnd event = new VertxHttpServerRequestEnd();
            event.method = request.method().name();
            event.uri = request.uri();
            event.bytesRead = bytesRead;
            event.commit();
        }
    }

    @Override
    public void requestReset(RequestMetric requestMetric) {
        requestMetric.reset = true;
        VertxHttpServerRequestReset event = new VertxHttpServerRequestReset();
        event.method = requestMetric.method;
        event.uri = requestMetric.uri;
        event.commit();
    }

    @Override
    public RequestMetric responsePushed(VertxNetServerMetrics.NetServerSocketMetric socketMetric, HttpMethod method, String uri, HttpResponse response) {
        VertxHttpServerResponsePushed event = new VertxHttpServerResponsePushed();
        event.method = method.name();
        event.uri = uri;
        event.statusCode = response.statusCode();
        event.commit();
        return new RequestMetric(method.name(), uri);
    }

    @Override
    public void responseEnd(RequestMetric requestMetric, HttpResponse response, long bytesWritten) {
        VertxHttpServerResponseEnd event = new VertxHttpServerResponseEnd();
        event.method = requestMetric.method;
        event.uri = requestMetric.uri;
        event.statusCode = response.statusCode();
        event.bytesWritten = bytesWritten;
        event.commit();
    }

    public static class RequestMetric {
            String method;
            String uri;
            boolean reset;

        public RequestMetric(String method, String uri) {
            this.method = method;
            this.uri = uri;
            reset = false;
        }
    }
}
