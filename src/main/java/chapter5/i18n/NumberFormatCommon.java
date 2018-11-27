package chapter5.i18n;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class NumberFormatCommon {
    public static void main(String[] args) {
        
    }

    private static void parseCurrencyExample() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String amt = "$92,807.99";
        try {
            //casts to a Number as a parent of wrapper classes
            Number parse = numberFormat.parse(amt);
            System.out.println(parse);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void parseNumberEample() {
        String stringToNumber = "45.50";
        NumberFormat numberFormat = NumberFormat.getInstance(Locale.US);
        NumberFormat numberFormat1 = NumberFormat.getInstance(Locale.FRANCE);
        try {
            System.out.println(numberFormat.parse(stringToNumber));

            //wrong locale - wrong parsing result
            System.out.println(numberFormat1.parse(stringToNumber));


            //The parse method parses only the beginning of a string. After it reaches a character that
            //cannot be parsed, the parsing stops and the value is returned.

            //this will cause an exception because first char is not a digit
            System.out.println(numberFormat.parse("x86"));

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void currencyFormattingExample() {
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        System.out.println(currencyInstance.format(46));

        NumberFormat currencyInstance1s = NumberFormat.getCurrencyInstance(Locale.CHINA);
        System.out.println(currencyInstance1s.format(46));
    }

    private static void formattingNumbersEample() {
        int perYear = 3_200_000;
        int perMonth = perYear / 12;

        NumberFormat us = NumberFormat.getInstance(Locale.US);
        System.out.println(us.format(perMonth));
        NumberFormat de = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println(de.format(perMonth));
        NumberFormat ca = NumberFormat.getInstance(Locale.CANADA_FRENCH);
        System.out.println(ca.format(perMonth));
    }
}
