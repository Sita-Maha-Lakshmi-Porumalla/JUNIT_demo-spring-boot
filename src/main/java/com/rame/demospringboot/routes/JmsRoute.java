package com.rame.demospringboot.routes;

import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JmsRoute extends RouteBuilder {

    @Value("${app.messageSource}")
    private String messageSource;

    @Value("${app.messageDestination}")
    private String messageDestination;


    @Override
    public void configure() throws Exception {
        consumeMessageFromAmq();
       // produceMessage2AmqUsingTimer();
        connectUsingDirect();
        postFileContentToAMQ();
        consumeToMove();

    }

    private void consumeToMove() {
        from("activemq:queue:fileMessage")
                .routeId("Consume2Move")
                .log(LoggingLevel.INFO, "Message consumed from ActiveMQ Queue: ${body}")
                .setHeader(Exchange.FILE_NAME,
                        simple("${file:name.noext}_Received_on_${date:now:yyyyMMdd}.${file:name.ext}"))
                .to("file:" + messageDestination)
                .log("File created at the destination");
    }


    private void postFileContentToAMQ() {
       from("file:" + messageSource)
               .routeId("Message-from-file-Route")
               .log(LoggingLevel.INFO, "Incoming Message ${body}")
               .to("activemq:queue:fileMessage")
               .log("Message Posted to the ActiveMQ Queue");
    }

    private void consumeMessageFromAmq() {
        from("activemq:queue:myqueue")
                .routeId("JMS-Message-Route")
                .log(LoggingLevel.INFO,"Incoming Message ${body}");
    }

    private void produceMessage2AmqUsingTimer() {
        from("timer:mytimer?period=5000")
                .routeId("Timer-Produce-Message_Route")
                .setBody(constant("Hello from Spring-Boot Camel!"))
                .log(LoggingLevel.INFO, "Producing message to the Direct component")
                .to("direct:messageProducer");
    }

    private void connectUsingDirect(){
        from("direct:messageProducer")
                .routeId("Connect-using-Direct")
                .to("activemq:queue:myqueue");

    }


}
