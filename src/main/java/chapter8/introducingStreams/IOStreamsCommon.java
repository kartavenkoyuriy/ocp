package chapter8.introducingStreams;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class IOStreamsCommon {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        simpleSkipExample();
    }

    private static void simpleSkipExample() throws IOException {
        //no case checking in file name
        try (InputStream is = new BufferedInputStream(new FileInputStream("D:\\install\\TiGeRs.txt"))) {
            System.out.println((char) is.read());
            //skip shows
            //input number - if stream longer than number of bytes to skip
            //less than input number but more than 0 - if stream ends before all bytes are skipped
            //0 or negative - if the stream already ends
            System.out.println("number of bytes that was actually skipped" + is.skip(10));
            is.read();
            //if stream is end, the is.read() returns "-1"
            System.out.println((char) is.read());
            System.out.println((char) is.read());
        }
    }


    private static void simpleFlushExample() {
        //TODO: implement flush
    }

    //mark() - means "remember this point so I can return here after 100 bytes read"
    private static void simpleMarkExample() throws IOException {
        try (InputStream is = new BufferedInputStream(new FileInputStream("D:\\install\\abcd.txt"))) {
            System.out.println((char) is.read());
            if (is.markSupported()) {
                is.mark(100);
                System.out.println((char) is.read());
                System.out.println((char) is.read());
                is.reset();
            }
            System.out.println((char) is.read());
            System.out.println((char) is.read());
            System.out.println((char) is.read());
        }
    }

    //for serializing and deserializing into objects
    private static void simpleObjectInputStreamExample() throws IOException, ClassNotFoundException {
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new BufferedInputStream(new FileInputStream("D:\\install\\error.log")))) {
            System.out.println(objectInputStream.readObject());
        }
    }


    private static void simpleBufferedReaderExample() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("D:\\install\\5291.saz"))) {
            System.out.println(bufferedReader.readLine());
        }
    }
}
