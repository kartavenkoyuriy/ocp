package chapter3.collections;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImpl {
    public static void main(String[] args) {
        List<String> strings = new LinkedList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.remove(4);
        System.out.println(strings);
    }

    private static void listMethods() {
        List<String> strings = new ArrayList<>();
        strings.add("2");
        strings.add(0,"1");
        strings.get(0);
        strings.indexOf("1");
        strings.lastIndexOf("2");

//        in Collection
//        strings.remove("2");

        strings.remove(0);
        strings.set(0,"1");
    }
}
