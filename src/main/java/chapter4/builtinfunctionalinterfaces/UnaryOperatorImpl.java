package chapter4.builtinfunctionalinterfaces;

import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class UnaryOperatorImpl {
    public static void main(String[] args) {

    }

    private static void binaryOperatorExample() {
        BinaryOperator<String> stringBinaryOperator = String::concat;
        BinaryOperator<String> stringBinaryOperatorLambda = (stringOne, stringTwo) -> stringOne.concat(stringTwo);

        System.out.println(stringBinaryOperator.apply("abc", "123"));
        System.out.println(stringBinaryOperatorLambda.apply("abc", "123"));
    }

    private static void unaryOperatorExample() {
        UnaryOperator<String> stringUnaryOperator = String::trim;
        UnaryOperator<String> stringUnaryOperatorLambda = string -> string.trim();

        System.out.println(stringUnaryOperator.apply(" a a "));
        System.out.println(stringUnaryOperatorLambda.apply(" a a "));
    }
}
