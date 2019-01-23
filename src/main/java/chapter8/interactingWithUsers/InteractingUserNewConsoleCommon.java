package chapter8.interactingWithUsers;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

public class InteractingUserNewConsoleCommon {

    //System.console() when running from IDE - returns null
    public static void main(String[] args) {
        consoleSimpleExample();
    }

    //console.writer() invokes PrintWriter
    //console.reader() invokes Reader
    private static void readerWriterExample() {

    }

    //It is recommended that
    //you call the flush() method prior to calling any readLine() or readPassword() methods in order
    //to ensure that no data is pending during the read. Failure to do so could result in a user prompt for
    //input with no preceding text, as the text prior to the prompt may still be in a buffer.
    private static void flushExample() {

    }

    private static void printfExample() {
        Console console = System.console();

        if (console != null) {
            //use PrintWriter's format
            console.writer().format(new Locale("fr", "CA"), "Hello World");
            console.writer().println("Welcome to Our Zoo!");
            //use Console's format
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

    private static void consoleReadLineExample() throws IOException {
        Console console = System.console();
        if (console == null) {
            throw new RuntimeException("Console is not available");
        } else {
            console.writer().print("How excited are you about your trip today? ");
            //use flush prior to calling readLine
            console.flush();
            //readLine() - without arguments
            String excitementAnswer = console.readLine();
            //readLine(String s, Object ... args) - formatted input
            String name = console.readLine("Enter your name");
            Integer age = null;
            //PrintWriter's print
            console.writer().print("What is your age");
            //flush prior readLine
            console.flush();
            BufferedReader reader = new BufferedReader(console.reader());
            //wrap console's reader with BufferedReader
            String s = reader.readLine();
            age = Integer.valueOf(s);
            //new line with PrintWriter's no-arg println()
            console.writer().println();
            //use Console's format
            console.format("Your name is " + name);
            console.writer().println();
            //use console's format
            console.format("Your age is " + age);
            //use console's printf, which invoke console's format
            console.printf("Your excitement level is: " + excitementAnswer);
        }
    }

    private static void readPasswordExample() {
        Console console = System.console();
        if (console == null) {
            throw new RuntimeException("Console is not available");
        } else {
            //read password with no prior formatted text
            //disabling echoing - user can't see what he is typing
            //return char to not store in the String pool
            char[] chars = console.readPassword();
            //read password with formatted text
            char[] repeatChars = console.readPassword("repeat password");

            boolean equals = Arrays.equals(chars, repeatChars);

            //override array with dummy data to wipe the entered password
            Arrays.fill(chars, '*');
            Arrays.fill(repeatChars, '*');

            console.format("Passwords are " + (equals ? "equals" : "not equals"));

        }
    }
}
