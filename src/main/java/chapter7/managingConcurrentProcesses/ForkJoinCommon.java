package chapter7.managingConcurrentProcesses;

public class ForkJoinCommon {

    public static void main(String[] args) {

    }

    private static int recursionFactorialExample(int i){
        if (i <= 1){
            return 1;
        }
        return i * recursionFactorialExample(i - 1);
    }

}
