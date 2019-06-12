package chapter7.concurrentParallelStreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

public class ConcurrentParallelStreamsCommon {

    private static int processData(int i){
        int result = i;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result + 1;
    }

    //avoid lambda that can produce side-effect
    //especially in parallel streams
    public static void main(String[] args) {
        independentOperationOrderExample();
    }

    //many(all?) intermediate operations are independent
    //order in parallel streams is not guaranteed
    private static void independentOperationOrderExample() {
        Arrays.asList("asd", "qwe", "zxc")
                .parallelStream()
                .map(String::toUpperCase)
                .forEach(System.out::println);

        System.out.println("***");

        //here in output intermediate and terminal operation are mixed
        Arrays.asList("asd", "qwe", "zxc")
                .parallelStream()
                .map(s -> {System.out.println(s); return s.toUpperCase();})
                .forEach(System.out::println);
    }

    //streams should not be parallel when processing small amount of data
    //because there is extra cost of parallel allocating and processing
    private static void compareStreamParallelStreamTime() {
        ArrayList<Integer> integerArrayList = new ArrayList<>();
        for (int i = 0; i < 1_000; i++) {
            integerArrayList.add(i);
        }

        long start = System.currentTimeMillis();
        integerArrayList.stream().map(a -> processData(a)).count();
        long finish = System.currentTimeMillis();
        System.out.println("stream time:" + (finish - start) / 1000.0);

        long startP = System.currentTimeMillis();
        integerArrayList.parallelStream().map(a -> processData(a)).count();
        long finishP = System.currentTimeMillis();
        System.out.println("parallel stream time:" + (finishP - startP) / 1000.0);
    }

    //this needs partly for cases when parallel and serial streams to ensure that the result is ordered
    //forEachOrdered reduces performance gained by parallelism
    //but the operations before forEachOrdered can still improve the performance
    private static void parallelForEachOrdered() {
        for (int i = 0; i < 10; i++) {
            new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9))
                    .parallelStream()
                    .forEachOrdered(System.out::print);
            System.out.println();

        }
    }

    private static void parallelStreamChangedOrderExample() {
        for (int i = 0; i < 10; i++) {
            new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9))
                    .stream()
                    .forEach(System.out::print);
            System.out.println();
        }
        System.out.println("***");
        for (int i = 0; i < 10; i++) {
            new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9))
                    .parallelStream()
                    .forEach(System.out::print);
            System.out.println();
        }
    }

    private static void creatingParallelStreamExample() {
        //first way to produce a parallel stream is to make it from a regular stream
        //this is an intermediate operation
        ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        Stream<Integer> stream = integers.stream();
        System.out.println("stream:" + stream.isParallel());
        //is an intermediate operation
        Stream<Integer> parallel = stream.parallel();
        System.out.println("parallel:" + parallel.isParallel());

        //---------------
        //second way - produce parallel stream from a collection
        Stream<Integer> integersParalel = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)).parallelStream();
    }
}
