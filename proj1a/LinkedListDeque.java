public class LinkedListDeque<T> {

    private class Node {
        T val;
        Node next, pre;
        Node(T data) {
            val = data;
            next = pre = null;
        }
    }

    private int size = 0;
    private Node front, back;

    public LinkedListDeque() {
        front = back = null;
    }

    public void addFirst(T item) {
        if (front == null) {
            front = new Node(item);
            back = front;
        } else {
            Node newNode = new Node(item);
            front.pre = newNode;
            newNode.next = front;
            front = newNode;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (back == null) {
            front = new Node(item);
            back = front;
        } else {
            Node newNode = new Node(item);
            back.next = newNode;
            newNode.pre = back;
            back = newNode;
        }
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node tep = front;
        while (tep != null) {
            System.out.print(tep.val + " ");
            tep = tep.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Node ans = front;
        front = front.next;
        if (front != null) {
            front.pre = null;
        } else {
            back = null;
        }
        return ans.val;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        Node ans = back;
        back = back.pre;
        if (back != null) {
            back.next = null;
        } else {
            front = null;
        }
        return ans.val;
    }

    public T get(int index) {
        Node tep = front;
        while (index != 0) {
            if (tep == null) {
                return null;
            }
            tep = tep.next;
            index -= 1;
        }
        return tep.val;
    }

    private T getRhelper(int index, Node n) {
        if (index >= size || index < 0) {
            return null;
        }
        if (index == 0) {
            return n.val;
        }
        return getRhelper(index - 1, n.next);
    }

    public T getRecursive(int index) {
        return getRhelper(index, front);
    }

}
