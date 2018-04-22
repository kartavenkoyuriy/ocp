package chapter3.compare.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class MultiFieldComparator implements Comparator<Squirrel> {

    @Override
    public int compare(Squirrel o1, Squirrel o2) {
        int result = o1.getSpecies().compareTo(o2.getSpecies());
        if (result != 0) {
            return result;
        }
        return o1.getWeight() - o2.getWeight();
    }
}

class ChainingComparatorExt implements Comparator<Squirrel> {

    @Override
    public int compare(Squirrel o1, Squirrel o2) {
        Comparator<Squirrel> comparator = Comparator
                .comparing(Squirrel::getSpecies)
                .thenComparingInt(Squirrel::getWeight);
        return comparator.compare(o1, o2);
    }
}

class ChainingComparator implements Comparator<Squirrel> {
    public int compare(Squirrel s1, Squirrel s2) {
        Comparator<Squirrel> c = Comparator.comparing(s -> s.getSpecies());
        c = c.thenComparingInt(s -> s.getWeight());
        return c.compare(s1, s2);
    }
}

public class Squirrel {
    private String species;
    private int weight;

    public Squirrel(String species) {
        if (species == null) {
            throw new IllegalArgumentException();
        }
        this.species = species;
    }

    public Squirrel(String species, int weight) {
        if (species == null) {
            throw new IllegalArgumentException();
        }
        this.weight = weight;
        this.species = species;
    }


    public static void main(String[] args) {
        Squirrel s01 = new Squirrel("a", 5);
        Squirrel s02 = new Squirrel("b", 5);
        Squirrel s03 = new Squirrel("c", 5);
        Squirrel s11 = new Squirrel("a", 3);
        Squirrel s21 = new Squirrel("a", 7);
//        Squirrel sNull = new Squirrel(null, 0);

        List<Squirrel> squirrels = new ArrayList<>();
        squirrels.add(s03);
        squirrels.add(s11);
//        squirrels.add(null);
        squirrels.add(s21);
        squirrels.add(s02);
        squirrels.add(s01);

        MultiFieldComparator multiFieldComparator = new MultiFieldComparator();
        ChainingComparator chainingComparator = new ChainingComparator();
        ChainingComparatorExt chainingComparatorExt = new ChainingComparatorExt();
        System.out.println(squirrels);
        Collections.sort(squirrels, multiFieldComparator);
//        Collections.sort(squirrels, chainingComparator);
//        Collections.sort(squirrels, chainingComparatorExt);
        System.out.println(squirrels);
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public String toString() {
        return "Squirrel{" +
                "species='" + species + '\'' +
                ", weight=" + weight +
                '}';
    }
}
