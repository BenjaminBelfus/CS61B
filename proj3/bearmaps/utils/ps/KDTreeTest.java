package bearmaps.utils.ps;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.List;
import java.util.Random;

public class KDTreeTest {

    @Test
    public void test1() {
        List<Point> l = new ArrayList<Point>();
        Point p = new Point(188.3541835438, -730.3409314820);
        l.add(p);
        l.add(new Point(-36.8399405401, -815.8883292022));
        l.add(new Point(818.3340334168, 699.3291524571));

        KDTree a = new KDTree(l);

       assertEquals(p, a.nearest(250, -500) );
       //System.out.println(a.nearest(250, -500));


        assertEquals(p, a.nearest(188.3541835438, -730.3409314820) );
        //System.out.println(a.nearest(150, -900));


        List<Point> j = new ArrayList<Point>();
        Point k = new Point(188.3541835438, -730.3409314820);
        j.add(k);
        j.add(new Point(-36.8399405401, -815.8883292022));
        j.add(new Point(818.3340334168, 699.3291524571));
        KDTree q = new KDTree(j);
        NaivePointSet w = new NaivePointSet(j);

        for (int i = 0; i < 10000; i++) {
            int max = 1000;
            int min = -1000;
            Point z = new Point( Math.random() * (max - min + 1) + min,  Math.random() * (max - min + 1) + min);
            //Point np = new Point(557.0955033042, -387.4601040531);

            Point o = q.nearest(z.getX(), z.getY());
            Point g = w.nearest(z.getX(), z.getY());
            //System.out.println(g);
            //System.out.println(o);

            if (!o.equals(g)) {
                System.out.println("this is the point we are failing: " + z);
                System.out.println("expected (naive) " + g);
                System.out.println("Actual (kdtree) " + o);
                return;
            }
        }
        System.out.println("completed");
    }



}