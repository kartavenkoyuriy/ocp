package chapter5.datesAndTimes;

import java.time.*;
import java.util.Set;

public class DateTimeCommons {
    public static void main(String[] args) {
        //constructors are private
//        LocalTime localTime = new LocalTime();

        //DateTimeException is thrown
//        LocalDate localDate = LocalDate.of(2015, Month.MAY, 35);
//        System.out.println(localDate);



    }

    private static void zonedDateTimeExample() {
        //constructors are private
//        LocalTime localTime = new LocalTime();

        Set<String> availableZoneIds = ZoneId.getAvailableZoneIds();
        System.out.println(availableZoneIds);

        System.out.println("***");

        ZoneId systemDefault = ZoneId.systemDefault();
        System.out.println(systemDefault);

        System.out.println("***");

        ZoneId.getAvailableZoneIds()
                .stream()
                .filter(s -> s.contains("Kiev"))
                .forEach(System.out::println);

        System.out.println("***");

        ZoneId zoneId = ZoneId.of("US/Eastern");
        //no overload method for Month.JUNE
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2015, 6, 18, 20, 30, 40, 5000000, zoneId);
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(LocalDate.now(), LocalTime.now(), zoneId);
        ZonedDateTime zonedDateTime2 = ZonedDateTime.of(LocalDateTime.now(), zoneId);

        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime1);
        System.out.println(zonedDateTime2);
    }

    private static void localDateTimeExamples() {
        //constructors are private
//        LocalTime localTime = new LocalTime();

        LocalDateTime localDateTime = LocalDateTime.of(2015, Month.DECEMBER, 25, 20, 11, 38, 200000);
        System.out.println(localDateTime);

        LocalDate now = LocalDate.now();
        LocalTime now1 = LocalTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.of(now, now1);
        System.out.println(localDateTime1);
    }

    private static void localDateExample() {
        //constructors are private
//        LocalTime localTime = new LocalTime();


        LocalTime time = LocalTime.of(10, 20);
        LocalTime time1 = LocalTime.of(10, 20, 56);
        LocalTime time2 = LocalTime.of(10, 20, 56, 200000);
        LocalTime time3 = LocalTime.of(10, 20, 56, 200);

        System.out.println(time);
        System.out.println(time1);
        System.out.println(time2);
        System.out.println(time3);
    }

    private static void localDateEamole() {
        //constructors are private
//        LocalTime localTime = new LocalTime();


        //different methods
        LocalDate date = LocalDate.of(2015, Month.JANUARY, 20);
        LocalDate date1 = LocalDate.of(2015, 1, 20);
        System.out.println(date);
        System.out.println(date1);

        System.out.println("***");

        Month dec = Month.DECEMBER;
        boolean b = dec == Month.APRIL;
        System.out.println(b);
    }

    private static void commonCreateEample() {
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println(ZonedDateTime.now());
    }
}
