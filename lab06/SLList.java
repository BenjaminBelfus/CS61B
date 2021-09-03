/**
 * An SLList is a list of integers, which encapsulates the
 * naked linked list structure.
 */
public class SLList {

    /**
     * IntListNode is a nested class that represents a single node in the
     * SLList, storing an item and a reference to the next IntListNode.## MICHELE
     */
    private static class IntListNode {
        /*
         * The access modifiers inside a private nested class are irrelevant:
         * both the inner class and the outer class can access these instance
         * variables and methods.
         */
        public int item;
        public IntListNode next;

        public IntListNode(int item, IntListNode next) {
            this.item = item;
            this.next = next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            IntListNode that = (IntListNode) o;
            return item == that.item;
        }

        @Override
        public String toString() {
            return item + "";
        }

    }

    /* The first item (if it exists) is at sentinel.next. */
    private IntListNode sentinel;
    private int size;

    /**
     * Creates an empty SLList.
     */
    public SLList() {
        sentinel = new IntListNode(42, null);
        sentinel.next = sentinel;
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntListNode(42, null);
        sentinel.next = new IntListNode(x, null);
        sentinel.next.next = sentinel;
        size = 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SLList slList = (SLList) o;
        if (size != slList.size) return false;

        IntListNode l1 = sentinel.next;
        IntListNode l2 = slList.sentinel.next;

        while (l1 != sentinel && l2 != slList.sentinel) {
            if (!l1.equals(l2)) return false;
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }

    @Override
    public String toString() {
        IntListNode l = sentinel.next;
        String result = "";

        while (l != sentinel) {
            result += l + " ";
            l = l.next;
        }

        return result.trim();
    }

    /**
     * Returns an SLList consisting of the given values.
     */
    public static SLList of(int... values) {
        SLList list = new SLList();
        for (int i = values.length - 1; i >= 0; i -= 1) {
            list.addFirst(values[i]);
        }
        return list;
    }

    /**
     * Returns the size of the list.
     */
    public int size() {
        return size;
    }

    /**
     * Adds x to the front of the list.
     */
    public void addFirst(int x) {
        sentinel.next = new IntListNode(x, sentinel.next);
        size += 1;
    }

    /**
     * Return the value at the given index.
     */
    public int get(int index) {
        IntListNode p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    /**
     * Adds x to the list at the specified index.
     */
    public void add(int index, int x) {
        IntListNode p = sentinel;
        if (index > size) {
            index = size;
        }
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        p.next = new IntListNode(x, p.next);
        size += 1;
    }


    /**
     * Destructively reverses this list.
     */
    public void reverse() {
<<<<<<< HEAD

        sentinel.next = helperMethod(sentinel.next);
    }


    public IntListNode helperMethod(IntListNode n) {
        if (n.next == sentinel) {
            return n ;
        } else {
            IntListNode toReturn = helperMethod(n.next);
            n.next.next = n;
            n.next = sentinel;
            return toReturn;
        }
=======
        reversehelper(sentinel.next,0, size);
    }


    public IntListNode reversehelper(IntListNode n, int index, int size) {
        int copysize = size;
        int copyindex = index;
        IntListNode pointer = n;
        if (pointer.next == sentinel) {
            return n;
        }
        if (index == size) {
            return n;
        }
        int temp = get(index);
        int lastone = get(size - 1);
        while (copyindex != 0){
            pointer = pointer.next;
            copyindex--;
            copysize--;
        } if (pointer.next == sentinel) {
            return n;
        }
        pointer.item = lastone;
        while (copysize != 1) {
            pointer = pointer.next;
            copysize--;
        }
        pointer.item = temp;
        return reversehelper(n, index + 1, size-1);
>>>>>>> f3c9963ed69c78532359504c4497ba9b15951a8b
    }
}