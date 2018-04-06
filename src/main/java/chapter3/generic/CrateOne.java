package chapter3.generic;

import chapter3.Animal;
import chapter3.Dog;

public class CrateOne<T> {

    private T unit;
    private T[] units;

    public CrateOne() {
//        can't be instantiated directly
//        unit = new T();

        //unit is null
        boolean isDog = unit instanceof Dog;
        System.out.println(isDog);
    }

    public CrateOne(T unit) {
        this.unit = unit;
    }

    public T unpackCrate(){
        return unit;
    }

    public void packCrate(T unit) {
        this.unit = unit;
    }

    public static <T> CrateOne<T> staticReturnCrate(T t){
        System.out.println("static generic method that returns wrapper with T" + t.toString());
        return new CrateOne<>(t);
    }

    public static <T> T staticReturnT(T t){
        System.out.println("static generic method that returns T" + t.toString());
        return t;
    }

//    omits the formal parameter type, and therefore it does not compile
//    public static T staticReturnTOne(T t){
//        return t;
//    }

    public static<T> T identity(T t){
        return t;
    }

    public static void main(String[] args) {
        new CrateOne<>();

    }

    private static void simpleExampleWithGenericTypes() {
        final Dog oldDogPlace = new Dog();
        CrateOne<Dog> crateWithDog = new CrateOne<>();
        crateWithDog.packCrate(oldDogPlace);
        final Dog newDogPlace = crateWithDog.unpackCrate();
    }

    private static void callStatic(Animal animal) {
//        <String>staticReturnCrate("qwe");
        staticReturnCrate("qwe");
        CrateOne.staticReturnCrate(animal);
        CrateOne.<String>staticReturnCrate("qwe");
    }

    @Override
    public String toString() {
        return "CrateOne{" +
                "unit=" + unit +
                '}';
    }
}
