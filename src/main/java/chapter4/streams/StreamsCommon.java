package chapter4.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamsCommon {

    public static void main(String[] args) {

    }

    private static void streamCreatingSources() {
        Stream<String> empty = Stream.empty();
        Stream<Integer> singeElementStream = Stream.of(1);
        Stream<Integer> ofArrayStream = Stream.of(1, 2, 3);

        List<String> strings = Arrays.asList("one", "two", "three");
        Stream<List<String>> listStream = Stream.of(strings);

        Stream<String> stream = strings.stream();
        Stream<String> parallelStream = strings.parallelStream();

        Stream<Double> infiniteStream = Stream.generate(Math::random);
        infiniteStream.limit(5).forEach(System.out::println);
        Stream<Integer> iterateStream = Stream.iterate(1, n -> n + 2);
        iterateStream.limit(5).forEach(System.out::println);
    }
}
