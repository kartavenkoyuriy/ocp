package chapter3.java8additions;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Java8AdditionsMap {
    public static void main(String[] args) {

    }

    private static void nullFunctionResult() {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("Jenny", 1);
        System.out.println(counts);

//        delete the value
        counts.computeIfPresent("Jenny", (k, v) -> null);
//        don't add the value
        counts.computeIfAbsent("Sam", k -> null);
        System.out.println(counts);
    }

    private static void computeIfAbsentExample() {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("Jenny", 1);
        counts.put("Tom", null);
        System.out.println(counts);

        Function<String, Integer> mapper = String::length;
        Function<String, Integer> mapper1 = s -> 1;
        counts.computeIfAbsent("Jenny", mapper);
        counts.computeIfAbsent("Tom", mapper);
        counts.computeIfAbsent("Sammm", mapper);
        System.out.println(counts);
    }

    private static void computeIfPresentExample() {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("Jenny", 1);
        counts.put("Tom", null);
        System.out.println(counts);

        BiFunction<String, Integer, Integer> countFunction = (s1, i1) -> i1 + 1;
        Integer sam = counts.computeIfPresent("Sam", countFunction);
        Integer jenny = counts.computeIfPresent("Jenny", countFunction);
        Integer tom = counts.computeIfPresent("Tom", countFunction);
        System.out.println(counts);
        System.out.println(sam);
        System.out.println(jenny);
        System.out.println(tom);
//        remove pair if function returns NULL
        counts.computeIfPresent("Jenny", (s1, i1) -> null);
        System.out.println(counts);
    }

    private static void mergeExample() {
//        old value, new value, calculated value
        BiFunction<String, String, String> mapper = (v1, v2) -> v1.length() > v2.length() ? v1 : v2;
        Map<String, String> favorites = new HashMap<>();
        favorites.put("Tom", "Bus Tour");
        favorites.put("Sam", "Bus");
        System.out.println(favorites);

        String tomResult = favorites.merge("Tom", "Skyline", mapper);
        String samResult = favorites.merge("Sam", "Skyline", mapper);
        System.out.println(favorites);
        System.out.println(tomResult);
        System.out.println(samResult);

        Map<String, String> favorites2 = new HashMap<>();
        favorites2.put("Tom", null);
        favorites2.put("Sam", "Tram");
        System.out.println(favorites2);
//        it will not use mapper if NULL value, it replace with new value
        String merge = favorites2.merge("Tom", "Skyline", mapper);
        String merge1 = favorites2.merge("Sam", "Skyline", mapper);
        System.out.println(favorites2);

        Map<String, String> favorites3 = new HashMap<>();
        BiFunction<String, String, String> mapper2 = (v1, v2) -> null;
        favorites3.put("Tom", "Tram");
        favorites3.put("Sam", "Tram");
        System.out.println(favorites3);
//        when mapper returns NULL and there is matching key it return the pair from the map
        favorites3.merge("Jane", "Skyline", mapper2);
        favorites3.merge("Sam", "Skyline", mapper2);
        System.out.println(favorites3);
    }

    private static void putIfAbsentExample() {
        Map<String, String> favorites = new HashMap<>();
        favorites.put("Tom", "Bus");
        System.out.println(favorites);
        favorites.put("Tom", "Vehicle");
        System.out.println(favorites);

        System.out.println("---");

        Map<String, String> favorites2 = new HashMap<>();
        favorites2.put("Sam", "Bus");
        favorites2.put("Tom", null);
        System.out.println(favorites2);
        favorites2.putIfAbsent("Sam", "Tram");
        favorites2.putIfAbsent("Tom", "Tram");
        favorites2.putIfAbsent("Bobby", "Tram");
        System.out.println(favorites2);
    }
}
