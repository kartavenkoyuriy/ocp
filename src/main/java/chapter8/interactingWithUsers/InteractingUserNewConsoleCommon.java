package chapter8.interactingWithUsers;

import java.io.Console;
import java.util.Locale;

public class InteractingUserNewConsoleCommon {

    //System.console() when running from IDE - returns null
    public static void main(String[] args) {
        consoleSimpleExample();
    }

    //console.writer() invokes PrintWriter
    //console.reader() invokes Reader
    private static void readerWriterExample(){

    }

    //It is recommended that
    //you call the flush() method prior to calling any readLine() or readPassword() methods in order
    //to ensure that no data is pending during the read. Failure to do so could result in a user prompt for
    //input with no preceding text, as the text prior to the prompt may still be in a buffer.
    private static void flushExample() {

    }

        private static void printfExample(){
        Console console = System.console();

        if (console != null) {
            console.writer().format(new Locale("fr", "CA"),"Hello World");

            console.writer().println("Welcome to Our Zoo!");
            console.format("Our zoo has 391 animals and employs 25 people.");
            console.writer().println();
            console.printf("The zoo spans 128.91 acres.");
        }

    }

    private static void consoleSimpleExample() {
        Console console = System.console();
        if (console != null) {
            String userInput = console.readLine();

            console.writer().println("You entered the following: " + userInput);
        }
    }

}
