package dataStructures;

public interface IStack<K extends Comparable<K>,V> {
    /**
     * Verifica si la pila está vacía 
     * @return
     */
    public boolean isEmpty();
    /**
     * Añade un nodo al inicio de la pila
     * @param node
     * @throws StackOverflowError
     */
    public void push(Node<K,V> node) throws StackOverflowError;
    /**
     * Obtiene al primer nodo de la pila
     * @return
     * @throws StackOverflowError
     */
    public V top() throws StackOverflowError;
    /**
     * Obtiene y elimina al primer nodo de la pila
     * @throws StackOverflowError
     */
    public void pop() throws StackOverflowError;
}
