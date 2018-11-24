package chapter4.primitiveStreams;

import java.sql.SQLOutput;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class CreatePrimitiveStream {
    public static void main(String[] args) {

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
