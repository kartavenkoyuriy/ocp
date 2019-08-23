package chapter6.exceptions;

import java.math.BigInteger;

public class CustomExceptionCommon extends Exception {

    public static void main(String[] args){
        BigInteger bigInteger = new BigInteger("1234567123456712345671234567");
        System.out.println(bigInteger);
    }

    private static void customExceptionExample() {
        //        throw new CustomExceptionCommon();
//        throw new CustomExceptionCommon(new RuntimeException());

        try {
            throw new CustomExceptionCommon();
        } catch (CustomExceptionCommon customExceptionCommon) {
            customExceptionCommon.printStackTrace();
        }
    }

    public CustomExceptionCommon() {
    }

    public CustomExceptionCommon(String message) {
        super(message);
    }

    //actually Exception has a constructor with Throwable as a parameter, not an Exception
    public CustomExceptionCommon(Exception cause) {
        super(cause);
    }
}
