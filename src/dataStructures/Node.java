package dataStructures;
/**
 * Esta clase representa un nodo de una estructura de datos doblemente enlazada. 
 * @param <K> tipo de dato para la clave del nodo, debe ser comparable.
 * @param <V> tipo de dato para el valor del nodo.
 */
public class Node<K extends Comparable<K>, V> {
    
    private K key; //clave del nodo.
    private V value; //valor del nodo.

    private Node<K, V> next; //referencia al siguiente nodo.
    private Node<K, V> previous; //referencia al nodo anterior.

    /**
     * Constructor de la clase Node. Inicializa la clave y el valor del nodo y establece las referencias a null.
     * @param key clave del nodo.
     * @param value valor del nodo.
     */
    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
        this.previous = null;
    }

    /**
     * Metodo que devuelve la clave del nodo.
     * @return K la clave del nodo.
     */
    public K getKey() {
        return key;
    }

    /**
     * Metodo que establece la clave del nodo.
     * @param key la clave a establecer en el nodo.
     */
    public void setKey(K key) {
        this.key = key;
    }

    /**
     * Metodo que devuelve el valor del nodo.
     * @return V el valor del nodo.
     */
    public V getValue() {
        return value;
    }

    /**
     * Metodo que establece el valor del nodo.
     * @param value el valor a establecer en el nodo.
     */
    public void setValue(V value) {
        this.value = value;
    }

    /**
     * Metodo que devuelve la referencia al siguiente nodo.
     * @return Node<K, V> la referencia al siguiente nodo.
     */
    public Node<K, V> getNext() {
        return next;
    }

    /**
     * Metodo que establece la referencia al siguiente nodo.
     * @param next la referencia al siguiente nodo a establecer.
     */
    public void setNext(Node<K, V> next) {
        this.next = next;
    }

    /**
     * Metodo que devuelve la referencia al nodo anterior.
     * @return Node<K, V> la referencia al nodo anterior.
     */
    public Node<K, V> getPrevious() {
        return previous;
    }

    /**
     * Metodo que establece la referencia al nodo anterior.
     * @param previous la referencia al nodo anterior a establecer.
     */
    public void setPrevious(Node<K, V> previous) {
        this.previous = previous;
    }

}