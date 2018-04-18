package chapter3.quiz;

import java.util.ArrayList;
import java.util.List;

class A{}
class B extends A{}
class C extends B{}

public class QuizTest {
    public static void main(String[] args) {

    }

    <T> T method1(List<T> list){
        return list.get(0);
    }

//    return type should nit be a wildcard
//    <T> <? extends T> method2(List<? extends T> list){
//        return list.get(0);
//    }

//    JVM thinks B is a generic letter instead of class B. "B extends A" should be replaced to "T extends A"
//    Here <B extends A> says that you want to use B as a type parameter just for this method and that it needs to extend the A class
//    <B extends A> B method3 (List<B> list){
//        return new B();
//    }

    void method4(List<? super B> list) {
    }

//    it tries to mix a method-specifc type parameter with a wildcard
//    <X> void method5(List<X super B> list){
//
//    }

    private static void instantiatingGenerics() {
        List<?> list1 = new ArrayList<A>();
        List<? extends A> list2 = new ArrayList<A>();
        List<? extends A> list21 = new ArrayList<B>();
        List<? extends A> list22 = new ArrayList<C>();
        List<? super A> list3 = new ArrayList<A>();
//        no
//        List<? super A> list31 = new ArrayList<B>();
//        but
//        list3.add(new B());
//        list3.add(new C());
        List<? super A> list31 = new ArrayList<Object>();
        List<? extends B> list4 = new ArrayList<B>();
        List<? extends B> list41 = new ArrayList<C>();
//        List<? extends B> list42 = new ArrayList<A>();
        List<? super B> list5 = new ArrayList<B>();
        List<? super B> list51 = new ArrayList<A>();
//        List<? super B> list52 = new ArrayList<C>();
//        wildcard cannot be instantiated, they may be the type
//        List<?> list6 = new ArrayList<? extends A>();
    }
}
