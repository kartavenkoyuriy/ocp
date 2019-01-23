package chapter8.interactingWithUsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InteractingUserOldCommon {

    public static void main(String[] args) throws IOException {
        //we don't close System.in. close it will prevent user input till the end of the application
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//            String userInput = reader.readLine();
//            System.out.println("user input:" + userInput);

        //behavior is the same - first calculate arguments, then concatenate and write
        System.out.println("user input:" + reader.readLine());
    }

}
