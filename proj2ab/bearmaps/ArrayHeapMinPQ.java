package bearmaps;
import java.util.*;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private class Node {
        private T item;
        private double priority;
        Node(T i, double p) {
            item = i;
            priority = p;
        }
    }


    private ArrayList<Node> heap;
    private HashMap<T, Integer> map;

    ArrayHeapMinPQ() {
        heap = new ArrayList<>();
        map = new HashMap<>();
    }

    @Override
    public void add(T item, double p) {
        if (contains(item)) {
            throw new IllegalArgumentException("Already exists!");
        }
        heap.add(new Node(item, p));
        map.put(item, size() - 1);
        shiftUp(size() - 1);
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean contains(T item) {
        return map.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Nothing in the PQ yet!");
        }
        return heap.get(0).item;
    }

    @Override
    public T removeSmallest() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Nothing in the PQ yet!");
        }
        T target = heap.get(0).item;
        map.remove(target);
        heap.set(0, heap.get(size() - 1));
        heap.remove(size() - 1);
        if (!heap.isEmpty()) {
            shiftDown(0);
        }
        return target;
    }

    @Override
    public void changePriority(T item, double p) {
        if (!contains(item)) {
            throw new NoSuchElementException("item no in the PQ yet!");
        }
        int i = map.get(item);
        if (p < heap.get(i).priority) {
            heap.get(i).priority = p;
            shiftUp(i);
        } else {
            heap.get(i).priority = p;
            shiftDown(i);
        }
    }

    private int parent(int i) {
        return i == 0 ? 0 : (i - 1) / 2;
    }

    private int targetChild(int i) {
        int left = i * 2 + 1 < size() ? i * 2 + 1 : i;
        int right = i * 2 + 2 < size() ? i * 2 + 2 : i;
        if (heap.get(left).priority < heap.get(right).priority) {
            return left;
        }
        return right;
    }

    private void shiftUp(int i) {
        int parent = parent(i);
        if (heap.get(i).priority < heap.get(parent).priority) {
            Node n1 = heap.get(i);
            Node n2 = heap.get(parent);
            map.put(n1.item, parent);
            map.put(n2.item, i);
            heap.set(parent, n1);
            heap.set(i, n2);
            shiftUp(parent);
        }
    }

    private void shiftDown(int i) {
        int child = targetChild(i);
        while (heap.get(i).priority > heap.get(child).priority) {
            Node n1 = heap.get(i);
            Node n2 = heap.get(child);
            map.put(n1.item, child);
            map.put(n2.item, i);
            heap.set(child, n1);
            heap.set(i, n2);
            i = child;
            child = targetChild(i);
        }
    }

}
