package chapter3.generic;

import java.util.ArrayList;
import java.util.List;

public class Crate<T> {

    private T content;

//    wrong
//    private static T content;


    public static void main(String[] args) {

    }

    private static void staticSyntax() {
        returnContentStatic(new Zebra());
        Crate.returnContentStatic(new Zebra());
        Crate.<Zebra>returnContentStatic(new Zebra());
    }

    private static void crateListWithDifferentGenericTypes() {
        Crate<Elephant> elephantCrate = new Crate<>(new Elephant());
        Crate<Zebra> zebraCrate = new Crate<>(new Zebra());

        List<Crate> crates = new ArrayList<>();
        crates.add(elephantCrate);
        crates.add(zebraCrate);

        for (Crate crate : crates) {
//            crate.getContent() - returns object
            System.out.println(crate.getContent().getClass());
            System.out.println(crate.getContent().toString());
            System.out.println(crate.getContent() instanceof Zebra);
            /*
            printed:

            class edu.test.mockito.Elephant
            edu.test.mockito.Elephant@6d6f6e28
            false
            class edu.test.mockito.Zebra
            edu.test.mockito.Zebra@135fbaa4
            true
             */
        }
    }

    private static void exampleGetSetGenericContent() {
        Elephant startElephant = new Elephant();
        Crate<Elephant> crateForElephant = new Crate<>();
        crateForElephant.packContent(startElephant);
        Elephant endElephant = crateForElephant.getContent();
    }

    public Crate() {
    }

    public Crate(T content) {
        this.content = content;
    }

    public void packContent(T content){
        this.content = content;
    }

    public Crate<T> returnThisObjectWithContent(T t){
        this.packContent(t);
        return this;
    }

    public <M> M returnContent(M m){
        return m;
    }

    public static <M> M returnContentStatic(M m){
        return m;
    }

    public T getContent() {
        return content;
    }
}

class Elephant {
    private String name;
}

class Zebra {
    private String name;
}