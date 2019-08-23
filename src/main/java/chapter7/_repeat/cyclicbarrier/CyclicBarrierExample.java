package chapter7._repeat.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierExample {
    private void emptyCage(){
        System.out.println("empty cage");
    }

    private void cleanCage(){
        System.out.println("clean cage");
    }

    private void populateCage(){
        System.out.println("populate cage");
    }

    public void doWork(CyclicBarrier c1, CyclicBarrier c2){
        try {
            emptyCage();
            c1.await();
            cleanCage();
            //also can be done with one CyclicBarrier c1
            c2.await();
            populateCage();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(4);
            CyclicBarrierExample workManager = new CyclicBarrierExample();
            CyclicBarrier barrier1 = new CyclicBarrier(4);
            CyclicBarrier barrier2 = new CyclicBarrier(4, () -> {
                System.out.println("!!!cages cleaned");
            });
            for (int i = 0; i < 4; i++) {
                service.submit(() -> workManager.doWork(barrier1, barrier2));
            }
        } finally {
            if (service != null){
                service.shutdown();
            }
        }
    }


}
