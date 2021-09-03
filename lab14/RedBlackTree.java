public class RedBlackTree<T extends Comparable<T>> {

    /* Root of the tree. */
    RBTreeNode<T> root;

    /* Creates an empty RedBlackTree. */
    public RedBlackTree() {
        root = null;
    }

    /* Creates a RedBlackTree from a given BTree (2-3-4) TREE. */
    public RedBlackTree(BTree<T> tree) {
        Node<T> btreeRoot = tree.root;
        root = buildRedBlackTree(btreeRoot);
    }

    /* Builds a RedBlackTree that has isometry with given 2-3-4 tree rooted at
       given node R, and returns the root node. */
    RBTreeNode<T> buildRedBlackTree(Node<T> r) {
        if (r == null) {
            return null;
        }
        if (r.getItemCount() == 1) {
            RBTreeNode to_return = new RBTreeNode(true, r.getItemAt(0));
            if (r.getChildrenCount() == 0) {
                return to_return;
            }
            else {
                to_return.left = buildRedBlackTree(r.getChildAt(0));
                to_return.right = buildRedBlackTree(r.getChildAt(1));
                return to_return;
            }
        } else if (r.getItemCount() == 2) {
            RBTreeNode to_return = new RBTreeNode(true, r.getItemAt(1));
            to_return.left = new RBTreeNode(false, r.getItemAt(0));
            if (r.getChildrenCount() == 0) {
                return to_return;
            } else {
                to_return.right = buildRedBlackTree(r.getChildAt(2));
                to_return.left.left = buildRedBlackTree(r.getChildAt(0));
                to_return.left.right = buildRedBlackTree(r.getChildAt(1));
                return to_return;
            }
        } else {
            RBTreeNode to_return = new RBTreeNode(true, r.getItemAt(1));
            to_return.left = new RBTreeNode(false, r.getItemAt(0));
            to_return.right = new RBTreeNode(false, r.getItemAt(2));
            if (r.getChildrenCount() == 0) {
                return to_return;
            } else {
                to_return.left.left = buildRedBlackTree(r.getChildAt(0));
                to_return.left.right = buildRedBlackTree(r.getChildAt(1));
                to_return.right.left = buildRedBlackTree(r.getChildAt(2));
                to_return.right.right = buildRedBlackTree(r.getChildAt(3));
                return to_return;
            }
        }
    }

    /* Flips the color of NODE and its children. Assume that NODE has both left
       and right children. */
    void flipColors(RBTreeNode<T> node) {
        node.isBlack = !node.isBlack;
        node.left.isBlack = !node.left.isBlack;
        node.right.isBlack = !node.right.isBlack;
    }

    /* Rotates the given node NODE to the right. Returns the new root node of
       this subtree. */
    RBTreeNode<T> rotateRight(RBTreeNode<T> node) {
        RBTreeNode saved = new RBTreeNode(false, node.item, node.left.right, node.right);
        node = new RBTreeNode(node.isBlack, node.left.item, node.left.left, saved);
        return node;
    }

    /* Rotates the given node NODE to the left. Returns the new root node of
       this subtree. */
    RBTreeNode<T> rotateLeft(RBTreeNode<T> node) {
        RBTreeNode saved = new RBTreeNode(false, node.item, node.left, node.right.left);
        node = new RBTreeNode(node.isBlack, node.right.item, saved, node.right.right);
        return node;
    }

    public void insert(T item) {   
        root = insert(root, item);  
        root.isBlack = true;    
    }

    private RBTreeNode<T> insert(RBTreeNode<T> node, T item) {
        //cuando llegas al ultimo leaf y es null tienes que insertar el nuevo item creando un nuevo node.
        if (node == null) {
          node = new RBTreeNode(false, item);
      }
        // caso de que el inseert ya esta
        if (item.compareTo(node.item) == 0) {
            return node;
        // caso que el item es mayor al node, vas para la derecha
        } else if (item.compareTo(node.item) > 0) {
            node.right = insert(node.right, item);
        // caso que el item es menor al node, vas a la izquierda
        } else {
            node.left = insert(node.left, item);
        }
        //spec 2.c
        if (isRed(node.right) && isRed(node.left) == false) {
            node = rotateLeft(node);
        }
        // spec 2.b
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        // spec 2.a
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    /* Returns whether the given node NODE is red. Null nodes (children of leaf
       nodes are automatically considered black. */
    private boolean isRed(RBTreeNode<T> node) {
        return node != null && !node.isBlack;
    }

    static class RBTreeNode<T> {

        final T item;
        boolean isBlack;
        RBTreeNode<T> left;
        RBTreeNode<T> right;

        /* Creates a RBTreeNode with item ITEM and color depending on ISBLACK
           value. */
        RBTreeNode(boolean isBlack, T item) {
            this(isBlack, item, null, null);
        }

        /* Creates a RBTreeNode with item ITEM, color depending on ISBLACK
           value, left child LEFT, and right child RIGHT. */
        RBTreeNode(boolean isBlack, T item, RBTreeNode<T> left,
                   RBTreeNode<T> right) {
            this.isBlack = isBlack;
            this.item = item;
            this.left = left;
            this.right = right;
        }
    }

}
