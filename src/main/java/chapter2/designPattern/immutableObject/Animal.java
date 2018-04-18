package chapter2.designPattern.immutableObject;

import java.util.ArrayList;
import java.util.List;

public final class Animal {
    private final String species;
    private final int age;
    private final List<String> favoriteFoods;

    public Animal(String species, int age, ArrayList<String> favoriteFoods) {
        this.species = species;
        this.age = age;
        if (favoriteFoods == null){
            throw new RuntimeException("Foods is required");
        }
        this.favoriteFoods = new ArrayList<>(favoriteFoods);
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public int getFavoriteFoodsCount(){
        return favoriteFoods.size();
    }

    public String getFavoriteFood(int index){
        return favoriteFoods.get(index);
    }

    //what's wrong?
    public ArrayList<String> getFavoriteFoods() {
        return new ArrayList<>(favoriteFoods);
    }

    public static void main(String[] args) {
//        Object v = null;
//        for (Object o : (List) v) {
//            System.out.println(o);
//        }
        if (null instanceof List){
            System.out.println(1);
        }
    }

}
