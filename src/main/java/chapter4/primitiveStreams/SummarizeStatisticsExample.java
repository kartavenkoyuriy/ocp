package chapter4.primitiveStreams;

import java.util.IntSummaryStatistics;
import java.util.stream.IntStream;

public class SummarizeStatisticsExample {
    public static void main(String[] args) {
        IntStream intStream = IntStream.of(1, 2, 3);
        int i = intStream.max().orElseThrow(IllegalArgumentException::new);
        System.out.println(i);

        System.out.println("***");

        IntStream intStream1 = IntStream.of(1, 3, 5, 7);
        IntSummaryStatistics statistics = intStream1.summaryStatistics();
        System.out.println(statistics.getMax());
        System.out.println(statistics.getMin());
    }
}
