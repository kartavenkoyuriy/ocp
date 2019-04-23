package chapter3._repeat.comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Duck implements Comparable<Duck>{
    private String name;

    public Duck(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Duck d) {
        return name.compareTo(d.getName());
    }
}

class LegacyDuck implements Comparable{

    private String name;

    public String getName() {
        return name;
    }

    public LegacyDuck(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
        return name.compareTo(((LegacyDuck) o).getName());
    }
}

class Dog{
    private int age;

    public Dog(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                '}';
    }
}

class Cat implements Comparable<Cat>{
    private int weight;

    public Cat(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public int compareTo(Cat o) {
        return weight - o.getWeight();
    }
}

public class ComparableExample {
    public static void main(String[] args) {

    }

    private static void compareResult() {
        Cat cat1 = new Cat(10);
        Cat cat2 = new Cat(2);
        Cat cat3 = new Cat(300);
        System.out.println(cat1.compareTo(cat2));
        System.out.println(cat2.compareTo(cat3));
        System.out.println(cat3.compareTo(cat1));
        System.out.println(cat3.compareTo(cat3));
    }

    private static void collectionsSortsOnlyComparable() {
        Duck d1 = new Duck("D2uck");
        Duck d3 = new Duck("duck3");
        Duck d2 = new Duck("1duck");
        List<Duck> ducks = new ArrayList<>();
        ducks.add(d1);
        ducks.add(d2);
        ducks.add(d3);
        System.out.println(ducks);
        Collections.sort(ducks);
        System.out.println(ducks);
    }

    private static void collectionsDoesntSort() {
//        List<Dog> dogs = new ArrayList<>();
//        Collections.sort(dogs);
    }
}
