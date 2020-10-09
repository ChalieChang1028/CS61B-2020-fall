import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class Node {
        private K key;
        private V value;
        private int size;
        private Node leftC;
        private Node rightC;

        Node(K k, V v) {
            key = k;
            value = v;
            leftC = null;
            rightC = null;
            size = 1;
        }
    }
    private Node root;

    public BSTMap() {
        root = null;
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        return gethelper(root, key);
    }

    private V gethelper(Node n, K key) {
        if (n == null) {
            return null;
        }
        if (key.compareTo(n.key) == 0) {
            return n.value;
        } else if (key.compareTo(n.key) < 0) {
            return gethelper(n.leftC, key);
        } else {
            return gethelper(n.rightC, key);
        }
    }

    @Override
    public int size() {
        if (root == null) {
            return 0;
        }
        return root.size;
    }

    private int size(Node n) {
        if (n == null) {
            return 0;
        }
        return n.size;
    }

    @Override
    public void put(K key, V value) {
        root = insert(root, key, value);
    }

    private Node insert(Node n, K key, V value) {
        if (n == null) {
            return new Node(key, value);
        }

        if (key.compareTo(n.key) == 0) {
            n.value = value;
        } else if (key.compareTo(n.key) < 0) {
            n.leftC = insert(n.leftC, key, value);
        } else {
            n.rightC = insert(n.rightC, key, value);
        }
        n.size = size(n.leftC) + size(n.rightC) + 1;
        return n;
    }

    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Not Supported");
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Not Supported");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Not Supported");
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Not Supported");
    }

    public void printInOrder() {
        if (root == null) {
            System.out.println("Empty Map");
        } else {
            printHelper(root);
        }
    }

    private void printHelper(Node n) {
        if (n.leftC == null && n.rightC == null) {
            System.out.println(n.key.toString());
        } else if (n.leftC == null && n.rightC != null) {
            System.out.println(n.key.toString());
            printHelper(n.rightC);
        } else if (n.rightC == null && n.leftC != null) {
            printHelper(n.leftC);
            System.out.println(n.key.toString());
        } else {
            printHelper(n.leftC);
            System.out.println(n.key.toString());
            printHelper(n.rightC);
        }
    }

}
