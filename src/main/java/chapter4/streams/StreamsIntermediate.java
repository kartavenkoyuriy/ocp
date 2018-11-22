package chapter4.streams;

import static java.util.Arrays.asList;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsIntermediate {

    public static void main(String[] args) {

    }

    private static void peekExample() {
        //describing
        //Stream<T> peek(Consumer<? super T> action)
        //useful for debugging
        //DO ONLY USE FOR OPERATION THAT DON'T CHANGE THE STREAM

        Stream<String> stringStream = Stream.of("bear1", "bear2", "grizzly");
        List<String> bearStringList = stringStream
                .filter(x -> x.startsWith("b"))
                .peek(x -> System.out.println(x.length()))
                .peek(System.out::println)
                .collect(Collectors.toList());
        System.out.println(bearStringList);

        List<Character> characters = asList('a', 'b', 'w');
        List<Integer> integers = asList(1, 2, 8);
        StringBuilder debugStringBuilder = new StringBuilder();
        Stream<List<?>> mixList = Stream.of(characters, integers);

        mixList
                .peek(debugStringBuilder::append)//append whole lists
                .map(List::size)
                .forEach(System.out::println);
        System.out.println(debugStringBuilder.toString());
    }

    private static void sortedExample() {
        //describing
        //Stream<T> sorted()
        //Stream<T> sorted(Comparator<? super T> comparator)
        //sort a finite stream

        Stream<String> stringStream = Stream.of("a", "A", "1");
        stringStream.sorted().forEach(System.out::print);
        System.out.println();

        Stream<String> stringStream1 = Stream.of("a", "A", "1");
        stringStream1.sorted(Comparator.reverseOrder()).forEach(System.out::print);
        System.out.println();

        Stream<String> stringStream2 = Stream.of("a", "A", "1");
//        stringStream2.sorted((a1, a2) -> {
//            return a1.compareTo(a2);
//        }).forEach(System.out::print);
        stringStream2.sorted(String::compareTo).forEach(System.out::print);

        //it doesn't compile because
        //Stream<T> sorted(Comparator<? super T> comparator)
        //_sorted_ wait for parameter that will take two objects and returns an int - _compare_
        //public int compare(String o1, String o2)
        //However, Comparator::reverseOrder doesn't do that. It is a reference
        //to a function that takes zero parameters and returns a Comparator.
        //instead we put here something that takes 0 parameters and return a Comparator
        //and we get such error
        //"Bad return type in method reference: cannot convert java.util.Comparator<T> to int"
//        stringStream1.sorted(Comparator::reverseOrder).forEach(System.out::print);
    }

    private static void flatMapExample() {
        //describe
        //<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper)
        //takes each element in the stream and makes any elements it contains top-level elements in a single stream
        //when you want to remove empty elements from a stream or you want to combine a stream of lists
        List<String> emptyStringList = asList();
        List<String> oneStringList = asList("Bonobo");
        List<String> manyStringsList = asList("Bonobo", "Mama Gorilla", "Papa Gorilla");

        //so it simply "flattened" collections to stream of contained objects
        Stream<List<String>> listStream = Stream.of(emptyStringList, oneStringList, manyStringsList);
        listStream.flatMap(List::stream).forEach(System.out::println);
    }

    private static void mapExample() {
        //describe
        //<R> Stream<R> map(Function<? super T, ? extends R> mapper)
        //is for transforming data.

        //As an example, this code converts a list of String objects to a list of Integers representing their lengths
        Stream<String> monkeyStream = Stream.of("monkey", "gorilla", "bonobo", "ape", "2");
        monkeyStream
                .map(String::length)
                .forEach(System.out::println);
    }

    private static void limitSkipExample() {
        //describe
        //Stream<T> limit(int maxSize)
        //limit any stream to first "maxSize" elements

        //Stream<T> skip(int n)
        //skip first "n" elements, then pass ahead

        Stream<Integer> integerStream = Stream.iterate(1, x -> x + 1);
        integerStream.skip(5).limit(2).forEach(System.out::println);
    }

    private static void distinctExample() {
        //describe
        //Stream<T> distinct()
        //remove duplicates
        //need finite stream
        Stream<String> monkeyStream = Stream.of("monkey", "ape", "2", "ape");
        monkeyStream
                .distinct()
                .forEach(System.out::println);

        //hangs
        Stream<String> monkeyInfiniteStream = Stream.generate(() -> "monkey");
        monkeyInfiniteStream
                .distinct()
                .forEach(System.out::println);
    }

    private static void filterExample() {
        //describe
        //Stream<T> filter(Predicate<? super T> predicate)
        //filter condition. if true - pass ahead

        Stream<String> monkeyStream = Stream.of("monkey", "gorilla", "bonobo", "ape", "2");
        monkeyStream.filter(x -> Character.isLetter(x.charAt(0)))
                .forEach(System.out::println);
    }

}
