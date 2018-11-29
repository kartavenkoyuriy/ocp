package chapter6.exceptions.tryWithResources;

class Turkey {

}

class TurkeyCage implements AutoCloseable{

    @Override
    //parent method declaring Exception for a potentially throwing in implementations
    public void close() {
        System.out.println("close method");
    }
}

class TurkeyCageWithCloseException implements AutoCloseable{

    @Override
    //parent method declaring Exception for a potentially throwing in implementations
    public void close() throws Exception {
        throw new Exception("Cage door doesn't close");
    }
}

class TurkeyCageWithCloseSpecialException implements AutoCloseable{

    @Override
    //parent method declaring Exception for a potentially throwing in implementations
    public void close() throws IllegalStateException {
        throw new IllegalStateException("Cage door doesn't close");
    }
}

class TurkeyCageImponent implements AutoCloseable{

    static int count = 0;
    int number;

    public TurkeyCageImponent(int number) {
        this.number = number;
        ++count;
        System.out.println("created:" + "count-" + count + "|number" + number);
    }

    @Override
    public void close() {
        System.out.println("destroyed:" + "count-" + count + "|number" + number);
    }
}

public class AutoCloseableExample {

    public static void main(String[] args) {

    }

    private static void idemponentResourceClosedOrderExample() {
        try(TurkeyCageImponent imponent1 = new TurkeyCageImponent(1);
                TurkeyCageImponent imponent2 = new TurkeyCageImponent(2)){
            //1. close method should be idemponent - multiple calls have same result, without side-effects
            //2. resources autoclosed in reverse order
        }
    }

    private static void closeMethodSpecificExceptionExample() {
        try(TurkeyCageWithCloseSpecialException cage = new TurkeyCageWithCloseSpecialException()){

        } catch (IllegalStateException e){
            System.err.println(e.getMessage());
            //it is recommend to throw a specific exception instead of a common Exception
            System.err.println(e.getClass());
            System.out.println("inside an outer catch block");
        }
    }

    private static void closeMethodExceptionExample() {
        try(TurkeyCageWithCloseException turkeyCageWithCloseException = new TurkeyCageWithCloseException()){
            System.out.println("turkey with thrown exception in 'close' method");
        //need to be a catch block, because implementation of AutoCloseable interface declared/thrown an Exception
        } catch (Exception e){
            System.err.println(e.getMessage());
            System.out.println("inside an outer catch block");
        }
    }

    private static void autoCloseableExample() {
        try(TurkeyCage turkeyCage = new TurkeyCage()){
            System.out.println(turkeyCage);
        }
    }

    private static void autoCloseableRequiredExample() {
        //not implemented auto-closeable
        //        try (Turkey turkey = new Turkey()) {
            System.out.println();
//        }
    }
}
