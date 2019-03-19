package chapter3.collections.set;

import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

public class NavigableSetSpecial {
    public static void main(String[] args) {
        navigableSetExample();
    }

    private static void treeSetOrder() {
        Set<String> stringOrder = new TreeSet<>();
        stringOrder.add("One");
        stringOrder.add("ONE");
        stringOrder.add("one");
        stringOrder.add("On");
        stringOrder.add("1");

        System.out.println(stringOrder);
    }

    private static void navigableSetExample() {
        NavigableSet<Integer> integers = new TreeSet<>();
        for (int i = 1; i <= 20; i++) {
            integers.add(i);
        }

//        highest element that less than "input parameter", or null if no such
        System.out.println(integers.lower(10));
//        highest element that less or equal than "input parameter", or null if no such
        System.out.println(integers.floor(10));
//        lowest element that more or equal than "input parameter", or null if no such
        System.out.println(integers.ceiling(10));
//        lowest element that more than "input parameter", or null if no such
        System.out.println(integers.higher(10));

        System.out.println("---");

        System.out.println(integers.lower(1));
        System.out.println(integers.floor(1));
        System.out.println(integers.ceiling(1));
        System.out.println(integers.higher(1));

        System.out.println("---");

        System.out.println(integers.lower(20));
        System.out.println(integers.floor(20));
        System.out.println(integers.ceiling(20));
        System.out.println(integers.higher(20));

        System.out.println("---");

        System.out.println(integers.lower(0));
        System.out.println(integers.floor(0));
        System.out.println(integers.ceiling(0));
        System.out.println(integers.higher(0));
    }
}
