package chapter3.generic;

public class OverloadGenericObject<T> {
    T t;

    public OverloadGenericObject(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return t.toString();
    }

    public static void main(String[] args) {
        System.out.println(new OverloadGenericObject<String>("hi"));
        System.out.println(new OverloadGenericObject("there"));
    }
}
