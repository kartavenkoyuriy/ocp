package chapter7._repeat.synchronizing;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

//monitor - lock

//monitor - structure that can to manage a concurrent access to a shared resource

//Synchronization is built around an internal entity known as the intrinsic lock or monitor lock.
// (The API specification often refers to this entity simply as a "monitor.") Intrinsic locks play a role in both aspects of synchronization:
// enforcing exclusive access to an object's state and establishing happens-before relationships that are essential to visibility.
//
//Every object has an intrinsic lock associated with it. By convention, a thread that needs exclusive and consistent access
// to an object's fields has to acquire the object's intrinsic lock before accessing them, and then release the intrinsic lock when it's done with them.
// A thread is said to own the intrinsic lock between the time it has acquired the lock and released the lock. As long as a thread owns an intrinsic lock,
// no other thread can acquire the same lock. The other thread will block when it attempts to acquire the lock.

public class SheepManager {

    //1. int. no synchronizing. operation is not atomic, therefore race condition. "reporting" is unorder, numbers can be duplicated, max number can be wrong
    // (because of duplication)
    //2. AtomicInteger. incrementAndGet. improve: no duplications. still race condition.
    //3. synchronized service.execute on manager. do not solve the problem since we've synchronized creation instead of execution.
    //4. synchronized incrementing and getting on this. solved, no race condition.
    // could have synchronized on any object, since we need to incrementAndGet by one thread.(details in book)
    //5. may return back to int
    //6. synchronized on method. it's the same since synchronizing on method same as synchronizing on object.
    //synchronizing on static method same as synchronizing on a class.

    //race conditions can be on the different levels: wrong order and duplicates. AtomicInteger can solve duplicate problem.
    //for solving order problem need to synchronize the resource.

    //
    // synchronizing - protecting data integrity at a cost of performance
    //
    private int count = 0;

    private synchronized void incrementAndReport() {
        System.out.println(++count + " ");
    }

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(20);
            SheepManager manager = new SheepManager();
            for (int i = 0; i < 10; i++) {
                service.execute(() -> manager.incrementAndReport());
            }
        } finally {
            if (service != null) {
                service.shutdown();
            }
        }
    }
}
