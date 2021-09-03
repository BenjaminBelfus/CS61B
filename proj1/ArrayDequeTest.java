
import org.junit.Test;
import static org.junit.Assert.*;

public class ArrayDequeTest {


    @Test
    public void addRemoveComprehensive() {
        System.out.println("Running add first test.");
        ArrayDeque<Double> ad1 = new ArrayDeque<>();
        try {
            assertTrue(ad1.isEmpty());
            /*Checks correct addition */
            ad1.addFirst(10.0);
            assertFalse(ad1.isEmpty());
            assertEquals((Double) 10.0, ad1.get(0));
            /* Checks adding first 10 elements before 10, using add first*/
            ad1.addFirst(9.0);
            ad1.addFirst(8.0);
            ad1.addFirst(7.0);
            ad1.addFirst(6.0);
            ad1.addFirst(5.0);
            ad1.addFirst(4.0);
            ad1.addFirst(3.0);
            ad1.addFirst(2.0);
            ad1.addFirst(1.0);
            ad1.addFirst(0.0);
            assertEquals((Double) 0.0, ad1.get(0));
            assertEquals((Double) 10.0, ad1.get(10));
            System.out.println("Printing out deque: ");
            ad1.printDeque();
            /* Add 9 more using addlast */
            ad1.addLast(11.0);
            ad1.addLast(12.0);
            ad1.addLast(13.0);
            ad1.addLast(14.0);
            ad1.addLast(15.0);
            ad1.addLast(16.0);
            ad1.addLast(17.0);
            ad1.addLast(18.0);
            ad1.addLast(19.0);
            assertEquals((Double) 11.0, ad1.get(11));
            assertEquals((Double) 19.0, ad1.get(19));
            System.out.println("Printing out deque: ");
            ad1.printDeque();
            /* Checks for correct size after 20 additions */
            assertEquals(20, ad1.size());
            /* Removes 10 elements using removefirst, and 10 elements using removelast*/
            for (int i = 0; i < 10; i++) {
                ad1.removeFirst();
            }
            System.out.println("After removal with removefirst");
            ad1.printDeque();
            System.out.println();

            for (int i = 0; i < 10; i++) {
                ad1.removeLast();
            }
            System.out.println("After removal with removelast");
            ad1.printDeque();
            assertTrue(ad1.isEmpty());
            /* Checks for correct size after 20 removals */
            assertEquals(0, ad1.size());
            /*Tries to remove from an empty list */
            ad1.removeFirst();
            ad1.removeLast();
            System.out.println("After removal from empty");
            ad1.printDeque();
            assertTrue(ad1.isEmpty());
            /* Checks for correct size after removing from empty */
            assertEquals(0, ad1.size());
            /*Adds two elements through both methods after removing from empty */
            ad1.addFirst(0.0);
            ad1.addLast(1.0);
            assertEquals((Double) 0.0, ad1.get(0));
            assertEquals((Double) 1.0, ad1.get(1));
            System.out.println("Twin final additions: ");
            ad1.printDeque();
            /* Checks for correct size after removing from empty and having two additions */
            assertEquals(2, ad1.size());
        } finally {
            System.out.println("Printing out deque: ");
            ad1.printDeque();
        }
    }


    @Test
    public void gettest() {
        System.out.println("Running both gets to chek for same behaviour.");

        ArrayDeque<Double> ad1 = new ArrayDeque<>();
        ArrayDeque<Double> ad2 = new ArrayDeque<>();

        try {
            assertTrue(ad1.isEmpty());

            ad1.addFirst(10.0);
            ad1.addFirst(9.0);
            ad1.addFirst(8.0);
            ad1.addFirst(7.0);
            ad1.addFirst(6.0);
            ad1.addFirst(5.0);
            ad1.addFirst(4.0);
            ad1.addFirst(3.0);
            ad1.addFirst(2.0);
            ad1.addFirst(1.0);
            ad1.addFirst(0.0);

            /*Checks for normal get behaviour with different indexes */
            assertEquals((Double) 0.0, ad1.get(0));

            assertEquals((Double) 6.0, ad1.get(6));

            assertEquals((Double) 7.0, ad1.get(7));

            assertEquals((Double) 10.0, ad1.get(10));

            /*Checks for correct get behaviour in edge cases */

            /* Index is greater than the size of the array */
            assertEquals(null, ad1.get(15));

            /* Index is negative */
            assertEquals(null, ad1.get(-1));

            /* Get applied to an empty list  */
            assertTrue(ad2.isEmpty());
            assertEquals(null, ad2.get(0));

            assertEquals(null, ad2.get(2));

            assertEquals(null, ad2.get(-2));

            System.out.println("5 NULLS");
            System.out.println(ad1.get(15));
            System.out.println(ad1.get(-1));
            System.out.println(ad2.get(0));
            System.out.println(ad2.get(2));
            System.out.println(ad2.get(-2));


        } finally {
            System.out.println("Printing out deque: ");
            ad1.printDeque();
        }
    }


    @Test
    public void printDequeTest() {
        System.out.println("Running printing deque test");

        ArrayDeque<Double> ad1 = new ArrayDeque<>();
        ArrayDeque<Double> ad2 = new ArrayDeque<>();

        try {
            assertTrue(ad1.isEmpty());

            /*Checks correct addition */
            ad1.addFirst(10.0);
            ad1.addFirst(9.0);
            ad1.addFirst(8.0);
            ad1.addFirst(7.0);
            ad1.addFirst(6.0);
            ad1.addFirst(5.0);
            ad1.addFirst(4.0);
            ad1.addFirst(3.0);
            ad1.addFirst(2.0);
            ad1.addFirst(1.0);
            ad1.addFirst(0.0);

            /*Prints after 11 additions */
            ad1.printDeque();

            ad1.addLast(11.0);
            ad1.addLast(12.0);
            ad1.addLast(13.0);
            ad1.addLast(14.0);
            ad1.addLast(15.0);
            ad1.addLast(16.0);
            ad1.addLast(17.0);
            ad1.addLast(18.0);
            ad1.addLast(19.0);

            /*Prints after 9 more additions */
            ad1.printDeque();

            /*Prints after removing the 10 elements that have just been added */
            ad1.removeLast();
            ad1.removeLast();
            ad1.removeLast();
            ad1.removeLast();
            ad1.removeLast();
            ad1.removeLast();
            ad1.removeLast();
            ad1.removeLast();
            ad1.removeLast();
            ad1.printDeque();


            //Print an empty list
            System.out.println("Empty list:");
            ad2.printDeque();

        } finally {
            System.out.println("Printing out deque: ");
            ad1.printDeque();
        }
    }

    /* Test from spec */

    @Test
    public void testsArrayAddLastBeforeResize() {
        System.out.println("Running test before resize to ensure proper operation.");
        ArrayDeque testing = new ArrayDeque();

        try {
            testing.addLast(0);
            testing.addLast(1);
            testing.addLast(2);
            testing.addLast(3);
            testing.addLast(4);
            testing.addLast(5);
            testing.addLast(6);
            testing.addLast(7);

            assertEquals(0, testing.get(0));
            assertEquals(1, testing.get(1));
            assertEquals(2, testing.get(2));
            assertEquals(3, testing.get(3));
            assertEquals(4, testing.get(4));
            assertEquals(5, testing.get(5));
            assertEquals(6, testing.get(6));
            assertEquals(7, testing.get(7));

        } finally {
            // The deque will be printed at the end of this test
            // or after the first point of failure.
            System.out.println("Printing out deque: ");
            testing.printDeque();
        }
    }

    @Test
    public void testsArrayAddFirstBeforeResize() {
        System.out.println("Running test before resize to ensure proper operation.");
        ArrayDeque testing = new ArrayDeque();

        try {
            testing.addFirst(7);
            testing.addFirst(6);
            testing.addFirst(5);
            testing.addFirst(4);
            testing.addFirst(3);
            testing.addFirst(2);
            testing.addFirst(1);
            testing.addFirst(0);

            assertEquals(0, testing.get(0));
            assertEquals(1, testing.get(1));
            assertEquals(2, testing.get(2));
            assertEquals(3, testing.get(3));
            assertEquals(4, testing.get(4));
            assertEquals(5, testing.get(5));
            assertEquals(6, testing.get(6));
            assertEquals(7, testing.get(7));

        } finally {
            // The deque will be printed at the end of this test
            // or after the first point of failure.
            System.out.println("Printing out deque: ");
            testing.printDeque();
        }
    }



    @Test
    public void testsArrayAddLastResizeNPlus1() {
        System.out.println("Running test before resize to ensure proper operation.");
        ArrayDeque testing = new ArrayDeque();

        try {
            testing.addLast(0);
            testing.addLast(1);
            testing.addLast(2);
            testing.addLast(3);
            testing.addLast(4);
            testing.addLast(5);
            testing.addLast(6);
            testing.addLast(7);
            testing.addLast(8);

            assertEquals(0, testing.get(0));
            assertEquals(1, testing.get(1));
            assertEquals(2, testing.get(2));
            assertEquals(3, testing.get(3));
            assertEquals(4, testing.get(4));
            assertEquals(5, testing.get(5));
            assertEquals(6, testing.get(6));
            assertEquals(7, testing.get(7));
            assertEquals(8, testing.get(8));


            /* Check correct size and length */
            System.out.println(testing.size());
            //System.out.println(testing.length)/ /* CHECK */
            assertEquals(9, testing.size());

        } finally {
            // The deque will be printed at the end of this test
            // or after the first point of failure.
            System.out.println("Printing out deque: ");
            testing.printDeque();
        }
    }



    @Test
    public void testsArrayAddFirstResizeNPlus1() {
        System.out.println("Running test before resize to ensure proper operation.");
        ArrayDeque testing = new ArrayDeque();

        try {
            testing.addFirst(8);
            testing.addFirst(7);
            testing.addFirst(6);
            testing.addFirst(5);
            testing.addFirst(4);
            testing.addFirst(3);
            testing.addFirst(2);
            testing.addFirst(1);
            testing.addFirst(0);

            assertEquals(0, testing.get(0));
            assertEquals(1, testing.get(1));
            assertEquals(2, testing.get(2));
            assertEquals(3, testing.get(3));
            assertEquals(4, testing.get(4));
            assertEquals(5, testing.get(5));
            assertEquals(6, testing.get(6));
            assertEquals(7, testing.get(7));
            assertEquals(8, testing.get(8));

            /* Check correct size and length */
            System.out.println(testing.size());
            //System.out.println(testing.length);/ /* CHECK */
            assertEquals(9, testing.size());

        } finally {
            // The deque will be printed at the end of this test
            // or after the first point of failure.
            System.out.println("Printing out deque: ");
            testing.printDeque();
        }
    }


    @Test
    public void testsArrayResizeUpThenDownAddFirst() {
        System.out.println("Running test before resize to ensure proper operation.");
        ArrayDeque testing = new ArrayDeque();


        try {
            for (int i = 0; i < 1000; i++) {
                testing.addFirst(i);
            }

            /* Check correct size and length */
            System.out.println(testing.size());

            System.out.println(testing.size()); /* CHECK */

            assertEquals(1000, testing.size());

            /*Removes 900... check for downsize */
            for (int i = 0; i < 900; i++) {
                testing.removeFirst();
            }
            System.out.println(testing.size());
            assertEquals(100, testing.size());

        } finally {
            // The deque will be printed at the end of this test
            // or after the first point of failure.
            System.out.println("Printing out deque: ");
            testing.printDeque();
        }
    }


    @Test
    public void testsArrayResizeUpThenDownAddLast() {
        System.out.println("Running test before resize to ensure proper operation.");
        ArrayDeque testing = new ArrayDeque();


        try {
            for (int i = 0; i < 1000; i++) {
                testing.addLast(i);
            }

            /* Check correct size and length */
            System.out.println(testing.size());
            assertEquals(1000, testing.size());

            for (int i = 0; i < 900; i++) {
                testing.removeLast();
            }
            System.out.println(testing.size());
            assertEquals(100, testing.size());

        } finally {
            // The deque will be printed at the end of this test
            // or after the first point of failure.
            System.out.println("Printing out deque: ");
            testing.printDeque();
        }
    }


    @Test
    public void testsArrayResizeUpThenDownCombination() {
        System.out.println("Running test before resize to ensure proper operation.");
        ArrayDeque testing = new ArrayDeque();
        try {
            for (int i = 0; i < 1000; i++) {
                if (i % 2 == 0) {
                    testing.addFirst(i);
                } else {
                    testing.addLast(i);
                }
            }

            System.out.println("print after additions");
            testing.printDeque();

            /* Check correct size and length */
            System.out.println(testing.size());
            assertEquals(1000, testing.size());



            /*Removes 900... check for downsize */
            for (int i = 0; i < 900; i++) {
                if (i % 2 == 0) {
                    testing.removeFirst();
                } else {
                    testing.removeLast();
                }
            }

            System.out.println("print after removals");
            testing.printDeque();

            System.out.println(testing.size());
            assertEquals(100, testing.size());


        } finally {
            // The deque will be printed at the end of this test
            // or after the first point of failure.
            System.out.println("Printing out deque: ");
            testing.printDeque();
        }
    }



    /* More tests */

    @Test
    public void addFirstContinuous() {
        System.out.println("Running test before resize to ensure proper operation.");
        ArrayDeque testing = new ArrayDeque();

        try {
            testing.addFirst(24);
            testing.addFirst(23);
            testing.addFirst(22);
            testing.addFirst(21);
            testing.addFirst(20);
            testing.addFirst(19);
            testing.addFirst(18);
            testing.addFirst(17);
            testing.addFirst(16);
            testing.addFirst(15);
            testing.addFirst(14);
            testing.addFirst(13);
            testing.addFirst(12);
            testing.addFirst(11);
            testing.addFirst(10);
            testing.addFirst(9);
            testing.addFirst(8);
            testing.addFirst(7);
            testing.addFirst(6);
            testing.addFirst(5);
            testing.addFirst(4);
            testing.addFirst(3);
            testing.addFirst(2);
            testing.addFirst(1);
            testing.addFirst(0);

            assertEquals(0, testing.get(0));
            assertEquals(1, testing.get(1));
            assertEquals(2, testing.get(2));
            assertEquals(3, testing.get(3));
            assertEquals(4, testing.get(4));
            assertEquals(5, testing.get(5));
            assertEquals(6, testing.get(6));
            assertEquals(7, testing.get(7));
            assertEquals(8, testing.get(8));
            assertEquals(9, testing.get(9));
            assertEquals(10, testing.get(10));
            assertEquals(11, testing.get(11));
            assertEquals(12, testing.get(12));
            assertEquals(13, testing.get(13));
            assertEquals(14, testing.get(14));
            assertEquals(15, testing.get(15));
            assertEquals(16, testing.get(16));
            assertEquals(17, testing.get(17));
            assertEquals(18, testing.get(18));
            assertEquals(19, testing.get(19));
            assertEquals(20, testing.get(20));
            assertEquals(21, testing.get(21));
            assertEquals(22, testing.get(22));
            assertEquals(23, testing.get(23));
            assertEquals(24, testing.get(24));


            /* Check correct size and length */
            System.out.println(testing.size());
            //System.out.println(testing.length);/ /* CHECK */
            assertEquals(25, testing.size());

        } finally {
            // The deque will be printed at the end of this test
            // or after the first point of failure.
            System.out.println("Printing out deque: ");
            testing.printDeque();
        }
    }


    @Test
    public void addLastContinuous() {
        System.out.println("Running test before resize to ensure proper operation.");
        ArrayDeque testing = new ArrayDeque();

        try {
            testing.addLast(0);
            testing.addLast(1);
            testing.addLast(2);
            testing.addLast(3);
            testing.addLast(4);
            testing.addLast(5);
            testing.addLast(6);
            testing.addLast(7);
            testing.addLast(8);
            testing.addLast(9);
            testing.addLast(10);
            testing.addLast(11);
            testing.addLast(12);
            testing.addLast(13);
            testing.addLast(14);
            testing.addLast(15);
            testing.addLast(16);
            testing.addLast(17);
            testing.addLast(18);
            testing.addLast(19);
            testing.addLast(20);
            testing.addLast(21);
            testing.addLast(22);
            testing.addLast(23);
            testing.addLast(24);

            assertEquals(0, testing.get(0));
            assertEquals(1, testing.get(1));
            assertEquals(2, testing.get(2));
            assertEquals(3, testing.get(3));
            assertEquals(4, testing.get(4));
            assertEquals(5, testing.get(5));
            assertEquals(6, testing.get(6));
            assertEquals(7, testing.get(7));
            assertEquals(8, testing.get(8));
            assertEquals(9, testing.get(9));
            assertEquals(10, testing.get(10));
            assertEquals(11, testing.get(11));
            assertEquals(12, testing.get(12));
            assertEquals(13, testing.get(13));
            assertEquals(14, testing.get(14));
            assertEquals(15, testing.get(15));
            assertEquals(16, testing.get(16));
            assertEquals(17, testing.get(17));
            assertEquals(18, testing.get(18));
            assertEquals(19, testing.get(19));
            assertEquals(20, testing.get(20));
            assertEquals(21, testing.get(21));
            assertEquals(22, testing.get(22));
            assertEquals(23, testing.get(23));
            assertEquals(24, testing.get(24));


            /* Check correct size and length */
            System.out.println(testing.size());
            //System.out.println(testing.length);/ /* CHECK */
            assertEquals(25, testing.size());

        } finally {
            // The deque will be printed at the end of this test
            // or after the first point of failure.
            System.out.println("Printing out deque: ");
            testing.printDeque();
        }
    }


    @Test
    public void testingResize() {
        System.out.println("Running resize/downsize test.");
        ArrayDeque testing = new ArrayDeque();
        int[] arr = new int[100];

        try {
            testing.addLast(1);
            testing.addLast(2);
            testing.addLast(3);
            testing.addLast(4);
            testing.addLast(5);
            testing.addLast(6);
            testing.addLast(7);
            testing.addLast(8);
            testing.printDeque();
            assertEquals(7, testing.get(6));
            System.out.println("this is item 6: " + testing.get(6));
            testing.addFirst(0);
        } finally {
            // The deque will be printed at the end of this test
            // or after the first point of failure.
            System.out.println("Printing out deque: ");
            testing.printDeque();
        }
    }

    @Test
    public void testResizebig() {
        ArrayDeque testing = new ArrayDeque();
        for (int i = 0; i < 100; i++) {
            testing.addFirst(i);
        }

        for (int i = 0; i < 51; i++) {
            if (i % 2 == 0) {
                testing.removeLast();
            } else {
                testing.removeFirst();
            }
        }
        testing.printDeque();
        System.out.println(testing.get(99));
    }

    @Test
    public void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        ArrayDeque<String> ad1 = new ArrayDeque<>();

        // Java will try to run the below code.
        // If there is a failure, it will jump to the finally block before erroring.
        // If all is successful, the finally block will also run afterwards.
        try {
            assertTrue(ad1.isEmpty());
            ad1.addFirst("front");
            //System.out.println("this is the item " + ad1.get(0));
            assertEquals(1, ad1.size());
            assertFalse(ad1.isEmpty());

            ad1.addLast("middle");
            assertEquals(2, ad1.size());

            ad1.addLast("back");
            assertEquals(3, ad1.size());
            System.out.println("finished try");
        } finally {
            // The deque will be printed at the end of this test
            // or after the first point of failure.
            System.out.println("Printing out deque: ");
            ad1.printDeque();
        }
    }

    /**
     * Adds an item, then removes an item, and ensures that deque is empty afterwards.
     */
    @Test
    public void addRemoveTest() {
        System.out.println("Running add/remove test.");
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();

        try {
            assertTrue(ad1.isEmpty());

            ad1.addFirst(10);
            assertFalse(ad1.isEmpty());

            ad1.removeFirst();


            assertTrue(ad1.isEmpty());
        } finally {
            System.out.println("Printing out deque: ");
            ad1.printDeque();
        }
    }


    @Test
    public void randomfuzz() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();
        for (int n = 0; n < 2000000; n++) {
            double x = Math.round(Math.random());
            if (x == 0) {
                double y = Math.round(Math.random());
                if (y == 0) {
                    double a = (Math.random() * 10);
                    ad1.addFirst((int) a);
                    lld1.addFirst((int) a);
                } else {
                    double a = (Math.random() * 10);
                    ad1.addLast((int) a);
                    lld1.addLast((int) a);
                }
            } else {
                double y = Math.round(Math.random());
                if (y == 0) {
                    ad1.removeFirst();
                    lld1.removeFirst();
                } else {
                    ad1.removeLast();
                    lld1.removeLast();
                }
            }
        }
        assertEquals(lld1.get(0), ad1.get(0));
    }


    @Test
    public void testingWithOneItem() {
        ArrayDeque testing = new ArrayDeque();
        testing.addFirst(1);
        testing.removeFirst();
    }

    @Test
    public void manuallyResize() {
        ArrayDeque testing = new ArrayDeque();
        for (int i = 0; i < 1000; i++) {
            testing.addFirst(i);
        }

        for (int i = 0; i < 9; i++) {
            if (i % 2 == 0) {
                System.out.println("should be five");
                testing.removeFirst();
            } else {
                System.out.println("should be four");
                testing.removeLast();
            }
        }
        System.out.println("helo");
        testing.addFirst("benjamin");
        testing.printDeque();
    }
}
