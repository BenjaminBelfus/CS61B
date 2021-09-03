import java.util.Arrays;

public class DistributionSorts {

    /* Destructively sorts ARR using counting sort. Assumes that ARR contains
       only 0, 1, ..., 9. */
    public static void countingSort(int[] arr) {
        // TODO
        int[] sorted_array = new int[arr.length];
        int[] counts = new int[10];
        int[] starts = new int[10];

        for (int i : arr) {
            counts[i] += 1;
        }

        int sort_indexer = 0;
        for (int i = 0; i < 10; i++) {
            starts[i] = sort_indexer;
            sort_indexer += counts[i];
        }

        for (int element : arr) {
            sorted_array[starts[element]] = element;
            starts[element] += 1;
        }

        System.arraycopy(sorted_array, 0, arr, 0, arr.length);
    }


    /* Destructively sorts ARR using LSD radix sort. */
    public static void lsdRadixSort(int[] arr) {
        int maxDigit = mostDigitsIn(arr);
        for (int d = 0; d < maxDigit; d++) {
            countingSortOnDigit(arr, d);
        }
    }


    /* A helper method for radix sort. Modifies ARR to be sorted according to
       DIGIT-th digit. When DIGIT is equal to 0, sort the numbers by the
       rightmost digit of each number. */
    private static void countingSortOnDigit(int[] arr, int digit) {
        // TODO
        int[] sorted_array = new int[arr.length];
        int[] counts = new int[10];
        int[] starts = new int[10];

        digit = (int) Math.pow(10, digit);

        for (int i : arr) {
            int element = (i / digit) % 10;
            counts[element] += 1;
        }

        int sort_indexer = 0;

        for (int i = 0; i < 10; i++) {
            starts[i] = sort_indexer;
            sort_indexer += counts[i];
        }

        for (int i : arr) {
            int element = (i / digit) % 10;
            sorted_array[starts[element]] = i;
            starts[element] += 1;
        }

        System.arraycopy(sorted_array, 0, arr, 0, arr.length);

    }



    /* Returns the largest number of digits that any integer in ARR has. */
    private static int mostDigitsIn(int[] arr) {
        int maxDigitsSoFar = 0;
        for (int num : arr) {
            int numDigits = (int) (Math.log10(num) + 1);
            if (numDigits > maxDigitsSoFar) {
                maxDigitsSoFar = numDigits;
            }
        }
        return maxDigitsSoFar;
    }

    /* Returns a random integer between 0 and 9999. */
    private static int randomInt() {
        return (int) (10000 * Math.random());
    }

    /* Returns a random integer between 0 and 9. */
    private static int randomDigit() {
        return (int) (10 * Math.random());
    }

    private static void runCountingSort(int len) {
        int[] arr1 = new int[len];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = randomDigit();
        }
        System.out.println("Original array: " + Arrays.toString(arr1));
        countingSort(arr1);
        if (arr1 != null) {
            System.out.println("Should be sorted: " + Arrays.toString(arr1));
        }
    }

    private static void runLSDRadixSort(int len) {
        int[] arr2 = new int[len];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = randomDigit();
        }
        System.out.println("Original array: " + Arrays.toString(arr2));
        lsdRadixSort(arr2);
        System.out.println("Should be sorted: " + Arrays.toString(arr2));

    }

    public static void main(String[] args) {
        runCountingSort(20);
        runLSDRadixSort(3);
        runLSDRadixSort(30);
    }
}