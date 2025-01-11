package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> c;

    public MaxArrayDeque(Comparator<T> c){
        this.c = c;
    }

    public T max(){
        return max(this.c);
    }

    public T max(Comparator<T> c) {
        T maxItem = null;
        for (int i = 0; i < size(); i++){
            T item = get(i);
            if (maxItem == null) {
                maxItem = item;
            }
            else if (c.compare(item, maxItem) > 0){
                maxItem = item;
            }
        }
        return maxItem;
    }

    public static class LengthComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s1.length() - s2.length();
        }
    }

    public static class ValueComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }

    public static void main(String[] args) {
        MaxArrayDeque<String> mad = new MaxArrayDeque<>(new LengthComparator());
        mad.addFirst("ab");
        mad.addFirst("b");
        System.out.println(mad.max());
        System.out.println(mad.max(new ValueComparator()));
    }

}
