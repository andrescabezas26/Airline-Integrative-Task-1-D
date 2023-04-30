package dataStructures;

import exceptions.HeapUnderflow;
import exceptions.KeyIsBigger;
import exceptions.KeyIsSmaller;

public interface IPriorityQueue<K extends Comparable<K>, V> {

    // Para colas de prioridad máximas

    public Node<K, V> heapMaximun(Node<K, V>[] array);

    public Node<K, V> heapExtracMax(Node<K, V>[] array) throws HeapUnderflow;

    public void heapIncreaseKey(Node<K, V>[] array, int i, K key) throws KeyIsSmaller;

    public void maxHeapInsert(Node<K, V>[] array, Node<K, V> node) throws KeyIsSmaller;

    // Para colas de prioridad mínimas
    
    public Node<K, V> heapMinimun(Node<K, V>[] array);

    public Node<K, V> heapExtracMin(Node<K, V>[] array) throws HeapUnderflow;

    public void heapDecreaseKey(Node<K, V>[] array, int i, K key) throws KeyIsBigger;

    public void minHeapInsert(Node<K, V>[] array, Node<K, V> node) throws KeyIsBigger;
}
