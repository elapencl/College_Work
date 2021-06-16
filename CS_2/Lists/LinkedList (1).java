import java.util.Iterator;
import java.util.Objects;

public class LinkedList<T> implements List<T>, Iterable<T> {

    //before doing anything in this class, we have to declare our Node containing item and next, this includes constructors
    private class Node {
        T item;
        Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }

        public Node() {
            this.item = null;
            this.next = null;
        }

    }

    //declare a node
    public LinkedList() {
        Node head = new Node();
    }

    private Node back = new Node();
    private Node head = new Node(null, back);


    //makes sure list is empty
    public boolean isEmpty() {
        return head.item == null;
    }

    //again making our object to a String
    @Override
    public String toString() {
        String result = "";
        if (head == null) return "[]";
        else {
            for (Node n = head; n.item != null; n = n.next) {
                if (n.next.item != null) result += n.item + "," + " ";
                else result += n.item;
            }
            return "[" + result + "]";
        }
    }


    //adds elements to list
    @Override
    public void add(T item) {
        if (head.item == null) head.item = item;
        else {
            back.item = item;
            Node latestBack = new Node();
            back.next = latestBack;
            back = latestBack;

        }
    }

    //clears our list
    @Override
    public void clear() {
        head = null;
    }

    //checks if list contains element given as item
    @Override
    public boolean contains(T item) {
        for (Node n = head; n.item != null; n = n.next) if (n.item == item) return true;
        return false;
    }


    //returns element on position i
    @Override
    public T get(int i) {
        int n = 0;
        for (Node node = head; node.item != null; node = node.next) {
            if (n == i) return node.item;
            n++;
        }
        return null;
    }

    //returns index of element equaling the item
    @Override
    public int indexOf(T item) {
        int n = 0;
        for (Node node = head; node.item != null; node = node.next) {
            if (node.item == item) return n;
            n++;
        }
        return -1;
    }


    //removes element at given index
    @Override
    public void removeAt(int i) {

        if (i == 0) {
            head = head.next;
            return;
        }
        Node n = head;
        for (int index = 0; index < i - 1; n = n.next, index++) continue;
        if (n.next.next != null) n.next = n.next.next;
        else n.next = null;
    }


    //replaces an item in the list with item at given index
    @Override
    public void set(int i, T item) {
        int newItem = 0;
        for (Node node = head; node.item != null; node = node.next) {
            if (newItem == i) node.item = item;
            newItem++;
        }
    }


    /**
     * Returns the number of elements in this list.
     */
    @Override
    public int size() {
        int noOfElements = 0;
        for (Node node = head; node.item != null; node = node.next) noOfElements++;
        return noOfElements;
    }

    //returns an iterator over elements of type
    @Override
    public Iterator<T> iterator() {
        return new ListIterator<T>();
    }

    class ListIterator<T> implements Iterator<T> {
        Node current = head;

        public boolean hasNext() {
            return current.next != null;
        }

        public T next() {
            T item = (T) current.item;
            current = current.next;
            return item;
        }

    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        if (this == object) {
            return true;
        }

        LinkedList that = (LinkedList) object;
        Node thatNode = that.head;
        for (Node node = head; node != null || thatNode != null; node = node.next) {
            if (((node == null) != (thatNode == null))
                    || (!Objects.equals(node.item, thatNode.item))) {
                return false;
            }
            thatNode = thatNode.next;
        }
        return true;
    }


}