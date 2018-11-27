package chapter5.i18n.javaClassPropertiesBundle;

import java.util.ListResourceBundle;
import java.util.Locale;
import java.util.ResourceBundle;

public class Tax_en_US extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{{"tax", new UsTaxCode()}};
    }

    public static void main(String[] args) {
//        ResourceBundle bundle = ResourceBundle.getBundle("chapter5.i18n.javaClassPropertiesBundle.Tax", Locale.US);
        ResourceBundle bundle = ResourceBundle.getBundle("chapter5.i18n.javaClassPropertiesBundle.Tax", Locale.FRANCE);
        System.out.println(bundle.getObject("tax"));
    }
}
