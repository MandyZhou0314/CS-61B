package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        this.comparator = c;
    }

    public T max() {
        return max(this.comparator);
    }

    public T max(Comparator<T> c) {
        T maxItem = null;
        for (int i = 0; i < size(); i++) {
            T item = get(i);
            if (maxItem == null) {
                maxItem = item;
            } else if (c.compare(item, maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }

    private static class LengthComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s1.length() - s2.length();
        }
    }

    private static class ValueComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }

    private static class IntValueComparator implements Comparator<Integer> {
        public int compare(Integer s1, Integer s2) {
            return s1.compareTo(s2);
        }
    }

    public static void main(String[] args) {
        MaxArrayDeque<Integer> mad2 = new MaxArrayDeque<>(new IntValueComparator());
        mad2.addFirst(0);
        mad2.addFirst(1);
        mad2.addLast(2);
        System.out.println(mad2.removeLast());
    }

}
