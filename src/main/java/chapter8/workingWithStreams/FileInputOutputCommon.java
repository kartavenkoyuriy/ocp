package chapter8.workingWithStreams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileInputOutputCommon {

    public static void main(String[] args) throws IOException {
        simpleDataCopyByBufferedOutputStream();
    }


    private static void simpleDataCopyByBufferedOutputStream() throws IOException {
        try (InputStream in = new BufferedInputStream(new FileInputStream("D:\\install\\bigtext.txt"));
                OutputStream out = new BufferedOutputStream(new FileOutputStream("D:\\install\\bigtext_copy1.txt"))) {
            byte[] buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = in.read(buffer)) != -1){
                //it is important to limit the length of the bytes that was actually overridden into a buffer
                //because the remaining bytes was not overridden and stay in the buffer from the previous read
                out.write(buffer, 0, lengthRead);
                //flush is nice to have
                //to ensure that the written data actually makes it to disk before the next buffer of data is read
                out.flush();
            }
        }
    }

    private static void simpleDataCopyByFileOutputStream() throws IOException {
        try (InputStream in = new FileInputStream("D:\\install\\zoo.txt");
                //if the file dosn't exist - it will be created
                //see details in constructor - FNF exception
                OutputStream out = new FileOutputStream("D:\\install\\zoo_copy1.txt")) {
            int b;
            while ((b = in.read()) != -1){
                //it remove all the data from destination file first(data will be overridden completely)
                //then sequensently copy the data
                out.write(b);
            }
            //flush is not necessary
//            out.flush();
        }
    }

}
