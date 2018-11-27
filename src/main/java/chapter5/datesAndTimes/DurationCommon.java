package chapter5.datesAndTimes;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static java.time.temporal.ChronoUnit.*;

public class DurationCommon {

    //Duration for day, hour, minute, second, nanosecond

    public static void main(String[] args) {

    }

    private static void unsupportedMixesExample() {
        LocalDate localDate = LocalDate.of(2020, 1, 1);
        Duration duration = Duration.of(1, DAYS);
        localDate = localDate.plus(duration);
        System.out.println(localDate);
    }

    private static void durationLimitationsEample() {
        LocalTime time = LocalTime.of(6, 15);
        LocalDate date = LocalDate.of(2015, 1, 10);
        LocalDateTime dateTime = LocalDateTime.of(date, time);

        Duration duration = Duration.of(23, HOURS);

        LocalTime plusTime = time.plus(duration);
        LocalDateTime plusDateTime = dateTime.plus(duration);

        //UnsupportedTemporalException, duration for LOCALTIME
//        LocalDate plusDate = date.plus(duration);
    }

    private static void durationPrintExample() {
        //Duration print scheme is next: PT#H#M#S

        //it round to hours
        //round DOWN to hours
        Duration days = Duration.ofDays(2);
        Duration hours = Duration.ofHours(28);
        Duration minutes = Duration.ofMinutes(128);

        //round UP to hours
        Duration seconds = Duration.ofSeconds(3600);
        Duration seconds1 = Duration.ofSeconds(3600, 200);
        Duration millis = Duration.ofMillis(1000000001);
        Duration nanos = Duration.ofNanos(1999999999);
        Duration nanos1 = Duration.ofNanos(9);

        System.out.println(days);
        System.out.println(hours);
        System.out.println(minutes);
        System.out.println(seconds);
        System.out.println(seconds1);
        System.out.println(millis);
        System.out.println(nanos);
        System.out.println(nanos1);
    }

    private static void durationCreationExample() {
        //no constructor with multiple data
        Duration duration = Duration.of(64, ChronoUnit.HOURS);
        //64*12(half of a day)
        Duration duration05 = Duration.of(64, ChronoUnit.HALF_DAYS);
        System.out.println(duration);
        System.out.println(duration05);


        //if you want 1.5 hour - create 90 mins
        Duration duration1 = Duration.of(90, MINUTES);
        System.out.println(duration1);
    }

}
