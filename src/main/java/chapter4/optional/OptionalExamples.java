package chapter4.optional;

import java.util.Optional;

public class OptionalExamples {

    public static void main(String[] args) throws Exception {
        optionalInctanceMethods();
    }

    private static void optionalInctanceMethods() throws Exception {
        String value = "qwe";
        Optional<String> stringOptional1 = value != null ? Optional.of(value) : Optional.empty();
//        the same
        Optional<String> stringOptional2 = Optional.ofNullable(value);
        Optional<String> stringOptionalEmpty = Optional.empty();

        System.out.println(stringOptional1);
        System.out.println(stringOptional2);

        System.out.println("---");

        System.out.println(stringOptional2.orElse("other"));
        System.out.println(stringOptionalEmpty.orElse("other"));

        System.out.println("---");

        System.out.println(stringOptional2.orElseGet(String::new));
        System.out.println(stringOptionalEmpty.orElseGet(String::new));

        System.out.println("---");

        System.out.println(stringOptional2.orElseThrow(Exception::new));
        System.out.println(stringOptionalEmpty.orElseThrow(Exception::new));
    }

    private static void simpleOptionalExample() {
        Optional<Double> average = getAverage(null);
        Optional<Double> average1 = getAverage(1, 4);
        Optional<Double> average2 = getAverage();

        System.out.println(average.isPresent());
        System.out.println(average1.isPresent());
        System.out.println(average2.isPresent());

//        without isPresent it may cause NoSuchElementException
//        average.get();

        if (average1.isPresent()){
            System.out.println(average1.get());
        }

//        another way
        average.ifPresent(x -> System.out.println(x));
//        and another way
        average.ifPresent(System.out::println);

//        System.out.println(average.ifPresent());
//        System.out.println(average1.ifPresent());
//        System.out.println(average2.ifPresent());
    }

    public static Optional<Double> getAverage(int... grades) {
        if (grades == null || grades.length == 0) {
            return Optional.empty();
        }

        int sum = 0;
        for (int grade : grades) {
            sum += grade;
        }
        return Optional.of((double) sum / grades.length);
    }
}
