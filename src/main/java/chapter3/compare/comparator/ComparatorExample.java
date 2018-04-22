package chapter3.compare.comparator;

import java.util.Comparator;


public class ComparatorExample {
    Comparator<DuckExtended> byWeight = new Comparator<DuckExtended>() {
        @Override
        public int compare(DuckExtended o1, DuckExtended o2) {
            return o1.getWeight()-o2.getWeight();
        }
    };

    Comparator<DuckExtended> byWeightLambda = (d1, d2) -> d1.getWeight()-d2.getWeight();

    Comparator<DuckExtended> getByWeightLambdaByInt = Comparator.comparingInt(DuckExtended::getWeight);



}
