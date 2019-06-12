package chapter7._repeat.forkjoin;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class RecursiveTaskExample extends RecursiveTask<Double> {

    double[] weights;
    int start;
    int end;

    public RecursiveTaskExample(double[] weights, int start, int end) {
        this.weights = weights;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {
        if (end - start < 3) {
            double sum = 0;
            for (int i = start; i < end; i++) {
                weights[i] = new Random().nextInt(100);
                System.out.println("weights[" + i + "]:" + weights[i]);
                sum += weights[i];
            }
            return sum;
        } else {
            int mid = start + ((end - start) / 2);
            System.out.println("start:" + start +
                    "; mid:" + mid +
                    "; end:" + end);
            RecursiveTaskExample otherTask = new RecursiveTaskExample(weights, start, mid);
            otherTask.fork();
            return new RecursiveTaskExample(weights, mid, end).compute() + otherTask.join();
        }
    }

    public static void main(String[] args) {
        double[] weight = new double[10];
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Double> forkJoinTask = new RecursiveTaskExample(weight, 0, weight.length);
        final Double sum = forkJoinPool.invoke(forkJoinTask);
        System.out.println(sum);
    }
}
