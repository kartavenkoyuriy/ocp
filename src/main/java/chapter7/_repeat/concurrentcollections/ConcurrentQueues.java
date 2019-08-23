package chapter7._repeat.concurrentcollections;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ConcurrentQueues {

    public static void main(String[] args) {


    }

    private static void dequeExample() {
        try {
            BlockingDeque<Integer> integerBlockingDeque = new LinkedBlockingDeque<>();
            integerBlockingDeque.offer(10);
            integerBlockingDeque.offer(11, 1, TimeUnit.SECONDS);
            integerBlockingDeque.offerFirst(12, 1, TimeUnit.SECONDS);
            integerBlockingDeque.offerLast(13, 1, TimeUnit.SECONDS);

            System.out.println(integerBlockingDeque.poll());
            System.out.println(integerBlockingDeque.pollFirst(1, TimeUnit.SECONDS));
            System.out.println(integerBlockingDeque.pollLast(1, TimeUnit.SECONDS));
            System.out.println(integerBlockingDeque.pollLast());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void queueExample() {
        try {
            BlockingQueue<Integer> integerBlockingQueue = new LinkedBlockingQueue<>();
            integerBlockingQueue.offer(10);
            integerBlockingQueue.offer(100, 2, TimeUnit.SECONDS);

            System.out.println(integerBlockingQueue.poll());
            System.out.println(integerBlockingQueue.poll(5, TimeUnit.SECONDS));
            //in third polling queue will wait until elem will be available or time elapsed
            System.out.println(integerBlockingQueue.poll(5, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
