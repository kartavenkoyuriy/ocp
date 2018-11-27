package chapter5.i18n.javaClassPropertiesBundle;

import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

public class Zoo_en extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"hello", "hello"},
                {"open", "open"},
                {"obj", new UsTaxCode("uno")}
        };
    }

    public static void main(String[] args) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("chapter5.i18n.javaClassPropertiesBundle.Zoo", Locale.US);
        resourceBundle.keySet().stream().map(x -> x + ":" + resourceBundle.getObject(x)).forEach(System.out::println);
    }
}
