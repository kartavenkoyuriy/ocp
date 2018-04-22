package chapter3.compare;


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

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Duck duck) {
        return name.compareTo(duck.getName());
    }
}

public class ComparableExample {
    public static void main(String[] args) {
        List<Duck> ducks = new ArrayList<>();
        ducks.add(new Duck("bcd"));
        ducks.add(new Duck("cdf"));
        ducks.add(new Duck("abc"));
        System.out.println(ducks);
        Collections.sort(ducks);
        System.out.println(ducks);
    }
}
