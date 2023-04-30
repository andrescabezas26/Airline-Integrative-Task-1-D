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

    void setUpZeroSize() {
        priorityQueueEmpty = new Heap<>(0);
    }

    // Test MaxHeapInsert

    @Test
    public void testMaxHeapInsert1() throws KeyIsSmaller {
        setUp();
        Node<Integer, String> node1 = new Node<>(1, "cat");
        Node<Integer, String> node2 = new Node<>(2, "dog");
        priorityQueue.maxHeapInsert(priorityQueue.getArray(), node1);
        priorityQueue.maxHeapInsert(priorityQueue.getArray(), node2);
        assertEquals(2, priorityQueue.heapMaximun(priorityQueue.getArray()).getKey().intValue());
    }

    @Test
    public void testMaxHeapInsert2() throws KeyIsSmaller {
        setUp();
        Node<Integer, String> node1 = new Node<>(1, "cat");
        priorityQueue.maxHeapInsert(priorityQueue.getArray(), node1);
        assertEquals(1, priorityQueue.heapMaximun(priorityQueue.getArray()).getKey().intValue());
    }

    @Test
    public void testMaxHeapInsert3() throws KeyIsSmaller {
        setUp();
        Node<Integer, String> node1 = new Node<>(1, "cat");
        Node<Integer, String> node2 = new Node<>(2, "dog");
        Node<Integer, String> node3 = new Node<>(3, "mouse");
        Node<Integer, String> node4 = new Node<>(4, "elephant");
        priorityQueue.maxHeapInsert(priorityQueue.getArray(), node1);
        priorityQueue.maxHeapInsert(priorityQueue.getArray(), node2);
        priorityQueue.maxHeapInsert(priorityQueue.getArray(), node3);
        priorityQueue.maxHeapInsert(priorityQueue.getArray(), node4);
        assertEquals(4, priorityQueue.heapMaximun(priorityQueue.getArray()).getKey().intValue());
    }

    // Test HeapMaximun

    @Test
    public void testHeapMaximun1() {
        setUpZeroSize();
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
        setUpZeroSize();
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
        Node<Integer, String> node = new Node<Integer, String>(1, "one");
        priorityQueue.maxHeapInsert(priorityQueue.getArray(), node);
        priorityQueue.heapIncreaseKey(priorityQueue.getArray(), 0, 0);
    }

    @Test
    public void testHeapIncreaseKey2() throws KeyIsSmaller {
        setUp();
        Node<Integer, String> node = new Node<Integer, String>(1, "one");
        priorityQueue.maxHeapInsert(priorityQueue.getArray(), node);

        Node<Integer, String> node2 = new Node<Integer, String>(2, "two");
        priorityQueue.maxHeapInsert(priorityQueue.getArray(), node2);

        assertEquals(2, priorityQueue.getArray()[0].getKey().intValue());
    }

    @Test
    public void testHeapIncreaseKey3() throws KeyIsSmaller, HeapUnderflow {
        setUp();
        Node<Integer, String>[] array = new Node[5];
        array[0] = new Node<Integer, String>(1, "one");
        array[1] = new Node<Integer, String>(2, "two");
        array[2] = new Node<Integer, String>(3, "three");
        array[3] = new Node<Integer, String>(4, "four");
        array[4] = new Node<Integer, String>(6, "six");

        for (int i = 0; i < array.length; i++) {
            priorityQueue.maxHeapInsert(priorityQueue.getArray(), array[i]);
        }

        assertEquals(6, priorityQueue.getArray()[0].getKey().intValue());
        priorityQueue.maxHeapInsert(priorityQueue.getArray(), new Node<Integer, String>(7, "seven"));
        assertEquals(7, priorityQueue.getArray()[0].getKey().intValue());
        priorityQueue.maxHeapInsert(priorityQueue.getArray(), new Node<Integer, String>(5, "five"));
        assertEquals(7, priorityQueue.getArray()[0].getKey().intValue());
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////// MinimunPriorityQueue///////////////////////////////////////////

    // Test MinHeapInsert

    @Test
    public void testMinHeapInsert1() throws KeyIsBigger {
        setUp();
        Node<Integer, String> node1 = new Node<>(1, "cat");
        Node<Integer, String> node2 = new Node<>(2, "dog");
        priorityQueue.minHeapInsert(priorityQueue.getArray(), node1);
        priorityQueue.minHeapInsert(priorityQueue.getArray(), node2);
        assertEquals(1, priorityQueue.heapMinimun(priorityQueue.getArray()).getKey().intValue());
    }

    @Test
    public void testMinHeapInsert2() throws KeyIsBigger {
        setUp();
        Node<Integer, String> node1 = new Node<>(1, "cat");
        priorityQueue.minHeapInsert(priorityQueue.getArray(), node1);
        assertEquals(1, priorityQueue.heapMinimun(priorityQueue.getArray()).getKey().intValue());
    }

    @Test
    public void testMinHeapInsert3() throws KeyIsBigger {
        setUp();
        Node<Integer, String> node1 = new Node<>(1, "cat");
        Node<Integer, String> node2 = new Node<>(2, "dog");
        Node<Integer, String> node3 = new Node<>(3, "mouse");
        Node<Integer, String> node4 = new Node<>(4, "elephant");
        priorityQueue.minHeapInsert(priorityQueue.getArray(), node1);
        priorityQueue.minHeapInsert(priorityQueue.getArray(), node2);
        priorityQueue.minHeapInsert(priorityQueue.getArray(), node3);
        priorityQueue.minHeapInsert(priorityQueue.getArray(), node4);
        assertEquals(1, priorityQueue.heapMinimun(priorityQueue.getArray()).getKey().intValue());
    }

    // Test HeapMinimun

    @Test
    public void testHeapMinimun1() throws KeyIsBigger {
        setUp();
        Node<Integer, String> node1 = new Node<>(2, "Hello");
        Node<Integer, String> node2 = new Node<>(3, "World");
        priorityQueue.minHeapInsert(priorityQueue.getArray(), node1);
        priorityQueue.minHeapInsert(priorityQueue.getArray(), node2);
        assertEquals(node1.getKey().intValue(),
                priorityQueue.heapMinimun(priorityQueue.getArray()).getKey().intValue());
    }

    @Test
    public void testHeapMinimun2() {
        setUpZeroSize();
        assertNull(priorityQueueEmpty.heapMinimun(priorityQueueEmpty.getArray()));

    }

    @Test
    public void testHeapMinimun3() throws KeyIsBigger {
        setUp();
        Node<Integer, String> node1 = new Node<>(2, "Hello");
        Node<Integer, String> node2 = new Node<>(2, "World");
        priorityQueue.minHeapInsert(priorityQueue.getArray(), node1);
        priorityQueue.minHeapInsert(priorityQueue.getArray(), node2);
        assertEquals(node1.getKey().intValue(),
                priorityQueue.heapMinimun(priorityQueue.getArray()).getKey().intValue());
    }

    // Test heapExtracMin

    @Test(expected = HeapUnderflow.class)
    public void testHeapExtractMin1() throws HeapUnderflow {
        setUpZeroSize();
        priorityQueueEmpty.heapExtracMin(priorityQueueEmpty.getArray());
    }

    @Test
    public void testHeapExtracMin2() throws HeapUnderflow, KeyIsBigger {
        setUp();
        Node<Integer, String> node1 = new Node<>(2, "Hello");
        Node<Integer, String> node2 = new Node<>(3, "World");
        priorityQueue.minHeapInsert(priorityQueue.getArray(), node1);
        priorityQueue.minHeapInsert(priorityQueue.getArray(), node2);
        Node<Integer, String> min = priorityQueue.heapExtracMin(priorityQueue.getArray());
        assertEquals(node1.getKey().intValue(), min.getKey().intValue());
        assertEquals(node2.getKey().intValue(),
                priorityQueue.heapMinimun(priorityQueue.getArray()).getKey().intValue());
    }

    @Test
    public void testHeapExtractMin3() throws HeapUnderflow, KeyIsBigger {
        setUp();
        Node<Integer, String>[] array = new Node[4];
        array[0] = new Node<Integer, String>(2, "two");
        array[1] = new Node<Integer, String>(4, "four");
        array[2] = new Node<Integer, String>(1, "one");
        array[3] = new Node<Integer, String>(3, "three");
        for (int i = 0; i < array.length; i++) {
            priorityQueue.minHeapInsert(priorityQueue.getArray(), array[i]);
        }
        assertEquals(array[2].getKey().intValue(),
                priorityQueue.heapExtracMin(priorityQueue.getArray()).getKey().intValue());
        Node<Integer, String> result = priorityQueue.heapExtracMin(priorityQueue.getArray());
        assertEquals(array[0].getKey().intValue(), result.getKey().intValue());
    }

    // Test DecreaseKey

    @Test(expected = KeyIsBigger.class)
    public void testDecreaseKey1() throws KeyIsBigger {
        setUp();
        Node<Integer, String>[] array = new Node[1];
        array[0] = new Node<Integer, String>(1, "one");
        priorityQueue.minHeapInsert(priorityQueue.getArray(), array[0]);
        priorityQueue.heapDecreaseKey(priorityQueue.getArray(), 0, 2);
    }

    @Test
    public void testDecreaseKey2() throws KeyIsBigger {
        setUp();
        Node<Integer, String> node = new Node<Integer, String>(1, "one");
        priorityQueue.minHeapInsert(priorityQueue.getArray(), node);

        Node<Integer, String> node2 = new Node<Integer, String>(2, "two");
        priorityQueue.minHeapInsert(priorityQueue.getArray(), node2);

        assertEquals(1, priorityQueue.getArray()[0].getKey().intValue());
    }

    @Test
    public void testDecreaseKey3() throws KeyIsBigger {
        setUp();
        Node<Integer, String>[] array = new Node[5];
        array[0] = new Node<Integer, String>(1, "one");
        array[1] = new Node<Integer, String>(2, "two");
        array[2] = new Node<Integer, String>(3, "three");
        array[3] = new Node<Integer, String>(4, "four");
        array[4] = new Node<Integer, String>(5, "five");

        for (int i = 0; i < array.length; i++) {
            priorityQueue.minHeapInsert(priorityQueue.getArray(), array[i]);
        }

        assertEquals(1, priorityQueue.getArray()[0].getKey().intValue());
        priorityQueue.minHeapInsert(priorityQueue.getArray(), new Node<Integer, String>(-1, "-one"));
        assertEquals(-1, priorityQueue.getArray()[0].getKey().intValue());
        priorityQueue.minHeapInsert(priorityQueue.getArray(), new Node<Integer, String>(-5, "-five"));
        assertEquals(-5, priorityQueue.getArray()[0].getKey().intValue());
    }

}