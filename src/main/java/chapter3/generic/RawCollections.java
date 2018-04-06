package chapter3.generic;

import java.util.ArrayList;
import java.util.List;

class Dragon{}

class Unicorn{}

public class RawCollections {

    public static void main(String[] args) {

    }

    private static void testGettingFromRawType() {
        List numbers = new ArrayList();
        numbers.add(1);

//        object cannot be casted to int
//        int one = numbers.get(0);
    }

    private static void callAddDragon() {
        List unicorns = new ArrayList();
        unicorns.add(new Unicorn());
        unicorns.add(new Unicorn());

//        ClassCastException
//        printDragons(unicorns);
    }

    private static void printDragons(List<Dragon> dragons){
        for (Dragon dragon : dragons) {
            System.out.println(dragon);
        }
    }
}
