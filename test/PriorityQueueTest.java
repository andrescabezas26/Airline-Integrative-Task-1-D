package test;

import static org.junit.Assert.*;

import org.junit.*;

import dataStructures.*;
import exceptions.*;

public class PriorityQueueTest {
    private Heap<Integer, String> priorityQueue;
    private Heap<Integer, String> priorityQueueEmpty;

    void setUp() {
        priorityQueue = new Heap<>(100);
    }

    void setUpHeapEmpty() {
        priorityQueueEmpty = new Heap<>(0);
    }

    // Test HeapMaximun

    @Test
    public void testHeapMaximun1() {
        setUpHeapEmpty();
        assertNull(priorityQueueEmpty.heapMaximun(priorityQueueEmpty.getArray()));
    }

    @Test
    public void testHeapMaximun2() throws KeyIsSmaller {
        setUp();
        Node<Integer, String> node = new Node<Integer, String>(1, "one");
        priorityQueue.maxHeapInsert(priorityQueue.getArray(), node);
        assertEquals(node, priorityQueue.heapMaximun(priorityQueue.getArray()));
    }

    @Test
    public void testHeapMaximun3() throws KeyIsSmaller {
        setUp();
        Node<Integer, String>[] array = new Node[4];
        array[0] = new Node<Integer, String>(2, "two");
        array[1] = new Node<Integer, String>(4, "four");
        array[2] = new Node<Integer, String>(1, "one");
        array[3] = new Node<Integer, String>(3, "three");

        for (int i = 0; i < array.length; i++) {
            priorityQueue.maxHeapInsert(priorityQueue.getArray(), array[i]);
        }
        assertEquals(4, priorityQueue.heapMaximun(priorityQueue.getArray()).getKey().intValue());
    }

    // Test ExtractMax

    @Test(expected = HeapUnderflow.class)
    public void testHeapExtractMax1() throws HeapUnderflow {
        setUpHeapEmpty();
        priorityQueueEmpty.heapExtracMax(priorityQueueEmpty.getArray());
    }

    @Test
    public void testHeapExtractMax2() throws HeapUnderflow, KeyIsSmaller {
        setUp();
        Node<Integer, String>[] array = new Node[1];
        array[0] = new Node<Integer, String>(1, "one");
        priorityQueue.maxHeapInsert(priorityQueue.getArray(), array[0]);
        Node<Integer, String> result = priorityQueue.heapExtracMax(priorityQueue.getArray());
        assertEquals(1, result.getKey().intValue());
        assertNull(priorityQueue.getArray()[0]);
    }

    @Test
    public void testHeapExtractMax3() throws HeapUnderflow, KeyIsSmaller {
        setUp();
        Node<Integer, String>[] array = new Node[4];
        array[0] = new Node<Integer, String>(2, "two");
        array[1] = new Node<Integer, String>(4, "four");
        array[2] = new Node<Integer, String>(1, "one");
        array[3] = new Node<Integer, String>(3, "three");
        for (int i = 0; i < array.length; i++) {
            priorityQueue.maxHeapInsert(priorityQueue.getArray(), array[i]);
        }
        Node<Integer, String> result = priorityQueue.heapExtracMax(priorityQueue.getArray());
        assertEquals(4, result.getKey().intValue());
    }

    // Test IncreaseKey

    @Test(expected = KeyIsSmaller.class)
    public void testHeapIncreaseKey1() throws KeyIsSmaller {
        setUp();
        Node<Integer, String>[] array = new Node[1];
        priorityQueue.maxHeapInsert(priorityQueue.getArray(), array[0]);
        array[0] = new Node<Integer, String>(1, "one");
        priorityQueue.heapIncreaseKey(array, 0, 0);
    }
    @Test
    public void testHeapIncreaseKey2() throws KeyIsSmaller {
        setUp();
        Node<Integer, String>[] array = new Node[1];
        array[0] = new Node<Integer, String>(1, "one");
        priorityQueue.heapIncreaseKey(array, 0, 1);
        assertEquals(1, array[0].getKey().intValue());
    }
    @Test
    public void testHeapIncreaseKey3() throws KeyIsSmaller {
        setUp();
        Node<Integer, String>[] array = new Node[1];
        array[0] = new Node<Integer, String>(1, "one");
        priorityQueue.heapIncreaseKey(array, 0, 2);
        assertEquals(2, array[0].getKey().intValue());
    }

    // Test HeapMinimun

    @Test
    public void testHeapMinimun1() throws KeyIsBigger {
        setUp();
        Node<Integer, String>[] array = new Node[10];
        Node<Integer, String> node1 = new Node<>(2, "Hello");
        Node<Integer, String> node2 = new Node<>(3, "World");
        priorityQueue.minHeapInsert(priorityQueue.getArray(), node1);
        priorityQueue.minHeapInsert(priorityQueue.getArray(), node2);
        assertEquals(node1, priorityQueue.heapMinimun(array));
    }
    @Test(expected = HeapUnderflow.class)
    public void testHeapMinimun2() {
        setUpHeapEmpty();
        priorityQueueEmpty.heapMinimun(priorityQueueEmpty.getArray());
    }
    @Test
    public void testHeapMinimun3() {
        Node<Integer, String>[] array = new Node[10];
        MinHeap<Integer, String> heap = new MinHeap<>(array);
        Node<Integer, String> node1 = new Node<>(2, "Hello");
        Node<Integer, String> node2 = new Node<>(2, "World");
        heap.minHeapInsert(array, node1);
        heap.minHeapInsert(array, node2);
        assertEquals(node1, heap.heapMinimun(array));
    }

    //Test heapExtracMin

    @Test
    public void testHeapExtracMinNonEmptyHeap() throws HeapUnderflow {
        Node<Integer, String>[] array = new Node[10];
        MinHeap<Integer, String> heap = new MinHeap<>(array);
        Node<Integer, String> node1 = new Node<>(2, "Hello");
        Node<Integer, String> node2 = new Node<>(3, "World");
        heap.minHeapInsert(array, node1);
        heap.minHeapInsert(array, node2);
        Node<Integer, String> min = heap.heapExtracMin(array);
        assertEquals(node1, min);
        assertEquals(node2, heap.heapMinimun(array));
    }

}