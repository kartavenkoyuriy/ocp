package main.java.chapter1;

/**
 * Created by Yuriy Kartavenko on 4/14/2017.
 */
public class InstanceofExamples {
    public static void main(String[] args) {
        HeavyAnimal hippo = new Hippo();
        //'Hippo' type of variable reference will cause compile error due to inconvertible types "Hippo"-"Elephant"
        //Hippo hippo = new Hippo();

        //The compilation check only applies when instanceof is called on a class.
        //When checking whether an object is an instanceof an interface, Java waits until runtime to do the check.
        //The compiler allows the statement because there could later be a class such as this:
        //class MotherHippo extends Hippo implements Mother { }
        boolean b1 = hippo instanceof Hippo; // true
        boolean b2 = hippo instanceof HeavyAnimal; // true
        boolean b3 = hippo instanceof Elephant; // false

        boolean b4 = hippo instanceof Object; // true
        Hippo nullHippo = null;
        boolean b5 = nullHippo instanceof Object; // false
    }
}

class HeavyAnimal {
}

class Hippo extends HeavyAnimal {
}

class Elephant extends HeavyAnimal {
}