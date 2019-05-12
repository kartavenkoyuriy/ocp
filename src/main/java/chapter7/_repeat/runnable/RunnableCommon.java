package chapter7._repeat.runnable;

class RunnableImpl implements Runnable{

    @Override
    public void run() {
        System.out.println("RunnableImpl");
    }
}

class ThreadImpl extends Thread{

    @Override
    public void run() {
        System.out.println("ThreadImpl");
    }
}

public class RunnableCommon {

    public static void main(String[] args) {
        //how new threads can be started
        //#1. pass a lambda into Thread constructor, invoke start()
        new Thread(() -> System.out.println("hello"))
                .start();
        //#2. pass a Runnable implementation into Thread constructor, invoke start()
        new Thread(new RunnableImpl()).start();
        //#3. extend Thread, override run(), invoke start()
        new ThreadImpl().start();


    }
}
