package chapter7._repeat.concurrentcollections;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SkipListCollections {

    public static void main(String[] args) {
        //ConcurrentModificationException
        //List<Integer> integerList = new ArrayList<>();

        //need to use SkipListCollections instead
        List<Integer> integerList = new CopyOnWriteArrayList<>();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        for (Integer integer : integerList) {
            integerList.add(integer + 10);
        }
    }

}
