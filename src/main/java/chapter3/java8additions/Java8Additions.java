package chapter3.java8additions;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

class Duck {
    private String name;
    private int weight;

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Duck{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                '}';
    }

    public Duck(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public Duck(String name) {
        this.name = name;
    }

    public Duck(int weight) {
        this.weight = weight;
    }
}

class DuckHelper {
    static int compareByName(Duck d1, Duck d2) {
        return d1.getName().compareTo(d2.getName());
    }

    static int compareByWeight(Duck d1, Duck d2) {
        return d1.getWeight() - d2.getWeight();
    }
}

public class Java8Additions {
    public static void main(String[] args) {

    }

    private static void forEachExample() {
        List<String> strings = Arrays.asList("qwe", "asd", "zxc");
        strings.forEach(s -> System.out.println(s));
        strings.forEach(System.out::println);
    }

    private static void replaceAllExample() {
        //        it's fixed size, but it can be updated
        List<Integer> integers = Arrays.asList(1, 2, 3);
        System.out.println(integers);
        integers.replaceAll(integer -> integer * 2);
        System.out.println(integers);

        List<String> strings = Arrays.asList("asd", "qwe", "zxc");
        System.out.println(strings);
        strings.replaceAll(s -> s += "1");
        System.out.println(strings);
    }

    private static void removeIfExample() {
        List<String> strings = new ArrayList<>();
        strings.add("");
        strings.add("asd");
        strings.add("qwe");
        strings.add("");
        strings.add("zxc");
        strings.add("");
        strings.add("a");
        System.out.println(strings);
//        How would you replace line 8 with a method reference? Trick question—you can’t. Since
//        startsWith takes a parameter that isn’t s, it needs to be specifed “the long way".
        strings.removeIf(s -> s.startsWith("a"));
        System.out.println(strings);
        strings.removeIf(String::isEmpty);
        System.out.println(strings);
    }

    private static void supplierExample() {
        Supplier<ArrayList> listSupplier = ArrayList::new;
        ArrayList arrayList = listSupplier.get();

        Supplier<ArrayList> listSupplier2 = () -> new ArrayList();
    }

    private static void predicateExample() {
        String s1 = "abc";
        Predicate<String> stringPredicate1 = String::isEmpty;
//        Predicate<String> stringPredicate11 = s1::isEmpty;
        Predicate<String> stringPredicate12 = s -> s.isEmpty();
        System.out.println(stringPredicate1.test(s1));
        System.out.println(stringPredicate12.test(s1));

        Predicate<String> stringPredicate2 = s1::startsWith;
//        can not, because it take a parameter
//        Predicate<String> stringPredicate21 = String::startsWith;
        Predicate<String> stringPredicate22 = s -> s.startsWith("a");
        System.out.println(stringPredicate2.test(s1));
        System.out.println(stringPredicate22.test(s1));
    }

    private static void consumerExample() {
        Integer i1 = 12;
        Integer i2 = 2;
        Integer i3 = 20;
        Integer i4 = 0;

        List<Integer> integers = Arrays.asList(12, 2, 20, 0);
        System.out.println(integers);
        Consumer<List<Integer>> methodRef = Collections::sort;
        methodRef.accept(integers);
        System.out.println(integers);
        Consumer<List<Integer>> lambda = l -> Collections.sort(l, Comparator.reverseOrder());
        lambda.accept(integers);
        System.out.println(integers);
    }

    private static void comparators() {
        List<Duck> ducks = new ArrayList<>();
        ducks.add(new Duck(4));
        ducks.add(new Duck(2));
        ducks.add(new Duck(8));
//        Collections.sort(ducks, DuckHelper::compareByWeight);
        Collections.sort(ducks, Comparator.comparingInt(Duck::getWeight));
        System.out.println(ducks);
    }

    private static void comparatorsByHelperClass() {
        Comparator<Duck> duckComparator1 = new Comparator<Duck>() {
            @Override
            public int compare(Duck o1, Duck o2) {
                return DuckHelper.compareByName(o1, o2);
            }
        };

        Comparator<Duck> duckComparator2 = (o1, o2) -> DuckHelper.compareByName(o1, o2);

        Comparator<Duck> duckComparator3 = DuckHelper::compareByName;
    }
}
