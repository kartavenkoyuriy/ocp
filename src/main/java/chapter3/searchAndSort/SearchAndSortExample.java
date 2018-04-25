package chapter3.searchAndSort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


class Rabbit {
    private int id;

    public Rabbit(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Rabbit{" +
                "id=" + id +
                '}';
    }
}

class Duck implements Comparable<Duck> {

    private int id;

    public Duck(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int compareTo(Duck o) {
        return this.getId() - o.getId();
    }

    @Override
    public String toString() {
        return "Duck{" +
                "id=" + id +
                '}';
    }
}

public class SearchAndSortExample {
    public static void main(String[] args) {
        List<Rabbit> rabbits = new ArrayList<>();
        rabbits.add(new Rabbit(5));
        rabbits.add(new Rabbit(3));
        System.out.println(rabbits);
        Comparator<Rabbit> rabbitComparator = (r1, r2) -> r1.getId() - r2.getId();
        Comparator<Rabbit> rabbitComparator1 = Comparator.comparingInt(Rabbit::getId);
        Collections.sort(rabbits, rabbitComparator1);
        System.out.println(rabbits);

        List<String> strings = new ArrayList<>();
        strings.add("3");
        strings.add("1");
        strings.add("2");
        System.out.println(strings);

//        undefined because unsorted
//        int i = Collections.binarySearch(strings, "3");
//        System.out.println(i);

        int i = Collections.binarySearch(strings, "3", Comparator.naturalOrder());
        System.out.println(strings);
        System.out.println(i);

//        undefined because unsorted
//        Comparator<String> stringReverseComparator = Comparator.reverseOrder();
//        int i = Collections.binarySearch(strings, "2", stringReverseComparator);
//        System.out.println(i);

        Comparator<String> stringComparator = Comparator.naturalOrder();

//        Collections.sort(strings, stringReverseComparator);
    }

    private static void sortComparable() {
        List<Duck> ducks = new ArrayList<>();
        ducks.add(new Duck(1));
        Collections.sort(ducks);
    }

    private static void sortNonComparable() {
        List<Rabbit> rabbits = new ArrayList<>();
        rabbits.add(new Rabbit(1));
//        does not compile because not comparable
//        Collections.sort(rabbits);
    }
}
