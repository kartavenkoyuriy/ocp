package chapter7._repeat.examRepeating01;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class RecursiveTaskCommon extends RecursiveTask<Double> {

    private Double[] weights;
    private int start;
    private int end;

    public RecursiveTaskCommon(Double[] weights, int start, int end) {
        this.weights = weights;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Double compute() {
        if (end - start <= 3) {
            double sum = 0;
            for (int i = start; i < end; i++) {
                weights[i] = (double) new Random().nextInt(100);
                System.out.println("Animal " + i + " weighted:" + weights[i]);
                sum += weights[i];
            }
            return sum;
        } else {
            int middle = start + ((end - start) / 2);

            //divide and conquer approach
            //the framework first “forks”, recursively breaking the task into
            //smaller independent subtasks until they are simple enough to be executed asynchronously.

            //create other task
            RecursiveTaskCommon otherTask = new RecursiveTaskCommon(weights, start, middle);
            //tell that this other task to be executed in a separate thread
            otherTask.fork();

            //the “join” part begins, in which results of all subtasks are recursively joined into a single result,
            //or in the case of a task which returns void, the program simply waits until every subtask is executed.

            //join method tells to current thread to wait
            return new RecursiveTaskCommon(weights, middle, end).compute() + otherTask.join();
        }
    }

    public static void main(String[] args) {
        Double[] weights = new Double[10];
        ForkJoinTask<Double> task = new RecursiveTaskCommon(weights, 0, weights.length);
        //Work Stealing Algorithm
        //Simply put – free threads try to “steal” work from deques of busy threads.
        ForkJoinPool pool = new ForkJoinPool();
        Double sum = pool.invoke(task);

        System.out.println();
        System.out.println(Arrays.toString(weights));
        System.out.println("sum:" + sum);
    }
}
