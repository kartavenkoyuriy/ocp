package chapter8.workingWithStreams;

import chapter8.workingWithStreams.serializing.Animal;
import chapter8.workingWithStreams.serializing.SpecialAnimal;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PrintStreamPrintWriterCommon {

    public static void main(String[] args) throws FileNotFoundException {

        lineSeparatorPrintLnExample();
    }

    private static void formatPrintfExample(){
        //printf(String format, Object ... args) call format(String format, Object ... args) with same argument set
    }

    private static void lineSeparatorPrintLnExample(){
        //no arg - write a line separator
        System.out.println();
        //with arg - write argument.toString or String.valueOf(argument), then a line separator
        System.out.println("234");

        //system variable where line separator stores
        System.out.println(System.getProperty("line.separator"));
    }

    private static void printWriteSameResultExample() throws FileNotFoundException {
        try (PrintWriter printWriter = new PrintWriter("D:\\install\\print-writer.txt")){
            printWriter.print(5);
            printWriter.write(String.valueOf(5));

            printWriter.print(2.0);
            printWriter.write(String.valueOf(2.0));

            SpecialAnimal animal = new SpecialAnimal();
            printWriter.print(animal);
            printWriter.write(animal != null ? animal.toString() : "null");
            //ambiguous call
            //printWriter.write(null);
            //passing argument annotated as not null
            //printWriter.write((String) null);
        }
    }

}
