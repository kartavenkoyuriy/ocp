package chapter5.i18n;

import java.util.Locale;

public class LocaleCommon {
    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        //it may be lowercase language only(fr) OR lowercase language + underscore + uppercase country(en_US)
        System.out.println(locale);

        //Germany - country
        System.out.println(Locale.GERMANY);
        //German - language
        System.out.println(Locale.GERMAN);

        //creating Locale for Hindi in India
        Locale locale1 = new Locale("hi", "IN");
        System.out.println(locale1);

        //creating our own Locale
        Locale locale2 = new Locale("aa", "AA");
        System.out.println(locale2);

    }
}
