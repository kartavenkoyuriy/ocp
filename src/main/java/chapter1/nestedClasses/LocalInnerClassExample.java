package chapter1.nestedClasses;

/**
 * Created by Yuriy Kartavenko on 6/27/2017.
 */
//+They do not have an access specifier.
//+They cannot be declared staticBlock and cannot declare staticBlock fields or methods.
//+They have access to all fields and methods of the enclosing class.
//+They do not have access to local variables of a method unless those variables are final or effectively final.
public class LocalInnerClassExample {

}

class Outer{
    //inner interface can be static
    static interface Asd{}


    private int length = 5;
    public void calculate(){

        final int width = 20;
        //They do not have an access specifier

        String a = "";

        //effectively final. uncomment for use inside inner class (no matter before or after)
//        a = "f";

        //They cannot be declared staticBlock and cannot declare staticBlock fields or methods.
        class Inner{
            public void multiply(){
                //They do not have access to local variables of a method unless those variables are final or effectively final.
                //java 1.7 and earlier - need to explicit mention "final" modifier. from 1.8 - no(effectively final)

                //They have access to all fields and methods of the enclosing class.
                System.out.println(a + length * width);

            }
        }
        //effectively final. uncomment for use inside inner class (no matter before or after)
//        a = "f";

        Inner inner = new Inner();
        inner.multiply();

//        return new multip;
    }

    public void newInner() {
        //can't use before initializing
        //        new Inner()
        String a = "";
        //class with same name in another method
        class Inner {
            public int a = 10;

        }
        new Inner();
//        a= "d";
    }

    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.calculate();
    }
}
