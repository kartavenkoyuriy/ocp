package chapter1.nestedClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Yuriy Kartavenko on 6/27/2017.
 */
public class StaticNestedClassExample {

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("string1");
        strings.add("string2");
        strings.add("string3");

        String base = "first is %1$s|second is %2$s|third is %3$s|and again %1$s";

//        System.out.println(String.format(base, strings.toArray()));
        System.out.println(strings);
    }

//    public static void main(String[] args) {
//
///*
// Input:
//        left = 1, right = 22
//        Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
//        */
//
//        List<Integer> result;
//        result = selfDividingNumbers(1, 22);
//        for (Integer integer : result) {
//            System.out.print(integer + ",");
//        }
//
////        System.out.println(isNumberSelfDividing(24));
////        System.out.println(isNumberSelfDividing(13));
////        System.out.println(isNumberSelfDividing(1234));
//
//    }

    public static List<Integer> selfDividingNumbers(int left, int right) {
        if (left < 1 || right < 1 || right < left || left > 10000 || right > 10000){
            throw new IllegalArgumentException("Input numbers are incorrect");
        }

        final ArrayList<Integer> range = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if(isNumberSelfDividing(i)){
                range.add(i);
            }
        }

        return range;
    }

    private static boolean isNumberSelfDividing(int i) {
        if(i <= 9){
            return true;
        }

        int digit = i;
        while(digit != 0){
            int least = digit % 10;
            if(least == 0 || i % least != 0){
                return false;
            }
            digit = digit / 10;
        }
        return true;
    }

}
