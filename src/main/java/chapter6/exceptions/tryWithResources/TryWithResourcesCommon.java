package chapter6.exceptions.tryWithResources;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TryWithResourcesCommon {

    public static void main(String[] args) {

    }

    private static void newApproachExample() throws IOException {
        Path pathIn = Paths.get("asd");
        Path pathOut = Paths.get("qwe");
        try (BufferedReader in = Files.newBufferedReader(pathIn);
                BufferedWriter out = Files.newBufferedWriter(pathOut)) {
            out.write(in.readLine());
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
