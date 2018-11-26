package chapter5.datesAndTimes;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import static java.time.temporal.ChronoUnit.*;

public class InstantCommon {

    //an Instant is a point in time

    public static void main(String[] args) {

    }

    private static void instantLimitationExample() {
        Instant instant = Instant.now();
        instant = instant.plus(1, SECONDS);
        instant = instant.plus(1, HOURS);
        instant = instant.plus(1, DAYS);
        //only up to DAY
//        instant = instant.plus(1, WEEKS);
    }

    private static void convertingInstantEample() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        //LocalDateTime can't be converted to an Instant
        Instant instant = zonedDateTime.toInstant();
        System.out.println(zonedDateTime);
        System.out.println(instant);

        Instant instant1 = Instant.ofEpochSecond(LocalDateTime.MIN.getNano());
        System.out.println(instant1);
    }

    private static void instantExample() {
        Instant start = Instant.now();
        long sum = 0;
        for (int i = 0; i < 1_000_000; i++) {
            sum += i;
        }
        System.out.println(sum);
        Instant end = Instant.now();
        Duration between = Duration.between(start, end);
        System.out.println(between);
    }
}
