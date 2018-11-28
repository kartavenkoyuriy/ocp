package chapter6.exceptions;


import java.io.IOException;
import java.sql.SQLException;

public class ExceptionsCommons {

    public static void main(String[] args) {

    }

    //RuntimeException might be declared or not in "throws" section no matter it is in method body
    //checked exception must be declared in "throws" section if is not caught in method body
    //checked exception can be declared in "throws" section if is not potentially throws in method body(for the future implementations), example below
    private static void throwThrowsExample() throws SQLException, RuntimeException {
        //throwing RuntimeException don't need to be caught or declared in "throws" section
        throw new UnsupportedOperationException();
    }

    private static void limitationCheckedExceptionExample() {
        //        try {
        System.out.println();

//        Java will not allow you to declare a catch block for a checked exception type
//        that cannot potentially be thrown by the try clause body. This is again to avoid
//        unreachable code

        //but it is legal to use
        //} catch (Exception e) {

//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    private static void catchClauseOrderExample() {
        try {
            throw new IOException();
//            throw new Exception();

            //It is illegal to declare
            //a subclass exception in a catch block that is lower down in the list than a superclass
            //exception because it will be unreachable code.

            //IOException then Exception. NOT vice versa
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e){
            //RuntimeException should be caught before Exception
            //may be before or after the other Exception successors
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            System.out.println();
            //c
        }
    }

    private static void classCastExceptionExample() {
        A a = new A();
        //ClassCastException
//        B b = (B)a;
    }
}

class A {

}

class B extends A {

}

class C extends B {

}