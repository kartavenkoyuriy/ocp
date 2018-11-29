package chapter5.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class ResourceBundleCommon {
    public static void main(String[] args) {

    }

    private static void onceChosenBundleRuleExample() {
        Locale.setDefault(new Locale("en", "US"));

        Locale fr = new Locale("fr");

        ResourceBundle bundle = ResourceBundle.getBundle("Zoo", fr);

        //After the bundle is chosen, only properties from hierarchy are available
        //here: go for 'age' to Zoo(even as Zoo_en is default)
        System.out.println(bundle.getString("name"));
        System.out.println(bundle.getString("age"));
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

    private static void bundleStreamExample() {
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

        ResourceBundle bundle = ResourceBundle.getBundle("Zoo", fr);
        System.out.println(bundle.getString("hello"));
        System.out.println(bundle.getString("open"));


//        printProperties(us);
//        System.out.println();
//        printProperties(fr);
    }

    private static void printProperties(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("Zoo", locale);
        System.out.println(bundle.getString("hello"));
        System.out.println(bundle.getString("open"));
    }
}
