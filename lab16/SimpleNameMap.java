import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

public class SimpleNameMap {

    private LinkedList<Entry>[] data;

    public SimpleNameMap() {
        data = (LinkedList<Entry>[]) new Object[10];
    }
    /* Returns the number of items contained in this map. */

    public int size() {
        int size = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null) {
                size = size + 1;
            }
        }
        return size;
    }

    public int h(String name) {
        return Math.floorMod(name.hashCode(), data.length);
    }

    /* Returns true if the map contains the KEY. */
    public boolean containsKey(String key) {
        int ind = h(key);
        LinkedList<Entry> b = (LinkedList<Entry>) data[ind];
        int bsize = b.size();
        for (int i = 0; i < bsize; i++) {
            Entry entry = b.get(i);
            if (entry.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /* Returns the value for the specified KEY. If KEY is not found, return
       null. */
    public String get(String key) {
        if (!(containsKey(key))) {
            return null;
        }
        int ind = h(key);
        LinkedList<Entry> b = (LinkedList<Entry>) data[ind];
        int bsize = b.size();
        for (int i = 0; i < bsize; i++) {
            Entry e = b.get(i);
            if (e.key.equals(key)) {
                return e.value;
            }
        }
        return null;
    }

    /* Puts a (KEY, VALUE) pair into this map. If the KEY already exists in the
       SimpleNameMap, replace the current corresponding value with VALUE. */
    public void put(String key, String value) {
        int ind = h(key);
        if (containsKey(key)) {
            LinkedList<Entry> b = (LinkedList<Entry>) data[ind];
            int bsize = b.size();
            for (int i = 0; i < bsize; i++) {
                Entry e = b.get(i);
                if (e.key.equals(key)) {
                    e.value = value;
                }
            }
        } else {
            LinkedList<Entry> b = (LinkedList<Entry>) data[ind];
            Entry newE = new Entry(key, value);
            if (calculateLoad(size() + 1) > 0.75) {
                resize();
            }
            b.add(newE);
        }
    }


    /* Removes a single entry, KEY, from this table and return the VALUE if
       successful or NULL otherwise. */
    public String remove(String key) {
        String to_return = null;
        int ind = h(key);
        LinkedList<Entry> b = (LinkedList<Entry>) data[ind];
        int bsize = b.size();
        for (int i = 0; i < bsize; i++) {
            Entry e = b.get(i);
            if (e.key.equals(key)) {
                to_return = e.value;
                b.remove(i);
            }
        }
        return to_return;
    }

    public void resize() {
        LinkedList<Entry>[] prior = data;
        int prior_size = data.length;
        data = (LinkedList<Entry>[]) new Object[prior_size * 2];
        for (int i = 0; i < prior_size; i++) {
            LinkedList<Entry> b = (LinkedList<Entry>) prior[i];
            for (Entry e : b) {
                put(e.key, e.value);
            }
        }
    }

    public double calculateLoad(int size) {
        int dsize = data.length;
        return ((size) / dsize);
    }

    private static class Entry {
        private String key;
        private String value;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        /* Returns true if this key matches with the OTHER's key. */
        public boolean keyEquals(Entry other) {
            return key.equals(other.key);
        }

        /* Returns true if both the KEY and the VALUE match. */
        @Override
        public boolean equals(Object other) {
            return (other instanceof Entry
                    && key.equals(((Entry) other).key)
                    && value.equals(((Entry) other).value));
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
}