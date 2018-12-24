package chapter7.threadingProblems;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Food {
}

class Drink {
}

public class DeadlockCommon {
    public static void main(String[] args) {
        simpleDeadLockExample();
    }

    //deadlock example with foxes, food, and drink
    //deadlock - when two or more threads are blocked forever
    //(when they block resources which need by other thread)
    //equally block each other's needed resources
    private static void simpleDeadLockExample() {
        Food food = new Food();
        Drink drink = new Drink();

        DeadlockCommon fox1 = new DeadlockCommon();
        DeadlockCommon fox2 = new DeadlockCommon();

        ExecutorService executorService = null;
        try {
            executorService = Executors.newFixedThreadPool(2);
            executorService.submit(() -> fox1.foodAndDrink(food, drink));
            executorService.submit(() -> fox2.drinkAndFood(food, drink));
        } finally {
            if (executorService != null) {
                executorService.shutdown();
            }
        }
    }

    private void drinkAndFood(Food food, Drink drink) {
        synchronized (drink) {
            System.out.println("get drink-" + "drinkAndFood");
            move();
            synchronized (food) {
                System.out.println("get food-" + "drinkAndFood");
            }
        }
    }

    private void foodAndDrink(Food food, Drink drink) {
        synchronized (food) {
            System.out.println("get food-" + "foodAndDrink");
            move();
            synchronized (drink) {
                System.out.println("get drink-" + "foodAndDrink");
            }
        }
    }

    private void move() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
