package chapter3.compare.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortRabbits {
    private int age;

    public SortRabbits(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return new Integer(age).toString();
    }

    public static void main(String[] args) {
        List<SortRabbits> rabbits = new ArrayList<>();
        rabbits.add(new SortRabbits(11));
        rabbits.add(new SortRabbits(9));
        rabbits.add(new SortRabbits(23));
        rabbits.add(new SortRabbits(2));
        rabbits.add(new SortRabbits(-87));
        rabbits.add(new SortRabbits(4));

        Comparator<SortRabbits> comparator = Comparator.comparingInt(SortRabbits::getAge);
        Comparator<SortRabbits> comparator1 = (r1, r2) -> r1.getAge() - r2.getAge();
        Comparator<SortRabbits> comparator2 = new Comparator<SortRabbits>() {
            @Override
            public int compare(SortRabbits o1, SortRabbits o2) {
                return o1.getAge() - o2.getAge();
            }
        };

        System.out.println(rabbits);
        Collections.sort(rabbits, comparator2);
        System.out.println(rabbits);
    }

}


