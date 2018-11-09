package chapter3.collections.queue;

import java.util.ArrayDeque;

public class QueueExampleRepeat {

    public static void main(String[] args) {
        ArrayDeque<String> arrayDeque = new ArrayDeque<>();

        //add to the end
//        arrayDeque.add("add 1");
//        arrayDeque.offer("offer 1");

        //both NPE
//        arrayDeque.add(null);
//        arrayDeque.offer(null);

//        System.out.println(arrayDeque.element());
        System.out.println(arrayDeque.peek());
        System.out.println(arrayDeque.peekFirst());
        System.out.println(arrayDeque.peekLast());



        arrayDeque.add("2");
        arrayDeque.add("3");
    }




}
