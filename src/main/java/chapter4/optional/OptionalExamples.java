package chapter4.optional;

import java.util.Optional;

public class OptionalExamples {

    public static void main(String[] args) {
        Optional<Double> average = getAverage(null);
        Optional<Double> average1 = getAverage(1, 3);
        Optional<Double> average2 = getAverage();

        System.out.println(average.isPresent());
        System.out.println(average1.isPresent());
        System.out.println(average2.isPresent());

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
