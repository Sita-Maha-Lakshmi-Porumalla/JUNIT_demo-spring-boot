package com.rame.demospringboot.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MyProcessor implements Processor {

    private static Logger logger= LogManager.getLogger(MyProcessor.class);

    @Override
    public void process(Exchange exchange) throws Exception {

        String messageBody = exchange.getIn().getBody(String.class);
        Map<String, Object> exchangeProperties = exchange.getProperties();

        logger.info("Message body from incoming Exchange, {} ", messageBody);
        logger.info("Exchange Properties {}", exchangeProperties);

        messageBody = messageBody.concat(" Updating the content using the processor..");

        logger.info("Updated the Message Body Exchange, {} ", messageBody);
        exchange.getIn().setBody(messageBody);

    }
}
