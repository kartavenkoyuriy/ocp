package chapter7.concurrentParallelStreams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ParallelStreamStatefulOperations {
    public static void main(String[] args) {
        parallelStreamsSideEffect();
    }

    //because of stateful lambda expression in parallel stream, result in 'integers' can't be predicted
    private static void parallelStreamsSideEffect() {
        List<Integer> integers = Collections.synchronizedList(new ArrayList<>());
        Arrays.asList(1, 2, 3, 4, 5, 6).parallelStream()
                .map(i -> {
                    //stateful lambda expression
                    //side-effect
                    integers.add(i + 10);
                    return i;
                })
                .forEachOrdered(i -> System.out.print(i + " "));

        System.out.println();
        //because of stateful lambda expression in parallel stream, result in 'integers' can't be predicted
        System.out.println(integers);
    }
}
