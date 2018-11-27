package chapter5.i18n.javaClassPropertiesBundle;

public class UsTaxCode {
    private String name;

    public UsTaxCode(String name) {
        this.name = name;
    }

    public UsTaxCode() {
    }

    @Override
    public String toString() {
        return "UsTaxCode{" +
                "name='" + name + '\'' +
                '}';
    }
}
