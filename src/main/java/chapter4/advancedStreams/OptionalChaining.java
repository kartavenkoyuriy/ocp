package chapter4.advancedStreams;

import chapter2.functionalInterfaces.TestVoidInterface;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

public class OptionalChaining {
    public static void main(String[] args) {
        //chaining that filters number of digits and prints
        Optional<Integer> integer = Optional.of(1111);
        integer.map(x -> "" + x)
                .filter(x -> x.length() == 3)
                .ifPresent(System.out::println);

        //map into another type of optional
        Optional
                .of("qwerty")
                .map(String::length)
                .ifPresent(System.out::println);

        Optional<String> optional = Optional.of("qwerty");

        //map vs flatmap
        //Chaining calls to flatMap() is useful when you want to transform one
        //Optional type to another.
        Optional<Optional<Integer>> integer1 = optional.map(OptionalChaining::calculator);
        Optional<Integer> integer2 = optional.map(OptionalChaining::calculatorInt);

        Optional<Integer> integer3 = optional.flatMap(OptionalChaining::calculator);
    }

    static Optional<Integer> calculator(String s) {
        return Optional.of(s.length());
    }

    static Integer calculatorInt(String s) {
        return s.length();
    }
}

class ExceptionCaseStudy{
    public static void main(String[] args) throws IOException {
        //pass the exception
        long count = ExceptionCaseStudy.create().stream().count();

        //Unhandled exception
        //one of the solutions - rethrow unchecked ex
        Supplier<List<String>> create = ExceptionCaseStudy::create1;
    }

    static List<String> create() throws IOException {
        throw  new IOException();
    }

    static List<String> create1() {
        try {
            throw  new IOException();
        } catch (IOException e) {
            //interesting point: when catch tries to handle the exception(not to rethrow something) - method need an additional return statement
            //when rethrow RuntimeEx - everything is fine

            throw new RuntimeException();
//            handle the exception
        }
    }


}
