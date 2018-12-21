package chapter7.managingConcurrentProcesses;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

//Tips for Reviewing a Fork/Join Class
//■ The class should extend RecursiveAction or RecursiveTask.
//■ If the class extends RecursiveAction, then it should override a protected compute()
//method that takes no arguments and returns void.Identifying Threading Problems 387
//■ If the class extends RecursiveTask, then it should override a protected compute()
//method that takes no arguments and returns a generic type listed in the class
//definition.
//■ The invokeAll() method takes two instances of the fork/join class and does not return
//a result.
//■ The fork() method causes a new task to be submitted to the pool and is similar to the
//thread executor submit() method.
//■ The join() method is called after the fork() method and causes the current thread to
//wait for the results of a subtask.
//■ Unlike fork(), calling compute() within a compute() method causes the task to wait
//for the results of the subtask.
//■ The fork() method should be called before the current thread performs a compute()
//operation, with join() called to read the results afterward.
//■ Since compute() takes no arguments, the constructor of the class is often used to pass
//instructions to the task
public class RecursiveTaskCommon extends RecursiveTask<Double> {

    private Double[] weights;
    private int start;
    private int end;

    public RecursiveTaskCommon(Double[] weights, int start, int end) {
        this.weights = weights;
        this.start = start;
        this.end = end;
    }

    //For the exam, make sure that fork() is
    //called before the current thread begins a subtask and that join() is called after it finishes
    //retrieving the results, in order for them to be done in parallel
    public static void main(String[] args) {
        Double[] weights = new Double[10];

        ForkJoinTask<Double> task = new RecursiveTaskCommon(weights, 0, weights.length);
        ForkJoinPool pool = new ForkJoinPool();
        Double sum = pool.invoke(task);

        //print results
        System.out.println();
        System.out.println("Weights:");
        Arrays.asList(weights).stream().forEach(d -> System.out.print(d.intValue() + " "));
        System.out.println();
        System.out.println("Sum:" + sum);
    }

    @Override
    protected Double compute() {
        if (end - start <= 3) {
            double sum = 0;
            for (int i = start; i < end; i++) {
                weights[i] = (double) new Random().nextInt(100);
                System.out.println("Animal weighted:" + i + "-" + weights[i]);
                sum += weights[i];
            }
            return sum;
        } else {
//            return multiThreadedPartExample();
            return singleThreadedPartExample();
        }
    }

    private Double multiThreadedPartExample() {
        int middle = start + ((end - start) / 2);
        System.out.println("[start=" + start + ",middle=" + middle + ",end=" + end + "]");
        RecursiveTask<Double> otherTask = new RecursiveTaskCommon(weights, start, middle);
        otherTask.fork();
        return new RecursiveTaskCommon(weights, middle, end).compute() + otherTask.join();
    }

    //In this example, the current thread calls join(), causing it to wait for the [start,middle]
    //subtask to fnish before starting on the [middle,end] subtask. In this manner, the results are
    //actually performed in a single-threaded manner.
    private Double singleThreadedPartExample() {
        int middle = start + ((end - start) / 2);
        System.out.println("[start=" + start + ",middle=" + middle + ",end=" + end + "]");
        RecursiveTask<Double> otherTask = new RecursiveTaskCommon(weights, start, middle);
        Double joinedResult = otherTask.fork().join();
        return new RecursiveTaskCommon(weights, middle, end).compute() + joinedResult;
    }
}
