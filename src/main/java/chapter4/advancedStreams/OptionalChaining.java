package chapter4.advancedStreams;

import java.util.Optional;

public class OptionalChaining {
    public static void main(String[] args) {

        //chaining that filters number of digits and prints
        Optional<Integer> integer = Optional.of(1111);
        integer.map(x -> "" + x)
                .filter(x -> x.length() == 3)
                .ifPresent(System.out::println);

        //
    }
}
