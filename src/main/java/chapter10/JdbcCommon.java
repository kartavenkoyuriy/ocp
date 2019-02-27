package chapter10;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class JdbcCommon {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        describeJdbcUrl();
        gettingDataBaseConnection();
        obtainingAStatement();
        executingAStatement();
        readingAResultSet();
        gettingDataFromColumn();
        scrollingResultSet();
        closingDbResources();
        dealingWithExceptions();

        simpleJdbcExample();
    }

    private static void dealingWithExceptions() {
        String url = " jdbc:derby:zoo";
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select not_a_column from animal")) {
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        } catch (SQLException e) {
            //returns a human-readable message as to what went wrong
            System.out.println(e.getMessage());
            //returns a code as to what went wrong. You can Google the name of your database and the SQL state to get more information about the error
            System.out.println(e.getSQLState());
            //is a database-specifi c code
            System.out.println(e.getErrorCode());
        }
    }

    private static void closingDbResources() throws SQLException {
        //Closing a Connection also closes the Statement and ResultSet
        //Statement also closes the ResultSet.

        //a try-with-resources statement closes the resources in the reverse order from which they were opened.
        //This means that the ResultSet is closed first, followed by the Statement, and then the Connection.
        //This is the standard order to close resources.

        //JDBC automatically closes a ResultSet when you run another SQL statement from the same Statement

        //How many resources are closed in this code?
        //4
        //explanation below
        String url = "jdbc:derby:zoo";
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select count(*) from animal")) {

            if (rs.next()) {
                System.out.println(rs.getInt(1));
            }

            //here 'rs' is closed because the same Statement runs another query
            ResultSet rs2 = stmt.executeQuery("select count(*) from animal");
            //here 'rs2' is closed because the same Statement runs another SQL statement

            //moreover executeUpdate() doesn't return a ResultSet, all ResultSets are closed.
            //there is only Statement and Connection left to close in try-with-resources
            int num = stmt.executeUpdate(
                    "update animal set name = 'clear' where name = 'other'");
        }
    }

    private static void scrollingResultSet() throws SQLException {
        String url = "jdbc:derby:zoo";
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement(
                ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        //beforeFirst() and afterLast() methods have a return type of void
        //The first() and last() methods return a boolean for whether they were successful at finding a row
        //The next() and previous() methods return a boolean for whether they were successful at finding a row

        //2 rows in the ResultSet
        ResultSet rs = stmt.executeQuery("select id from species order by id");
        //puts the cursor after the last row in the result
        rs.afterLast();
        //moves it back one
        System.out.println(rs.previous()); // true
        //outputs id 2 since the cursor is on the last row
        System.out.println(rs.getInt(1)); // 2

        //moves one more back, !!! which takes us to row #1 !!!
        System.out.println(rs.previous()); // true
        System.out.println(rs.getInt(1)); // 1
        //goes back to the last row, which is row 2
        System.out.println(rs.last()); // true
        System.out.println(rs.getInt(1)); // 2
        //goes to the first row, which is row 1
        System.out.println(rs.first()); // true
        System.out.println(rs.getInt(1)); // 1
        //goes to a point immediately before the first row
        rs.beforeFirst();
        //there is no data to read before the first row
        System.out.println(rs.getInt(1)); // throws SQLException

        //-----------------------------------------
        //an example where the query doesn’t return any rows
        ResultSet rs1 = stmt.executeQuery("select id from species where id = -99");
        System.out.println(rs1.first()); // false
        System.out.println(rs1.last()); // false

        //-----------------------------------------
        //ABSOLUTE:
        //The first row is row 1, the second is row 2, and so on.

        //absolute(int i) returns boolean
        //returns true if the cursor is moved to a position in this ResultSet object; false if the cursor is before the first row or after the last row

        //If the given row number is negative, the cursor moves to an absolute row position with respect to the end of the result set. For example,
        //calling the method absolute(-1) positions the cursor on the last row; calling the method absolute(-2) moves the cursor to the next-to-last row, and so on.
        //Calling absolute(1) is the same as calling first(). Calling absolute(-1) is the same as calling last().

        //If the row number specified is zero, the cursor is moved to before the first row.
        //An attempt to position the cursor beyond the first/last row in the result set leaves the cursor before the first row or after the last row.

        //return 5 rows

        //cursor at the second row of the ResultSet
        System.out.println(rs.absolute(2)); // true
        System.out.println(rs.getString("id")); // 2
        //puts the cursor before the result set, so absolute() returns false
        System.out.println(rs.absolute(0)); // false
        //moves the cursor to the last row, showing that you can go to a valid row after going outside the table
        System.out.println(rs.absolute(5)); // true
        System.out.println(rs.getString("id")); // 5
        //moves the cursor to the second-to-last row
        System.out.println(rs.absolute(-2)); // true
        System.out.println(rs.getString("id")); // 4
        //-----------------------------------------

        //RELATIVE:
        //Attempting to move beyond the first/last row in the result set positions the cursor before/after the the first/last row.
        //Calling relative(0) is valid, but does not change the cursor position.

        //Note: Calling the method relative(1) is identical to calling the method next()
        //and calling the method relative(-1) is identical to calling the method previous().

        //moves the cursor to the first row of the result
        System.out.println(rs.next()); // true
        System.out.println(rs.getString("id")); // 1
        //moves forward two rows to row 3
        System.out.println(rs.relative(2)); // true
        System.out.println(rs.getString("id")); // 3
        //moves backward one to row 2
        System.out.println(rs.relative(-1)); // true
        System.out.println(rs.getString("id")); // 2
        //tries to move forward four rows,
        //which would position the cursor by row 6. There is no row 6, so this is just after the last
        //row. Since there is no row 6, the method returns false.
        System.out.println(rs.relative(4)); // false


    }

    private static void gettingDataFromColumn() throws SQLException {
        //getBoolean    boolean             BOOLEAN
        //getDate       java.sql.Date       DATE
        //getDouble     double              DOUBLE
        //getInt        int                 INTEGER
        //getLong       long                BIGINT
        //getObject     Object              Any type
        //getString     String              CHAR, VARCHAR
        //getTime       java.sql.Time       TIME
        //getTimeStamp  java.sql.TimeStamp  TIMESTAMP

        //There is no getChar method

        String url = "jdbc:derby:zoo";
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select date_born from animal where name = 'Elsa'");
        if (rs.next()) {
            //When calling getDate, JDBC returns just the date part of the value
            java.sql.Date sqlDate = rs.getDate(1);
            //This is an older class, but Java 8 adds a method to convert it to the new LocalDate type.
            LocalDate localDate = sqlDate.toLocalDate();
            System.out.println(localDate); // 2001―05―06

            //When calling getTime, JDBC returns just the time part of the value
            java.sql.Time sqlTime = rs.getTime(1);
            //This is the hours and minutes. It also optionally includes more granular pieces like seconds
            LocalTime localTime = sqlTime.toLocalTime();
            System.out.println(localTime); // 02:15

            //both the date and time
            java.sql.Timestamp sqlTimeStamp = rs.getTimestamp(1);
            LocalDateTime localDateTime = sqlTimeStamp.toLocalDateTime();
            System.out.println(localDateTime); // 2001―05―06T02:15

            Object idField = rs.getObject("id");
            Object nameField = rs.getObject("name");
            // how to confirm that the type is Integer before casting and unboxing it into an int
            if (idField instanceof Integer) {
                int id = (Integer) idField;
                System.out.println(id);
            }
            if (nameField instanceof String) {
                String name = (String) nameField;
                System.out.println(name);
            }
        }

    }

    private static void readingAResultSet() throws SQLException {
        String url = "jdbc:derby:zoo";
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();
        Map<Integer, String> idToNameMap = new HashMap<>();
        ResultSet rs = stmt.executeQuery("select id, name from species");
        //Each time through the loop represents one row in the ResultSet
        //A ResultSet has a cursor, which points to the current location in the data
        while (rs.next()) {
            //can be accessed as a column name
            int id = rs.getInt("id");
            //can be accessed as a column number
            int idInt = rs.getInt(1);
            String name = rs.getString("name");
            idToNameMap.put(id, name);
        }
        System.out.println(idToNameMap); // {1=African Elephant, 2=Zebra}

        //When you want only one row, you use an if statement rather than a while loop:
        ResultSet rsOneRow = stmt.executeQuery("select count(*) from animal");
        if (rsOneRow.next()) {
            System.out.println(rsOneRow.getInt(1));
        }

        //--------------PROBLEMS--------------------------

        //column start from 1
        int id = rs.getInt(0); // BAD CODE

        //Calling rs.next() works. It returns false. However, calling a getter afterward does
        //throw a SQLException because the result set cursor does not point to a valid position
        ResultSet rs1 = stmt.executeQuery(
                "select * from animal where name= 'Not in table'");
        rs1.next();
        rs1.getInt(1); // throws SQLException

        //Not calling rs.next() at all is a problem
        ResultSet rs2 = stmt.executeQuery("select count(*) from animal");
        rs2.getInt(1); // throws SQLException

        //Trying to get a column that isn’t in the ResultSet is just as bad as an invalid column index
        ResultSet rs3 = stmt.executeQuery("select id from animal");
        rs3.next();
        rs3.getInt("badColumn"); // throws SQLException

    }

    private static void executingAStatement() throws SQLException {
        String url = "jdbc:derby:zoo";
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();

        //DELETE, INSERT, or UPDATE uses executeUpdate()
        //It returns the number of rows that were inserted, deleted, or changed
        int result = stmt.executeUpdate(
                "insert into species values(10, 'Deer', 3)");
        //runs a statement to insert 1 row
        System.out.println(result); // 1
        result = stmt.executeUpdate(
                "update species set name = '' where name = 'None'");
        //checks the whole table, but no records match, so the result is 0
        System.out.println(result); // 0
        result = stmt.executeUpdate(
                "delete from species where id = 10");
        //deletes the row created
        System.out.println(result); // 1

        //when executeUpdate("select * from animal")
        //then SQLException "A result was returned when none was expected."
        //we get an exception because the driver can’t translate the query into the expected return type

        //-----------------------------------------------------------------------------

        //SQL statement that begins with SELECT. executeQuery().
        ResultSet rs = stmt.executeQuery("select * from species");

        //get a SQLException when using executeQuery() with SQL that changes the database
        //when executeQuery("insert * from animal")
        //then SQLException "No results were returned by the query."

        //-----------------------------------------------------------------------------

        //execute(). can run either a query or an update. It
        //returns a boolean so that we know whether there is a ResultSet
        boolean isResultSet = stmt.execute("");
        if (isResultSet) {
            ResultSet rsExecute = stmt.getResultSet();
            //If sql is a SELECT, the boolean is true and we can get the ResultSet
            System.out.println("ran a query");
        } else {
            int resultExecute = stmt.getUpdateCount();
            //If it is not a
            //SELECT, we can get the number of rows updated.
            System.out.println("ran an update");
        }
    }

    private static void obtainingAStatement() throws SQLException {
        String url = "jdbc:derby:zoo";
        Connection conn = DriverManager.getConnection(url);
        Statement stmt = conn.createStatement();

        // The first is the ResultSet type, and the other is the ResultSet concurrency mode
        Statement stmtExtended = conn.createStatement(
                //1. By default, a ResultSet is in TYPE_FORWARD_ONLY mode. You can go through the data once in the order in which it was retrieved
                //2. With TYPE_SCROLL_INSENSITIVE(You can scroll up and down), you have a static view of what the ResultSet looked like when you did the
                //query. If the data changed in the table, you will see it as it was when you did the query.
                //3. TYPE_SCROLL_SENSITIVE(You can scroll up and down), you would see the latest data when scrolling through the ResultSet.
                //most databases and database drivers don’t actually support.
                ResultSet.TYPE_FORWARD_ONLY,
                //1. By default, a ResultSet is in CONCUR_READ_ONLY mode. It means that you can’t update the result set. Most of the time, you will
                //use INSERT, UPDATE, or DELETE SQL statements to change the database rather than a ResultSet.
                //2. CONCUR_UPDATABLE. it lets you modify the database through the ResultSet.
                //Databases and JDBC drivers are not required to support CONCUR_UPDATABLE
                ResultSet.CONCUR_READ_ONLY);
    }

    private static void simpleJdbcExample() throws SQLException {
        String url = "jdbc:derby:zoo";
        try (Connection conn = DriverManager.getConnection(url);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("select name from animal")) {
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }
    }

    private static void gettingDataBaseConnection() throws SQLException, ClassNotFoundException {
        //Class.forName() loads a class. This lets DriverManager use a Driver, even if the JAR
        //doesn’t have a META-INF/service/java.sql.Driver file. There’s no harm in including
        //Class.forName(), even if the newer driver does have this file.

        //Having META-INF/service/java.sql.Driver inside the JAR became mandatory with
        //JDBC 4.0 in Java 6.

        //it's obsolete from JDBC 4.0 and Java 6
        Class.forName("org.postgresql.Driver");

        //Unless the exam specifies a command line, you can assume that the correct JDBC driver
        //JAR is in the classpath. The exam creators explicitly ask about the driver JAR if they want
        //you to think about it.

        //The DriverManager class looks through the classpath for JARs that contain a Driver.
        //DriverManager knows that a JAR is a driver because it contains a file called java.sql.Driver
        //in the directory META-INF/services. In other words, a driver might contain this information:
        //META-INF
        //−service
        //―java.sql.Driver
        //com
        //−wiley
        //―MyDriver.class
        //Inside the java.sql.Driver file is one line. It is the fully qualified package name of the
        //Driver implementation class.

        //object is not a "connection" class(it's some vendor's implementation)
        Connection conn = DriverManager.getConnection("jdbc:derby:zoo");

        //extended, with login and password
        Connection connExtended = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/ocp-book",
                "username",
                "password");
    }

    private static void describeJdbcUrl() {
        //correct scheme - "protocol:vendor:db specific connection details"
        //
        //correct examples:
        //jdbc:postgresql://localhost/zoo
        //jdbc:oracle:thin:@123.123.123.123:1521:zoo
        //jdbc:mysql://localhost:3306/zoo?profileSQL=true
        //
        //incorrect examples:
        //jdbc:postgresql://local/zoo - "local" instead of "localhost"
        //jdbc:mysql://123456/zoo - "123456" instead of IP/localhost
        //jdbc;oracle;thin;/localhost/zoo - semicolon instead of colon
        String url = "jdbc:derby:zoo";
    }
}
