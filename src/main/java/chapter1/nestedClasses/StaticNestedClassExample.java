package chapter1.nestedClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yuriy Kartavenko on 6/27/2017.
 */
public class StaticNestedClassExample {

    private String a;

    private static class Nested {
        private int price = 6;
    }

    public static void main(String[] args) {
        Nested nested = new Nested();
        System.out.println(nested.price);
        System.out.println(new StaticNestedClassExample().a);
    }

}
