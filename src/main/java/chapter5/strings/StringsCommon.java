package chapter5.strings;

public class StringsCommon {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder("qwe");
        System.out.println(builder);
        //StringBuilder is mutable. When it reverse()
        StringBuilder reverse = builder.reverse();
        System.out.println(builder);
        System.out.println(reverse);
        boolean b = builder == reverse;
        System.out.println(b);
    }
}
