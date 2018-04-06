package chapter3.generic.interfaceExample;

public class ShippableCrateElephant<T> implements Shippable<T> {

    @Override
    public void ship(T t) {
        System.out.println("elephant. generic implementing");
    }
}
