import org.junit.Test;
import static org.junit.Assert.*;

public class SLListTest {

    @Test
    public void testSLListAdd() {
        SLList test1 = SLList.of(1, 3, 5);
        SLList test2 = new SLList();

        test1.add(1, 2);
        System.out.println(test1);
        test1.add(3, 4);
        System.out.println(test1);
        assertEquals(5, test1.size());
        assertEquals(3, test1.get(2));
        assertEquals(4, test1.get(3));

        test2.add(1, 1);
        assertEquals(1, test2.get(0));
        assertEquals(1, test2.size());

        test2.add(10, 10);
        assertEquals(10, test2.get(1));
        test1.add(0, 0);
        assertEquals(SLList.of(0, 1, 2, 3, 4, 5), test1);
    }

    @Test
    public void testSLListReverse() {
        SLList test4 = SLList.of(1, 2, 3);
        SLList test5 = new SLList(5);
        SLList test6 = new SLList();

        test4.reverse();
        System.out.println(test4);
        assertEquals(3, test4.size());
        assertEquals(3, test4.get(0));
        assertEquals(2, test4.get(1));
        assertEquals(1, test4.get(2));


        test5.reverse();
        assertEquals(1, test5.size());
        assertEquals(5, test5.get(0));

        test6.reverse();
        assertEquals(0, test6.size());
    }
}
