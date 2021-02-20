package com.rame.demospringboot.routes;

import com.rame.demospringboot.processor.MyProcessor;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileRoute extends RouteBuilder {


    @Value("${app.source}")
    private String sourceLocation;

    @Value("${app.destination}")
    private String destination;

    @Autowired
    private MyProcessor processor;

    @Override
    public void configure() throws Exception {
      // fileCopier();
        renameFile();
    }

        /* File Name Manipulation */
    private void renameFile() {
        from("file:" + sourceLocation)
                .routeId("Rename-File-Route")
                .log(LoggingLevel.INFO, "Incoming File: ${file:name}")
                .setHeader(Exchange.FILE_NAME, simple("${file:name.noext}_${date:now:yyyyMMdd}.${file:name.ext}"))
                .log(LoggingLevel.INFO, "Renamed File: ${file:name}")
                .to("file:" + destination);
    }

    private void fileCopier() {
        from("file:" + sourceLocation)
                .routeId("Demo-File-Route")
                .process(processor)
                .to("file:" + destination)
                .log(LoggingLevel.INFO, "File is moved to destination \n ${body} ");
    }
}
