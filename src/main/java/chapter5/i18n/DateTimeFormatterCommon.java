package chapter5.i18n;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class DateTimeFormatterCommon {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2020, Month.MAY, 5);
        LocalTime localTime = LocalTime.of(16, 37);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

    }

    private static void parseDateEample() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy MM dd");
        LocalDate parse = LocalDate.parse("2000 10 03", dateTimeFormatter);
        LocalTime parse1 = LocalTime.parse("11:24");
        System.out.println(parse);
        System.out.println(parse1);
    }

    private static void patternExceptionEample(LocalDate localDate, LocalTime localTime, LocalDateTime localDateTime) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm");
        System.out.println(dateTimeFormatter.format(localDateTime));
        System.out.println(dateTimeFormatter.format(localTime));
        //here will be an exception because formatter has info only about time
        System.out.println(dateTimeFormatter.format(localDate));
    }

    private static void formatterFromPatternExample() {
        LocalDate localDate = LocalDate.of(2020, Month.MAY, 5);
        LocalTime localTime = LocalTime.of(16, 37);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy MM dd, hh:mm");
        System.out.println(dateTimeFormatter.format(localDateTime));
    }

    private static void shortMediumFormatExample() {
        LocalDate localDate = LocalDate.of(2020, Month.MAY, 5);
        LocalTime localTime = LocalTime.of(16, 37);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        DateTimeFormatter fMedium = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
        DateTimeFormatter fShort = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

        System.out.println(fMedium.format(localDateTime));
        System.out.println(fShort.format(localDateTime));
    }

    private static void formatterSimpleExample() {
        LocalDate localDate = LocalDate.of(2020, Month.MAY, 5);
        LocalTime localTime = LocalTime.of(16, 37);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        System.out.println(localDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        //correct formatter should be chosen
        System.out.println(localTime.format(DateTimeFormatter.ISO_LOCAL_TIME));
        //correct formatter should be chosen
        System.out.println(localDateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }

    private static void fomatterAndDateInteractionEample() {
        LocalDate localDate = LocalDate.of(2020, Month.MAY, 5);
        LocalTime localTime = LocalTime.of(16, 37);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);

        System.out.println(dateTimeFormatter.format(localDate));
        //and vice versa
        System.out.println("and vice versa:" + localDate.format(dateTimeFormatter));

        System.out.println(dateTimeFormatter.format(localDateTime));
        //wrong formatter
//        System.out.println(dateTimeFormatter.format(localTime));
        System.out.println(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(localTime));
    }

    private static void ofLocalizedDateTimeFormatterRestrictionsEample() {
        LocalDate localDate = LocalDate.of(2020, Month.MAY, 5);
        LocalTime localTime = LocalTime.of(16, 37);
        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);

        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        System.out.println(dateTimeFormatter1.format(localDateTime));
        //ofLocalizedDateTime works only with LocalDateTime, throw exception here
//        System.out.println(dateTimeFormatter1.format(localDate));
        //ofLocalizedDateTime works only with LocalDateTime, throw exception here
//        System.out.println(dateTimeFormatter1.format(localTime));
    }

    private static void getDataFromDateExample() {
        LocalDate localDate = LocalDate.of(2013, Month.JANUARY, 4);
        System.out.println(localDate.getYear());
        System.out.println(localDate.getMonth());
        System.out.println(localDate.getDayOfYear());
        System.out.println(localDate.getMonthValue());
        System.out.println(localDate.getDayOfWeek());
    }
}
