package chapter6.exceptions;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;

public class RethrowingExceptionsCommon {

    //obvious chapter

    public static void main(String[] args) {

        //handling can be done by multi-catch
        try {
            parseData();
            //but NPE will not handle here
            //throw new NullPointerException("npe");
        } catch (IOException | SQLException e) {
            System.out.println("catched npe");
            e.printStackTrace();
        }

        //or handling can be done by generalized Exception
        try {
            parseData();
            //and NPE will be handle here
            throw new NullPointerException("npe");
        } catch (Exception e) {
            System.out.println("catched npe");
            e.printStackTrace();
        }


    }

    private static void parseData() throws IOException, SQLException, DateTimeParseException{}
}
