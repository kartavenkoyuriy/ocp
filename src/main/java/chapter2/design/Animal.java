package chapter2.design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//imagine that we are designing a new Animal class, and we have the following
//        design requirements:
//        ■ Each animal has a non‐null, non‐empty species field
//        ■ Each animal has an age field that is greater than or equal to zero
public class Animal {
    private String species;
    private int age;

    public Animal(String species, int age) {
        setAge(age);
        setSpecies(species);
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        if (species == null || species.isEmpty()) {
            throw new IllegalArgumentException("Species must be non-null and non empty");
        }
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age must be greater than or equal to zero");
        }
        this.age = age;
    }

    public static void main(String[] args) {
        final int[] ints;
        final int[] generateArray = generateArray();
        final long start = System.currentTimeMillis();
        ints = twoSum(generateArray, 1_999_997);
        System.out.println(System.currentTimeMillis() - start);
//        ints = twoSum(new int[]{11, 15, 2, 8, 7}, 9);
//        ints = twoSum(new int[]{3,2,4}, 6);
        System.out.println("result:");
        for (int anInt : ints) {
            System.out.print(anInt + "+");
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if(map.containsKey(complement) && map.get(complement) != i){
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");

//                int[] internalResult = new int[nums.length];
//        for (int i = 0; i < nums.length; i++) {
//            internalResult[i] = target - nums[i];
//            for (int j = 0; j < i; j++) {
//                System.out.println(nums[i] + "|" + internalResult[j]);
//                if (nums[i] == internalResult[j]) {
//                    return new int[]{j, i};
//                }
//            }
//        }
//        throw new IllegalArgumentException("No two sum solution");

//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                if (nums[i] + nums[j] == target) {
//                    return new int[]{i, j};
//                }
//            }
//        }
//        throw new IllegalArgumentException("No two sum solution");
    }

    static int[] generateArray() {
        int[] result = new int[1_000_000];
        for (int i = 0; i < result.length; i++) {
            result[i] = i;
        }
        return result;
    }
}
