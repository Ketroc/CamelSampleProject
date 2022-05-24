package com.ketroc.camelstreamms.beans;

import static org.junit.jupiter.api.Assertions.*;

import com.ketroc.camelstreamms.json.ViewerEvent;
import com.ketroc.camelstreamms.json.ViewerEventList;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TimeWatchedTransformerTest {

    TimeWatchedTransformer timeWatchedTransformer;

    ViewerEventList noViewerStream;
    ViewerEventList _10min1ViewerStream;
    ViewerEventList _30min2ViewerStream;

    @BeforeAll
    void setUp() {
        timeWatchedTransformer = new TimeWatchedTransformer();

        ViewerEvent _10minViewer = new ViewerEvent(
                "bob",
                123L,
                LocalDateTime.now(),
                LocalDateTime.now().plus(10, ChronoUnit.MINUTES)
        );
        ViewerEvent _20minViewer = new ViewerEvent(
                "jill",
                123L,
                LocalDateTime.now(),
                LocalDateTime.now().plus(20, ChronoUnit.MINUTES)
        );
        noViewerStream = new ViewerEventList(Collections.emptyList());
        _10min1ViewerStream = new ViewerEventList(List.of(_10minViewer));
        _30min2ViewerStream = new ViewerEventList(List.of(_10minViewer, _20minViewer));
    }

    @Test
    void testProcessMessage_ForNoViewers() {
        assertEquals(Collections.emptyMap(), timeWatchedTransformer.transformMessage(noViewerStream));
    }

    @Test
    void testProcessMessage_ForOneViewer() {
        assertEquals(Map.of(123L, 10), timeWatchedTransformer.transformMessage(_10min1ViewerStream));
    }

    @Test
    void testProcessMessage_ForTwoViewersOfOneStream() {
        assertEquals(Map.of(123L, 30), timeWatchedTransformer.transformMessage(_30min2ViewerStream));
    }
}