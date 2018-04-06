package chapter3.generic.bounds;

import java.util.ArrayList;
import java.util.List;

public class LowerBoundedWildcards {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("tweet");
        List<Object> objects = new ArrayList<>(strings);
        addSound(strings);
        addSound(objects);
    }

//    No (unbounded generics are immutable). Don't know what type of list
//    public static void addSound(List<?> list){
//        list.add("quack");
//    }

//    No (unbounded generics are immutable). Don't know what type of list
//    public static void addSound(List<? extends Object> list){
//        list.add("quack");
//    }

//    Must be exact match of list. List<String> won't pass
//    public static void addSound(List<Object> list){
//        list.add("quack");
//    }

    public static void addSound(List<? super String> list) {
        list.add("quack");
    }

}
