import org.junit.Test;

import java.util.PriorityQueue;

import static org.junit.Assert.*;

public class MinHeapTest {

    @Test
    public void test1() {

        /**
        MinHeap<Integer> hp = new MinHeap();
        hp.insert(1);
        hp.insert(2);
        hp.insert(3);
        hp.insert(4);
        hp.insert(5);
         */



        MinHeapPQ<String> pq = new MinHeapPQ();
        pq.insert("a", 5);
        //pq.insert("2", 2);
        //pq.insert("3", 8);
        //pq.insert("4", 9);
        //pq.insert("5", 1);
        String item = pq.poll();

        assertEquals("a", item);
    }
}
