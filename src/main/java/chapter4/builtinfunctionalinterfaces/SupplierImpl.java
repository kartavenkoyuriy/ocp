package chapter4.builtinfunctionalinterfaces;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

//Supplier interface doesn't take any parameters, it only return data. Could use as a caller for a factory etc.
public class SupplierImpl {

    public static void main(String[] args) {

    }

    private static void supplierTest() {
        Supplier<LocalDate> localDateSupplier = () -> LocalDate.now();
        Supplier<LocalDate> localDateSupplier1 = LocalDate::now;

        LocalDate localDate = localDateSupplier.get();
        LocalDate localDate1 = localDateSupplier1.get();

        System.out.println(localDate);
        System.out.println(localDate1);

        System.out.println("---");

//        this method reference looks to constructor StringBuilder(){} because of Supplier type of interface
        Supplier<StringBuilder> stringBuilderSupplier = StringBuilder::new;
//        this won't work, because Supplier doesn't provide any parameters(?)
//        Supplier<StringBuilder> stringBuilderSupplier01 = StringBuilder::new("asd");

//        this method reference looks to constructor StringBuilder(CharSequence seq){} because of Consumer type of interface
//        but why it consume new StringBuilder???
        Consumer<StringBuilder> stringBuilderConsumer = StringBuilder::new;

        Supplier<StringBuilder> stringBuilderSupplier1 = () -> new StringBuilder();
//        the next will work
        Supplier<StringBuilder> stringBuilderSupplier11 = () -> new StringBuilder("qwe");

        System.out.println(stringBuilderSupplier.get());
        System.out.println(stringBuilderSupplier1.get());

        System.out.println("---");

        Supplier<ArrayList<String>> arrayListSupplier = () -> new ArrayList<>();
        Supplier<ArrayList<String>> arrayListSupplier1 = ArrayList::new;

        System.out.println(arrayListSupplier.get());
        System.out.println(arrayListSupplier1.get());
    }

    private static void methodReferenceUnderstanding() {
        String s = "abc";
        Predicate<String> stringPredicate = s::startsWith;
        Predicate<String> stringPredicate1 = (input) -> s.startsWith(input);
        System.out.println(stringPredicate.test("a"));
        System.out.println(stringPredicate1.test("a"));

//        won't work because need a parameter???
//        Predicate<String> stringPredicate1 = String::startsWith;
//        work because doesn't need a parameter???
//        Predicate<String> stringPredicate2 = String::isEmpty;
    }
}
