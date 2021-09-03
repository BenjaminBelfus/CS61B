import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

public class BooleanSetTest {

    @Test
    public void testBasics() {
        BooleanSet aSet = new BooleanSet(100);
        assertEquals(0, aSet.size());
        for (int i = 0; i < 100; i += 2) {
            aSet.add(i);
            assertTrue(aSet.contains(i));
        }
        assertEquals(50, aSet.size());

        for (int i = 0; i < 100; i += 2) {
            aSet.remove(i);
            assertFalse(aSet.contains(i));
        }
        assertTrue(aSet.isEmpty());
        assertEquals(0, aSet.size());
    }

    @Test
    public void testtoarray() {
        BooleanSet aSet = new BooleanSet(100);
        for (int i = 0; i < 100; i += 1) {
            aSet.add(i);
            assertTrue(aSet.contains(i));
        }
        aSet.remove(10);
        int[] p = aSet.toIntArray();
        for (int i = 0; i < aSet.size(); i++) {
            System.out.println(p[i]);
        }
        System.out.println(aSet.toIntArray());
    }
}