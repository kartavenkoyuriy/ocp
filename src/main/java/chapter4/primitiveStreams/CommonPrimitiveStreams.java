package chapter4.primitiveStreams;


import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CommonPrimitiveStreams {
    public static void main(String[] args) {
        Stream<Integer> integerStream = Stream.of(3, 5, 6);
        Integer reduce = integerStream.reduce(0, (init, x) -> init + x);
        System.out.println(reduce);

        Stream<Integer> integerStream1 = Stream.of(3, 5, 6);
        int sum = integerStream1
                .mapToInt(x -> x)
                .sum();
        System.out.println(sum);

        Stream<Integer> integerStream2 = Stream.of(3, 5, 6);
        OptionalDouble average = integerStream2.mapToInt(x -> x).average();
        average.ifPresent(System.out::println);

        IntStream intStream;

    }
}
