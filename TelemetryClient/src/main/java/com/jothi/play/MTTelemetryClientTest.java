package com.jothi.play;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MTTelemetryClientTest {

    final ThreadPoolExecutor executor;
    private static final String Prefix = "RRR";
    private static final int QUEUE_SIZE = 50000;
    private final BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<>(QUEUE_SIZE);

    public MTTelemetryClientTest() {
        executor = new ThreadPoolExecutor(10,
                20, 5000, TimeUnit.MILLISECONDS, blockingQueue);
        executor.prestartAllCoreThreads();
    }

    public static void main(String[] args) {
        MTTelemetryClientTest tct = new MTTelemetryClientTest();
        Map<String, String> props = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            String k = "K" + i;
            String v = "V" + i;
            props.put(k,v);
        }

        int numEvents = 500;

        long start = System.currentTimeMillis();

        for (int i = 0; i < numEvents; i++) {
            String eventName = Prefix + i;
            tct.executor.execute(new TelemetryPoster(eventName, props));
        }
        tct.executor.shutdown();
        try {
            tct.executor.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        long postingTime = end - start;

        System.out.println("Prefix: " + Prefix + " Post time: " + postingTime);
        System.out.println("Completed Tasks: " + tct.executor.getCompletedTaskCount());
    }

}
