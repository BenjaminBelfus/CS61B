import java.util.Iterator;
import java.util.LinkedList;

public class HashMap<K, V> implements Map61BL<K, V> {

    private LinkedList<Entry>[] data;

    private int capacity;

    private double lf;

    public HashMap() {
        data = (LinkedList<Entry>[]) new LinkedList[16];
        this.capacity = 16;
        this.lf = 0.75;
    }

    public HashMap(int capacity) {
        data = (LinkedList<Entry>[]) new LinkedList[capacity];
        this.capacity = capacity;
        this.lf = 0.75;
    }

    public HashMap(int capacity, double loadFactor) {
        data = (LinkedList<Entry>[]) new LinkedList[capacity];
        this.capacity = capacity;
        this.lf = loadFactor;
    }

    public int size() {
        int size = 0;
        int dl = data.length;
        for (int i = 0; i < dl; i++) {
            if (data[i] != null) {
                int d_i_size = data[i].size();
                for (int j = 0; j < d_i_size; j++) {
                    if (data[i].get(j).key != null) {
                        if (data[i].get(j).value != null) {
                            size++;
                        }
                    }
                }
            }
        }
        return size;
    }

    public boolean containsKey(K key) {
        int ind = h(key);
        if (data[ind] != null) {
            int ds = data[ind].size();
            for (int i = 0; i < ds; i++) {
                Entry<K, V> e = data[ind].get(i);
                if (e.key != null) {
                    if (e.key.equals(key)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public V get(K key) {
        if (!containsKey(key)) {
            return null;
        }
        int ind = h(key);
        for (int i = 0; i < data[ind].size(); i++) {
            Entry<K, V> e = data[ind].get(i);
            if (e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }


    public void put(K key, V value) {
        int index = h(key);
        if (containsKey(key)) {
            int ds = data[index].size();
            for (int i = 0; i < ds; i++) {
                Entry<K, V> e = data[index].get(i);
                if (e.key.equals(key)) {
                    e.value = value;
                }
            }
        } else {
            Entry<K, V> newE = new Entry<K, V>(key, value);
            if (calculateLoad(size() + 1) > lf) {
                resize();
            }

            if (data[index] == null) {
                data[index] = new LinkedList<Entry>();
                data[index].addFirst(newE);
            } else {
                data[index].addLast(newE);
            }
        }
    }

    public V remove(K key) {
        V to_return = null;
        int ind = h(key);
        for (int i = 0; i < data[ind].size(); i++) {
            Entry<K, V> e = data[ind].get(i);
            if (e.key.equals(key)) {
                to_return = e.value;
                data[ind].remove(i);
            }
        }
        return to_return;
    }

    public int h(K key) {
        return Math.floorMod(key.hashCode(), capacity());
    }

    public void resize() {
        LinkedList<Entry>[] prior = data;
        int pl = prior.length;
        capacity = capacity * 2;
        data = (LinkedList<Entry>[]) new LinkedList[capacity];
        for (int i = 0; i < pl; i++) {
            if (prior[i] != null) {
                for (Entry<K, V> e: prior[i]) {
                    put(e.key, e.value);
                }
            }
        }
    }

    public double calculateLoad(int size) {
        int dl = data.length;
        return (size / dl);
    }

    /* Removes all of the mappings from this map. */
    public void clear() {
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                for (int j = 0; j < data[i].size(); j++) {
                    Entry<K, V> e = data[i].get(j);
                    e.key = null;
                    e.value = null;
                }
            }
        }
    }

    public boolean remove(K key, V value) {
        int ind = h(key);
        int ds = data[ind].size();
        for (int i = 0; i < ds; i++) {
            Entry<K, V> e = data[ind].get(i);
            if (e.key.equals(key) && e.value.equals(value)) {
                data[ind].remove(i);
                return true;
            }
        }
        return false;
    }

    public int capacity() {
        return capacity;
    }

    public Iterator<K> iterator() throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Invalid operation for sorted list.");
    }

    private static class Entry<K, V> {
        private K key;
        private V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public boolean keyEquals(Entry<K, V> other) {
            return key.equals(other.key);
        }

        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry && key.equals(((Entry<K, V>) other).key) && value.equals(((Entry<K, V>) other).value));
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
}