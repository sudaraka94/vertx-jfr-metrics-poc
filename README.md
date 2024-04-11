## vertx-jfr-metrics-poc

This is a Proof of Concept of a Vertx Metrics SPI implementation which emits JFR events. Right now
this only supports some of the Event Bus Handler events,
- HandlerRegistered
- HandlerUnregistered
- MessageSent
- MessageReceived

### Running the POC

- Building a fat jar
```shell
mvn clean package
```
- Run the Jar
```shell
java -XX:StartFlightRecording:filename=sm.jfr -jar ./target/vertx-jfr-metrics-1.0-SNAPSHOT-app.jar
```
### Reading JFR Events
In the POC JFR events are categorised for the ease of reference. All the vertx related events are categorised under 
the category `Vertx`.


#### Event Bus Related Events
All the event bus related events are categorised under the category `VertxEventBus`, the below command should output 
all the relevant events in your terminal.

```shell
jfr print --categories VertxEventBus sm.jfr
```

<details>
<summary>Example output</summary>

```text
io.vertx.VertxEventBusHandlerRegistered {
  startTime = 19:34:17.634 (2024-04-10)
  duration = 0.0933 ms
  address = "my.address"
  eventThread = "main" (javaThreadId = 1)
  stackTrace = [
    io.vertx.jfr.impl.VertxEventBusMetrics.handlerRegistered(String) line: 17
    io.vertx.jfr.impl.VertxEventBusMetrics.handlerRegistered(String) line: 10
    io.vertx.core.eventbus.impl.HandlerRegistration.register(boolean, boolean, Promise) line: 71
    io.vertx.core.eventbus.impl.MessageConsumerImpl.handler(Handler) line: 206
    io.vertx.core.eventbus.impl.EventBusImpl.consumer(String, Handler) line: 183
    ...
  ]
}

io.vertx.VertxEventBusMessageReceived {
  startTime = 19:34:17.641 (2024-04-10)
  address = "my.address"
  publish = false
  local = true
  handlers = 1
  eventThread = "main" (javaThreadId = 1)
  stackTrace = [
    io.vertx.jfr.impl.VertxEventBusMetrics.messageReceived(String, boolean, boolean, int) line: 52
    io.vertx.core.eventbus.impl.EventBusImpl.deliverMessageLocally(MessageImpl) line: 373
    io.vertx.core.eventbus.impl.EventBusImpl.sendLocally(MessageImpl, Promise) line: 353
    io.vertx.core.eventbus.impl.EventBusImpl.sendOrPub(ContextInternal, MessageImpl, DeliveryOptions, Promise) line: 345
    io.vertx.core.eventbus.impl.EventBusImpl.sendOrPub(OutboundDeliveryContext) line: 349
    ...
  ]
}

io.vertx.VertxEventBusMessageSent {
  startTime = 19:34:17.643 (2024-04-10)
  address = "my.address"
  publish = false
  local = true
  remote = false
  eventThread = "main" (javaThreadId = 1)
  stackTrace = [
    io.vertx.jfr.impl.VertxEventBusMetrics.messageSent(String, boolean, boolean, boolean) line: 40
    io.vertx.core.eventbus.impl.OutboundDeliveryContext.written(Throwable) line: 68
    io.vertx.core.eventbus.impl.OutboundDeliveryContext.tryComplete(Void) line: 48
    io.vertx.core.eventbus.impl.OutboundDeliveryContext.tryComplete(Object) line: 27
    io.vertx.core.Promise.tryComplete() line: 121
    ...
  ]
}

io.vertx.VertxEventBusHandlerUnregistered {
  startTime = 19:34:17.645 (2024-04-10)
  duration = 0.00317 ms
  address = "my.address"
  eventThread = "main" (javaThreadId = 1)
  stackTrace = [
    io.vertx.jfr.impl.VertxEventBusMetrics.handlerUnregistered(VertxEventBusMetrics$HandlerMetric) line: 29
    io.vertx.jfr.impl.VertxEventBusMetrics.handlerUnregistered(Object) line: 10
    io.vertx.core.eventbus.impl.HandlerRegistration.unregister() line: 86
    io.vertx.core.eventbus.impl.MessageConsumerImpl.unregister() line: 111
    io.vertx.core.eventbus.impl.EventBusImpl.unregisterAll() line: 460
    ...
  ]
}
```
</details>

#### HTTP Server Related Events
All the event bus related events are categorised under the category `VertxHttpServer`, the below command should output
all the relevant events in your terminal.

```shell
jfr print --categories VertxHttpServer sm.jfr
```

<details>
<summary>Example output</summary>

```text
io.vertx.server.VertxHttpServerRequestBegin {
  startTime = 01:05:51.560 (2024-04-12)
  uri = "/"
  method = "GET"
  eventThread = "vert.x-eventloop-thread-0" (javaThreadId = 34)
  stackTrace = [
    io.vertx.jfr.impl.VertxHttpServerMetrics.requestBegin(VertxNetServerMetrics$NetServerSocketMetric, HttpRequest) line: 22
    io.vertx.jfr.impl.VertxHttpServerMetrics.requestBegin(Object, HttpRequest) line: 15
    io.vertx.core.http.impl.Http1xServerRequest.reportRequestBegin() line: 603
    io.vertx.core.http.impl.Http1xServerRequest.handleBegin(boolean) line: 155
    io.vertx.core.http.impl.Http1xServerConnection.handleMessage(Object) line: 182
    ...
  ]
}

io.vertx.server.VertxHttpServerResponseEnd {
  startTime = 01:05:51.568 (2024-04-12)
  uri = "/"
  method = "GET"
  statusCode = 200
  bytesWritten = 23
  eventThread = "vert.x-eventloop-thread-0" (javaThreadId = 34)
  stackTrace = [
    io.vertx.jfr.impl.VertxHttpServerMetrics.responseEnd(VertxHttpServerMetrics$RequestMetric, HttpResponse, long) line: 63
    io.vertx.jfr.impl.VertxHttpServerMetrics.responseEnd(Object, HttpResponse, long) line: 15
    io.vertx.core.http.impl.Http1xServerConnection.reportResponseComplete() line: 331
    io.vertx.core.http.impl.Http1xServerConnection.responseComplete() line: 263
    io.vertx.core.http.impl.Http1xServerConnection$1.write() line: 249
    ...
  ]
}

io.vertx.server.VertxHttpServerRequestEnd {
  startTime = 01:05:51.570 (2024-04-12)
  uri = "/"
  method = "GET"
  bytesRead = 0
  eventThread = "vert.x-eventloop-thread-0" (javaThreadId = 34)
  stackTrace = [
    io.vertx.jfr.impl.VertxHttpServerMetrics.requestEnd(VertxHttpServerMetrics$RequestMetric, HttpRequest, long) line: 33
    io.vertx.jfr.impl.VertxHttpServerMetrics.requestEnd(Object, HttpRequest, long) line: 15
    io.vertx.core.http.impl.Http1xServerRequest.reportRequestComplete() line: 595
    io.vertx.core.http.impl.Http1xServerRequest.onEnd() line: 577
    io.vertx.core.http.impl.Http1xServerRequest.handleEnd() line: 571
    ...
  ]
}
```
</details>

#### TCP Server Related Events
All the event bus related events are categorised under the category `VertxNetServer`, the below command should output
all the relevant events in your terminal.

```shell
jfr print --categories VertxNetServer sm.jfr
```

<details>
<summary>Example output</summary>

```text
io.vertx.server.VertxNetServerConnected {
  startTime = 01:05:51.555 (2024-04-12)
  remoteName = "127.0.0.1"
  host = "127.0.0.1"
  port = 59173
  path = N/A
  eventThread = "vert.x-eventloop-thread-0" (javaThreadId = 34)
  stackTrace = [
    io.vertx.jfr.impl.VertxNetServerMetrics.connected(SocketAddress, String) line: 19
    io.vertx.jfr.impl.VertxNetServerMetrics.connected(SocketAddress, String) line: 10
    io.vertx.core.http.impl.HttpServerWorker.configureHttp1Handler(ChannelPipeline, SslChannelProvider, SSLHelper) line: 324
    io.vertx.core.http.impl.Http1xUpgradeToH2CHandler.channelRead(ChannelHandlerContext, Object) line: 126
    io.netty.channel.AbstractChannelHandlerContext.invokeChannelRead(Object) line: 444
    ...
  ]
}

io.vertx.server.VertxNetServerConnected {
    startTime = 01:05:51.573 (2024-04-12)
    remoteName = "127.0.0.1"
    host = "127.0.0.1"
    port = 59173
    path = N/A
    bytesWritten = 23
    bytesRead = 0
    eventThread = "vert.x-eventloop-thread-0" (javaThreadId = 34)
    stackTrace = [
    io.vertx.jfr.impl.VertxNetServerMetrics.disconnected(VertxNetServerMetrics$NetServerSocketMetric, SocketAddress) line: 32
    io.vertx.jfr.impl.VertxNetServerMetrics.disconnected(Object, SocketAddress) line: 10
    io.vertx.core.net.impl.ConnectionBase.handleClosed() line: 384
    io.vertx.core.http.impl.Http1xServerConnection.handleClosed() line: 538
    io.vertx.core.net.impl.VertxHandler.channelInactive(ChannelHandlerContext) line: 143
    ...
    ]
}
```
</details>

> [!TIP]
> In case you want to output the events as a json, simply pas the `--json` flag to the command,
> ```shell
> jfr print --categories VertxEventBus --json sm.jfr
> ```
