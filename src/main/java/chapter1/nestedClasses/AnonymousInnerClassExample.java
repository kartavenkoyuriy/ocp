package chapter1.nestedClasses;

/**
 * Created by Yuriy Kartavenko on 6/27/2017.
 */
public class AnonymousInnerClassExample {
    public static void main(String[] args) {
        System.out.println(new AnonInner().admission(20));
    }
}

class AnonInner{
    //same for interface, but with public method modifier
    abstract class SaleTodayOnly {
        abstract int dollarsOff();
    }
    public int admission(int basePrice){
        SaleTodayOnly saleTodayOnly = new SaleTodayOnly() {
            @Override
            int dollarsOff() {
                return 3;
            }
        };
        return basePrice - saleTodayOnly.dollarsOff();
    }
}

interface implementable1{}

interface implementable2{}