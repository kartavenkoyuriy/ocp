package chapter1.nestedClasses;

/**
 * Created by Yuriy Kartavenko on 6/27/2017.
 */
public abstract class AnonymousInnerClassExample {
    public abstract void testMethod3();
    public static void main(String[] args) {
        System.out.println(new AnonInner().admission(20));
    }
}

class AnonInner{
    //same for interface, but with public method modifier
    abstract class SaleTodayOnly extends AnonymousInnerClassExample implements implementable1, implementable2 {
        abstract int dollarsOff();
    }
    public int admission(int basePrice){
        SaleTodayOnly saleTodayOnly = new SaleTodayOnly() {
            @Override
            int dollarsOff() {
                return 3;
            }

            @Override
            public void testMethod() {

            }

            @Override
            public void testMethod2() {

            }

            @Override
            public void testMethod3() {

            }
        };
        return basePrice - saleTodayOnly.dollarsOff();
    }
}

interface implementable1{
    void testMethod();
}

interface implementable2{
    void testMethod2();
}