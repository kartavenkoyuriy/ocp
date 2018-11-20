package chapter4.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamsTerminal {

    //TODO understand reduction
    /*
    Reductions are
    a special type of terminal operation where all of the contents of the stream are combined
    into a single primitive or Object. For example, you might have an int or a Collection.
     */
    public static void main(String[] args) {
        //doesn't work with infinite streams
        //reduction

        Stream<String> monkeyStream = Stream.of("monkey", "gorilla", "bonobo", "ape", "2");


    }

    //doesn't work with infinite streams
    //not a reduction
    //takes a Consumer as a parameter
    //return type is void
    private static void forEachExample() {
        //If you
        //want something to happen, you have to make it happen in the loop

        Stream<String> monkeyStream = Stream.of("monkey", "gorilla", "bonobo", "ape", "2");
//        monkeyStream.forEach(System.out::println);
        List<String> firstLetters = new ArrayList<>();
        monkeyStream.forEach(x -> firstLetters.add(String.valueOf(x.charAt(0))));
        System.out.println(firstLetters);

        //Streams
        //cannot use a traditional for loop to run because they don’t implement the Iterable interface.
//        Stream s = Stream.of(1);
//        for (Integer i: s) {} // DOES NOT COMPILE

        //forEach(either on Stream or Collection) can't be used to update the values in the list, because it uses new references
//        List<String> stringList = Arrays.asList("one", "two");
//        stringList.forEach(x -> x += "123");
//        System.out.println(stringList);
//
//        List<Integer> integerList = Arrays.asList(1, 2, 3);
//        integerList.forEach(x -> x += 10);
//        System.out.println(integerList);
//
//        for (Integer integer : integerList) {
//            integer += 10;
//        }
    }

    //not a reduction
    //work with an infinite streams(sometimes)
    //takes a Predicate as a parameter
    //returns boolean
    private static void anyMatchAllMatchNoneMatchExample() {
        Stream<String> monkeyStream = Stream.of("monkey", "gorilla", "bonobo", "ape", "2");

        Predicate<String> stringPredicate = x -> Character.isLetter(x.charAt(0));
        System.out.println(monkeyStream.anyMatch(x -> Character.isLetter(x.charAt(0))));
//        System.out.println(monkeyStream.allMatch(stringPredicate));
//        System.out.println(monkeyStream.noneMatch(stringPredicate));

        Stream<String> infiniteStream = Stream.generate(() -> "chimp");
//        System.out.println(infiniteStream.anyMatch(stringPredicate));
        //hang
        System.out.println(infiniteStream.allMatch(stringPredicate));
        System.out.println(infiniteStream.noneMatch(stringPredicate));
    }

    //work with infinite streams
    //mot a reduction
    //return an Optional
    private static void findFirstFindAnyExample() {
        //findAny() is useful
        //when you are working with a parallel stream. It gives Java the ﬂexibility to return to you
        //the frst element it comes by rather than the one that needs to be frst in the stream based
        //on the intermediate operations

        Stream<String> monkeyStream = Stream.of("monkey", "gorilla", "bonobo", "ape");
        Stream<String> infiniteMonkeyStream = Stream.generate(() -> "chimp");

//        monkeyStream.findAny().ifPresent(System.out::println);
        monkeyStream.findFirst().ifPresent(System.out::println);

        //SOMETIMES "findAny" in parallel stream prints other integer that "1"
        Stream<Integer> infiniteIntegerStream = Stream.iterate(1, (x) -> x + 1).parallel();
        System.out.println(infiniteIntegerStream.findAny().orElseThrow(IllegalArgumentException::new));
//        System.out.println(infiniteIntegerStream.findFirst().orElseThrow(IllegalArgumentException::new));
    }

    //both are reduction because they need to look into each value
    //don't work with infinite streams
    //takes a Comparator as a parameter
    //return an Optional
    private static void minMaxExample() {
        //hang on finite streams
        //both are reduction because they need to look into each value
        Stream<String> monkeyStream = Stream.of("monkey", "gorilla", "bonobo", "ape");
        System.out.println(monkeyStream.max((a, b) -> a.length() - b.length()).orElseThrow(IllegalArgumentException::new));
        // simplify comparing
        //        System.out.println(monkeyStream.max(Comparator.comparingInt(String::length)));

        // empty stream, nothing to compare, comparator will never be called, optional will be empty
//        Stream<String> empty = Stream.empty();
//        System.out.println(empty.max((a, b) -> b.length() - a.length()).orElseThrow(IllegalArgumentException::new));

        //here MIN or MAX functions returns the element which is same as others
        Stream<Integer> generate = Stream.generate(() -> 1);
        Optional<Integer> min = generate.limit(5).max((a, b) -> 0);
        System.out.println(min.orElseThrow(IllegalArgumentException::new));
    }

    //it's a reduction terminal operation because it looks on each element and return a single value
    //doesn't work with infinite streams
    //return an int
    private static void countExample() {
        Stream<String> monkeyStream = Stream.of("monkey", "gorilla", "bonobo");
        System.out.println(monkeyStream.count());
//        System.out.println(monkeyStream.limit(2).count());

        Stream<String> generate = Stream.generate(() -> "1");
        //hangs
//        System.out.println(generate.count());
        System.out.println(generate.limit(5).count());

        //prints "0"
        Stream<Object> empty = Stream.empty();
        System.out.println(empty.count());
    }
}
