package chapter7.concurrentCollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteCollections {
    public static void main(String[] args) {

    }

    private static void exceptionWhileAttemptUseLikeCopyOnWrite() {
        List<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3));
        for (Integer integer : integers) {
            System.out.println(integer);
            integers.add(integer + 10);
        }
        System.out.println(integers);
    }

    private static void notProperUseCopyOnWriteCollections() {
        List<Integer> integers = new CopyOnWriteArrayList<>(Arrays.asList(1, 2, 3));
        integers.remove(0);
        System.out.println(integers);
    }

    //when removing/adding/changing reference(but not when changing a content of an object)
    //- this collection will copied new data
    //to a new underlying structure
    //useful in loops, because in regular ArrayList here would be ConcurrentModificationException
    //
    //commonly used in multi-threaded environment situations where reads are far more common than writes
    //consume a lot of memory
    private static void copyOnWriteCollectionExample() {
        List<Integer> integers = new CopyOnWriteArrayList<>(Arrays.asList(1, 2, 3));
        for (Integer integer : integers) {
            System.out.println(integer);
            integers.add(integer + 10);
        }
        System.out.println(integers);
    }
}
