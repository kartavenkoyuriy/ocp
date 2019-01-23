package chapter8.workingWithStreams.serializing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializingObjectInputOutputCommon {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<SpecialAnimal> animals = new ArrayList<>();
        animals.add(new SpecialAnimal("tiger", 1, 'r'));
        animals.add(new SpecialAnimal("elephant", 2, 'A'));
        animals.add(null);

        System.out.println(animals);

        File destination = new File( "D:\\install\\special_animals.data");
        writeSpecialAnimalsToFile(animals, destination);
        System.out.println(getSpecialAnimals(destination));
    }

    private static void animalSerializationExample() throws IOException, ClassNotFoundException {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("tiger", 1, 'r'));
        animals.add(new Animal("elephant", 2, 'A'));
        animals.add(null);

        System.out.println(animals);

        File destination = new File( "D:\\install\\animals.data");
        writeAnimalsToFile(animals, destination);
        System.out.println(getAnimals(destination));
    }

    private static List<Animal> getAnimals(File source) throws IOException, ClassNotFoundException {
        List<Animal> animals = new ArrayList<>();
        try (ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(new FileInputStream(source)))) {
            while (true){
                Object object = is.readObject();
                //Serializing can work with null values
                //but it should be handled
                if(object instanceof Animal || object == null){
                    animals.add((Animal) object);
                }
            }
        } catch (EOFException e){
            //end of file reached
        }

        return animals;
    }

    private static void writeAnimalsToFile(List<Animal> animals, File destination) throws IOException {
        try(ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(destination)))){
            for (Animal animal : animals) {
                os.writeObject(animal);
            }
        }
    }

    private static List<SpecialAnimal> getSpecialAnimals(File source) throws IOException, ClassNotFoundException {
        List<SpecialAnimal> specialAnimals = new ArrayList<>();
        try (ObjectInputStream is = new ObjectInputStream(new BufferedInputStream(new FileInputStream(source)))) {
            while (true) {
                Object fetchedObject = is.readObject();
                if (fetchedObject instanceof SpecialAnimal) {
                    specialAnimals.add((SpecialAnimal) fetchedObject);
                }
            }
        } catch (EOFException e){
            //end of file reached
        }

        return specialAnimals;
    }

    private static void writeSpecialAnimalsToFile(List<SpecialAnimal> specialAnimals, File destination) throws IOException {
        try (ObjectOutputStream os = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(destination)))) {
            for (SpecialAnimal specialAnimal : specialAnimals) {
                os.writeObject(specialAnimal);
            }
        }
    }
}
