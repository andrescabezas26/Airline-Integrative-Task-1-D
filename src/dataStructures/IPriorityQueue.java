package dataStructures;

import exceptions.HeapUnderflow;
import exceptions.KeyIsBigger;
import exceptions.KeyIsSmaller;

public interface IPriorityQueue<K extends Comparable<K>, T> {

    // Para colas de prioridad máximas

    public Couple<K, T> heapMaximun(Couple<K, T>[] array);

    public Couple<K, T> heapExtracMax(Couple<K, T>[] array) throws HeapUnderflow;

    public void heapIncreaseKey(Couple<K, T>[] array, int i, K key) throws KeyIsSmaller;

    public void maxHeapInsert(Couple<K, T>[] array, Couple<K, T> couple) throws KeyIsSmaller;

    // Para colas de prioridad mínimas

    public Couple<K, T> heapMinimun(Couple<K, T>[] array);

    public Couple<K, T> heapExtracMin(Couple<K, T>[] array) throws HeapUnderflow;

    public void heapDecreaseKey(Couple<K, T>[] array, int i, K key) throws KeyIsBigger;

    public void minHeapInsert(Couple<K, T>[] array, Couple<K, T> couple) throws KeyIsBigger;
}
