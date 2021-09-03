
import org.junit.Test;
import org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


public class TESTINGTTT {

        @Test
        public void testingMerge() {
            DLList a = new DLList();
            a.addFirst(2);
            a.addFirst(5);
            a.addFirst(3);
            a.addFirst(7);
            a.addFirst(10);
            DLList b = a.insertionSort();

            System.out.println(b);


            DLList T = new DLList();
            T.addLast(2);
            T.addLast(3);
            T.addLast(5);
            T.addLast(7);
            T.addLast(10);


            //assertEquals(T.mergeSort(), a.mergeSort());
        }

    }
