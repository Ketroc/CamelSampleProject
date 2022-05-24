package com.ketroc.camelstreamms.beans;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(OutputCaptureExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoggingProcessorTest {

    LoggingProcessor loggingProcessor;

    @BeforeAll
    void setUp() {
        loggingProcessor = new LoggingProcessor();
    }

    @Test
    void testProcessMessage_ForNoStream(CapturedOutput output) {
        loggingProcessor.processMessage(Collections.emptyMap());
        assertFalse(output.getOut().contains("Stream id"));
    }

    @Test
    void testProcessMessage_ForOneStream(CapturedOutput output) {
        loggingProcessor.processMessage(Map.of(123L, 30));
        assertTrue(output.getOut().contains("Stream id 123 was watched for 0 hr 30 min"));
    }

    @Test
    void testProcessMessage_ForTwoStreams(CapturedOutput output) {
        loggingProcessor.processMessage(Map.of(100L, 300, 999L, 75));
        assertTrue(output.getOut().contains("Stream id 100 was watched for 5 hr 0 min"));
        assertTrue(output.getOut().contains("Stream id 999 was watched for 1 hr 15 min"));
    }
}