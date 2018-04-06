package chapter3.generic.bounds;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BoundsCommon {
    public static void main(String[] args) {


        List<String> strings = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");

//        compile error, because String can be implicitly cast to Object
//        Object stringToObject = "1";
//        but List<String> can be cast(implicitly or explicitly) to List<Object> because of
//        List<Integer> numbers = new ArrayList<>();
//        numbers.add(42);
//        List<Object> objects = numbers;
//        objects.add("42");
//        System.out.println(numbers.get(1));
//        but with arrays
//        Integer[] numbers = new Integer[]{42};
//        Object[] objects = numbers;
//        objects[0] = "42";//ArrayStoreException
//
//        so will be compile error
//        printList(strings);
        printListWhatever(strings);
    }

    public static void printListObject(List<Object> objectsToPrint){
        for (Object o : objectsToPrint) {
            System.out.println(o);
        }
    }

    public static void printListWhatever(List<?> objectsToPrint){
        for (Object o : objectsToPrint) {
            System.out.println(o);
        }
    }

    private static void clarifyingBounds() {
        List<?> wildcardList1 = new ArrayList<String>();
        List<?> wildcardList2 = new ArrayList<Object>();

        System.out.println("---");

//        wrong because marked "list or its successors"
//        List<? extends List> boundExtend1 = new ArrayList<Object>();
//        List<? extends List> boundExtend2 = new ArrayList<Collection>();
        List<? extends List> boundExtend3 = new ArrayList<List>();
        List<? extends List> boundExtend4 = new ArrayList<ArrayList>();
        List<? extends List> boundExtend5 = new ArrayList<>();

        System.out.println("---");

//        wrong because marked "list or its ancestors"
//        List<? super List> boundSuper1 = new ArrayList<ArrayList>();
        List<? super List> boundSuper2 = new ArrayList<List>();
        List<? super List> boundSuper3 = new ArrayList<Collection>();
        List<? super List> boundSuper4 = new ArrayList<Object>();
    }
}
