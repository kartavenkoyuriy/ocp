package chapter6.exceptions.tryWithResources;

class JammedTurkeyCageException implements AutoCloseable{

    @Override
    public void close() throws IllegalStateException {
        throw new IllegalStateException("Cage door doesn't close");
    }
}
public class SuppressedExceptionsCommon {

    public static void main(String[] args) {

    }

}
