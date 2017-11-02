package com.jothi.play;

import com.microsoft.applicationinsights.TelemetryClient;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by joneelam on 11/2/2017.
 */
public class TelemetryPoster implements Runnable {
    private static final ThreadLocal<TelemetryClient> tc =
            new ThreadLocal<TelemetryClient>() {
                @Override
                protected TelemetryClient initialValue() {
                    TelemetryClient tc = new TelemetryClient();
                    tc.getContext().getOperation().setName("TC-TEST");
                    tc.getContext().getDevice().setType("PC");
                    System.out.println("Initing TC");
                    return tc;
                }
            };

    private static final ThreadLocal<AtomicInteger> taskCount = new ThreadLocal<AtomicInteger>(){
        @Override
        protected AtomicInteger initialValue() {
            return new AtomicInteger(0);
        }
    };

    private final String event;
    private final Map<String, String> map;

    TelemetryPoster(String eventName, Map<String, String> props) {
        event = eventName;
        map = props;
    }

    @Override
    public void run() {
        tc.get().trackEvent(event, map, null);
        tc.get().flush();
        taskCount.get().getAndIncrement();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public int getTaskCount() {
        return taskCount.get().get();
    }
}
