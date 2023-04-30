package dataStructures;

import exceptions.HeapUnderflow;
import exceptions.KeyIsBigger;
import exceptions.KeyIsSmaller;

public interface IPriorityQueue<K extends Comparable<K>, V> {

    // Para colas de prioridad máximas

    /**
     * Devuelve el nodo con la clave máxima del montículo. Si el montículo está
     * vacío, devuelve null.
     *
     * @param array El array que representa el montículo.
     * @return El nodo con la clave máxima del montículo.
     */
    public Node<K, V> heapMaximun(Node<K, V>[] array);

    /**
     * 
     * Este método extrae el elemento máximo del montículo representado por el
     * arreglo dado.
     * 
     * @param array el arreglo que representa el montículo
     * 
     * @throws HeapUnderflow si el montículo está vacío
     * 
     * @return el elemento máximo en el montículo
     */
    public Node<K, V> heapExtracMax(Node<K, V>[] array) throws HeapUnderflow;

    /**
     * 
     * Este método aumenta la clave del elemento en la posición i del montículo
     * representado por el arreglo dado.
     * 
     * @param array el arreglo que representa el montículo
     * @param i     la posición del elemento cuya clave se desea aumentar
     * @param key   la nueva clave
     * @throws KeyIsSmaller si la nueva clave es menor que la clave actual
     */
    public void heapIncreaseKey(Node<K, V>[] array, int i, K key) throws KeyIsSmaller;

    /**
     * Inserta un nodo en el montículo máximo. Si la clave del nodo es más pequeña
     * que la clave del nodo máximo,
     * lanza una excepción KeyIsSmaller.
     *
     * @param array El array que representa el montículo.
     * @param node  El nodo que se va a insertar en el montículo.
     * @throws KeyIsSmaller Si la clave del nodo es más pequeña que la clave del
     *                      nodo máximo del montículo.
     */
    public void maxHeapInsert(Node<K, V>[] array, Node<K, V> node) throws KeyIsSmaller;

    // Para colas de prioridad mínimas

    /**
     * Devuelve el nodo con la clave mínima del montículo. Si el montículo está
     * vacío, devuelve null.
     *
     * @param array El array que representa el montículo.
     * @return El nodo con la clave mínima del montículo.
     */
    public Node<K, V> heapMinimun(Node<K, V>[] array);

    /**
     * 
     * Este método extrae el elemento mínimo del montículo representado por el
     * arreglo dado.
     * 
     * @param array el arreglo que representa el montículo
     * @throws HeapUnderflow si el montículo está vacío
     * @return el elemento mínimo en el montículo
     */
    public Node<K, V> heapExtracMin(Node<K, V>[] array) throws HeapUnderflow;

    /**
     * 
     * Este método disminuye la clave del elemento en la posición i del montículo
     * representado por el arreglo dado.
     * 
     * @param array el arreglo que representa el montículo
     * @param i     la posición del elemento cuya clave se desea disminuir
     * @param key   la nueva clave
     * @throws KeyIsBigger si la nueva clave es mayor que la clave actual
     */
    public void heapDecreaseKey(Node<K, V>[] array, int i, K key) throws KeyIsBigger;

    /**
     * Inserta un nodo en el montículo mínimo. Si la clave del nodo es más grande
     * que la clave del nodo mínimo,
     * lanza una excepción KeyIsBigger.
     *
     * @param array El array que representa el montículo.
     * @param node  El nodo que se va a insertar en el montículo.
     * @throws KeyIsBigger Si la clave del nodo es más grande que la clave del nodo
     *                     mínimo del montículo.
     */
    public void minHeapInsert(Node<K, V>[] array, Node<K, V> node) throws KeyIsBigger;
}
