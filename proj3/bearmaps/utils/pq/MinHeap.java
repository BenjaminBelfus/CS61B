package bearmaps.utils.pq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

/* A MinHeap class of Comparable elements backed by an ArrayList. */
public class MinHeap<E extends Comparable<E>> {

    HashMap<E, Integer> index_mapping = new HashMap<>();

    /* An ArrayList that stores the elements in this MinHeap. */
    private ArrayList<E> contents;
    private int size;
    // TODO: YOUR CODE HERE (no code should be needed here if not 
    // implementing the more optimized version)

    /* Initializes an empty MinHeap. */
    public MinHeap() {
        contents = new ArrayList<>();
        contents.add(null);

    }

    /* Returns the element at index INDEX, and null if it is out of bounds. */
    private E getElement(int index) {
        if (index >= contents.size()) {
            return null;
        } else {
            return contents.get(index);
        }
    }

    /* Sets the element at index INDEX to ELEMENT. If the ArrayList is not big
       enough, add elements until it is the right size. */
    private void setElement(int index, E element) {
        while (index >= contents.size()) {
            contents.add(null);
        }
        contents.set(index, element);
    }

    /* Swaps the elements at the two indices. */
    private void swap(int index1, int index2) {
        E element1 = getElement(index1);
        E element2 = getElement(index2);

        index_mapping.replace(element1, index2); //sets element1  -->  index2
        index_mapping.replace(element2, index1); //sets element2 --> index1

        setElement(index2, element1);
        setElement(index1, element2);

    }

    /* Prints out the underlying heap sideways. Use for debugging. */
    @Override
    public String toString() {
        return toStringHelper(1, "");
    }

    /* Recursive helper method for toString. */
    private String toStringHelper(int index, String soFar) {
        if (getElement(index) == null) {
            return "";
        } else {
            String toReturn = "";
            int rightChild = getRightOf(index);
            toReturn += toStringHelper(rightChild, "        " + soFar);
            if (getElement(rightChild) != null) {
                toReturn += soFar + "    /";
            }
            toReturn += "\n" + soFar + getElement(index) + "\n";
            int leftChild = getLeftOf(index);
            if (getElement(leftChild) != null) {
                toReturn += soFar + "    \\";
            }
            toReturn += toStringHelper(leftChild, "        " + soFar);
            return toReturn;
        }
    }

    /* Returns the index of the left child of the element at index INDEX. */
    private int getLeftOf(int index) {
        // TODO: YOUR CODE HERE
        return (index * 2);
    }

    /* Returns the index of the right child of the element at index INDEX. */
    private int getRightOf(int index) {
        // TODO: YOUR CODE HERE
        return ((index * 2) + 1);
    }

    /* Returns the index of the parent of the element at index INDEX. */
    private int getParentOf(int index) {
        // TODO: YOUR CODE HERE
        return (index / 2);
    }

    /* Returns the index of the smaller element. At least one index has a
       non-null element. If the elements are equal, return either index. */
    private int min(int index1, int index2) {
        // TODO: YOUR CODE HERE
        E one = getElement(index1);
        E two = getElement(index2);
        if (one == null) { //don't need
            return index2;
        }
        if (two == null) {
            return index1;
        }
        if ((one.compareTo(two) == 0) || (one.compareTo(two) < 0)) { //one is min
            return index1;
        }
        else {
            return index2;
        }
    }

    /* Returns but does not remove the smallest element in the MinHeap. */
    public E findMin() {
        // TODO: YOUR CODE HERE
        return getElement(1);
    }

    /* Bubbles up the element currently at index INDEX. */
    private void bubbleUp(int index) {
        // TODO: YOUR CODE HERE
        int pid = getParentOf(index);
        int cid = index;
        while (cid > 1 && (min(pid, cid) == cid)) {
            swap(cid, pid);

            cid = pid;
            pid = pid/2;
        }
    }

    /* Bubbles down the element currently at index INDEX. */
    private void bubbleDown(int index) {
        // TODO: YOUR CODE HERE
        int pid = index;
        int scid = min(getLeftOf(pid), getRightOf(pid));
        while ((min(pid, scid) == scid)) {
            swap(pid, scid);
            pid = scid;
            scid = min(getLeftOf(pid), getRightOf(pid));
        }
    }

    /* Returns the number of elements in the MinHeap. */
    public int size() {
        // TODO: YOUR CODE HERE
        return size;
    }

    /* Inserts ELEMENT into the MinHeap. If ELEMENT is already in the MinHeap,
       throw an IllegalArgumentException.*/
    public void insert(E element) {
        // TODO: YOUR CODE HERE
        if (contains(element)) {
            throw new IllegalArgumentException();
        }
        else {
            size = size + 1;
            contents.add(size, element);
            index_mapping.put(element, size);
            bubbleUp(size);
        }
    }

    /* Returns and removes the smallest element in the MinHeap. */
    public E removeMin() {
        // TODO: YOUR CODE HERE
        E to_remove = findMin();
        swap(1, (size));

        index_mapping.remove(contents.get(size)); //Removes the min, which after swap is located at index size.
        contents.remove(size);
        size = size -1;
        if (size > 1) {
            bubbleDown(1);
        }

        return to_remove;
    }

    /* Replaces and updates the position of ELEMENT inside the MinHeap, which
       may have been mutated since the initial insert. If a copy of ELEMENT does
       not exist in the MinHeap, throw a NoSuchElementException. Item equality
       should be checked using .equals(), not ==. */
    public void update(E element) {
        // TODO: YOUR CODE HERE
        if (element == null) {
            return;
        }
        if (index_mapping.containsKey(element)) {
            int index = index_mapping.get(element);
            setElement(index, element);
            bubbleDown(index);
            bubbleUp(index);
        }
        else {
            throw new NoSuchElementException();
        }
        /**
        int index_found = -1;
        if (contains(element)) {
            for (int i = 1; i <= size; i++) { //think of data structures learned in course to mke it more efficient. thurs, frid, rastering, kd trees, tries, exercises,
                // trees, hashtables
                if (contents.get(i).equals(element)) {
                    index_found = i;
                    break;
                }
            }
        } else  {
            throw new NoSuchElementException();
        }
        setElement(index_found, element);
        bubbleDown(index_found);
        bubbleUp(index_found);
         */
    }

    /* Returns true if ELEMENT is contained in the MinHeap. Item equality should
       be checked using .equals(), not ==. */
    public boolean contains(E element) {
        // TODO: YOUR CODE HERE
        if (element == null) {
            return false;
        }
        //return (contents.contains(element)); // aslo fix thisd
        return (index_mapping.containsKey(element));
    }
}
