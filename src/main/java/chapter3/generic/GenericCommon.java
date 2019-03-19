package chapter3.generic;

class A{}
class B extends A{}
class C extends B{}

class D<C>{
    //wrong
//    A a = new C();
    //wrong
//    C c1 = new C();


    C c = (C)new Object();
}

public class GenericCommon {

}
