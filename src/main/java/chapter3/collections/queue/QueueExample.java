package chapter3.collections.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;
import java.util.Stack;

public class QueueExample {

    public static void main(String[] args) {

        nullPassingStackVsQueue();
    }

    private static void nullPassingStackVsQueue() {
//        NPE
//        Deque<String> stringDeque = new ArrayDeque<>();
//        stringDeque.add(null);

        Stack<String> strings = new Stack<>();
        strings.add(null);
        strings.add(null);
        System.out.println(strings.capacity());
        System.out.println(strings.size());
        strings.add(null);
        System.out.println("---");
        System.out.println(strings.size());
        System.out.println(strings.peek());
        System.out.println(strings.size());
    }

    private static void stackMethods() {
        Stack<String> stringStack = new Stack<>();
//        Stack has only push/poll/peek methods
        System.out.println(stringStack.push("1"));
        System.out.println(stringStack.push("2"));
        System.out.println(stringStack.push("3"));
        System.out.println(stringStack);
        System.out.println(stringStack.pop());
        System.out.println(stringStack);
        System.out.println(stringStack.peek());
        System.out.println(stringStack);


    }


    private static void queueMethods() {
        Queue<String> stringQueue = new ArrayDeque<>();

//        add - add an element to the back, returns true or throw exception
//        offer - add an element to the back, returns true or false
        stringQueue.add("first in");
        stringQueue.offer("second in");
        System.out.println(stringQueue);
//        null add of offer will cause NPE
//        stringQueue.add(null);
//        stringQueue.offer(null);

//        remove - return and remove next(first-to-out) element, returns elem or throw exception
//        poll - return and remove next(first-to-out) element, returns elem or null
        System.out.println(stringQueue.poll());
        System.out.println(stringQueue);
        System.out.println(stringQueue.remove());
        System.out.println(stringQueue);
//        if no elements will returns null
        System.out.println(stringQueue.poll());
        System.out.println(stringQueue);
//        if no elements will throw NoSuchElementException
//        System.out.println(stringQueue.remove());
        System.out.println(stringQueue);

        stringQueue.add("first in");
        stringQueue.offer("second in");
        System.out.println(stringQueue);

//        just returns next element, or null
        System.out.println(stringQueue.peek());
        System.out.println(stringQueue);

        ArrayDeque<String> stringArrayDeque = new ArrayDeque<>();
        stringArrayDeque.offer("1");
        stringArrayDeque.offer("2");
        System.out.println(stringArrayDeque);
//        ArrayDeque(Stack) method. add in front
        stringArrayDeque.push("3");
        System.out.println(stringArrayDeque);
    }
}
