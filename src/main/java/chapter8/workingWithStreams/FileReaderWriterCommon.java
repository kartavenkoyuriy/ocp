package chapter8.workingWithStreams;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReaderWriterCommon {
    public static void main(String[] args) throws IOException {
        File source = new File("D:\\install\\bigtext.txt");
        File destination = new File("D:\\install\\bigtext_010.txt");
        List<String> stringList = readFile(source);
        //can postprocess the data
        for (String s : stringList) {
            System.out.println(s);
        }
        writeFile(destination, stringList);
    }

    private static List<String> readFile(File sourceFile) throws IOException {
        List<String> result = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(sourceFile))) {
            //string instead of char
            String readLine;
            while ((readLine = reader.readLine()) != null) {
                result.add(readLine);
            }
        }

        return result;
    }

    private static void writeFile(File destinationFile, List<String> stringsToWrite) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFile))) {
            for (String stringToWrite : stringsToWrite) {
                writer.write(stringToWrite);
                //use newLine() because readLine() separates by new-line-delimiter
                writer.newLine();
            }
            writer.flush();
        }
    }
}
