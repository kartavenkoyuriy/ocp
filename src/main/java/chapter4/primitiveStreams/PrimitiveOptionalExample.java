package chapter4.primitiveStreams;

import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class PrimitiveOptionalExample {
    public static void main(String[] args) {
        IntStream intStream = IntStream.rangeClosed(1, 10);
        //special optional for primitive
        OptionalDouble average = intStream.average();

        System.out.println(average);
        System.out.println(average.getAsDouble());
        System.out.println(average.orElse(Double.NaN));//returns value
        System.out.println(average.orElseGet(() -> Double.NaN));//returns supplier

        //Optional types for primitives
        //This is really easy to remember since the only thing
        //that changes is the primitive name.
        //OptionalLong - getAsLong; LongSupplier for "orElseGet()"; "average() always returns OptionalDouble"



    }


}
