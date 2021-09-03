package bearmaps.utils.ps;

import java.util.List;


public class KDTree implements PointSet {
    private Node root;

    private class Node {
        private Point point;
        private Boolean node_indicator;
        private Node left;
        private Node right;

        public Node(Point p, boolean n_indicator) {
            this.point = p;
            this.node_indicator = n_indicator;
        }
    }


    public KDTree(List<Point> points) {
        for (Point p : points) {
            root = add(p, root, false);
        }
    }


    private Node add(Point pt, Node nd, boolean ni) {
        if (nd == null) {
            return new Node(pt, ni);
        }
        int pt_comparator = point_comparator(pt, nd.point, ni);

        if (pt_comparator == 0) {
            return nd;
        }
        else if (pt_comparator < 0) {
            nd.left = add(pt, nd.left, !ni);
            return nd;
        } else {
            nd.right = add(pt, nd.right, !ni);
            return nd;
        }
    }

    private int point_comparator(Point miche, Point benja, boolean semaforo) {
        if (semaforo) {
            return Double.compare(miche.getY(), benja.getY());
        } else {
            return Double.compare(miche.getX(), benja.getX());

        }
    }


    @Override
    public Point nearest(double x, double y) {
        Point target = new Point(x, y);
        Node toReturn= nearest_helper_fxn(root, target, root);
        return toReturn.point;
    }



    private Node nearest_helper_fxn(Node n, Point p, Node best_found) {
        if (n == null) {
            return best_found;
        }
        if (Point.distance(p, best_found.point) > Point.distance(p, n.point)) {
            best_found = n;
        }

        int pt_comparator = point_comparator(p, n.point, n.node_indicator);
        Node gs_child;
        Node bs_child;

        if (pt_comparator >= 0) {
            gs_child = n.right;
            bs_child = n.left;
        } else {
            gs_child = n.left;
            bs_child = n.right;
        }

        if (gs_child != null) {
            best_found = nearest_helper_fxn(gs_child, p, best_found);
        }

        if (bs_child != null) {
            Point best_possible;
            if (!n.node_indicator) {
                best_possible = new Point(n.point.getX(), p.getY()); //comparing on x -> get x value of node and use y value of actual point
            } else {
                best_possible = new Point(p.getX(), n.point.getY()); //comparing on y -> get y value of node and use x value of actual point
            }
            if (Point.distance(p, best_found.point) > Point.distance(p, best_possible)) {
                best_found = nearest_helper_fxn(bs_child, p, best_found);
            }
        }
        return best_found;
    }
}