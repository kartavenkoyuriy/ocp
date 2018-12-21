package chapter7.managingConcurrentProcesses;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class ForkJoinCommon extends RecursiveAction {

    private Double[] weights;
    private int start;
    private int end;

    public ForkJoinCommon(Double[] weights, int start, int end) {
        this.weights = weights;
        this.start = start;
        this.end = end;
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];

        ForkJoinTask<?> task = new ForkJoinCommon(weights, 0, weights.length);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(task);

        //print results
        System.out.println();
        System.out.println("Weights:");
        Arrays.asList(weights).stream().forEach(d -> System.out.print(d.intValue() + " "));

    }

    private static int recursionFactorialExample(int i) {
        if (i <= 1) {
            return 1;
        }
        return i * recursionFactorialExample(i - 1);
    }

    @Override
    protected void compute() {
        if (end - start <= 3) {
            for (int i = start; i < end; i++) {
                weights[i] = (double) new Random().nextInt(100);
                System.out.println("Animal weighted:" + i + "-" + weights[i]);
            }
        } else {
            int middle = start + ((end - start) / 2);
            System.out.println("[start=" + start + ",middle=" + middle + ",end=" + end + "]");
            invokeAll(new ForkJoinCommon(weights, start, middle),
                    new ForkJoinCommon(weights, middle, end));
        }
    }
}
