import java.util.*;

public class MyHashMap<K, V> implements Map61B<K, V> {

    private class Entry<K, V>{
        K key;
        V value;
        Entry (K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private int size;
    private double factor;
    private LinkedList<Entry<K, V>>[] table;

    public MyHashMap() {
        capacity = 16;
        factor = 0.75;
        size = 0;
        table = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity];
    }

    public MyHashMap(int size) {
        this.capacity = size;
        this.factor = 0.75;
        this.size = 0;
        table = (LinkedList<Entry<K, V>>[]) new LinkedList[size];
    }

    public MyHashMap(int size, double factor) {
        this.capacity = size;
        this.factor = factor;
        this.size = 0;
        table = (LinkedList<Entry<K, V>>[]) new LinkedList[size];
    }

    private int hash(K key, int capacity) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        size = 0;
        table = (LinkedList<Entry<K, V>>[]) new LinkedList[capacity];
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException();
        }
        int ind = hash(key, capacity);
        if (table[ind] == null) {
            return null;
        }
        for (Entry<K, V> e : table[ind]) {
            if (key.equals(e.key)) {
                return e.value;
            }
        }
        return null;
    }



    @Override
    public int size() {
        return size;
    }

    private LinkedList<Entry<K, V>> putHelper(K key) {
        int i = hash(key, capacity);
        LinkedList<Entry<K, V>> e = table[i];
        if (e == null) {
            e = new LinkedList<>();
            table[i] = e;
        }
        return e;
    }

    @Override
    public void put(K key, V value) {
        if (key == null || value == null) {
            throw new IllegalArgumentException();
        }

        int ind = hash(key, capacity);
        LinkedList<Entry<K, V>> Entry = putHelper(key);
        for (Entry<K, V> e : Entry) {
            if (key.equals(e.key)) {
                e.value = value;
                table[ind] = Entry;
                if (factor * capacity < 1.0 * size) {
                    resize();
                }
                return;
            }
        }
        Entry<K, V> e = new Entry<>(key, value);
        Entry.add(e);
        size += 1;
        table[ind] = Entry;
        if (factor * capacity < 1.0 * size) {
            resize();
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                for (Entry<K, V> e : table[i]) {
                    set.add(e.key);
                }
            }
        }
        return set;
    }

    private Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = new HashSet<>();
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                for (Entry<K, V> e : table[i]) {
                    set.add(e);
                }
            }
        }
        return set;
    }

    private void resize() {
        int newCap = capacity * 2;
        MyHashMap<K, V> newTable = new MyHashMap<>(newCap, factor);
        for (Entry<K, V> e : entrySet()) {
            newTable.put(e.key, e.value);
        }
        this.table = newTable.table;
        this.size = newTable.size;
        this.capacity = newTable.capacity;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }
}

