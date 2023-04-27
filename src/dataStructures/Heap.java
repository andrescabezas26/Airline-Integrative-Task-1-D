package dataStructures;

import java.util.ArrayList;

import exceptions.*;

public class Heap<K extends Comparable<K>, T> implements IMonticulo<K, T>, IPriorityQueue<K, T> {

    /*
     * La pareja se inicia así:
     * Couple<Integer, Cat> couple1 = new Couple<>(firstCat.getAge(), firstCat)
     * 
     * El arreglo genérico se inicia así:
     * Couple<Integer, Cat>[] array = new Couple[10]
     * array = (Couple<Integer, Cat>[]) array
     * 
     * Para asiganr valores en el arreglo:
     * array[0] = couple1
     * 
     */

    private Couple<K, T>[] array;
    private int heapSize;
    
    /**
     * Se inicializa con un array anteriormente creado, y su heapSize es igual a la
     * cantidad de elementos que van a hacer parte del arbol a trabajar
     * 
     * @param array
     */
    public Heap(int sizeArray) {
        array = (Couple<K, T>[]) new Couple[sizeArray];
        int heSize = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                heSize++;
            }
        }
        this.heapSize = heSize;
    }

    /**
     * Hijo izquierdo(Flamini)
     * 
     * @param i
     * @return
     */
    public int getLeft(int i) {
        if (i == 0) {
            return 1;
        }
        return 2 * i;
    }

    /**
     * Hijo derecho(Oscar)
     * 
     * @param i
     * @return
     */
    public int getRight(int i) {
        if (i == 0) {
            return 2;
        }
        return 2 * i + 1;
    }

    public int getParent(int i) {
        if (i == 0) {
            return 0;
        }
        return Math.floorDiv(i, 2);
    }

    // Revisar documentación en la interfaz
    @Override
    public void maxHeapify(Couple<K, T>[] array, int i) {

        int left = getLeft(i);
        int right = getRight(i);
        int largest = i;

        if (left < heapSize &&  array[left].getKey().compareTo(array[i].getKey()) > 0) {
            largest = left;
        }
        if (right < heapSize && array[right].getKey().compareTo(array[largest].getKey()) > 0) {
            largest = right;
        }
        if (largest != i) {

            swap(i, largest);
            maxHeapify(array, largest);
        }
    }

    // Revisar documentación en la interfaz
    @Override
    public void minHeapify(Couple<K, T>[] array, int i) {

        int left = getLeft(i);
        int right = getRight(i);
        int smallest = i;

        if (left < heapSize && array[left].getKey().compareTo(array[i].getKey()) < 0) {
            smallest = left;
        }
        if (right < heapSize && array[right].getKey().compareTo(array[smallest].getKey()) < 0) {
            smallest = right;
        }
        if (smallest != i) {

            swap(i, smallest);
            minHeapify(array, smallest);
        }
    }

    // Revisar documentación en la interfaz
    @Override
    public void buildMaxHeap(Couple<K, T>[] array) {

        for (int i = Math.floorDiv(array.length, 2); i >= 0; i--) {
            maxHeapify(array, i);
        }
    }

    // Revisar documentación en la interfaz
    @Override
    public void buildMinHeap(Couple<K, T>[] array) {

        for (int i = Math.floorDiv(array.length, 2); i >= 0; i--) {
            minHeapify(array, i);
        }
    }

    // Revisar documentación en la interfaz
    @Override
    public void heapSortMinToMax(Couple<K, T>[] array) {
        for (int i = heapSize - 1; i >= 1; i--) {
            swap(0, i);
            heapSize--;
            maxHeapify(array, 0);
        }
    }

    // Revisar documentación en la interfaz
    @Override
    public void heapSortMaxToMin(Couple<K, T>[] array) {
        for (int i = heapSize - 1; i >= 1; i--) {
            swap(0, i);
            heapSize--;
            minHeapify(array, 0);
        }
    }

    /**
     * Intercambia la posición entre dos elementos dados
     * 
     * @param index1
     * @param index2
     */
    public void swap(int index1, int index2) {
        Couple<K, T> temp = new Couple<K, T>(array[index1].getKey(), array[index1].getObject());
        Couple<K, T> temp2 = new Couple<K, T>(array[index2].getKey(), array[index2].getObject());
        array[index1] = temp2;
        array[index2] = temp;
    }

    public String toString() {
        String msj = "[ ";
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                msj += array[i].getKey() + " ";
            }
        }
        return msj + "]";
    }

    /**
     * @return Couple<K, T>[] return the array
     */
    public Couple<K, T>[] getArray() {
        return array;
    }

    /**
     * @param array the array to set
     */
    public void setArray(Couple<K, T>[] array) {
        this.array = array;
    }

    /**
     * @return int return the heapSize
     */
    public int getHeapSize() {
        return heapSize;
    }

    /**
     * @param heapSize the heapSize to set
     */
    public void setHeapSize(int heapSize) {
        this.heapSize = heapSize;
    }

    @Override
    public Couple<K, T> heapExtracMax(Couple<K, T>[] array) throws HeapUnderflow {

        try {

            if (heapSize < 1) {
                throw new HeapUnderflow("Heap underflow");
            }
        } catch (HeapUnderflow e) {
            e.printStackTrace();
        }
        
        Couple<K, T> max = new Couple<>(array[1].getKey(), array[1].getObject());
        array[0] = array[heapSize];
        --heapSize;
        maxHeapify(array, 0);
        return max;
    }

    @Override
    public Couple<K, T> heapExtracMin(Couple<K, T>[] array) {
        try {

            if (heapSize < 1) {
                throw new HeapUnderflow("Heap underflow");
            }
        } catch (HeapUnderflow e) {
            e.printStackTrace();
        }
        Couple<K, T> min = new Couple<>(array[0].getKey(), array[0].getObject());
        array[0] = array[heapSize];
        heapSize--;
        maxHeapify(array, 0);
        return min;
    }

    @Override
    public void heapIncreaseKey(Couple<K, T>[] array, int i, K key) throws KeyIsSmaller {

        try {
            if (key.compareTo(array[i].getKey()) < 0) {
                throw new KeyIsSmaller("New key is smaller than current key");
            }
        } catch (KeyIsSmaller e) {
            e.printStackTrace();
        }

        array[i].setKey(key);
        if (array[getParent(i)] == null) {
            return;
        }
        while (i > 0 && array[getParent(i)].getKey().compareTo(array[i].getKey()) < 0) {
            swap(i, getParent(i));
            i = getParent(i);
            if (array[getParent(i)] == null) {
                return;
            }
        }

    }

    @Override
    public void heapDecreaseKey(Couple<K, T>[] array, int i, K key) throws KeyIsBigger {
        try {
            if (key.compareTo(array[i].getKey()) > 0) {
                throw new KeyIsBigger("New key is bigger than current key");
            }
        } catch (KeyIsBigger e) {
            e.printStackTrace();
        }

        array[i].setKey(key);
        while (i > 0 && array[getParent(i)].getKey().compareTo(array[i].getKey()) > 0) {
            swap(i, getParent(i));
            i = getParent(i);
        }
    }

    @Override
    public Couple<K, T> heapMaximun(Couple<K, T>[] array) {
        return array[0];
    }

    @Override
    public Couple<K, T> heapMinimun(Couple<K, T>[] array) {
        return array[0];
    }

    @Override
    public void maxHeapInsert(Couple<K, T>[] array, Couple<K, T> couple) throws KeyIsSmaller {

        heapSize++;
        array[heapSize] = couple;
        heapIncreaseKey(array, heapSize, couple.getKey());

    }

    @Override
    public void minHeapInsert(Couple<K, T>[] array, Couple<K, T> couple) throws KeyIsBigger {
        heapSize++;
        array[heapSize] = couple;
        heapDecreaseKey(array, heapSize, couple.getKey());

    }

}
