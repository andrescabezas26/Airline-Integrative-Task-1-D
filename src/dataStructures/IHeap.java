package dataStructures;

public interface IHeap<K extends Comparable<K>, T> {

    /*
     * Toca hacer los tres seguidos, trabajan en conjunto:
     * maxHeapify
     * buildMaxHeap
     * heapSortMinToMax
     */

    /**
     * Se encarga de hacer que en cada grupo de Papá: H.Izquierdo y H.Derecho, Se
     * mantega que Papá es mayor a sus hijos
     * 
     * @param array
     * @param i
     */
    public void maxHeapify(Node<K, T>[] array, int i);

    /**
     * Se encarga de hacer que en cada grupo de Papá: H.Izquierdo y H.Derecho, Se
     * mantega que Papá es menor a sus hijos
     * 
     * @param array
     * @param i
     */
    public void minHeapify(Node<K, T>[] array, int i);

    /**
     * Deja el valor maximo en la raiz
     * 
     * @param array
     */
    public void buildMaxHeap(Node<K, T>[] array);

    /**
     * Deja el valor minimo en la raiz
     * 
     * @param array
     */
    public void buildMinHeap(Node<K, T>[] array);

    /**
     * Organiza de < a >
     * 
     * @param array
     */
    public void heapSortMinToMax(Node<K, T>[] array);

    /**
     * Organiza de > a <
     * 
     * @param array
     */
    public void heapSortMaxToMin(Node<K, T>[] array);
}
