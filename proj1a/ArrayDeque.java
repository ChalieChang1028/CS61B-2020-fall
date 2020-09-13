public class ArrayDeque <T> {

    private T[] items;
    private int size, nextF, nextL;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        size = 0;
        nextF = 4;
        nextL = 5;
    }

    public void addFirst(T item) {
        if (nextF == nextL) {
            items[nextF] = item;
            T[] newA = (T[]) new Object[items.length * 2];
            for (int i = 0; i < nextF; i++) {
                newA[i] = items[i];
            }
            for (int i = nextF; i < items.length; i++){
                newA[i + items.length] = items[i];
            }
            nextF += items.length - 1;
            items = newA;
        } else {
            items[nextF] = item;
            nextF = (nextF - 1 + items.length) % items.length;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (nextF == nextL) {
            items[nextL] = item;
            T[] newA = (T[]) new Object[items.length * 2];
            for (int i = 0; i <= nextF; i++) {
                newA[i] = items[i];
            }
            for (int i = nextF+1; i < items.length; i++){
                newA[i + items.length] = items[i];
            }
            nextF += items.length;
            nextL += 1;
            items = newA;
        } else {
            items[nextL] = item;
            nextL = (nextL + 1) % items.length;
        }
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(items[(i+nextF+1+items.length)%items.length] + " ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        nextF = (1 + nextF) % items.length;
        int newlen = items.length/2 + 1;
        T ans = items[nextF];

        if (items.length >= 16 && size < items.length/4.0) {
            T[] newA = (T[]) new Object[newlen];
            for (int i = nextF+1; i < nextF+size+1; i++) {
                newA[i%newlen] = items[i% items.length];
            }
            nextF %= newlen;
            nextL %= newlen;
            items = newA;
        } else {
            items[nextF] = null;
        }
        return ans;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size -= 1;
        nextL = (nextL - 1 + items.length) % items.length;
        int newlen = items.length/2 + 1;
        T ans = items[nextL];

        if (items.length >= 16 && size < items.length/4.0) {
            T[] newA = (T[]) new Object[newlen];
            for (int i = nextF+1; i < nextF+size+1; i++) {
                newA[i%newlen] = items[i% items.length];
            }
            nextF %= newlen;
            nextL %= newlen;
            items = newA;
        } else {
            items[nextL] = null;
        }
        return ans;
    }

    public T get(int index) {
        if (index > size) {
            return null;
        }
        return items[(index+nextF+1) % items.length];
    }

}
