package com.rame.demospringboot.processor;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;


public class MyProcessorTest extends CamelTestSupport {

    @Test
    public void testMyProcessor() throws Exception {
        DefaultCamelContext context = new DefaultCamelContext();
        DefaultExchange exchange = new DefaultExchange(context);

        exchange.getIn().setBody("Test Message Body", String.class);

        MyProcessor processor = new MyProcessor();
        processor.process(exchange);

//        DefaultExchange expected = new DefaultExchange(context);
//        expected.getIn().setBody("Test Message Body Updating the content using the processor..");
//
//        assertOutMessageBodyEquals(expected , exchange.getIn().getBody());

        System.out.println(exchange.getIn().getBody());
    }

}
