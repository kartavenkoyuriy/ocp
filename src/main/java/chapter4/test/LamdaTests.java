package chapter4.test;

import java.util.function.BooleanSupplier;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LamdaTests {
    public static void main(String[] args) {

    }

    private static void castingPrimitives() {
        BooleanSupplier b = () -> true;
        Boolean bo = b.getAsBoolean();
        System.out.println(bo);
    }

    private static void primitiveStreamApi() {
        Stream<Integer> integerStream = Stream.of(1);
        IntStream intStream = integerStream.mapToInt(x -> x);
        DoubleStream doubleStream = intStream.mapToDouble(x -> x);

        //not because of cast, but because of IntStream
//        Stream<Integer> integerStream1 = doubleStream.mapToInt(x -> (int) x);
    }

    private static void variabaleType() {
        IntStream empty = IntStream.empty();
        int sum = empty.sum();
        System.out.println();
    }
}
