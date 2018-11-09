package chapter3.java8additions_redo;

import chapter2.functionalInterfaces.TestVoidInterface;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.function.Supplier;

public class DuckHelper {
    public static int compareByName(Duck d1, Duck d2){
        return d1.getName().compareTo(d2.getName());
    }

    public static int compareByWeight(Duck d1, Duck d2){
        return d1.getWeight() - d2.getWeight();
    }

    public static void main(String[] args) {
        Comparator<Duck> byWeightLambda = (d1, d2) -> DuckHelper.compareByWeight(d1, d2);
        Comparator<Duck> byWeightMethodRef = DuckHelper::compareByWeight;

        Supplier<ArrayList> newArrayList = ArrayList::new;
        newArrayList.get().removeIf(s -> s.equals(null));
        newArrayList.get().forEach(System.out::println);

    }
}
