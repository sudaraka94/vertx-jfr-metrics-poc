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
- Reading the events from the file
```shell
jfr print --categories VertxEventBus sm.jfr
```

All the events emitted by the implementation are categorised under the category `VertxEventBus` and
the above command should output all the relevant events in your terminal.

Example output,
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

In case you want to output the events as a json, simply pas the `--json` flag to the command,
```shell
jfr print --categories VertxEventBus --json sm.jfr
```
