package chapter3.java8additions_redo;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Java8MapApi {

    static Map<String, String> favorites = new HashMap<>();


    public static void main(String[] args) {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("Jenny", 1);
        counts.put("Tom", null);
        counts.put("Ivan", null);
        counts.put("Sam", 1);

        Function<String, Integer> function = (k) -> 10;

        counts.computeIfAbsent("Jenny", function);//ignore
        counts.computeIfAbsent("Ivan", function);//fill
        counts.computeIfAbsent("Tolik", function);//create

        counts.computeIfAbsent("George", (k) -> null);//put if absent null
        counts.computeIfAbsent("Tolik", (k) -> null);
        counts.computeIfAbsent("Alex", (k) -> null);//ignore, because need to no key replace to NULL

        System.out.println(counts);
    }

    private static void computeIfPresentExample() {
        Map<String, Integer> counts = new HashMap<>();
        counts.put("Jenny", 1);
        counts.put("Tom", null);
        counts.put("Ivan", null);
        counts.put("Sam", 1);

        BiFunction<String, Integer, Integer> biFunction = (k, v) -> v + 1;

        counts.computeIfPresent("Jenny", biFunction);
        counts.computeIfPresent("Tom", biFunction);
        counts.computeIfPresent("Ivan", (k, v) -> null);
        counts.computeIfPresent("Sam", (k, v) -> null);

        System.out.println(counts);
    }

    private static void mergeExample() {
        favorites.clear();

        favorites.put("Jenny", "Bus Tour");
        favorites.put("Tom", "Tram");
        favorites.put("Ivan", null);
        favorites.put("Sam", null);

        BiFunction<String, String, String> mapper = (s1, s2) -> s1.length() > s2.length() ? s1 : s2;
        String jenny = favorites.merge("Jenny", "Skyride", mapper);
        String tom = favorites.merge("Tom", "Skyride", mapper);

        favorites.merge("Ivan", "a", (s1, s2) -> s1 + s2);
        favorites.merge("Tom", "a", (s1, s2) -> s1 + s2);

        System.out.println(favorites);
        System.out.println(jenny);
        System.out.println(tom);
    }

    private static void putIfAbsentExample() {
        favorites.clear();

        favorites.put("Jenny", "Bus Tour");
        favorites.put("Tom", null);

        favorites.putIfAbsent("Jenny", "Tram");
        favorites.putIfAbsent("Sam", "Tram");
        favorites.putIfAbsent("Tom", "Tram");

        System.out.println(favorites);
    }


}
