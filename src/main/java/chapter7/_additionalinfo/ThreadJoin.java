package chapter7._additionalinfo;

public class ThreadJoin {

    public static void main(String[] args) {
        final Thread thread = new Thread(() -> {

            for (int i = 0; i < 10_000; i++) {
                System.out.println(Thread.currentThread().getName() +":"+ i);
            }
        });
        thread.setName("t1");
        thread.start();

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            System.err.println("joined");
        }

        for (int i = 0; i < 10_000; i++) {
            System.out.println(Thread.currentThread().getName() +":"+ i);
        }

    }
}
