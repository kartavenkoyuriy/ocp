package main.java.chapter1.nestedClasses;

import java.io.Serializable;

/**
 * Created by Yuriy Kartavenko on 6/27/2017.
 */
public class MemberInnerClassExample {
    private String greetings = "secret greetings";

    void localVariable(){
        final String local = "1";
    }

    //+any access modifier
    //+can extend any class or interface
    //+can be abstract or final
    //+Can access members of the outer class including private members
    //+can NOT declare static field or methods
    protected final class Inner implements Serializable {
        //can NOT declare static field or methods
//        static String a;

        private int iterate = 3;

        public void go() {
            for (int i = 0; i < iterate; i++) {
                //+Can access members of the outer class including private members
                System.out.println(greetings);
            }
        }
    }

    public void goInner() {
        Inner inner = new Inner();
        inner.go();
    }

    public static void main(String[] args) {
//        new MemberInnerClassExample().goInner();
//
//        System.out.println("---");
//
//        Inner inner = new MemberInnerClassExample().new Inner();
//        inner.go();
//
//        System.out.println("---");
//
//        MemberInnerClassExample example = new MemberInnerClassExample();
//        Inner inner1 = example.new Inner();
//        inner1.go();

        System.out.println("---");

        A a = new A();
        A.B b = a.new B();
        A.B.C c = b.new C();
//        A.B.C c1 = new A().new B().new C();
        c.allTheX();
    }
}

class A {
    private int x = 10;

    class B {
        private int x = 20;

        class C {
            private int x = 30;

            public void allTheX() {
                System.out.println(x);
                System.out.println(this.x);
                //We still want an instance variable. But this time we want the one on the B class, which is the variable on line 4
                System.out.println(B.this.x);
                System.out.println(A.this.x);
            }
        }
    }
}

class CaseOfThePrivateInterface {
//interface can only be referred to within the current outer class
    private interface Secret {
        public void shh();
    }

    class DontTell implements Secret {
        public void shh() {
        }
    }
}