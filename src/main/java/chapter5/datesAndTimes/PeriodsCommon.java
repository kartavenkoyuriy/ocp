package chapter5.datesAndTimes;

import java.time.*;

public class PeriodsCommon {

    //Period for year, month, week(to day), day

    public static void main(String[] args) {
        System.out.println(Period.of(1,2,3));

        Instant i = Instant.now();

        Period p1 = Period.ofDays(1);
        Duration d1 = Duration.ofDays(1);

        LocalDate localDate = LocalDate.of(2015, 1, 1);

        localDate.plus(p1);
        localDate.plus(d1);
    }

    private static void periodLimitationExample() {
        LocalDate localDate = LocalDate.of(2015, 1, 1);

        LocalTime localTime = LocalTime.of(14, 12);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        Period period = Period.ofMonths(1);
        Period p1 = Period.ofDays(1);
        Duration d1 = Duration.ofDays(1);

        localDateTime = localDateTime.plus(period);
        localDate = localDate.plus(period);
        localDate = localDate.plus(d1);
        //Period can't be added to a LocalTime
//        localTime = localTime.plus(period);
    }

    private static void periodImprovementsExample() {
        LocalDate startDate = LocalDate.of(2015, Month.JANUARY, 1);
        LocalDate endDate = LocalDate.of(2015, Month.MARCH, 30);
        toy(startDate, endDate);

        System.out.println("***");

        Period period = Period.ofMonths(1);
        //one year and seven days
        Period period1 = Period.of(1, 0, 7);
        toyPeriod(startDate, endDate, period);
    }

    private static void periodChainingExample() {
        //Period has static members, so it doesn't support chain, it will use only last method call(7 days)
        //it will actually do this
//        Period wrong = Period.ofYears(1);
//        wrong = Period.ofDays(7);
        Period period2 = Period.ofYears(1).ofDays(7);
    }

    private static void periodPrintingExample() {
        Period period3 = Period.ofMonths(3);
        //print like P#Y#M#D where # is a number. it cleans the empty values, here it will be "P3M"
        System.out.println(period3);

        Period period4 = Period.of(0, 23, 56);
        //Period omit check amount months in a year and days in a month
        System.out.println(period4);

        Period period5 = Period.ofWeeks(3);
        //Period uses only year/month/day, so here it will be "P21D"
        System.out.println(period5);

        Period period6 = Period.ofMonths(12);
        //it still prints 12 months instead of year
        System.out.println(period6);
    }

    private static void toy(LocalDate startDate, LocalDate endDate) {
        LocalDate upTo = startDate;
        while (upTo.isBefore(endDate)) {
            System.out.println("new toy");
            upTo = upTo.plusMonths(1);
        }
    }

    private static void toyPeriod(LocalDate startDate, LocalDate endDate, Period period) {
        LocalDate upTo = startDate;
        while (upTo.isBefore(endDate)) {
            System.out.println("new toy");
            upTo = upTo.plus(period);
        }
    }
}
