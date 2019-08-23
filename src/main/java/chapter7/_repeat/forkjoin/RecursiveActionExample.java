package chapter7._repeat.forkjoin;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

public class RecursiveActionExample extends RecursiveAction {

    double[] weights;
    int start;
    int end;

    public RecursiveActionExample(double[] weights, int start, int end) {
        this.weights = weights;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start < 3) {
            for (int i = start; i < end; i++) {
                weights[i] = new Random().nextInt(100);
                System.out.println("weights[" + i + "]:" + weights[i]);
            }
        } else {
            int mid = start + ((end - start) / 2);
            System.out.println("start:" + start +
                    "; mid:" + mid +
                    "; end:" + end);
            invokeAll(new RecursiveActionExample(weights, start, mid),
                    new RecursiveActionExample(weights, mid, end));
        }
    }

    public static void main(String[] args) {
        double[] weights = new double[10];

        ForkJoinTask<?> forkJoinTask = new RecursiveActionExample(weights, 0, weights.length);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.invoke(forkJoinTask);

        System.out.println(Arrays.toString(weights));
    }
}
