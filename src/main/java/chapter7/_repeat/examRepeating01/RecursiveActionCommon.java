package chapter7._repeat.examRepeating01;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class RecursiveActionCommon extends RecursiveAction {

    private Double[] weights;
    private int start;
    private int end;

    //Fork Join framework uses when don't know how many threads will be needed
    //it divides work and recursivelly call itself until fits into base case
    public RecursiveActionCommon(Double[] weights, int start, int end) {
        this.weights = weights;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        //maximum amount of work
        //base case
        if (end - start <= 3) {
            for (int i = start; i < end; i++) {
                weights[i] = (double) new Random().nextInt(100);
                System.out.println("Animal " + i + " weighted:" + weights[i]);
            }
        } else {
            int middle = start + ((end - start) / 2);
            System.out.println("[start=" + start + ",middle=" + middle + ",end=" + end + "]");
            //recursively call itself with divided work
            invokeAll(new RecursiveActionCommon(weights, start, middle),
                    new RecursiveActionCommon(weights, middle, end));
        }
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];
        ForkJoinTask<?> task = new RecursiveActionCommon(weights, 0, 10);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);

        System.out.println();
        Arrays.asList(weights).forEach(x -> System.out.print(x + " "));
    }


}
