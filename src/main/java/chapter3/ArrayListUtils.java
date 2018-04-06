package chapter3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayListUtils {
    public static void main(String[] args) {

        //why?
        //ArrayList<Animal> dogs = new ArrayList<Dog>();

    }

    private static void addAndRemove() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(new Integer(3));
        numbers.add(new Integer(5));
        numbers.remove(1);

        //Integer equals compare intValue
        numbers.remove(new Integer(5));
        System.out.println(numbers);
    }

    private static void binarySearch() {
        int[] numbers = {6, 9, 1, 8};
        Arrays.sort(numbers);
        System.out.println(Arrays.binarySearch(numbers, 6)); // 1

        // Negating that gives us -1 and subtracting 1 gives us -2.
        System.out.println(Arrays.binarySearch(numbers, 3)); // -2
    }

    private static void notResizableArray() {
        final String[] array = {"gerbil", "mouse"};
        final List<String> list = Arrays.asList(array);
        System.out.println(list);
        array[0] = "new";
        System.out.println(list);

        //because it is backed by the underlying array
        list.remove(1);//UnsupportedOperationException
    }



}
