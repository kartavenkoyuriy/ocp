package chapter4.builtinfunctionalinterfaces;

import java.util.function.BiFunction;
import java.util.function.Function;

interface TriFunction<T, U, V, R>{
    R apply(T t, U u, V v);
}

public class FunctionImpl {

    public static void main(String[] args) {
    }

    private static void testOwnTriFunction() {
        TriFunction<String, Integer, Exception, Boolean> testTriFunction = (s, i, e) -> s != null || i != null || e != null;
        System.out.println(testTriFunction.apply("asd", 1, new Exception()));
        System.out.println(testTriFunction.apply(null, 1, new Exception()));
        System.out.println(testTriFunction.apply("asd", null, new Exception()));
        System.out.println(testTriFunction.apply("asd", 1, null));
        System.out.println(testTriFunction.apply(null, null, null));
    }

    private static void biFunctionExample() {
        BiFunction<String, String, String> stringStringStringBiFunction = String::concat;
        BiFunction<String, String, String> stringStringStringBiFunctionLambda = (stringOne, stringTwo) -> stringOne.concat(stringTwo);
        System.out.println(stringStringStringBiFunction.apply("qwe", "123"));
        System.out.println(stringStringStringBiFunctionLambda.apply("qwe", "123"));
    }

    private static void functionExample() {
        Function<String, Integer> stringIntegerFunction = String::length;
        Function<String, Integer> stringIntegerFunctionLambda = string -> string.length();
        System.out.println(stringIntegerFunction.apply("qwerty"));
        System.out.println(stringIntegerFunctionLambda.apply("qwerty"));

        System.out.println("---");

        Function<String, Boolean> stringBooleanFunctionPredicate = String::isEmpty;
        Function<String, Boolean> stringBooleanFunctionPredicateLambda = string -> string.isEmpty();
        System.out.println(stringBooleanFunctionPredicate.apply(""));
        System.out.println(stringBooleanFunctionPredicateLambda.apply(""));
    }

}
