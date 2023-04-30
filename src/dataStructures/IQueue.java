package dataStructures;

public interface IQueue<K extends Comparable<K>, V> {
    /**
     * Verifica si la cola está vacía
     * 
     * @return
     */
    public boolean isEmpty();

    /**
     * Obtiene y elimina al primer nodo de la cola
     * 
     * @return
     */
    public V poll();

    /**
     * Obtiene, pero no elimina al primer nodo de la cola
     * 
     * @return
     */
    public V peek();

    /**
     * Añade un nodo al final de la cola
     * 
     * @param node
     */
    public void offer(Node<K, V> node);
}
