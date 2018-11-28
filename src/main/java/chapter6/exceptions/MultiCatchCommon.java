package chapter6.exceptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class MultiCatchCommon {

    public static void main(String[] args) {

    }

    private static void reassignMulticatchExample() {
        try {
            System.out.println();
        } catch (Exception e) {
            //i'ts bad, but it is allowed to reassign the exception variable
            e = new RuntimeException();
            e.printStackTrace();
        }

        try {
            throw new IOException();
        } catch (RuntimeException | IOException e) {
            //in multicatch variable is effectively so it doesn't allow to reassign a variable
            //e = (IOException)new Exception();
        }
    }

    private static void subclassMulticatchExample() {
        try {
            throw new IOException();
        } catch (IOException e) {
            //FileNotFoundException is a subclass of IOException. multi-catch is redundant, it won't compile
//        } catch (FileNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void multicatchSimpleExample() {
        try {
            Path path = Paths.get("dolphinsBorn.txt");
            String text = new String(Files.readAllBytes(path));
            LocalDate localDate = LocalDate.parse(text);
            System.out.println(localDate);
        //if the catches blocks are the same, there are three approaches:
        //1. generalize to Exception. bad if we want to handle only mentioned exceptions
        //2. provide a helper method. better, but still we have code duplication
        //3. multi-catch. our choice. PIPE as a delimiter, and one variable name, at the end
        } catch (IOException | DateTimeParseException e) {
            //does not compile. one variable name
            //} catch (IOException e | DateTimeParseException e) {
            //does not compile. one variable name
            //} catch (IOException e | DateTimeParseException e1) {
            //does not compile. variable name, at the end
            //} catch (IOException e | DateTimeParseException) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
