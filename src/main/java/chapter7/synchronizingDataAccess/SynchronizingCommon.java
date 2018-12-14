package chapter7.synchronizingDataAccess;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class SynchronizingCommon {

    private int count = 0;
    private AtomicInteger countAtomic = new AtomicInteger(0);

    private void incrementAndReport() {
        System.out.print((++count) + " ");
    }

    private void incrementAndReportAtomic() {
        System.out.print((countAtomic.incrementAndGet()) + " ");
    }

    private void incrementAndReportAtomicSynchronizedOnObject() {
        synchronized (this) {
            System.out.print((countAtomic.incrementAndGet()) + " ");
        }
    }

    //synchronized method modifier means synchronized on an object
    //same as incrementAndReportAtomicSynchronizedOnObject()
    private synchronized void incrementAndReportAtomicSynchronizedOnMethod() {
        System.out.print((countAtomic.incrementAndGet()) + " ");
    }

    public static void printDaysWorkOnClass() {
        synchronized (SynchronizingCommon.class) {
            System.out.println("Finished work");
        }
    }

    //synchronized static method modifier means synchronized on a class
    //same as printDaysWorkOnClass()
    public static synchronized void printDaysWorkOnMethod() {
        System.out.println("Finished work");
    }

    public static void main(String[] args) {
        threadPoolRaceConditionAtomicSynchronizedOnObjectExample();
    }

    private static void threadPoolRaceConditionAtomicSynchronizedOnObjectExample() {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(20);
            SynchronizingCommon common = new SynchronizingCommon();

            for (int i = 0; i < 200; i++) {
                //no need to use AtomicInteger if the object is synchronized
                executorService.submit(() -> common.incrementAndReportAtomicSynchronizedOnMethod());
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }

    private static void threadPoolRaceConditionAtomicExample() {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(20);
            SynchronizingCommon common = new SynchronizingCommon();

            //if under 20 threads - no race condition is seen
            //or this method should be run in loop(for example with 10 iteration)
            for (int i = 0; i < 10; i++) {
                executorService.submit(() -> common.incrementAndReportAtomic());
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }

    //if under 20 threads - no race condition is seen
    private static void manyThreadsRaceConditionExample() {
        SynchronizingCommon common = new SynchronizingCommon();

        //if under 20 threads - no race condition is seen
        //or this method should be run in loop(for example with 10 iteration)
        for (int i = 0; i < 10; i++) {
            new Thread(() -> common.incrementAndReport()).start();
        }
    }

    //if under 20 threads - no race condition is seen
    private static void threadPoolRaceConditionExample() {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(20);
            SynchronizingCommon common = new SynchronizingCommon();

            //if under 20 threads - no race condition is seen
            //or this method should be run in loop(for example with 10 iteration)
            for (int i = 0; i < 10; i++) {
                executorService.submit(() -> common.incrementAndReport());
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }
}
