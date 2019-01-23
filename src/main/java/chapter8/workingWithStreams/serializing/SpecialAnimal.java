package chapter8.workingWithStreams.serializing;

import java.io.Serializable;

public class SpecialAnimal implements Serializable {

    private static final long serialVersionUID = 2L;
    //'transient' fields are not lost during serialization
    //when deserializing - JVM set default values according to data type
    private transient String name;
    private transient int age = 10;
    //static doesn't write to disk
    //in this example it filled with the last assigned static value for all objects
    //
    //as the variable is shared by all instances of the class and is the last value in our sample program
    private static char type = 'c';

    //when deserializing - no initializators are invoked
    {
        this.age = 14;
    }

    //when deserializing - no default constructor invoked
    public SpecialAnimal() {
        this.name = "Unknown";
        this.age = 12;
        this.type = 'Q';
    }

    //when deserializing - no other constructors invoked
    public SpecialAnimal(String name, int age, char type) {
        this.name = name;
        this.age = age;
        this.type = type;
    }

    @Override
    public String toString() {
        return "SpecialAnimal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type=" + type +
                '}';
    }

}
