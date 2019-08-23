package chapter7._repeat.interference.join;

public class JoinSimple extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 10_000; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        JoinSimple joinSimple = new JoinSimple();
        joinSimple.start();
//        joinSimple.notify();
//        joinSimple.wait();
        for (int i = 0; i < 10_000; i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);
        }
//        joinSimple.join();
    }
}
