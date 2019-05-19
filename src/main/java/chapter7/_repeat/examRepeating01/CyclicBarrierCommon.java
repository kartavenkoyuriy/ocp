package chapter7._repeat.examRepeating01;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierCommon {
    private void getAnimalsOut(){
        System.out.println("Animals out");
    }

    private void cleanCage(){
        System.out.println("Cage cleaned");
    }

    private void getAnimalsIn(){
        System.out.println("Animals in");
    }

    private void doWork(){
        getAnimalsOut();
        cleanCage();
        getAnimalsIn();
    }

    //can be done with 1 CyclicBarrier
    private void doWorkPaused(CyclicBarrier barrier1, CyclicBarrier barrier2){
        try {
            getAnimalsOut();
            barrier1.await();
            cleanCage();
            barrier2.await();
            getAnimalsIn();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }

    private static void printResultWithBarrier() {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(4);
            CyclicBarrier barrier1 = new CyclicBarrier(4);
            CyclicBarrier barrier2 = new CyclicBarrier(4);
            CyclicBarrierCommon cyclicBarrierCommon = new CyclicBarrierCommon();
            for (int i = 0; i < 4; i++) {
                executorService.submit(() -> cyclicBarrierCommon.doWorkPaused(barrier1, barrier2));
            }
        } finally {
            if(executorService != null){
                executorService.shutdown();
            }
        }
    }

    private static void printResultWithoutBarrier() {
        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(4);
            CyclicBarrierCommon cyclicBarrierCommon = new CyclicBarrierCommon();
            for (int i = 0; i < 4; i++) {
                executorService.submit(cyclicBarrierCommon::doWork);
            }
        } finally {
            if(executorService != null){
                executorService.shutdown();
            }
        }
    }
}
