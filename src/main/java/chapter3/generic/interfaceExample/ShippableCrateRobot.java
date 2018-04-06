package chapter3.generic.interfaceExample;

import chapter3.Robot;

public class ShippableCrateRobot implements Shippable<Robot>{
    @Override
    public void ship(Robot robot) {
        System.out.println("robot. explicit implementing");
    }
}
