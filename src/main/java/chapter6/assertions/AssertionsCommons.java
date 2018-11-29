package chapter6.assertions;

public class AssertionsCommons {
    public static void main(String[] args) {
        //assertions are made for debugging
        //allowing you to verify that something that
        //you think is true during the coding phase is actually true at runtime

        //enables by -ea key

        //this means: enable assertions(-ea) for all classes in package and subpackages(...)
        //but exclude/disable assertions(-da) for mentioned packages
        //and run Main
        //java -ea:com.wiley.demos... -da:com.wiley.demos.TestColors my.programs.Main

        //For checking input parameters in method/constructor
        //it is better to use IllegalArgumentException than assert

    }

    private static void assertBadPracticeExample() {
        int x = 10;
        //assert should not have side effects
        assert ++x == 10;
    }

    private static void simpleAssertion() {
        int numGuests = -5;
        assert numGuests > 0;
        System.out.println(numGuests);
    }
}
