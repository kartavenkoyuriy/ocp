package chapter3.generic.bounds;

import java.util.ArrayList;
import java.util.List;

interface Flyer{}
class HangGlider implements Flyer{}
class Goose implements Flyer{}

class Bird {}
class Sparrow extends Bird {}

public class UpperBoundedWildcards {
    public static void main(String[] args) {

    }

    private static void immutableLists() {
        //because the real list might be an successor of Sparrow
        //wildcard and upper-bounded lists are immutable(no ADD but technically can be removed)
        List<? extends Bird> birds = new ArrayList<>();
//        birds.add(new Sparrow());
//        birds.add(new Bird());
//        birds.add(new Object());
        birds.remove(1);
    }

    private static void passListToFlyerInterface() {
        List<Goose> gooses = new ArrayList<>();
//            can pass only to generic upperbounded method
//            anyFlyer(gooses);
        groupsOfFlyers(gooses);
    }

    private static void anyFlyer(List<Flyer> flyiers){
        System.out.println(flyiers);
    }

    private static void groupsOfFlyers(List<? extends Flyer> fliers){
        System.out.println(fliers);
    }

    private static void addToImmutableList() {
        List<? extends Number> numbers = new ArrayList<>();

//        list is immutable
//        it doesn't know what type. it can't add 1 to List<Double> and can't add 2.3 to List<Integer>
//        numbers.add(1);
//        numbers.add(2.3);
//        numbers.add(new Integer(3));
    }

    private static long longTotalGeneric(List<? extends Number> numbers){
        long count = 0;
        for (Number number : numbers) {
            count += number.longValue();
        }
        return count;
    }

    private static long longTotalRaw(List<Object> numbers){
        long count = 0;
        for (Object number : numbers) {
            count += ((Number)number).longValue();
        }
        return count;
    }
}
