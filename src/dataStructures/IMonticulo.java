package dataStructures;

public interface IMonticulo<K extends Comparable<K>, T> {

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
    public void maxHeapify(Couple<K, T>[] array, int i);

    /**
     * Se encarga de hacer que en cada grupo de Papá: H.Izquierdo y H.Derecho, Se
     * mantega que Papá es menor a sus hijos
     * 
     * @param array
     * @param i
     */
    public void minHeapify(Couple<K, T>[] array, int i);

    /**
     * Deja el valor maximo en la raiz
     * 
     * @param array
     */
    public void buildMaxHeap(Couple<K, T>[] array);

    /**
     * Deja el valor minimo en la raiz
     * 
     * @param array
     */
    public void buildMinHeap(Couple<K, T>[] array);

    /**
     * Organiza de < a >
     * 
     * @param array
     */
    public void heapSortMinToMax(Couple<K, T>[] array);

    /**
     * Organiza de > a <
     * 
     * @param array
     */
    public void heapSortMaxToMin(Couple<K, T>[] array);
}
