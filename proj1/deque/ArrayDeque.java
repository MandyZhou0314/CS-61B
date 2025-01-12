package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int firstIndex;
    private int lastIndex;

    ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        firstIndex = 0;
        lastIndex = 1;
    }

    @Override
    public int size() {
        return this.size;
    }

    //circular approach
    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[firstIndex] = x;
        firstIndex = (firstIndex - 1 + items.length) % items.length;
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[lastIndex] = x;
        lastIndex = (lastIndex + 1) % items.length;
        size++;
    }

    // should be able to handle size increasing and decreasing
    private void resize(int cap) {
        T[] newArray = (T[]) new Object[cap];
        for (int i = 0; i < size(); i++) {
            newArray[i] = get(i);
        }
        lastIndex = size;
        items = newArray;
        firstIndex = newArray.length - 1;
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        } else if (size < items.length * 0.25 && items.length > 16) {
            resize(items.length / 3);
        }
        T x = get(0);
        firstIndex = (firstIndex + 1) % items.length;
        size--;
        return x;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        } else if (size < items.length * 0.25 && items.length > 16) {
            resize(items.length / 3);
        }
        T x = get(size - 1);
        lastIndex = (lastIndex - 1 + items.length) % items.length;
        size--;
        return x;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int arrayIndex = (firstIndex + 1 + index) % items.length;
        return items[arrayIndex];
    }

    @Override
    public void printDeque() {
        for (int i = 0; i < size(); i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        int arrayIndex;

        ArrayDequeIterator() {
            arrayIndex = 0;
        }

        @Override
        public boolean hasNext() {
            return arrayIndex < size();
        }

        @Override
        public T next() {
            T ret = get(arrayIndex);
            arrayIndex++;
            return ret;
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof ArrayDeque)) {
            return false;
        }
        ArrayDeque<T> other = (ArrayDeque<T>) o;
        if (this.size != other.size) {
            return false;
        }
        Iterator<T> iterator1 = this.iterator();
        Iterator<T> iterator2 = other.iterator();
        while (iterator1.hasNext()) {
            T t1 = iterator1.next();
            T t2 = iterator2.next();
            if (!t1.equals(t2)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private static void main(String[] args) {
        ArrayDeque<String> ad = new ArrayDeque<>();
        ad.addFirst("a");
        ad.addFirst("b");
        ad.addLast("c");
        ad.addLast("d");
        ad.addFirst("a");
        ad.addFirst("b");
        ad.addLast("c");
        ad.addLast("d");
        ad.addLast("d");
        ad.removeFirst();
        ad.removeLast();
        ad.removeFirst();
        ad.removeLast();
        ad.removeFirst();
        ad.removeLast();
        ad.removeFirst();
        ad.removeLast();
        ad.printDeque();
        ad.addFirst("a");
        ad.addFirst("b");
        ad.addLast("c");
        ad.addLast("d");
        ad.addFirst("a");
        ad.addFirst("b");
        ad.addLast("c");
        ad.addLast("d");
        System.out.println(ad.get(3));
        ad.printDeque();
        ad.removeFirst();
        ad.removeLast();
        ad.printDeque();
    }
}
