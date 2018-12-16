package chapter7.concurrentCollections;

import java.util.*;

public class SynchronizedCollections {
    public static void main(String[] args) {
        exceptionWithIteratorExample();
    }

    //Collections.synchronized... not synchronize the iterators that they produce
    private static void exceptionWithIteratorExample() {
        List<Integer> integersList = Collections.synchronizedList(new ArrayList<>(Arrays.asList(1, 2, 3)));
        for (Integer integer : integersList) {
            integersList.remove(integer);
        }
    }

    //use only when get a regular collection and need to use it concurrently
    //to create concurrent collection should use special collections from java.util.concurrent package
    private static void creationSynchronizedCollection() {
        List<Integer> integersList = Collections.synchronizedList(new ArrayList<>(Arrays.asList(1, 2, 3)));
        Set<Integer> integersSet = Collections.synchronizedSet(new HashSet<>(Arrays.asList(1, 2, 3)));
    }
}
