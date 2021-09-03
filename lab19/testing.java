import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class testing {
    @Test
    public void testtt() {
        Graph g = new Graph(5);
        g.addEdge(0, 2, 1);
        g.addEdge(2,4, 0);
        g.addEdge(1, 3, 0);
        g.addEdge(2, 3, 0);
        //g.addEdge(3,1, 0);

        assertTrue("true", g.pathExists(0, 4));
        assertTrue("true", g.isAdjacent(1, 3));
        assertFalse("false", g.isAdjacent(0, 3));

        Graph small = new Graph(2);
        small.addEdge(0, 1, 0);
        assertTrue("true", small.pathExists(0, 1));
        List<Integer> a = new ArrayList<Integer>();
        a.add(0);
        a.add(1);
        assertThat(small.path(0, 1), is(a));
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(2);
        expected.add(4);
        g.addEdge(4, 1, 0);
        expected.add(1);

        assertThat(g.path(0, 1), is(expected));

        Graph f = new Graph(10);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i != j) {
                    f.addEdge(i, j, 0);
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.println("this is i: " + i);
                System.out.println("this is j: " + j);
                if (i != j) {
                    f.path(i, j);
                }
            }
        } 
    }

    @Test
    public void test_shortest() {
        Graph g = new Graph(5);
        g.addEdge(0, 2, 1);
        g.addEdge(2,4, 3);
        g.addEdge(1, 3, 5);
        g.addEdge(2, 3, 1);
        g.addEdge(1, 0, 1);

        List l = g.shortestPath(1, 3);
        List expected = new ArrayList();
        expected.add(1);
        expected.add(0);
        expected.add(2);
        expected.add(3);
        assertEquals(expected, l);
    }

    @Test
    public void test_shortest2() {
        Graph g = new Graph(4);
        g.addEdge(0, 2, 6);
        g.addEdge(0, 1, 1);
        g.addEdge(1, 2, 2);
        g.addEdge(1, 3, 5);
        g.addEdge(2, 3, 5);

        List l = g.shortestPath(0, 3);
        List expected = new ArrayList();
        expected.add(0);
        expected.add(1);
        expected.add(3);

        assertEquals(expected, l);

        ///////
        Graph h = new Graph(4);
        h.addUndirectedEdge(0, 2, 6);
        h.addUndirectedEdge(0, 1, 1);
        h.addUndirectedEdge(1, 2, 2);
        h.addUndirectedEdge(1, 3, 5);
        h.addUndirectedEdge(2, 3, 5);

        List n = h.shortestPath(0, 3);
        List exp = new ArrayList();
        exp.add(0);
        exp.add(1);
        exp.add(3);

        assertEquals(exp, n);
    }
}
