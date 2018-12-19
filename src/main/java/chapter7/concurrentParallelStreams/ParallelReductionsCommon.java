package chapter7.concurrentParallelStreams;

import java.util.Arrays;

public class ParallelReductionsCommon {

    public static void main(String[] args) {

        unorderedExample();
    }

    //all streams are ordered by default
    //unordered() on serial streams has no effect
    //unordered() on parallel streams tells JVM that order doesn't matter
    //For parallel streams, relaxing the ordering constraint can sometimes enable more efficient execution
    //
    //In cases where the stream has an encounter order, but the user does not particularly care about that encounter order,
    //explicitly de-ordering the stream with unordered() may improve parallel performance for some stateful or terminal operations.
    //However, most stream pipelines, such as the "sum of weight of blocks" example above,
    //still parallelize efficiently even under ordering constraints.
    private static void unorderedExample() {
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).stream().unordered();
        for (int i = 0; i < 10; i++) {
            Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).stream().unordered().parallel().skip(2).forEach(System.out::print);
            System.out.println();
        }
    }

    //skip(), limit(), findFirst() and others - ordered operations(all streams by default),
    //parallel streams works slowly due to synchronization-like
    //as well, order is provided, and result is the same
    private static void orderedOperationExample() {
        //this will always print 1
        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).stream().skip(2).limit(4).findFirst().get());

        //because of parallel stream here the answer is unpredictable
        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).parallelStream().skip(2).limit(4).findFirst().get());
    }

    private static void parallelReductionDifference() {
        //this will always print 1
        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6).stream().findAny().get());

        //because of parallel stream here the answer is unpredictable
        System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream().findAny().get());
    }
}
