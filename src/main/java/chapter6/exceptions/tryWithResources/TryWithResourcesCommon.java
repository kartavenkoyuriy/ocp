package chapter6.exceptions.tryWithResources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class TryWithResourcesCommon {

    public static void main(String[] args) {

    }

    private static void scopeVariableExample() {
        try {
            Scanner scanner = new Scanner(System.in);
        } catch (Exception e) {
            //variable scanner is out of scope here
            e.printStackTrace();
        } finally {
            //variable scanner is out of scope here
        }

        try (Scanner scanner = new Scanner(System.in)){
            scanner.hasNext();
        } catch (Exception e){
            //variable scanner is out of scope here
//            scanner.hasNext();
        } finally {
            //variable scanner is out of scope here
        }
    }

    private static void catchFinallyExample() {
        Path pathIn = Paths.get("asd");
        Path pathOut = Paths.get("qwe");
        try (BufferedReader in = Files.newBufferedReader(pathIn);
                BufferedWriter out = Files.newBufferedWriter(pathOut)) {
            out.write(in.readLine());
            //resources are closed at the end of the "try" block(implicit "finally" block)
        } catch (IOException e){
            //it is still valid(optional) to add a "catch" block
        } finally {
            //it is still valid(optional) to add a "finally" block
        }
    }

    private static void newApproachExample() throws IOException {
        Path pathIn = Paths.get("asd");
        Path pathOut = Paths.get("qwe");
        try (BufferedReader in = Files.newBufferedReader(pathIn);
                BufferedWriter out = Files.newBufferedWriter(pathOut)) {
            out.write(in.readLine());
            //resources are closed at the end of the "try" block
        }
    }

    private static void oldApproachExample() throws IOException {
        Path pathIn = Paths.get("asd");
        Path pathOut = Paths.get("qwe");
        BufferedReader in = null;
        BufferedWriter out = null;
        {
            try {
                in = Files.newBufferedReader(pathIn);
                out = Files.newBufferedWriter(pathOut);
                out.write(in.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            }
        }
    }
}
