package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.*
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author Mandy Zhou
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private final HashSet<K> keys;
    private final double loadFactor;
    private int kvCount;

    /** Constructors */
    public MyHashMap() {
        this(16, 0.75);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.buckets = new Collection[initialSize];
        this.loadFactor = maxLoad;
        this.kvCount = 0;
        this.keys = new HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    @Override
    public void clear() {
        this.buckets = new Collection[16];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = createBucket();
        }
        keys.clear();
        kvCount = 0;
    }

    /* Helper method of containsKey and put to get bucket */
    private Collection<Node> getBucket(K key) {
        int hashCode = key.hashCode();
        int index = Math.floorMod(hashCode, buckets.length);
        Collection<Node> bucket = buckets[index];
        return bucket;
    }

    @Override
    public boolean containsKey(K key) {
        Collection<Node> bucket = getBucket(key);
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        Collection<Node> bucket = getBucket(key);
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return kvCount;
    }

    /* if not and then compare key with each node key*/
    @Override
    public void put(K key, V value) {
        Collection<Node> bucket = getBucket(key);
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        kvCount++;
        keys.add(key);
        bucket.add(createNode(key, value));
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }

    @Override
    public V remove(K key) {
        Collection<Node> bucket = getBucket(key);
        for (Node node : bucket) {
            if (node.key.equals(key)) {
                bucket.remove(node);
                return node.value;
            }
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        Collection<Node> bucket = getBucket(key);
        for (Node node : bucket) {
            if (node.key.equals(key) && node.value.equals(value)) {
                bucket.remove(node);
                return node.value;
            }
        }
        return null;
    }
}
