package chapter3.generic.interfaceExample;

public class ShippableCrateDog implements Shippable {
    @Override
    public void ship(Object o) {
        System.out.println("dog. raw implementing");
    }
}
