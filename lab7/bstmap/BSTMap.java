package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable, V> implements Map61B {

    int size = 0;
    BSTNode<K, V> root;

    /* Constructor */
    private class BSTNode<K, V> {
        private K key;
        private V value;
        private BSTNode<K, V> left;
        private BSTNode<K, V> right;
        BSTNode(K key, V value, BSTNode<K, V> left, BSTNode<K, V> right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }

    /* Removes all the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /* Helper method of containsKey by using recursion */
    private boolean containsKeyFromNode(K key, BSTNode<K, V> node){
        if (node == null) {
            return false;
        }
        else if (key.compareTo(node.key) == 0) {
            return true;
        }
        else if (key.compareTo(node.key) < 0) {
            return containsKeyFromNode(key, node.left);
        }
        else if (key.compareTo(node.key) > 0) {
            return containsKeyFromNode(key, node.right);
        }
        return false;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(Object key) {
        return containsKeyFromNode((K) key, root);
    }

    /* Helper method of get by using recursion */
    private Object getValueFromNode(K key, BSTNode<K, V> node){
        if (node == null) {
            return null;
        }
        else if (key.compareTo(node.key) == 0) {
            return node.value;
        }
        else if (key.compareTo(node.key) < 0) {
            return getValueFromNode(key, node.left);
        }
        else if (key.compareTo(node.key) > 0) {
            return getValueFromNode(key, node.right);
        }
        return null;
    }

    /* Returns the value to which the specified key is mapped, or null if no mapping*/
    @Override
    public Object get(Object key) {
        return getValueFromNode((K) key, root);
    }

    @Override
    public int size() {
        return size;
    }

    /* Helper method of put by using recursion */
    private BSTNode putFromNode(K key, V value, BSTNode<K, V> node) {
        if (node == null) {
            size++;
            return new BSTNode(key, value, null, null);
        }
        if (key.compareTo(node.key) < 0) {
            node.left = putFromNode(key, value, node.left);
        }
        else if (key.compareTo(node.key) > 0) {
            node.right = putFromNode(key, value, node.right);
        }
        else {
            node.value = value;
        }
        return node;
    }

    /* Inserts the specified value with the specified key in this map. */
    @Override
    public void put(Object key, Object value) {
        root = putFromNode((K)key, (V)value, root);
    }

    /* Helper method of print by using recursion */
    private void printSubtreeFromNode(BSTNode<K, V> node){
        if (node == null) {
            return;
        }
        printSubtreeFromNode(node.left);
        System.out.print("[" + node.key + ":" + node.value + "] ");
        printSubtreeFromNode(node.right);
    }

    /* Prints key and value pair in order*/
    public void printInOrder(){
        printSubtreeFromNode(root);
        System.out.println();
    }

    @Override
    public Set keySet() {
        throw new UnsupportedOperationException("Unsupported operation.");
    }

    @Override
    public Object remove(Object key) {
        throw new UnsupportedOperationException("Unsupported operation.");
    }

    @Override
    public Object remove(Object key, Object value) {
        throw new UnsupportedOperationException("Unsupported operation.");
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Unsupported operation.");
    }

    public static void main(String[] args) {
        BSTMap<Integer, String> map = new BSTMap<Integer, String>();
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        map.put(4, "four");
        map.put(5, "five");
        map.clear();
        map.printInOrder();
        System.out.println(map.containsKey(3));
        System.out.println(map.containsKey(6));
        System.out.println(map.get(2));
    }
}
