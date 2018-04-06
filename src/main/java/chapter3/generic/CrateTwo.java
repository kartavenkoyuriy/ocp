package chapter3.generic;

import chapter3.Animal;
import chapter3.Dog;

public class CrateTwo<T, U> {
    private T contents;
    private U sizeLimit;

    public CrateTwo() {
    }

    public CrateTwo(T contents, U sizeLimit) {
        this.contents = contents;
        this.sizeLimit = sizeLimit;
    }

    public T emptyCrate() {
        return contents;
    }

    public void packCrate(T contents, U sizeLimit){
        this.contents = contents;
        this.sizeLimit = sizeLimit;
    }

    public static <T, U> CrateTwo<T, U> ship(T t){
        System.out.println("Preparing " + t);
        return new CrateTwo<>();
    }

    public static void main(String[] args) {
        new CrateTwo<>(new Dog(), 15_000);
        final CrateTwo<Dog, Integer> dogIntegerCrateTwo = new CrateTwo<>(new Dog(), 15_000);
    }

    private static void noInstantiateNPE() {
        final CrateTwo<Object, Object> emptyCrateTwo = new CrateTwo<>();
        System.out.println(emptyCrateTwo);
        System.out.println(emptyCrateTwo.contents.getClass());//NPE
        System.out.println(emptyCrateTwo.sizeLimit.getClass());//NPE
    }

    private void instantiate() {
        Animal animal = new Animal();
        Integer size = 15_000;
        final CrateTwo<Animal, Integer> objectObjectCrateTwo = new CrateTwo<>();
        objectObjectCrateTwo.packCrate(animal, size);
    }

    @Override
    public String toString() {
        return "Crate{" +
                "contents=" + contents +
                ", sizeLimit=" + sizeLimit +
                '}';
    }
}
