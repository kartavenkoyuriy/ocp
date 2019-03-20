package chapter2.functionalInterfaces;

import java.util.function.Predicate;

/**
 * Created by Yuriy Kartavenko on 7/18/2017.
 */
public class FindMatchingAnimals {
    private static void print(Animal animal, Predicate<Animal> checkTrait){
        if(checkTrait.test(animal)){
            System.out.println(animal);
        }
    }

    private static void testVoid(TestVoidInterface testVoidInterface){
        System.out.println("1");
    }

    public static void main(String[] args) {
//        print(new Animal("1", true, false), a -> a.isCanHop());
//        print(new Animal("2", false, true), a -> a.isCanSwim());
        print(new Animal("2", false, false), a -> a.isCanSwim());

        //how it looks - empty second part
//        testVoid(() -> {});

    }
}
