package dataStructures;

import exceptions.*;

public class Heap<K extends Comparable<K>, V> implements IHeap<K, V>, IPriorityQueue<K, V> {

    /*
     * La pareja se inicia así:
     * Node<Integer, Cat> node1 = new Node<>(firstCat.getAge(), firstCat)
     * 
     * El arreglo genérico se inicia así:
     * Node<Integer, Cat>[] array = new Node[10]
     * array = (Node<Integer, Cat>[]) array
     * 
     * Para asiganr valores en el arreglo:
     * array[0] = node1
     * 
     */

    private Node<K, V>[] array;
    private int heapSize;

    /**
     * Se inicializa con un array anteriormente creado, y su heapSize es igual a la
     * cantidad de elementos que van a hacer parte del arbol a trabajar
     * 
     * @param array
     */
    public Heap(int sizeArray) {
        array = (Node<K, V>[]) new Node[sizeArray];
        int heSize = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                heSize++;
            }
        }
        this.heapSize = heSize;
    }

    /**
     * Devuelve el índice del hijo izquierdo de un nodo i.
     * 
     * @param i Índice del nodo.
     * @return Índice del hijo izquierdo de i.
     */
    public int getLeft(int i) {
        if (i == 0) {
            return 1;
        }
        return 2 * i;
    }

    /**
     * Devuelve el índice del hijo derecho de un nodo i.
     * 
     * @param i Índice del nodo.
     * @return Índice del hijo derecho de i.
     */
    public int getRight(int i) {
        if (i == 0) {
            return 2;
        }
        return 2 * i + 1;
    }

    /**
     * Devuelve el índice del padre de un nodo i.
     * 
     * @param i Índice del nodo.
     * @return Índice del padre de i.
     */
    public int getParent(int i) {
        if (i == 0) {
            return 0;
        }
        return Math.floorDiv(i, 2);
    }

    /**
     * Implementación del método maxHeapify de la interfaz IHeap. Acomoda los nodos
     * en el arreglo genérico para que se cumpla la propiedad de max heap. Recibe el
     * arreglo y el índice del nodo a acomodar.
     * 
     * @param array Arreglo genérico de nodos.
     * @param i     Índice del nodo a acomodar.
     */
    @Override
    public void maxHeapify(Node<K, V>[] array, int i) {

        int left = getLeft(i);
        int right = getRight(i);
        int largest = i;

        if (left < heapSize && array[left].getKey().compareTo(array[i].getKey()) > 0) {
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

    /**
     * Implementación del método minHeapify de la interfaz IHeap. Acomoda los nodos
     * en el arreglo genérico para que se cumpla la propiedad de min heap.Recibe el
     * arreglo y el índice del nodo a acomodar.
     * 
     * @param array Arreglo genérico de nodos.
     * @param i     Índice del nodo a acomodar.
     */
    @Override
    public void minHeapify(Node<K, V>[] array, int i) {

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
    public void buildMaxHeap(Node<K, V>[] array) {

        for (int i = Math.floorDiv(array.length, 2); i >= 0; i--) {
            maxHeapify(array, i);
        }
    }

    // Revisar documentación en la interfaz
    @Override
    public void buildMinHeap(Node<K, V>[] array) {

        for (int i = Math.floorDiv(array.length, 2); i >= 0; i--) {
            minHeapify(array, i);
        }
    }

    // Revisar documentación en la interfaz
    @Override
    public void heapSortMinToMax(Node<K, V>[] array) {
        for (int i = heapSize - 1; i >= 1; i--) {
            swap(0, i);
            heapSize--;
            maxHeapify(array, 0);
        }
    }

    // Revisar documentación en la interfaz
    @Override
    public void heapSortMaxToMin(Node<K, V>[] array) {
        for (int i = heapSize - 1; i >= 1; i--) {
            swap(0, i);
            heapSize--;
            minHeapify(array, 0);
        }
    }

    /**
     * Intercambia los nodos en los índices i y j del arreglo genérico.
     * 
     * @param i Índice del primer nodo a intercambiar.
     * @param j Índice del segundo nodo a intercambiar.
     */
    public void swap(int index1, int index2) {
        Node<K, V> temp = new Node<K, V>(array[index1].getKey(), array[index1].getValue());
        Node<K, V> temp2 = new Node<K, V>(array[index2].getKey(), array[index2].getValue());
        array[index1] = temp2;
        array[index2] = temp;
    }

    /**
     * @return Node<K, V>[] return the array
     */
    public Node<K, V>[] getArray() {
        return array;
    }

    /**
     * @param array the array to set
     */
    public void setArray(Node<K, V>[] array) {
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
    public Node<K, V> heapExtracMax(Node<K, V>[] array) throws HeapUnderflow {

        if (heapSize <= 0) {
            throw new HeapUnderflow("Heap underflow");
        } else {
            Node<K, V> max = new Node<>(array[0].getKey(), array[0].getValue());
            array[0] = array[heapSize - 1];
            --heapSize;
            maxHeapify(array, 0);
            return max;

        }

    }

    @Override
    public Node<K, V> heapExtracMin(Node<K, V>[] array) throws HeapUnderflow {
        if (heapSize <= 0) {
            throw new HeapUnderflow("Heap underflow");
        } else {
            Node<K, V> min = new Node<>(array[0].getKey(), array[0].getValue());
            array[0] = array[heapSize - 1];
            array[heapSize - 1] = null;
            --heapSize;
            minHeapify(array, 0);
            return min;
        }
    }

    @Override
    public void heapIncreaseKey(Node<K, V>[] array, int i, K key) throws KeyIsSmaller {

        if (key.compareTo(array[i].getKey()) < 0) {
            throw new KeyIsSmaller("New key is smaller than current key");
        } else {
            array[i].setKey(key);
            while (i > 0 && array[getParent(i)].getKey().compareTo(array[i].getKey()) < 0) {
                swap(i, getParent(i));
                i = getParent(i);

            }
        }

    }

    @Override
    public void heapDecreaseKey(Node<K, V>[] array, int i, K key) throws KeyIsBigger {

        if (key.compareTo(array[i].getKey()) > 0) {
            throw new KeyIsBigger("New key is bigger than current key");
        } else {
            array[i].setKey(key);
            while (i > 0 && array[getParent(i)].getKey().compareTo(array[i].getKey()) > 0) {
                swap(i, getParent(i));
                i = getParent(i);
            }
        }

    }

    @Override
    public Node<K, V> heapMaximun(Node<K, V>[] array) {
        if (heapSize == 0) {
            return null;
        }
        return array[0];
    }

    @Override
    public Node<K, V> heapMinimun(Node<K, V>[] array) {
        if (heapSize == 0) {
            return null;
        }
        return array[0];
    }

    @Override
    public void maxHeapInsert(Node<K, V>[] array, Node<K, V> node) throws KeyIsSmaller {

        array[heapSize] = node;
        heapSize++;
        heapIncreaseKey(array, heapSize - 1, node.getKey());

    }

    @Override
    public void minHeapInsert(Node<K, V>[] array, Node<K, V> node) throws KeyIsBigger {

        array[heapSize] = node;
        heapSize++;
        heapDecreaseKey(array, heapSize - 1, node.getKey());

    }

}
