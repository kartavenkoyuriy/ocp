package chapter2.functionalInterfaces.interfaceInheritance;

public interface CanWalk {
    default void walk() {
        System.out.println("Walking");
    }
}

interface CanRun {
    public default void walk() {
        System.out.println("Walking");
    }

    public abstract void run();
}

interface CanSprint extends CanWalk, CanRun {
    void sprint();

    //class AND interface should overridede default method
    @Override
    default void walk() {

    }
}