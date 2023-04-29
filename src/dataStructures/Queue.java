package dataStructures;
/**
 * Una implementación simple de una estructura de datos de cola.
 *
 * @param <K> el tipo de las claves que se mantienen en esta cola
 * @param <V> el tipo de los valores que se mantienen en esta cola
 */
public class Queue<K extends Comparable<K>, V> implements IQueue<K, V> {
    /**
     * El primer nodo en la cola.
     */
    private Node<K, V> first;
    /**
     * El último nodo en la cola.
     */
    private Node<K, V> last;

    public Queue() {
        this.first = null;
        this.last = null;
    }
    /**
     * Devuelve true si esta cola está vacía, false en caso contrario.
     *
     * @return true si esta cola está vacía, false en caso contrario
     */
    @Override
    public boolean isEmpty() {
        if (this.first == null) {
            return true;
        }
        return false;
    }
    /**
     * Recupera y elimina la cabeza de esta cola, o devuelve null si esta
     * cola está vacía.
     *
     * @return la cabeza de esta cola, o null si esta cola está vacía
     */
    @Override
    public V poll() {
        V value=null;
        if (!isEmpty()) {
            value=first.getValue();
            if (last != null) {
                first=first.getNext();
            }
        }
        return value;
    }
    /**
     * Recupera, pero no elimina, la cabeza de esta cola, o devuelve
     * null si esta cola está vacía.
     *
     * @return la cabeza de esta cola, o null si esta cola está vacía
     */
    @Override
    public V peek() {
        return first.getValue();
    }
    /**
     * Agrega el nodo especificado al final de esta cola.
     *
     * @param node el nodo que se agregará a esta cola
     */
    @Override
    public void offer(Node<K, V> node) {
        if (!isEmpty()) {
            if (last == null) {
                last=node;
            } else {
                last.setNext(node);
                last=node;
            }
        } else {
            first = node;
            first.setNext(last);
        }
    }
}
