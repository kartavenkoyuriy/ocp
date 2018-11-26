package chapter5.datesAndTimes;

import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.time.temporal.ChronoUnit.HOURS;

public class SavingTimeCommon {
    public static void main(String[] args) {
        //saving time example
        //a moment in spring when clock 1 hour ahead at 2a.m., so "1 + 1 = 3a.m."
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2016, 3, 13, 1, 30, 0, 0, ZoneId.of("US/Eastern"));
        System.out.println(zonedDateTime);
        zonedDateTime = zonedDateTime.plus(1, HOURS);
        //UTC offset also changed
        System.out.println(zonedDateTime);

        System.out.println("***");

        //saving time example
        //a moment in fall when clock 1 hour back at 2a.m., so "1 + 1 = 1a.m."
        ZonedDateTime zonedDateTime1 = ZonedDateTime.of(2016, 11, 6, 1, 30, 0, 0, ZoneId.of("US/Eastern"));
        System.out.println(zonedDateTime1);
        zonedDateTime1 = zonedDateTime1.plus(1, HOURS);
        //UTC offset also changed
        System.out.println(zonedDateTime1);

        System.out.println("***");

        ZonedDateTime zonedDateTime2 = ZonedDateTime.of(2016, 3, 13, 2, 30, 0, 0, ZoneId.of("US/Eastern"));
        //JVM understand that this time doesn't exist and move it 1 hour ahead
        System.out.println(zonedDateTime2);

    }
}
