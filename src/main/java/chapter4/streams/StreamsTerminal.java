package chapter4.streams;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsTerminal {

    //TODO understand reduction
    /*
    Reductions are
    a special type of terminal operation where all of the contents of the stream are combined
    into a single primitive or Object. For example, you might have an int or a Collection.
     */
    public static void main(String[] args) {
        //reduceReduceReduceExample();
    }

    private static void collectCollectExample() {
        //MUTABLE reduction - more efficient because we use same object while accumulating

        //---------------------------------------------------------------------------------------------------

        //describing of
        //<R> R collect(Supplier<R> supplier, BiConsumer<R, ? super T> accumulator, BiConsumer<R, R> combiner);
        //This is a really useful method, because it lets us get data out of streams and into another form.

        //first argument - supplier, in which we will collect data
        //second argument - biconsumer, which will append/add the data to the supplier
        //third argument - biconsumer, for parallel. merging two(???) "data collection" into supplier

        //for SB its OK only if we don't care about letter order
        Stream<String> wolfStream = Stream.of("w", "o", "l", "f");
        StringBuilder stringBuilder = wolfStream.collect(StringBuilder::new, StringBuilder::append, StringBuilder::append);
        System.out.println(stringBuilder.toString());

        Stream<String> wolfStream1 = Stream.of("w", "o", "l", "f");
        //sorted
        TreeSet<Object> treeSet = wolfStream1.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
        System.out.println(treeSet);

        //---------------------------------------------------------------------------------------------------

        //describing of
        //<R,A> R collect(Collector<? super T, A,R> collector)
        //provide a collector, which will do all the work into mention data structure

        Stream<String> wolfStream2 = Stream.of("w", "o", "l", "f");
        //no sort
        Set<String> stringSet = wolfStream2.collect(Collectors.toSet());
        System.out.println(stringSet);

        Stream<String> wolfStream3 = Stream.of("w", "o", "l", "f");
        //sorted
        TreeSet<String> stringTreeSet = wolfStream3.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(stringTreeSet);

        //---------------------------------------------------------------------------------------------------
    }

    //doesn't work with infinite streams
    //reduction
    //three overloaded methods - each described inside
    private static void reduceReduceReduceExample() {
        //---------------------------------------------------------------------------------------------------

        //describing of
        //T reduce(T identity, BinaryOperator<T> accumulator);
        //where first operator - initial value,
        //second(BinaryOperator) - holds updatedInitialValue, nextStreamElement, and operation with them
        String[] wolfArray = new String[]{"w", "o", "l", "f"};
        String resultArray = "";
        for (String s : wolfArray) {
            resultArray += s;
        }
        System.out.println(resultArray);

        //vs

        Stream<String> wolfStream = Stream.of("w", "o", "l", "f");
        String resultStream = wolfStream.reduce("", (accum, nextElem) -> accum + nextElem);
        //the same
        //String resultStream = wolfStream.reduce("", String::concat);
        System.out.println(resultStream);

        //another example
        Stream<Integer> integerStream = Stream.iterate(1, number -> number + 1);
        Integer multiplyResult = integerStream.limit(5).reduce(1, (accum, next) -> accum * next);
        //returns a value, but a stream is empty
        //Integer multiplyResult = (Integer) Stream.empty().reduce(1, (accum, next) -> null);
        System.out.println(multiplyResult);

        //---------------------------------------------------------------------------------------------------

        //describing of
        //Optional<T> reduce(BinaryOperator<T> accumulator);
        //returns Optional because Stream could be empty
        //(this is method overload when need to show Optional[empty], when it is empty)
        //
        //when no need in initial value
        //However, sometimes it is nice to differentiate the case where the
        //stream is empty rather than the case where there is a value that happens to match the identity
        //being returned from calculation. The signature returning an Optional lets us differentiate these cases.
        //For example, we might return Optional.empty() when the stream is empty
        //and Optional.of(3) when there is a value.
        //
        //another words - for differentiate empty streams and initial values
        //(Integer) Stream.empty().reduce(1, (accum, next) -> null); - Optional[1]
        //and
        //Stream.empty().reduce((a, b) -> a * b).ifPresent(System.out::println); - Optional[empty]
        BinaryOperator<Integer> multiply = (a, b) -> a * b;

        Stream<Integer> empty = Stream.empty();
        Stream<Integer> integerStreamOneValue = Stream.of(3);
        Stream<Integer> integerStreamManyValues = Stream.of(3, 5, 6);

        System.out.println("what if BinaryOperator returns null");
        System.out.println("start null");
        empty.reduce(multiply).ifPresent(System.out::println);
        integerStreamOneValue.reduce(multiply).ifPresent(System.out::println);
        integerStreamManyValues.reduce(multiply).ifPresent(System.out::println);
        System.out.println("end null");

        //what if BinaryOperator returns null - not interesting
//        BinaryOperator<Integer> multiplyNull = (a, b) -> null;
//        empty.reduce(multiplyNull).ifPresent(System.out::println); - no output
//        integerStreamOneValue.reduce(multiplyNull).ifPresent(System.out::println); - 3
//        integerStreamManyValues.reduce(multiplyNull).ifPresent(System.out::println); - 90

        //---------------------------------------------------------------------------------------------------

        //describing of
        //<U> U reduce(U identity, BiFunction<U, ? super T, U> accumulator, BinaryOperator<U> combiner);
        //used when we are processing collections in parallel. It
        //allows Java to create intermediate reductions and then combine them at the end

        Stream<Integer> integerStreamForParallel = Stream.of(1, 2, 3, 4, 5).parallel();
        Integer parallelReduceResult = integerStreamForParallel.reduce(1, multiply, multiply);
        System.out.println("parallelReduceResult:" + parallelReduceResult);
    }

    //doesn't work with infinite streams
    //not a reduction(??? because NO RETURN TYPE ...!!!)
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
        System.out.println(infiniteStream.anyMatch(stringPredicate));
        //hang
//        System.out.println(infiniteStream.allMatch(stringPredicate));
//        System.out.println(infiniteStream.noneMatch(stringPredicate));
    }

    //not a reduction
    //work with infinite streams
    //return an Optional
    private static void findFirstFindAnyExample() {
        //findAny() is useful
        //when you are working with a parallel stream. It gives Java the ﬂexibility to return to you
        //the first element it comes by rather than the one that needs to be first in the stream based
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
        Stream<String> monkeyStream = Stream.of("monkey", "gorilla", "bonobo", "ape");
        System.out.println(monkeyStream.max((a, b) -> a.length() - b.length()).orElseThrow(IllegalArgumentException::new));
        // simplify comparing
        //        System.out.println(monkeyStream.max(Comparator.comparingInt(String::length)));

        // empty stream, nothing to compare, comparator will never be called, optional will be empty
//        Stream<String> empty = Stream.empty();
//        System.out.println(empty.max((a, b) -> b.length() - a.length()).orElseThrow(IllegalArgumentException::new));

        Stream<Integer> integerStream = Stream.iterate(1, i -> i + 1);
        Optional<Integer> max = integerStream.limit(3).max(Comparator.comparingInt(Integer::intValue));
        max.ifPresent(System.out::println);

        //here MIN or MAX functions returns the element which is same as others
        Stream<Integer> generate = Stream.generate(() -> 1);
        Optional<Integer> min = generate.limit(5).max((a, b) -> 0);
        System.out.println(min.orElseThrow(IllegalArgumentException::new));
    }

    //it's a reduction terminal operation because it looks on each element and return a single value
    //doesn't work with infinite streams
    //return a long
    //really is "return mapToLong(e -> 1L).sum()"
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
