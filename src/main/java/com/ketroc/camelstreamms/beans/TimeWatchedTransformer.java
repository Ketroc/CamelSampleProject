package com.ketroc.camelstreamms.beans;

import com.ketroc.camelstreamms.json.ViewerEvent;
import com.ketroc.camelstreamms.json.ViewerEventList;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TimeWatchedTransformer {
    public Map<Long, Integer> transformMessage(ViewerEventList viewerEventList) {
         return viewerEventList
                .listOfEvents()
                .stream()
                .collect(
                        Collectors.groupingBy(
                                ViewerEvent::streamId,
                                Collectors.summingInt(viewerEvent -> numMinutesWatched(viewerEvent))
                        )
                );
    }

    private int numMinutesWatched(ViewerEvent viewerEvent) {
        return (int)Duration
                .between(viewerEvent.startWatchingTimestamp(), viewerEvent.endWatchingTimestamp())
                .toMinutes();
    }
}
