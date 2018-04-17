package chapter3.collections;

import java.util.HashSet;
import java.util.Set;

public class CollectionsCommon {
    public static void main(String[] args) {
        Set<String> strings = new HashSet<>();
        System.out.println(strings.size());
        strings.add("1");
        System.out.println(strings.size());
        System.out.println(strings.contains(new Integer(1)));
        System.out.println(strings.contains(null));
        strings.add(null);
        System.out.println(strings.contains(null));

    }
}
