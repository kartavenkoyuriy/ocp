package chapter7.concurrentCollections;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

public class ConcurrentCollectionsCommon {

    Map<String, Object> map = new HashMap<>();
    Map<String, Object> concurrentMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws InterruptedException {


    }

    private static void concurrentModificationException() {
        ConcurrentCollectionsCommon c = new ConcurrentCollectionsCommon();

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> concurrentMap = new ConcurrentHashMap<>();

        map.put("1", 1);
//        map.put("2", 2);
        //for two key-value pairs it will be ConcurrentModificationException
        //because keySet iterator is not updated properly
        for (String s : map.keySet()) {
            map.remove(s);
        }

        concurrentMap.put("1", 1);
        concurrentMap.put("2", 2);
        //here everything is fine
        for (String s : concurrentMap.keySet()) {
            concurrentMap.remove(s);
        }
        System.out.println(concurrentMap.size());
    }

    private static void sameReferenceTypeForConcurrentCollection() {
        ConcurrentCollectionsCommon c = new ConcurrentCollectionsCommon();

        Map<String, Object> map = new HashMap<>();
        Map<String, Object> concurrentMap = new ConcurrentHashMap<>();

        //working with map interface
        System.out.println(c.map.put("1", "1"));
        System.out.println(c.map.get("1"));
        System.out.println(c.concurrentMap.put("1", "1"));
        System.out.println(c.concurrentMap.get("1"));
    }

    private void put(String key, String value) {
        map.put(key, value);
    }

    private Object get(String key) {
        return map.get(key);
    }


}
