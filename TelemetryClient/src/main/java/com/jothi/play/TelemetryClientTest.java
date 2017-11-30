package com.jothi.play;


import com.microsoft.applicationinsights.TelemetryClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple program that posts a certain number of events to the AppInsights cluster.
 * Play around with the payload (num KV pairs), Flush Time and Num Events to publish.
 * Note that the Instrumentation Key has to be set correctly in the ApplicationInsights.xml in the resources directory.
 */
public class TelemetryClientTest {

    private final TelemetryClient telemetryClient;
    private final static String PREFIX = "P5"; // A prefix that helps us query the AppInsight for the events easily
    private final static long SLEEP_TIME_AFTER_FLUSH_MILLIS = 100;
    private final static int NUM_KV_PAIRS = 100; // Crude way to control the Payload size
    private final static int NUM_EVENTS_TO_PUBLISH = 50000;

    private TelemetryClientTest() {
        this.telemetryClient = new TelemetryClient();
        telemetryClient.getContext().getOperation().setName("TC-TEST");
        telemetryClient.getContext().getDevice().setType("PC");
    }

    private TelemetryClient getTelemetryClient() {
        return telemetryClient;
    }

    public static void main(String[] args) {
        TelemetryClientTest tct = new TelemetryClientTest();
        Map<String, String> props = new HashMap<>();
        for (int i = 0; i < NUM_KV_PAIRS; i++) {
            String k = "K" + i;
            String v = "V" + i;
            props.put(k, v);
        }

        long start = System.currentTimeMillis();
        try {
            for (int i = 0; i < NUM_EVENTS_TO_PUBLISH; i++) {
                String eventName = PREFIX + i;
                tct.getTelemetryClient().trackEvent(eventName, props, null);

                // There apparently is a limitation of 3000 telemetries/second. So, sleep for 1.5 seconds
                // just in case
                if (i % 3000 == 0) {
                    Thread.sleep(1500);
                }
            }

            long end = System.currentTimeMillis();
            long postingTime = end - start;

            // Done with all posting. Flush now
            start = System.currentTimeMillis();
            tct.getTelemetryClient().flush();
            end = System.currentTimeMillis();
            Thread.sleep(SLEEP_TIME_AFTER_FLUSH_MILLIS);

            System.out.println("Prefix: " + PREFIX +
                    "\nPost time: " + postingTime +
                    "\n Flush took: " + (end - start) +
                    "\nFlush Sleep Time: " + SLEEP_TIME_AFTER_FLUSH_MILLIS);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
