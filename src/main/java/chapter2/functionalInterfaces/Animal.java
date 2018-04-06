package chapter2.functionalInterfaces;

/**
 * Created by Yuriy Kartavenko on 7/18/2017.
 */

class Mammal {
    public void testMammal() {}
}

public class Animal extends Mammal {
    private String species;
    private boolean canHop;
    private boolean canSwim;

    public Animal(String species, boolean canHop, boolean canSwim) {
        this.species = species;
        this.canHop = canHop;
        this.canSwim = canSwim;
    }

    @Override
    public void testMammal() {
        super.testMammal();
    }

    public String getSpecies() {
        return species;
    }

    public boolean isCanHop() {
        return canHop;
    }

    public boolean isCanSwim() {
        return canSwim;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "species='" + species + '\'' +
                ", canHop=" + canHop +
                ", canSwim=" + canSwim +
                '}';
    }
}

class Dino extends Animal {
    public Dino(String species, boolean canHop, boolean canSwim) {
        super(species, canHop, canSwim);
    }
}