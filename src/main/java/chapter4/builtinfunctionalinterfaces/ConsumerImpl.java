package chapter4.builtinfunctionalinterfaces;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ConsumerImpl {
    public static void main(String[] args) {
        consumerExample();
    }

    private static void biConsumerExample() {
        //        we can't process 'println' method because BiConsumer consumes 2 parameters, but println consumes only 1
//        BiConsumer<String, Integer> stringIntegerBiConsumer = System.out::println;
        BiConsumer<String, Integer> stringIntegerBiConsumer = (s, i) -> System.out.println(s + i);

        Map<String, Integer> stringIntegerMap = new HashMap<>();
//        compile error, because BiConsumer needs to know to which map we should pass the parameters
//        BiConsumer<String, Integer> stringIntegerBiConsumer2 = Map::put;

//        !!!This time we used an instance method reference since we want to call a method on the local variable map.
        BiConsumer<String, Integer> stringIntegerBiConsumer1 = stringIntegerMap::put;
        stringIntegerBiConsumer1.accept("qwe", 123);
        System.out.println(stringIntegerMap);
        BiConsumer<String, Integer> stringIntegerBiConsumer01 = (k, v) -> stringIntegerMap.put(k, v);
        stringIntegerBiConsumer01.accept("asd", 456);
        System.out.println(stringIntegerMap);

        System.out.println("---");
        System.out.println("same type example");

        Map<String, String> stringStringMap = new HashMap<>();
        BiConsumer<String, String> stringStringBiConsumer = stringStringMap::put;
        stringStringBiConsumer.accept("qwe", "rty");
        stringStringBiConsumer.accept("123", "456");
        System.out.println(stringStringMap);
    }


    private static void consumerExample() {
//        consumer waits for 1 parameter, method reference will auto pick it(below)
//        but it can't process it(like concatenate with something etc.)
        Consumer<String> stringConsumer = System.out::println;
        stringConsumer.accept("123");
        stringConsumer.accept("asd");
//        consumer waits for 1 parameter, so it should be passed to lambda(notice that we CAN update it to method reference)
//        Consumer<String> stringConsumer01 = () -> System.out.println();
        Consumer<String> stringConsumer01 = (s) -> System.out.println(s);
        stringConsumer01.accept("ololo");
//        and after adding it can be postprocessing(notice that we CAN'T update it to method reference)
        Consumer<String> stringConsumer02 = (s) -> System.out.println(s + "alalal");
        stringConsumer02.accept("ololo");


        Consumer<String> stringConsumer1 = System.out::println;
        stringConsumer1.accept("zxc");
        stringConsumer1.accept("cvb");

        List<String> list = Arrays.asList("qwe", "asd", "zxc");
        list.forEach(s -> s = s.concat("10"));//will not update because use reference copy
        System.out.println(list);
    }
}
