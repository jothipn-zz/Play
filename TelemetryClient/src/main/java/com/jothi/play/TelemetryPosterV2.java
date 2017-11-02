package com.jothi.play;

import com.microsoft.applicationinsights.TelemetryClient;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by joneelam on 11/2/2017.
 */
public class TelemetryPosterV2 implements Runnable {
    private final String event;
    private final Map<String, String> map;
    private final TelemetryClient tc = new TelemetryClient();

    TelemetryPosterV2(String eventName, Map<String, String> props) {
        tc.getContext().getOperation().setName("TC-TEST");
        tc.getContext().getDevice().setType("PC");
        event = eventName;
        map = props;
    }

    @Override
    public void run() {
        tc.trackEvent(event, map, null);
        tc.flush();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
