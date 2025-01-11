package deque;


//set classes or methods and remember to add comments to private if necessary
public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node (T item, Node next, Node prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }
    private final Node sentinel;
    private int size;

    //creates an empty linked list deque
    public LinkedListDeque() {
        this.sentinel = new Node(null, null, null);
        this.sentinel.next = this.sentinel;
        this.sentinel.prev = this.sentinel;
        this.size = 0;
    }

    //circular sentinel approach
    @Override
    public void addFirst(T x){
        Node n = new Node(x, null, null);
        n.next = this.sentinel.next;
        n.prev = this.sentinel;
        this.sentinel.next.prev = n;
        this.sentinel.next = n;
        size++;
    }

    //circular sentinel approach
    @Override
    public void addLast(T x){
        Node n = new Node(x, null, null);
        n.prev = this.sentinel.prev;
        n.next = this.sentinel;
        this.sentinel.prev.next = n;
        this.sentinel.prev = n;

        size++;
    }

    //circular sentinel approach
    @Override
    public T removeFirst(){
        if (size == 0)
            return null;
        Node n = this.sentinel.next;
        this.sentinel.next.next.prev = this.sentinel;
        this.sentinel.next = this.sentinel.next.next;
        size--;
        return n.item;
    }

    //circular sentinel approach
    @Override
    public T removeLast(){
        if (size == 0)
            return null;
        Node n = this.sentinel.prev;
        n.prev.next = this.sentinel;
        this.sentinel.prev = n.prev;
        size--;
        return n.item;
    }

    @Override
    public int size(){
        return this.size;
    }

    //uses iteration
    @Override
    public T get(int index) {
        if (index >= size)
            return null;
        Node getNode = this.sentinel.next;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                return getNode.item;
            }
            getNode = getNode.next;
        }
        return null;
    }

    // helper function of getRecursive considering sentinel can't be changed
    private T getRecursiveFromNode(Node n, int index){
        if (index == 0){
            return n.item;
        }
        else{
            return getRecursiveFromNode(n.next, index - 1);
        }
    }

    //uses recursion
    public T getRecursive(int index){
        Node getNodeRec = this.sentinel.next;
        if (index >= size)
            return null;
        return getRecursiveFromNode(getNodeRec, index);
    }

    // TODO
    public boolean equals(Object o){
        return true;
    }

    // TODO
    //public Iterator<T> iterator(){

    //}

    @Override
    public void printDeque() {
        Node curNode = this.sentinel.next;
        while (curNode != this.sentinel) {
            System.out.print(curNode.item + " ");
            curNode = curNode.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedListDeque<String> ll = new LinkedListDeque<>();
        ll.addFirst("a");
        ll.addFirst("b");
        ll.addLast("c");
        ll.addLast("d");
        ll.removeFirst();
        ll.removeLast();
        ll.printDeque();
        System.out.println(ll.isEmpty());
        System.out.println(ll.get(0));
        System.out.println(ll.getRecursive(4));
    }
}
