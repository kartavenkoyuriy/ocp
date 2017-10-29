package chapter2.objectDesign;

//imagine that we are designing a new Animal class, and we have the following
//objectDesign requirements:
//■ Each animal has a non‐null, non‐empty species field
//■ Each animal has an age field that is greater than or equal to zero
public class Animal {
    private String species;
    private int age;

    public Animal(String species) {
        setSpecies(species);
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        if (species == null || species.isEmpty()){
            throw new IllegalArgumentException("Species must be non-null and non empty");
        }
        this.species = species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 0){
            throw new IllegalArgumentException("Age must be greater than or equal to zero");
        }
        this.age = age;
    }
}
