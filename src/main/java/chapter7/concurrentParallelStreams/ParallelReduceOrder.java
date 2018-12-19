package chapter7.concurrentParallelStreams;

import java.util.Arrays;

public class ParallelReduceOrder {

    public static void main(String[] args) {
//        System.out.println(Arrays.asList("w", "o", "l", "f")
        System.out.println(Arrays.asList("w", "o", "l", "f", "a", "s", "d", "f", "g", "h")
                .parallelStream()
                .reduce("X", String::concat));

    }

    //a=1,b=2,c=3;
    //'(a op b) op c' is equal to 'a op (b op c)'
    //(1-2)-3=-4;1-(2-3)=2; - not associative
    //result is unpredictable(for example: -9 instead of -45)
    private static void nonAssosiativeReduceAccumulator() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
                    .parallelStream()
                    .reduce(0, (i1, i2) -> i1 - i2));
        }
    }

    //looks like ordered
    //"but is not constrained to execute sequentially" - what?
    //
    //With parallel streams, though, we now have to be concerned about order. What if
    //the elements of a string are combined in the wrong order to produce wlfo or flwo? The
    //Streams API prevents this problem, while still allowing streams to be processed in parallel,
    //as long as the arguments to the reduce() operation adhere to certain principles.
    //
    //Requirements for reduce() Arguments
    //■ The identity must be defined such that for all elements in the stream u,
    //combiner.apply(identity, u) is equal to u.
    //■ The accumulator operator op must be associative and stateless such that (a op b) op c
    //is equal to a op (b op c).
    //■ The combiner operator must also be associative and stateless and compatible with the
    //identity, such that for all u and t combiner.apply(u,accumulator.apply(identity,t))
    //is equal to accumulator.apply(u,t).
    //
    //If you follow these principles when building your reduce() arguments, then the
    //operations can be performed using a parallel stream and the results will be ordered as they
    //would be with a serial stream. Note that these principles still apply to the identity and
    //accumulator when using the one- or two-argument version of reduce() on parallel streams.
    //
    //otherwise - unpredictable/unordered result
    private static void simpleParallelReduceExample() {
        for (int j = 0; j < 10; j++) {
            System.out.println(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)
                    .parallelStream()
                    .reduce("", (i, s1) -> i + s1, (s2, s3) -> s2 + s3));
        }
    }
}
