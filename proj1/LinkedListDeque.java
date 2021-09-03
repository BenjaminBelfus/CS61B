public class LinkedListDeque<T> implements Deque<T> {

    private class Node {
        private T item;
        private Node previous;
        private Node next;

        private Node(T currentItem, Node prev, Node ne) {
            item = currentItem;
            previous = prev;
            next = ne;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.previous = sentinel;
        sentinel.next = sentinel;
    }

    @Override
    public void addFirst(T item) {
        Node firstNode = sentinel.next;
        Node newFirstNode = new Node(item, sentinel, firstNode);
        sentinel.next = newFirstNode;
        newFirstNode.next.previous = newFirstNode;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node lastNode = sentinel.previous;
        Node newLastNode = new Node(item, lastNode, sentinel);
        sentinel.previous = newLastNode;
        newLastNode.previous.next = newLastNode;
        size += 1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        if (this.isEmpty()) {
            System.out.println("");
        } else  {
            Node pointer = sentinel.next;
            String toPrint = pointer.item + " ";
            while (pointer.next != sentinel) {
                pointer = pointer.next;
                toPrint += pointer.item + " ";
            }
            System.out.println(toPrint);
        }
    }

    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        } else {
            Node toremove = sentinel.next;
            Node newFirstnode = sentinel.next.next;
            sentinel.next = newFirstnode;
            newFirstnode.previous = sentinel;
            toremove.next = null;
            toremove.previous = null;
            size -= 1;
            return toremove.item;
        }
    }

    @Override
    public T removeLast() {
        if (sentinel.previous == sentinel) {
            return null;
        } else {
            Node toremove = sentinel.previous;
            Node newLastnode = sentinel.previous.previous;
            sentinel.previous = newLastnode;
            newLastnode.next = sentinel;
            toremove.next = null;
            toremove.previous = null;
            size -= 1;
            return toremove.item;
        }
    }

    @Override
    public T get(int index) {
        Node pointer = sentinel.next;
        if (index > size || index < 0) {
            return null;
        }
        for (int i = 0; i < index; i++) {
            pointer = pointer.next;
        }
        if (pointer.item == null) {
            return null;
        } else {
            return pointer.item;
        }
    }

    public T getRecursive(int index) {
        Node pointer = sentinel.next;
        if (index > size || index < 0) {
            return null;
        }
        if (index == 0) {
            return pointer.item;
        } else {
            return helperMethod(pointer, index);
        }
    }

    private T helperMethod(Node n, int index) {
        if (index == 0) {
            return n.item;
        } else {
            n = n.next;
            return helperMethod(n, index - 1);
        }
    }
}
