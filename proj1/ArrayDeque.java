public class ArrayDeque<T> implements Deque<T> {

    private T[] arr;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        arr = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    private void resize(int capacity) {
        T[] newarr = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newarr[i] = this.get(i);
        }
        arr = newarr;
        nextFirst = arr.length - 1;
        nextLast = size;
    }


    @Override
    public void addFirst(T item) {
        if (size == arr.length) {
            resize(size * 2);
        }
        if (nextFirst < 0) {
            nextFirst = (arr.length - 1);
        }
        arr[nextFirst] = item;
        size += 1;
        nextFirst = ((nextFirst - 1) % arr.length);
    }

    @Override
    public void addLast(T item) {
        if (size == arr.length) {
            resize(size * 2);
        }
        arr[nextLast] = item;
        size += 1;
        nextLast = ((nextLast + 1) % arr.length);
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int toremove;
        if (nextFirst == (arr.length - 1)) {
            toremove = 0;
            nextFirst = toremove;
        } else {
            toremove = nextFirst + 1;
            nextFirst = toremove;
        }
        T toreturn = arr[toremove];
        arr[toremove] = null;
        size -= 1;
        if (size < arr.length * 0.25 && arr.length >= 16) {
            resize((int) (arr.length * 0.5));
        }
        return toreturn;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int toremove;
        if (nextLast == 0) {
            toremove = arr.length - 1;
            nextLast = toremove;
        } else {
            toremove = nextLast - 1;
            nextLast = toremove;
        }
        T toreturn = arr[toremove];
        arr[toremove] = null;
        size -= 1;
        if (size < arr.length * 0.25 && arr.length >= 16) {
            resize((int) (arr.length * 0.5));
        }
        return toreturn;
    }

    @Override
    public T get(int index) {
        if ((index < 0) || (index >= size)) {
            return null;
        }
        return arr[(nextFirst + 1 + index) % arr.length];
    }
}
