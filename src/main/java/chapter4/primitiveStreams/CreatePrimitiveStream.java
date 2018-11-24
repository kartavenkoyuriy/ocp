package chapter4.primitiveStreams;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import java.util.List;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class CreatePrimitiveStream {
    public static void main(String[] args) {

        //mapping to the same
        //type you started with is just called map(). When returning an object stream, the method is
        //mapToObj(). Beyond that, it’s the name of the primitive type in the map method name.
        DoubleStream doubleStream = Stream.empty().mapToDouble(x -> 1);
        Stream<Double> objectStream = doubleStream.mapToObj(x -> x);


        //Function parameters when mapping between types of streams
        //you can start by thinking about the source and target types.
        //When the target type is an object, you drop the To from the name. When the mapping is to the same type
        //you started with, you use a unary operator instead of a function for the primitive streams.
        Stream<String> stringStream = Stream.of("tiger", "dog", "elephant");
        IntStream intStream = stringStream.mapToInt(String::length);
        intStream.forEach(System.out::println);

        System.out.println("\n***");

        List<String> strings = asList("qwe", "asdf", "zxcvbn");
        List<String> strings1 = asList("1", "12", "123");
        strings.stream().mapToInt(String::length).forEach(System.out::print);

        System.out.println("\n***");

        Stream.of(strings, strings1)
                .flatMap(List::stream)
                .mapToInt(String::length)
                .forEach(System.out::print);

        System.out.println("\n***");

        //flatMapToInt
        strings1.stream().flatMapToInt(x -> IntStream.of(x.length())).forEach(System.out::print);

    }

    private static void rangeExample() {
        //range() - end exclusive
        IntStream range = IntStream.range(1, 10);
        range.forEach(System.out::print);

        System.out.println();

        //rangeClosed() - end inclusive
        IntStream range1 = IntStream.rangeClosed(1, 10);
        range1.forEach(System.out::print);
    }

    private static void generateIterateExample() {
        DoubleStream doubleStreamGenerate = DoubleStream.generate(Math::random);
        IntStream intStreamGenerate = IntStream.generate(() -> (int) Math.round(Math.random() * 100));

        doubleStreamGenerate.limit(10).forEach(System.out::println);
        System.out.println("***");
        intStreamGenerate.limit(10).forEach(System.out::println);
        System.out.println("***");

        DoubleStream doubleStreamIterate = DoubleStream.iterate(1024, x -> x / 2);
        doubleStreamIterate.limit(16).forEach(System.out::println);
//        doubleStreamIterate.limit(16).forEach(x -> System.out.println(x));

        System.out.println("***");
        Random r = new Random();
        r.ints().limit(5).forEach(System.out::println);
    }

    private static void customCreatingPrimitiveStreamsExample() {
        DoubleStream empty = DoubleStream.empty();
        DoubleStream doubleStreamOneElem = DoubleStream.of(3.14);
        DoubleStream doubleStreamManyElem = DoubleStream.of(1.1, 5.5, 2, 3.4);

        empty.forEach(System.out::println);
        System.out.println("***");
        doubleStreamOneElem.forEach(System.out::println);
        System.out.println("***");
        doubleStreamManyElem.forEach(System.out::println);
        System.out.println("***");
    }
}
