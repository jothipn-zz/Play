package com.jothi.play;


import com.microsoft.applicationinsights.TelemetryClient;
import com.microsoft.applicationinsights.telemetry.Duration;

import java.util.HashMap;
import java.util.Map;

public class TelemetryClientTest {

    private final TelemetryClient telemetryClient;
    private static final String Prefix = "PPP";

    public TelemetryClientTest() {
        this.telemetryClient = new TelemetryClient();
        telemetryClient.getContext().getOperation().setName("TC-TEST");
        telemetryClient.getContext().getDevice().setType("PC");
    }

    TelemetryClient getTelemetryClient() { return telemetryClient;}

    public static void main(String[] args) {
        TelemetryClientTest tct = new TelemetryClientTest();
        Map<String, String> props = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            String k = "K" + i;
            String v = "V" + i;
            props.put(k,v);
        }

        long start = System.currentTimeMillis();

//        Duration durtion = new Duration(1,1,1,1,1);
//        tct.getTelemetryClient().trackDependency("td", "cn",durtion,true);

        for (int i = 0; i < 50000; i++) {
            String eventName = Prefix + i;
            tct.getTelemetryClient().trackEvent(eventName, props, null);
            tct.getTelemetryClient().flush();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long end = System.currentTimeMillis();
        long postingTime = end - start;
//        start = System.currentTimeMillis();
//        tct.getTelemetryClient().flush();
//        end = System.currentTimeMillis();

        System.out.println("Prefix: " + Prefix + " Post time: " + postingTime + ", Flush took: " +
                (end - start));
//
//        try {
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
