package chapter4.advancedStreams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectingResults {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("lions", "tigers", "bears", "tigers1");
        Map<Integer, Long> collect = stream.collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(collect);

        Stream<String> stream1 = Stream.of("lions", "tigers", "bears", "tigers1");
        Map<Integer, Double> collect1 = stream1.collect(Collectors.groupingBy(String::length, Collectors.averagingInt(String::length)));
        System.out.println(collect1);

//        Stream<String> stream2 = Stream.of("lions", "tigers", "bears", "tigers1");
//        Map<Integer, Optional<Character>> collect2 = stream2.collect(
//                Collectors.groupingBy(
//                        String::length,
//                        Collectors.mapping(s -> s.charAt(0),
//                                Collectors.minBy(Comparator.naturalOrder()))));
//        System.out.println(collect2);


    }

    private static void partitioningExample() {
        //Partitioning is a special case of grouping. With partitioning, there are only two possible
        //groups—true and false. Partitioning is like splitting a list into two parts.

        Stream<String> stream = Stream.of("lions", "tigers", "bears", "tigers1");
        Map<Boolean, List<String>> collect = stream.collect(Collectors.partitioningBy(x -> x.length() > 5));
        System.out.println(collect);

        Stream<String> stream1 = Stream.of("lions", "tigers", "bears", "tigers1");
        Map<Boolean, Set<String>> collect1 = stream1.collect(Collectors.partitioningBy(x -> x.length() > 5, Collectors.toSet()));
        System.out.println(collect1);

        //Unlike groupingBy(), we cannot change the type of Map that gets returned. However,
        //there are only two keys in the map, so does it really matter which Map type we use?
    }

    private static void groupingExample() {
        Stream<String> stream = Stream.of("lions", "tigers", "bears", "tigers1");
        //grouping to Map<Integer, List> where key is the result of function, and value contains list of data that fit the function
        //This makes the keys in the map the function value and the values the function results.
        Map<Integer, List<String>> collect = stream.collect(Collectors.groupingBy(String::length));
        System.out.println(collect);

        Stream<String> stream1 = Stream.of("lions", "tigers", "bears", "tigers1");
        //this implementation may change the grouping collection
        Map<Integer, Set<String>> collect1 = stream1.collect(Collectors.groupingBy(String::length, Collectors.toSet()));
        System.out.println(collect1);

        Stream<String> stream2 = Stream.of("lions", "tigers", "bears", "tigers1");
        //this implementation may change the grouping collection AND choose the type of wrapping collection
        TreeMap<Integer, Set<String>> collect2 = stream2.collect(Collectors.groupingBy(String::length, TreeMap::new, Collectors.toSet()));
        System.out.println(collect2);
    }

    private static void mapCollectorsExample() {
        Stream<String> stream = Stream.of("lions", "tigers", "bears", "tigers1");
        Map<String, Integer> collect = stream.collect(Collectors.toMap(s -> s, String::length));
        System.out.println(collect);

        Stream<String> stream1 = Stream.of("lions", "tigers", "bears", "tigers1");
        //throwing an exception with duplicate key
//        Map<Integer, String> collect1 = stream1.collect(Collectors.toMap(String::length, s -> s));
//        System.out.println(collect1);

        Stream<String> stream2 = Stream.of("lions", "tigers", "bears", "tigers1");
        //Function.identity() = s -> s
        //this toMap implementation contains merging BinaryOperator for same keys
        Map<Integer, String> collect2 = stream2.collect(Collectors.toMap(String::length, Function.identity(), (s1, s2) -> s1 + "," + s2));
        System.out.println(collect2);
        //implementation of Map is not guaranteed
        System.out.println(collect2.getClass());

        System.out.println("***");

        Stream<String> stream3 = Stream.of("lions", "tigers", "bears", "tigers1");
        //this toMap implementation contains merging BinaryOperator for same keys and add a Supplier for certain collection type
        TreeMap<Integer, String> collect3 = stream3.collect(Collectors.toMap(String::length, Function.identity(), (v1, v2) -> v1 + "," + v2, TreeMap::new));
        System.out.println(collect3);
        System.out.println(collect3.getClass());
    }

    private static void basicCollectorsExample() {
        Stream<String> stream = Stream.of("lions", "tigers", "bears");
        String collect = stream.collect(Collectors.joining(", "));
        System.out.println(collect);

        Stream<String> stream1 = Stream.of("lions", "tigers", "bears");
        Double collect1 = stream1.collect(Collectors.averagingInt(String::length));
        System.out.println(collect1);

        Stream<String> stream2 = Stream.of("lions", "tigers", "bears", "tigers1");
        TreeSet<String> t = stream2.filter(x -> x.startsWith("t")).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(t);

        System.out.println("***");

        Stream<String> stream31 = Stream.of("lions", "tigers", "bears", "tigers1");
        Stream<String> stream32 = Stream.of("lions", "tigers", "bears", "tigers1");
        Stream<String> stream33 = Stream.of("lions", "tigers", "bears", "tigers1");
        Optional<String> collect31 = stream31.collect(Collectors.maxBy((s1, s2) -> s1.length() - s2.length()));
        collect31.ifPresent(System.out::println);
        Optional<String> collect32 = stream32.collect(Collectors.maxBy(Comparator.comparingInt(String::length)));
        collect32.ifPresent(System.out::println);
        Optional<String> max33 = stream33.max(Comparator.comparingInt(String::length));
        max33.ifPresent(System.out::println);
    }
}
