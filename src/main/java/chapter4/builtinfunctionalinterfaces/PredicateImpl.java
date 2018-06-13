package chapter4.builtinfunctionalinterfaces;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class PredicateImpl {
    public static void main(String[] args) {

    }

    private static void predicateDefaultMethods() {
        Predicate<String> egg = s -> s.contains("egg");
//        String s = "asd";
//        Predicate<String> egg1 = s::contains;

        Predicate<String> brown = s -> s.contains("brown");

        Predicate<String> eggAndBrown = egg.and(brown);

        System.out.println(eggAndBrown.test("egg"));
        System.out.println(eggAndBrown.test("brown"));
        System.out.println(eggAndBrown.test("egg_brown"));

        System.out.println("---");

        Predicate<String> eggsButNotBrown = egg.and(brown.negate());
        System.out.println(eggsButNotBrown.test("egg"));
        System.out.println(eggsButNotBrown.test("brown"));
        System.out.println(eggsButNotBrown.test("egg_brown"));
    }

    private static void predicateExample() {
        Predicate<String> stringPredicate = String::isEmpty;
        Predicate<String> stringPredicateLambda = x -> x.isEmpty();
        System.out.println(stringPredicate.test(""));
        System.out.println(stringPredicate.test("asd"));
        System.out.println(stringPredicateLambda.test(""));
        System.out.println(stringPredicateLambda.test("asd"));

        System.out.println("---");

//        next won't work because test() method assumes to receive a String into a parameter, but we have already String identified
//        we should define 1 unknown variable, but we've already define it - it is wrong
//        String testString = "asd";
//        Predicate<String> stringPredicate1 = testString::isEmpty;
//        Predicate<String> stringPredicateLambda1 = testString -> testString.isEmpty();

//        next will work because we need to use startWith with parameter, another way - BiPredicate(in related section)
        String testString1 = "asd";
        Predicate<String> stringPredicate2 = testString1::startsWith;
        System.out.println(stringPredicate2.test("as"));
        Predicate<String> stringPredicateLambda21 = (x) -> testString1.startsWith(x);
        System.out.println(stringPredicateLambda21.test("as"));
    }

    private static void biPredicateExample() {
        //        here first parameter is a string, and the second - is an argument
        BiPredicate<String, String> stringBiPredicate = String::startsWith;
        System.out.println(stringBiPredicate.test("asd", "as"));
        BiPredicate<String, String> stringBiPredicateLambda = (string, prefix) -> string.startsWith(prefix);
        System.out.println(stringBiPredicateLambda.test("asd", "as"));
    }
}
