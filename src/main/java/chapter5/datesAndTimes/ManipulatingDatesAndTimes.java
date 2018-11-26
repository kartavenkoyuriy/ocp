package chapter5.datesAndTimes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class ManipulatingDatesAndTimes {

    //The date and time classes are immutable

    public static void main(String[] args) {

        //Leap years are years that are a multiple of 4 or 400 but not other multiples of 100. For example, 2000 and 2016
        //are leap years, but 2100 is not.

        LocalDate date = LocalDate.of(2018, Month.NOVEMBER, 26);
        System.out.println(date);
        date = date.plusDays(2);
        System.out.println(date);
        date = date.minusYears(3);
        System.out.println(date);
        //doesn't compile
//        date = date.plusMinutes(3);

        LocalDateTime localDateTime = LocalDateTime.of(date, LocalTime.now()).plusDays(1).minusYears(1);
        System.out.println(localDateTime);
    }
}
