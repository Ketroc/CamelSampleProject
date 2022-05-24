package com.ketroc.camelstreamms.routes;

import com.ketroc.camelstreamms.beans.LoggingProcessor;
import com.ketroc.camelstreamms.beans.TimeWatchedTransformer;
import com.ketroc.camelstreamms.json.ViewerEventList;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StreamTimeRouter extends RouteBuilder {
    @Autowired
    private TimeWatchedTransformer timeWatchedTransformer;

    @Autowired
    private LoggingProcessor loggingProcessor;

    @Override
    public void configure() throws Exception {
        from("file:files/input")
                .wireTap("file:files/completed")
                .unmarshal()
                .json(JsonLibrary.Jackson, ViewerEventList.class)
                .bean(timeWatchedTransformer)
                .bean(loggingProcessor);
                //.log(LoggingLevel.INFO, "body ${body}");
    }
}
