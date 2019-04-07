package chapter6.exceptions.tryWithResources;

import jdk.nashorn.internal.runtime.ECMAException;

class JammedTurkeyCageException implements AutoCloseable {

    @Override
    public void close() throws IllegalStateException {
        throw new IllegalStateException("Cage door doesn't close");
    }


//    @Override
//    public void close() throws Exception {
//        throw new Exception();
//    }
}

public class SuppressedExceptionsCommon {

    public static void main(String[] args) {
        eraseExceptionsExample();
    }

    private static void eraseExceptionsExample() {
        try (JammedTurkeyCageException t = new JammedTurkeyCageException()) {
            throw new NullPointerException("npe now");
            //1. suppressed exceptions apply only to exception, thrown in the catch block
        } catch (Exception e) {
            //2. catch block may erase if do nothing with caught exception and throw his own
            //below it erases
            throw new RuntimeException("catch");
        } finally {
            //3. followed exceptions erase all previous exceptions(including suppressed)
            throw new RuntimeException("couldn't find them");
        }
    }

    private static void simpleSuppressedExceptionExample() {
        try (JammedTurkeyCageException t = new JammedTurkeyCageException()) {
            throw new NullPointerException("NPE");
        } catch (Exception e) {
            e.printStackTrace();
            //or it can be managed, as it shown below

//            System.err.println(e.getMessage());
//            for (Throwable throwable : e.getSuppressed()) {
//                System.err.println(throwable.getMessage());
//            }
        }
    }

}
