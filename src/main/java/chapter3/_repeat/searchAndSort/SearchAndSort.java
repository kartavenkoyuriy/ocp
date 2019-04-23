package chapter3._repeat.searchAndSort;

import java.util.*;

class Rabbit{int i;}

public class SearchAndSort {
    public static void main(String[] args) {

    }

    private static void treeSetWithComparatorInConstructor() {
        Set<Rabbit> rabbits = new TreeSet<>(Comparator.comparingInt(o -> o.i));
        rabbits.add(new Rabbit());
    }

    private static void binarySearchWithWrongOrder() {
        List<String> strings = Arrays.asList("A", "F");
        Comparator c = Comparator.reverseOrder();
        int h = Collections.binarySearch(strings, "F", c);
        System.out.println(h);
    }

    private static void sortingWithComparator() {
        Rabbit r1 = new Rabbit();
        Rabbit r2 = new Rabbit();

        List<Rabbit> rabbits = new ArrayList<>();
        rabbits.add(r1);
        rabbits.add(r2);
        Collections.sort(rabbits, Comparator.comparingInt(o -> o.i));
    }

    private static void runtimeErrorBecauseTreeSetForComparable() {
        Rabbit r1 = new Rabbit();
        Rabbit r2 = new Rabbit();
        Set<Rabbit> rabbits = new TreeSet<>();
        rabbits.add(r1);
    }

    private static void notComparableCompileError() {
        Rabbit r1 = new Rabbit();
        Rabbit r2 = new Rabbit();
        List<Rabbit> rabbits = new ArrayList<>();
        rabbits.add(r1);
        rabbits.add(r2);
//        compile error because Rabbit is not Comparable
//        Collections.sort(rabbits);
    }
}
