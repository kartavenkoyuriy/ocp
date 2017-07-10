package main.java.chapter1.inheritance;

/**
 * Created by Yuriy Kartavenko on 7/7/2017.
 */
public class Browsers {
    static class Browser {
        public void go() {
            System.out.println("Inside Browser");
        }
    }

    static class Firefox extends Browser {
        public void go() {
            System.out.println("Inside Firefox");
        }
    }

    static class IE extends Browser {
        @Override
        public void go() {
            System.out.println("Inside IE");
        }
    }

    public static void main(String[] args) {
        Browser b = new Firefox();
        //class cast will be successful if instanceOf successful
        // now it will throw a ClassCastException at runtime since the object being referenced is not an instance of the IE class
//        IE e = (IE) b;
        System.out.println(b instanceof IE);
//        e.go();
    }
}

