package chapter3.compare.repeat.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparingInt;

class Duck implements Comparable<Duck> {

    private String name;
    private int age;

    public Duck(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Duck o) {
        return name.compareTo(o.getName());
    }

    @Override
    public String toString() {
        return "Duck{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

public class ComparatorExample {
    public static void main(String[] args) {
        Duck d5 = new Duck("CD", 10);
        Duck d4 = new Duck("B", 10);
        Duck d2 = new Duck("A", 8);
        Duck d1 = new Duck("A", 10);
        Duck d3 = new Duck("B", 2);

        List<Duck> ducks = new ArrayList<>();
        ducks.add(d5);
        ducks.add(d4);
        ducks.add(d2);
        ducks.add(d1);
        ducks.add(d3);

        System.out.println("1" + ducks);

        Collections.sort(ducks);

        System.out.println("2" + ducks);

        Comparator<Duck> duckFullComparator = new Comparator<Duck>() {
            @Override
            public int compare(Duck o1, Duck o2) {
                return o1.getAge() - o2.getAge();
            }
        };

        Comparator<Duck> duckLambdaComparator = (o1, o2) -> o1.getAge()-o2.getAge();
        Comparator<Duck> duckMetodReferenceComparator = Comparator.comparingInt(Duck::getAge);

        Collections.sort(ducks, duckMetodReferenceComparator);

        System.out.println("3" + ducks);

lambdaLater(ducks);

    }

    private static void lambdaLater(List<Duck> ducks) {
        Comparator<Duck> duckFullLambda = (o1, o2) -> o1.compareTo(o2) == 0 ? o1.getAge() - o2.getAge() : o1.compareTo(o2);
        Comparator<Duck> duckFullLambda2 = (o1, o2) -> {
            final int result = o1.compareTo(o2);
            if (result != 0) {
                return result;
            }
            return o1.getAge() - o2.getAge();
        };
        Comparator<Duck> duckFullLambda4 = new Comparator<Duck>() {
            @Override
            public int compare(Duck o1, Duck o2) {
                Comparator<Duck> duckComparator = Comparator.comparing(d -> d.getName());
                duckComparator = duckComparator.thenComparingInt(d -> d.getAge());
                return duckComparator.compare(o1, o2);
            }
        };


        Comparator<Duck> duckFullLambda7 = Comparator.comparing((Duck d) -> d.getName()).thenComparingInt((Duck d) -> d.getAge());
        Comparator<Duck> duckFullLambda6 = Comparator.comparing(Duck::getName).thenComparingInt(Duck::getAge);


        Collections.sort(ducks, duckFullLambda);
        System.out.println("3" + ducks);
        Collections.sort(ducks, duckFullLambda2);
        System.out.println("4" + ducks);
        Collections.sort(ducks, duckFullLambda4);
        System.out.println("5" + ducks);
        Collections.sort(ducks, duckFullLambda6);
        System.out.println("6" + ducks);
        Collections.sort(ducks, duckFullLambda7);
        System.out.println("7" + ducks);
    }


}
