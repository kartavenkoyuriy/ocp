package chapter7.concurrentCollections;

import java.util.concurrent.*;

public class QueueDequeCommon {
    public static void main(String[] args) throws InterruptedException {
        blockingDequeExample();
    }

    //TODO revise
    private static void blockingDequeExample() throws InterruptedException {
        BlockingDeque<Integer> blockingDeque = new LinkedBlockingDeque<>();
        blockingDeque.offer(10);
        blockingDeque.offerFirst(100, 1, TimeUnit.SECONDS);
        blockingDeque.offerLast(200,1, TimeUnit.SECONDS);
        blockingDeque.offer(50, 1, TimeUnit.SECONDS);
        System.out.println(blockingDeque);

        System.out.println(blockingDeque.poll());
        System.out.println(blockingDeque.pollFirst(1, TimeUnit.SECONDS));
        System.out.println(blockingDeque.pollLast(1, TimeUnit.SECONDS));
        System.out.println(blockingDeque.poll(1, TimeUnit.SECONDS));
    }

    //TODO revise
    private static void blockingQueueExample() throws InterruptedException {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>(1);
        blockingQueue.offer(4);

        //        System.out.println(blockingQueue);
//        Thread.sleep(5000);
//        System.out.println(blockingQueue);
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();
            executorService.submit(() -> {
                System.out.println(blockingQueue.poll());
                System.out.println(blockingQueue.poll());
                System.out.println(blockingQueue.poll(5, TimeUnit.SECONDS));
                return null;
            });

        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
        blockingQueue.offer(10, 2, TimeUnit.SECONDS);
    }
}
