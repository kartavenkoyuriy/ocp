package chapter3.collections.map;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

class A{
    @Override
    public int hashCode() {
        return 1;
    }

//    @Override
//    public boolean equals(Object obj) {
//        return true;
//    }
}

public class MapExample {
    public static void main(String[] args) {

    }

    private static void nullKeysInMaps() {
        //        NPE
//        final Map<String, String> hashtable = new Hashtable<>();
//        hashtable.put(null, null);

        final Map<String, String> map = new HashMap<>();
        map.put(null, null);
    }

    private static void comparebleTreeSet() {
//        classCastException
//        Set<A> as = new TreeSet<>();
//        as.add(new A());

//        npe
//        Set<A> as1 = new TreeSet<>();
//        as1.add(null);
    }

    private static void sameHashKeyMap() {
        Map<A, String> map = new HashMap<>();


        map.put(new A(), "1");
        map.put(new A(), "2");


        System.out.println(map.size());
        System.out.println(map.containsKey(new A()));
        System.out.println(map.get(new A()));

        System.out.println("---");

        A a1 = new A();
        System.out.println(map.containsKey(a1));
        System.out.println(map.get(a1));


        System.out.println("clear");
        System.out.println("---");

        map.clear();
        A a = new A();
        map.put(a, "s1");
        map.put(a, "s2");
        System.out.println(map.containsKey(a));
        System.out.println(map.get(a));
        System.out.println(map.size());

//        for (Map.Entry<A, String> aStringEntry : map.entrySet()) {
//            System.out.println(aStringEntry.getKey() + "|" + aStringEntry.getValue());
//        }
    }
}
