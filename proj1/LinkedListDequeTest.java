import org.junit.Test;
import static org.junit.Assert.*;

/** Performs some basic linked list tests. */
public class LinkedListDequeTest {

    /** Adds a few things to the deque, checking isEmpty() and size() are correct,
     * finally printing the results. */

    @Test
    public void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");


        LinkedListDeque<String> lld1 = new LinkedListDeque<>();

        // Java will try to run the below code.
        // If there is a failure, it will jump to the finally block before erroring.
        // If all is successful, the finally block will also run afterwards.
        try {
            assertTrue(lld1.isEmpty());

            lld1.addFirst("front");
            assertEquals(1, lld1.size());
            assertFalse(lld1.isEmpty());

            lld1.addLast("middle");
            assertEquals(2, lld1.size());

            lld1.addLast("back");
            assertEquals(3, lld1.size());

        } finally {
            // The deque will be printed at the end of this test
            // or after the first point of failure.
            System.out.println("Printing out deque: ");
            lld1.printDeque();
        }

    }

    /** Adds an item, then removes an item, and ensures that deque is empty afterwards. */
    @Test
    public void addRemoveTest() {
        System.out.println("Running add/remove test.");

        LinkedListDeque<Integer> lld1 = new LinkedListDeque<>();

        try {
            assertTrue(lld1.isEmpty());

            lld1.addFirst(10);
            assertFalse(lld1.isEmpty());

            lld1.removeFirst();
            assertTrue(lld1.isEmpty());
        } finally {
            System.out.println("Printing out deque: ");
            lld1.printDeque();
        }
    }

    @Test
    public void addRemoveComprehensive() {
        System.out.println("Running add first test.");
        LinkedListDeque<Double> lld1 = new LinkedListDeque<>();
        try {
            assertTrue(lld1.isEmpty());
            /*Checks correct addition */
            lld1.addFirst(10.0);
            assertFalse(lld1.isEmpty());
            assertEquals((Double) 10.0, lld1.get(0));
            /* Checks adding first 10 elements before 10, using add first*/
            lld1.addFirst(9.0);
            lld1.addFirst(8.0);
            lld1.addFirst(7.0);
            lld1.addFirst(6.0);
            lld1.addFirst(5.0);
            lld1.addFirst(4.0);
            lld1.addFirst(3.0);
            lld1.addFirst(2.0);
            lld1.addFirst(1.0);
            lld1.addFirst(0.0);
            assertEquals((Double) 0.0, lld1.get(0));
            assertEquals((Double) 10.0, lld1.get(10));
            System.out.println("Printing out deque: ");
            lld1.printDeque();
            /* Add 9 more using addlast */
            lld1.addLast(11.0);
            lld1.addLast(12.0);
            lld1.addLast(13.0);
            lld1.addLast(14.0);
            lld1.addLast(15.0);
            lld1.addLast(16.0);
            lld1.addLast(17.0);
            lld1.addLast(18.0);
            lld1.addLast(19.0);
            assertEquals((Double) 11.0, lld1.get(11));
            assertEquals((Double) 19.0, lld1.get(19));
            System.out.println("Printing out deque: ");
            lld1.printDeque();
            /* Checks for correct size after 20 additions */
            assertEquals(20, lld1.size());
            /* Removes 10 elements using removefirst, and 10 elements using removelast*/
            for (int i = 0; i < 10; i++) {
                lld1.removeFirst();
            }
            System.out.println("After removal with removefirst");
            lld1.printDeque();
            System.out.println();

            for (int i = 0; i < 10; i++) {
                lld1.removeLast();
            }
            System.out.println("After removal with removelast");
            lld1.printDeque();
            assertTrue(lld1.isEmpty());
            /* Checks for correct size after 20 removals */
            assertEquals(0, lld1.size());
            /*Tries to remove from an empty list */
            lld1.removeFirst();
            lld1.removeLast();
            System.out.println("After removal from empty");
            lld1.printDeque();
            assertTrue(lld1.isEmpty());
            /* Checks for correct size after removing from empty */
            assertEquals(0, lld1.size());
            /*Adds two elements through both methods after removing from empty */
            lld1.addFirst(0.0);
            lld1.addLast(1.0);
            assertEquals((Double) 0.0, lld1.get(0));
            assertEquals((Double) 1.0, lld1.get(1));
            System.out.println("Twin final additions: ");
            lld1.printDeque();
            /* Checks for correct size after removing from empty and having two additions */
            assertEquals(2, lld1.size());

        } finally {
            System.out.println("Printing out deque: ");
            lld1.printDeque();
        }
    }


    @Test
    public void getsAreSame() {
        System.out.println("Running both gets to chek for same behaviour.");

        LinkedListDeque<Double> lld1 = new LinkedListDeque<>();
        LinkedListDeque<Double> lld2 = new LinkedListDeque<>();

        try {
            assertTrue(lld1.isEmpty());

            lld1.addFirst(10.0);
            lld1.addFirst(9.0);
            lld1.addFirst(8.0);
            lld1.addFirst(7.0);
            lld1.addFirst(6.0);
            lld1.addFirst(5.0);
            lld1.addFirst(4.0);
            lld1.addFirst(3.0);
            lld1.addFirst(2.0);
            lld1.addFirst(1.0);
            lld1.addFirst(0.0);

            /*Checks for normal get behaviour with different indexes */
            assertEquals((Double) 0.0, lld1.get(0));
            assertEquals(lld1.get(0), lld1.getRecursive(0));

            assertEquals((Double) 6.0, lld1.get(6));
            assertEquals(lld1.get(6), lld1.getRecursive(6));

            assertEquals((Double) 7.0, lld1.get(7));
            assertEquals(lld1.get(7), lld1.getRecursive(7));

            assertEquals((Double) 10.0, lld1.get(10));
            assertEquals(lld1.get(10), lld1.getRecursive(10));

            /*Checks for correct get behaviour in edge cases */

            /* Index is greater than the size of the array */
            assertEquals(null, lld1.get(15));
            assertEquals(lld1.get(15), lld1.getRecursive(15));

            /* Index is negative */
            assertEquals(null, lld1.get(-1));
            assertEquals(lld1.get(-1), lld1.getRecursive(15));


            /* Get applied to an empty list  */
            assertTrue(lld2.isEmpty());
            assertEquals(null, lld2.get(0));
            assertEquals(lld2.get(0), lld2.getRecursive(0));


            assertEquals(null, lld2.get(2));
            assertEquals(lld2.get(2), lld2.getRecursive(2));

            assertEquals(null, lld2.get(-2));
            assertEquals(lld2.get(-2), lld2.getRecursive(-2));


        } finally {
            System.out.println("Printing out deque: ");
            lld1.printDeque();
        }
    }

    @Test
    public void printDequeTest() {
        System.out.println("Running printing deque test");

        LinkedListDeque<Double> lld1 = new LinkedListDeque<>();
        LinkedListDeque<Double> lld2 = new LinkedListDeque<>();

        try {
            assertTrue(lld1.isEmpty());

            /*Checks correct addition */
            lld1.addFirst(10.0);
            lld1.addFirst(9.0);
            lld1.addFirst(8.0);
            lld1.addFirst(7.0);
            lld1.addFirst(6.0);
            lld1.addFirst(5.0);
            lld1.addFirst(4.0);
            lld1.addFirst(3.0);
            lld1.addFirst(2.0);
            lld1.addFirst(1.0);
            lld1.addFirst(0.0);

            /*Prints after 11 additions */
            lld1.printDeque();

            lld1.addLast(11.0);
            lld1.addLast(12.0);
            lld1.addLast(13.0);
            lld1.addLast(14.0);
            lld1.addLast(15.0);
            lld1.addLast(16.0);
            lld1.addLast(17.0);
            lld1.addLast(18.0);
            lld1.addLast(19.0);

            /*Prints after 9 more additions */
            lld1.printDeque();

            /*Prints after removing the 10 elements that have just been added */
            lld1.removeLast();
            lld1.removeLast();
            lld1.removeLast();
            lld1.removeLast();
            lld1.removeLast();
            lld1.removeLast();
            lld1.removeLast();
            lld1.removeLast();
            lld1.removeLast();
            lld1.printDeque();


            //Print an empty list
            System.out.println("Empty list:");
            lld2.printDeque();

        } finally {
            System.out.println("Printing out deque: ");
            lld1.printDeque();
        }
    }
}
