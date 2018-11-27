package chapter5.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class ResourceBundleCommon {
    public static void main(String[] args) {
        bundlePlaceHolderExample();

    }

    private static void bundlePlaceHolderExample() {
        //look at a properties files
        Locale us = new Locale("en", "US");
        ResourceBundle zoo = ResourceBundle.getBundle("Zoo", us);
        String placeholder = zoo.getString("placeholder");
        String formatted = MessageFormat.format(placeholder, "Tommy");
        System.out.println(formatted);
    }

    private static void bundleToPropertiesExample() {
        Locale us = new Locale("en", "US");
        Locale fr = new Locale("fr", "FR");

        ResourceBundle zoo = ResourceBundle.getBundle("Zoo", us);
        Properties properties = new Properties();
        zoo.keySet().stream().forEach(x -> properties.put(x, zoo.getString(x)));
        System.out.println(properties.stringPropertyNames());

        //getProperty() uses get() but allows a default value
        System.out.println(properties.get("notAProperty"));
        System.out.println(properties.getProperty("notAProperty"));

        System.out.println(properties.getProperty("notAProperty", "123"));
    }

    private static void bundleStreamEample() {
        Locale us = new Locale("en", "US");
        Locale fr = new Locale("fr", "FR");

        ResourceBundle zoo = ResourceBundle.getBundle("Zoo", us);
        zoo
                .keySet()
                .stream()
                .forEach(System.out::println);
        zoo
                .keySet()
                .stream()
                .map(x -> x + ":" + zoo.getString(x))
                .forEach(System.out::println);
    }

    private static void basicBundleExample() {
        Locale us = new Locale("en", "US");
        Locale fr = new Locale("fr", "FR");

        printProperties(us);
        System.out.println();
        printProperties(fr);
    }

    private static void printProperties(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("Zoo", locale);
        System.out.println(bundle.getString("hello"));
        System.out.println(bundle.getString("open"));
    }
}
