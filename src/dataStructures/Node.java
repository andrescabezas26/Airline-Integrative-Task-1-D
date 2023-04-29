package dataStructures;

public class Node<K extends Comparable<K>, V> {

    private K key;
    private V value;

    private Node<K, V> next;
    private Node<K, V> previous;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.previous = null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<K, V> getNext() {
        return next;
    }

    public void setNext(Node<K, V> next) {
        this.next = next;
    }

    /**
     * @return Node<K, V> return the previous
     */
    public Node<K, V> getPrevious() {
        return previous;
    }

    /**
     * @param previous the previous to set
     */
    public void setPrevious(Node<K, V> previous) {
        this.previous = previous;
    }

}
