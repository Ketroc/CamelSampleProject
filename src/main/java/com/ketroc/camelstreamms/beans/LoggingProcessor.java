package com.ketroc.camelstreamms.beans;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;

@Component
public class LoggingProcessor {
    Logger logger = LoggerFactory.getLogger(LoggingProcessor.class);

    public void processMessage(Map<Long, Integer> streamWatchedTimes) {
         streamWatchedTimes.forEach((streamId, minutesWatched) -> {
             Duration timeWatched = Duration.ofMinutes(minutesWatched);
             logger.info("Stream id {} was watched for {} hr {} min", streamId, timeWatched.toHours(), timeWatched.toMinutesPart());
         });
    }
}
