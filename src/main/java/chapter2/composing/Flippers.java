package chapter2.composing;

import java.util.Arrays;

public class Flippers {
    public void toFlip(){
        System.out.println("flip-flip");
    }

    public static void main(String[] args) {
//        // TODO Auto-generated method stub
        int[] unsortedList = {4, 7, 2, 1, 11, 14, 3, 6, 5, 13, 8, 15, 10, 9, 17, 18, 22, 44, 21, 12, 30};
        quicksortB(unsortedList, 0, unsortedList.length-1);
//        quicksort(unsortedList, 0, unsortedList.length - 1, "text");
        System.out.println(Arrays.toString(unsortedList));
    }


    public static void quicksortB(int[] unsortedList, int startIndex, int lastIndex) {

        if (startIndex<lastIndex) {
            System.out.println("before sort:" + Arrays.toString(unsortedList));
            int pos = sort(unsortedList, startIndex, lastIndex);
//            int pos = partition(unsortedList, startIndex, lastIndex, "");
            System.out.println("after sort:" + Arrays.toString(unsortedList));
            quicksortB(unsortedList, startIndex, pos-1);
            quicksortB(unsortedList, pos+1, lastIndex);
        }
    }

    public static int sort(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int currIndex = startIndex;
        for (int i = startIndex + 1; i < endIndex; i++) {
            if (pivot >= arr[i]) {
                int temp = arr[currIndex + 1];
                arr[currIndex + 1] = arr[i];
                arr[i] = temp;
                currIndex++;
            }
        }
        int temp = arr[startIndex];
        arr[startIndex] = arr[currIndex];
        arr[currIndex] = temp;
        return currIndex;
    }


    // Quick sort algorithm
    public static void quicksort(int[] numbers, int low, int high, String text) {
        if (low < high) {
            int dp = partition(numbers, low, high, text);

            quicksort(numbers, low, dp-1, "First");
            quicksort(numbers, dp+1, high,  "Second");
        }
    }

    // partition numbers[low] to numbers[high] using numbers[low] as the pivot
    private static int partition(int[] numbers, int low, int high, String text) {
//        System.out.println(low + " | "  + high + text);
        int pivot = numbers[low];
        int i = low;
        for (int j = low + 1; j <= high; j++) {
            if (numbers[j] < pivot) {
                ++i;
                change(numbers, i, j);
            }
            //end for
        }
        change(numbers, low, i);
        return i;
    }

    public static void change(int[] arr, int firstIndex, int secIndex) {
        int t = arr[firstIndex];
        arr[firstIndex] = arr[secIndex];
        arr[secIndex] = t;
    }
}

