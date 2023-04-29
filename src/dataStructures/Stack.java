package dataStructures;
/**
 * Una implementación simple de una estructura de datos de pila.
 *
 * @param <K> el tipo de claves que se mantienen en esta pila
 * @param <V> el tipo de valores que se mantienen en esta pila
 */
public class Stack<K extends Comparable<K>, V> implements IStack<K, V> {
    
    /**
     * El primer nodo de la pila.
     */
    private Node<K, V> top;

    /**
     * Crea una pila vacía.
     */
    public Stack() {
        this.top = null;
    }

    /**
     * Devuelve verdadero si esta pila está vacía, falso en caso contrario.
     *
     * @return verdadero si esta pila está vacía, falso en caso contrario
     */
    @Override
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Inserta el nodo especificado en la parte superior de esta pila.
     *
     * @param node el nodo a insertar en esta pila
     * @throws StackOverflowError si la pila está llena
     */
    @Override
    public void push(Node<K, V> node) throws StackOverflowError {
        
        if (!isEmpty()) {
            node.setNext(top);
        }

        top = node;
    }

    /**
     * Devuelve el valor del nodo en la parte superior de esta pila sin eliminarlo,
     * o null si esta pila está vacía.
     *
     * @return el valor del nodo en la parte superior de esta pila, o null si esta pila está vacía
     * @throws StackOverflowError si la pila está llena
     */
    @Override
    public V top() throws StackOverflowError {
        return top.getValue();
    }

    /**
     * Elimina el nodo en la parte superior de esta pila.
     *
     * @throws StackOverflowError si la pila está vacía
     */
    @Override
    public void pop() throws StackOverflowError {
        top = top.getNext();
    }
}
