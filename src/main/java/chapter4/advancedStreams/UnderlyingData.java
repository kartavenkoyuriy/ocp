package chapter4.advancedStreams;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class UnderlyingData {
    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("cat");
        stringList.add("dog");
        Stream<String> stream = stringList.stream();
        stringList.add("elephant");
        long count = stream.count();
        //prints "3" because streams are lazily evaluated
        System.out.println(count);
    }
}
