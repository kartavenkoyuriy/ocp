package chapter7.concurrentParallelStreams;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelCollectExample {

    public static void main(String[] args) {

    }

    private static void groupingByConcurrentParallelExample() {
        //The key to applying parallel reductions is to encourage the JVM to take advantage of
        //the parallel structures, such as using a groupingByConcurrent() collector on a parallel
        //stream rather than a groupingBy() collector. By encouraging the JVM to take advantage
        //of the parallel processing, we get the best possible performance at runtime.
        ConcurrentMap<Integer, List<String>> collect = Stream.of("lions", "tigers", "bears", "tigers1")
                .parallel()
                .collect(Collectors.groupingByConcurrent(String::length));
        System.out.println(collect);
    }

    private static void parallelCollectToConcurrentMapExample() {
        Map<Integer, String> map = Stream.of("lions", "tigers", "bears", "tigers1")
                .parallel()
                .collect(Collectors.toConcurrentMap(String::length, v -> v, (s1, s2) -> s1 + "|" + s2));
        System.out.println(map);
    }

    //Requirements for Parallel Reduction with collect()
    //■ The stream is parallel.
    //■ The parameter of the collect operation has the Collector.Characteristics.CONCURRENT characteristic.
    //■ Either the stream is unordered, or the collector has the characteristic Collector.Characteristics.UNORDERED.
    private static void oneArgumentCollectExample() {
        Set<String> collect = Stream.of("w", "o", "l", "f").parallel().collect(Collectors.toSet());
        System.out.println(collect);
    }


    private static void collectExtendedParallelExample() {
        //ConcurrentSkipListSet means 'sorted'
        //should use a concurrent collection to make sure no ConcurrentModificationExample will be thrown
        SortedSet<String> collect = Stream.of("w", "o", "l", "f").parallel()
                .collect(ConcurrentSkipListSet::new, ConcurrentSkipListSet::add, ConcurrentSkipListSet::addAll);
        System.out.println(collect);
    }
}
