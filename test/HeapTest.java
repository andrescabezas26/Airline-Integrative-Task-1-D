package test;

import static org.junit.Assert.*;
import org.junit.Test;

import dataStructures.*;
import exceptions.HeapUnderflow;
import exceptions.KeyIsBigger;
import exceptions.KeyIsSmaller;

public class HeapTest {
    private Heap<Integer, String> bigHeap;
    private Heap<Integer, String> smallHeap;

    public void setUpBig() {
        bigHeap = new Heap<>(10);
    }

    public void setUpSmall() {
        smallHeap = new Heap<>(5);
    }

    // Test GeftLeft

    @Test
    public void testGetLeft1() {
        setUpBig();
        int i = 3;
        int expected = 6;
        int result = bigHeap.getLeft(i);
        assertEquals(expected, result);
    }

    @Test
    public void testGetLeft2() {
        setUpBig();
        int i = 0;
        int expected = 1;
        int result = bigHeap.getLeft(i);
        assertEquals(expected, result);
    }

    @Test
    public void testGetLeft3() {
        setUpBig();
        int i = 2;
        int expected = 4;
        int result = bigHeap.getLeft(i);
        assertEquals(expected, result);
    }

    // Test GeftRight

    @Test
    public void testGetRight1() {
        setUpBig();
        int i = 3;
        int expected = 7;
        int result = bigHeap.getRight(i);
        assertEquals(expected, result);
    }

    @Test
    public void testGetRight2() {
        setUpBig();
        int i = 0;
        int expected = 2;
        int result = bigHeap.getRight(i);
        assertEquals(expected, result);
    }

    @Test
    public void testGetRight3() {
        setUpBig();
        int i = 2;
        int expected = 5;
        int result = bigHeap.getRight(i);
        assertEquals(expected, result);
    }

    // Test GetParent

    @Test
    public void testGetParent1() {
        setUpBig();
        int i = 3;
        int expected = 1;
        int result = bigHeap.getParent(i);
        assertEquals(expected, result);
    }

    @Test
    public void testGetParent2() {
        setUpBig();
        int i = 0;
        int expected = 0;
        int result = bigHeap.getParent(i);
        assertEquals(expected, result);
    }

    @Test
    public void testGetParent3() {
        setUpBig();
        assertEquals(3, bigHeap.getParent(7));
    }

    // Test MaxHeapify

    @Test
    public void testMaxHeapify1() throws KeyIsSmaller {
        setUpSmall();
        Node<Integer, String>[] array = new Node[4];
        array[0] = new Node<>(1, "A");
        array[1] = new Node<>(2, "B");
        array[2] = new Node<>(3, "C");
        array[3] = new Node<>(4, "D");

        smallHeap.getArray()[0] = array[0];
        smallHeap.getArray()[1] = array[1];
        smallHeap.getArray()[2] = array[2];
        smallHeap.getArray()[3] = array[3];
        smallHeap.heapIncreaseKey(smallHeap.getArray(), 3, 4);
        smallHeap.maxHeapify(smallHeap.getArray(), 0);
        assertEquals(4, smallHeap.getArray()[0].getKey().intValue());
    }

    @Test
    public void testMaxHeapify2() {
        setUpSmall();
        smallHeap.getArray()[0] = new Node<>(1, "A");
        smallHeap.getArray()[1] = new Node<>(2, "B");
        smallHeap.getArray()[2] = new Node<>(3, "C");
        smallHeap.setHeapSize(3);
        assertEquals(1, smallHeap.getArray()[0].getKey().intValue());
        smallHeap.maxHeapify(smallHeap.getArray(), 0);
        assertEquals(3, smallHeap.getArray()[0].getKey().intValue());

    }

    @Test
    public void testMaxHeapify3() throws KeyIsSmaller {
        setUpSmall();
        smallHeap.getArray()[0] = new Node<>(1, "A");
        smallHeap.getArray()[1] = new Node<>(2, "B");
        smallHeap.getArray()[2] = new Node<>(3, "C");
        smallHeap.setHeapSize(3);
        assertEquals(1, smallHeap.getArray()[0].getKey().intValue());
        smallHeap.maxHeapify(smallHeap.getArray(), 0);
        assertEquals(3, smallHeap.getArray()[0].getKey().intValue());
        smallHeap.setHeapSize(4);
        smallHeap.getArray()[3] = new Node<>(4, "D");
        smallHeap.heapIncreaseKey(smallHeap.getArray(), 3, 4);
        smallHeap.maxHeapify(smallHeap.getArray(), 0);
        assertEquals(4, smallHeap.getArray()[0].getKey().intValue());
    }

    // Test BuildMaxHeap

    @Test
    public void testBuildMaxHeap1() throws KeyIsSmaller {
        setUpBig();
        Node<Integer, String>[] array = new Node[6];
        array[0] = new Node<>(4, "four");
        array[1] = new Node<>(2, "two");
        array[2] = new Node<>(8, "eight");
        array[3] = new Node<>(5, "five");
        array[4] = new Node<>(1, "one");
        array[5] = new Node<>(6, "six");
        for (int i = 0; i < array.length; i++) {
            bigHeap.getArray()[i] = array[i];
            bigHeap.setHeapSize(bigHeap.getHeapSize() + 1);

        }
        bigHeap.maxHeapify(bigHeap.getArray(), 0);
        bigHeap.buildMaxHeap(bigHeap.getArray());
        assertEquals(8, bigHeap.getArray()[0].getKey().intValue());// 8
        assertEquals(6, bigHeap.getArray()[1].getKey().intValue());// 6
        assertEquals(4, bigHeap.getArray()[2].getKey().intValue());// 5
        assertEquals(5, bigHeap.getArray()[3].getKey().intValue());// 4
        assertEquals(1, bigHeap.getArray()[4].getKey().intValue());// 1
        assertEquals(2, bigHeap.getArray()[5].getKey().intValue());// 2
    }

    @Test
    public void testBuildMaxHeap2() {
        setUpBig();
        bigHeap.getArray()[0] = new Node<>(5, "five");
        bigHeap.buildMaxHeap(bigHeap.getArray());
        assertEquals(5, bigHeap.getArray()[0].getKey().intValue());
    }

    @Test
    public void testBuildMaxHeap3() throws KeyIsSmaller, KeyIsBigger {
        setUpBig();
        Node<Integer, String>[] array = new Node[4];
        array[0] = new Node<>(1, "one");
        array[1] = new Node<>(2, "two");
        array[2] = new Node<>(3, "three");
        array[3] = new Node<>(4, "four");
        for (int i = 0; i < array.length; i++) {
            bigHeap.getArray()[i] = array[i];
            bigHeap.setHeapSize(bigHeap.getHeapSize() + 1);

        }
        bigHeap.maxHeapify(bigHeap.getArray(), 4);
        bigHeap.buildMaxHeap(bigHeap.getArray());

        assertEquals(4, bigHeap.getArray()[0].getKey().intValue());
        assertEquals(3, bigHeap.getArray()[1].getKey().intValue());
        assertEquals(1, bigHeap.getArray()[2].getKey().intValue());
        assertEquals(2, bigHeap.getArray()[3].getKey().intValue());
    }

    // Test HeapsortMinToMax

    @Test
    public void testHeapSortMinToMax1() {
        setUpBig();
        Node<Integer, String>[] array = new Node[8];
        array[0] = new Node<>(5, "E");
        array[1] = new Node<>(2, "B");
        array[2] = new Node<>(8, "H");
        array[3] = new Node<>(3, "C");
        array[4] = new Node<>(1, "A");
        array[5] = new Node<>(4, "D");
        array[6] = new Node<>(6, "F");
        array[7] = new Node<>(7, "G");
        for (int index = 0; index < array.length; index++) {
            bigHeap.getArray()[index] = array[index];
            bigHeap.setHeapSize(bigHeap.getHeapSize() + 1);
            bigHeap.maxHeapify(bigHeap.getArray(), 0);
        }
        bigHeap.heapSortMinToMax(bigHeap.getArray());
        Node<Integer, String>[] expected = new Node[8];
        expected[0] = new Node<>(1, "A");
        expected[1] = new Node<>(2, "B");
        expected[2] = new Node<>(3, "C");
        expected[3] = new Node<>(4, "D");
        expected[4] = new Node<>(5, "E");
        expected[5] = new Node<>(6, "F");
        expected[6] = new Node<>(7, "G");
        expected[7] = new Node<>(8, "H");

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].getValue().toString(), bigHeap.getArray()[i].getValue().toString());
        }
    }

    @Test
    public void testHeapSortMinToMax2() throws HeapUnderflow {
        setUpSmall();
        Node<Integer, String>[] array = new Node[5];
        array[0] = new Node<>(5, "E");
        array[1] = new Node<>(2, "B");
        array[2] = new Node<>(8, "H");
        array[3] = new Node<>(3, "C");
        array[4] = new Node<>(1, "A");
        for (int index = 0; index < array.length; index++) {
            smallHeap.getArray()[index] = array[index];
            smallHeap.setHeapSize(smallHeap.getHeapSize() + 1);
            smallHeap.maxHeapify(smallHeap.getArray(), 0);
        }
        smallHeap.heapExtracMax(smallHeap.getArray());
        smallHeap.heapSortMinToMax(smallHeap.getArray());
        Node<Integer, String>[] expected = new Node[4];
        expected[0] = new Node<>(1, "A");
        expected[1] = new Node<>(2, "B");
        expected[2] = new Node<>(3, "C");
        expected[3] = new Node<>(5, "E");

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].getValue().toString(), smallHeap.getArray()[i].getValue().toString());
        }

    }

    @Test
    public void testHeapSortMinToMax3() {
        setUpSmall();
        Node<Integer, String>[] array = new Node[5];
        array[0] = new Node<>(5, "E");
        array[1] = new Node<>(2, "B");
        array[2] = new Node<>(2, "H");
        array[3] = new Node<>(1, "C");
        array[4] = new Node<>(1, "A");
        for (int index = 0; index < array.length; index++) {
            smallHeap.getArray()[index] = array[index];
            smallHeap.setHeapSize(smallHeap.getHeapSize() + 1);
            smallHeap.maxHeapify(smallHeap.getArray(), 0);
        }
        smallHeap.heapSortMinToMax(smallHeap.getArray());
        Node<Integer, String>[] expected = new Node[5];
        expected[0] = new Node<>(1, "C");
        expected[1] = new Node<>(1, "A");
        expected[2] = new Node<>(2, "H");
        expected[3] = new Node<>(2, "B");
        expected[4] = new Node<>(5, "E");

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].getValue().toString(), smallHeap.getArray()[i].getValue().toString());
        }

    }

    // Test MinHeapify

    @Test
    public void testMinHeapify1() throws KeyIsBigger {
        setUpSmall();
        Node<Integer, String>[] array = new Node[4];
        array[0] = new Node<>(4, "A");
        array[1] = new Node<>(3, "B");
        array[2] = new Node<>(2, "C");
        array[3] = new Node<>(1, "D");

        smallHeap.getArray()[0] = array[0];
        smallHeap.getArray()[1] = array[1];
        smallHeap.getArray()[2] = array[2];
        smallHeap.getArray()[3] = array[3];
        smallHeap.heapDecreaseKey(smallHeap.getArray(), 3, 1);
        smallHeap.minHeapify(smallHeap.getArray(), 0);
        assertEquals(1, smallHeap.getArray()[0].getKey().intValue());
    }

    @Test
    public void testMinHeapify2() {
        setUpSmall();
        smallHeap.getArray()[0] = new Node<>(3, "A");
        smallHeap.getArray()[1] = new Node<>(2, "B");
        smallHeap.getArray()[2] = new Node<>(1, "C");
        smallHeap.setHeapSize(3);
        assertEquals(3, smallHeap.getArray()[0].getKey().intValue());
        smallHeap.minHeapify(smallHeap.getArray(), 0);
        assertEquals(1, smallHeap.getArray()[0].getKey().intValue());

    }

    @Test
    public void testMinHeapify3() throws KeyIsBigger {
        setUpSmall();
        smallHeap.getArray()[0] = new Node<>(4, "A");
        smallHeap.getArray()[1] = new Node<>(3, "B");
        smallHeap.getArray()[2] = new Node<>(2, "C");
        smallHeap.setHeapSize(3);
        assertEquals(4, smallHeap.getArray()[0].getKey().intValue());
        smallHeap.minHeapify(smallHeap.getArray(), 0);
        assertEquals(2, smallHeap.getArray()[0].getKey().intValue());
        smallHeap.setHeapSize(4);
        smallHeap.getArray()[3] = new Node<>(1, "D");
        smallHeap.heapDecreaseKey(smallHeap.getArray(), 3, 1);
        smallHeap.minHeapify(smallHeap.getArray(), 0);
        assertEquals(1, smallHeap.getArray()[0].getKey().intValue());
    }

    // Test BuildMinHeap

    @Test
    public void testBuildMinHeap1() throws KeyIsSmaller {
        setUpBig();
        Node<Integer, String>[] array = new Node[6];
        array[0] = new Node<>(4, "four");
        array[1] = new Node<>(2, "two");
        array[2] = new Node<>(8, "eight");
        array[3] = new Node<>(5, "five");
        array[4] = new Node<>(1, "one");
        array[5] = new Node<>(6, "six");
        for (int i = 0; i < array.length; i++) {
            bigHeap.getArray()[i] = array[i];
            bigHeap.setHeapSize(bigHeap.getHeapSize() + 1);

        }
        bigHeap.minHeapify(bigHeap.getArray(), 0);
        bigHeap.buildMinHeap(bigHeap.getArray());
        assertEquals(1, bigHeap.getArray()[0].getKey().intValue());// 1
        assertEquals(2, bigHeap.getArray()[1].getKey().intValue());// 2
        assertEquals(4, bigHeap.getArray()[2].getKey().intValue());// 4
        assertEquals(5, bigHeap.getArray()[3].getKey().intValue());// 5
        assertEquals(8, bigHeap.getArray()[4].getKey().intValue());// 8
        assertEquals(6, bigHeap.getArray()[5].getKey().intValue());// 6
    }

    @Test
    public void testBuildMinHeap2() {
        setUpBig();
        bigHeap.getArray()[0] = new Node<>(5, "five");
        bigHeap.buildMinHeap(bigHeap.getArray());
        assertEquals(5, bigHeap.getArray()[0].getKey().intValue());
    }

    @Test
    public void testBuildMinHeap3() throws KeyIsSmaller, KeyIsBigger {
        setUpBig();
        Node<Integer, String>[] array = new Node[4];
        array[0] = new Node<>(4, "one");
        array[1] = new Node<>(3, "two");
        array[2] = new Node<>(2, "three");
        array[3] = new Node<>(1, "four");
        for (int i = 0; i < array.length; i++) {
            bigHeap.getArray()[i] = array[i];
            bigHeap.setHeapSize(bigHeap.getHeapSize() + 1);

        }
        bigHeap.minHeapify(bigHeap.getArray(), 4);
        bigHeap.buildMinHeap(bigHeap.getArray());

        assertEquals(1, bigHeap.getArray()[0].getKey().intValue());
        assertEquals(2, bigHeap.getArray()[1].getKey().intValue());
        assertEquals(4, bigHeap.getArray()[2].getKey().intValue());
        assertEquals(3, bigHeap.getArray()[3].getKey().intValue());
    }

    // Test HeapsortMaxToMin

    @Test
    public void testHeapSortMaxToMin1() {
        setUpBig();
        Node<Integer, String>[] array = new Node[8];
        array[0] = new Node<>(5, "E");
        array[1] = new Node<>(2, "B");
        array[2] = new Node<>(8, "H");
        array[3] = new Node<>(3, "C");
        array[4] = new Node<>(1, "A");
        array[5] = new Node<>(4, "D");
        array[6] = new Node<>(6, "F");
        array[7] = new Node<>(7, "G");
        for (int index = 0; index < array.length; index++) {
            bigHeap.getArray()[index] = array[index];
            bigHeap.setHeapSize(bigHeap.getHeapSize() + 1);
            bigHeap.minHeapify(bigHeap.getArray(), 0);
        }
        bigHeap.buildMinHeap(bigHeap.getArray());
        bigHeap.heapSortMaxToMin(bigHeap.getArray());
        Node<Integer, String>[] expected = new Node[8];
        expected[7] = new Node<>(1, "A");
        expected[6] = new Node<>(2, "B");
        expected[5] = new Node<>(3, "C");
        expected[4] = new Node<>(4, "D");
        expected[3] = new Node<>(5, "E");
        expected[2] = new Node<>(6, "F");
        expected[1] = new Node<>(7, "G");
        expected[0] = new Node<>(8, "H");

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].getValue().toString(), bigHeap.getArray()[i].getValue().toString());
        }
    }

    @Test
    public void testHeapSortMaxToMin2() throws HeapUnderflow {
        setUpSmall();
        Node<Integer, String>[] array = new Node[5];
        array[0] = new Node<>(5, "E");
        array[1] = new Node<>(2, "B");
        array[2] = new Node<>(8, "H");
        array[3] = new Node<>(3, "C");
        array[4] = new Node<>(1, "A");
        for (int index = 0; index < array.length; index++) {
            smallHeap.getArray()[index] = array[index];
            smallHeap.setHeapSize(smallHeap.getHeapSize() + 1);
            smallHeap.minHeapify(smallHeap.getArray(), 0);
        }
        smallHeap.buildMinHeap(smallHeap.getArray());
        smallHeap.heapExtracMin(smallHeap.getArray());
        smallHeap.heapSortMaxToMin(smallHeap.getArray());
        Node<Integer, String>[] expected = new Node[4];
        expected[3] = new Node<>(2, "B");
        expected[2] = new Node<>(3, "C");
        expected[1] = new Node<>(5, "E");
        expected[0] = new Node<>(8, "H");

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].getValue().toString(), smallHeap.getArray()[i].getValue().toString());
        }

    }

    @Test
    public void testHeapSortMaxToMin3() {
        setUpSmall();
        Node<Integer, String>[] array = new Node[5];
        array[0] = new Node<>(5, "E");
        array[1] = new Node<>(2, "B");
        array[2] = new Node<>(2, "H");
        array[3] = new Node<>(1, "C");
        array[4] = new Node<>(1, "A");
        for (int index = 0; index < array.length; index++) {
            smallHeap.getArray()[index] = array[index];
            smallHeap.setHeapSize(smallHeap.getHeapSize() + 1);
            smallHeap.maxHeapify(smallHeap.getArray(), 0);
        }
        smallHeap.buildMinHeap(smallHeap.getArray());
        smallHeap.heapSortMaxToMin(smallHeap.getArray());
        Node<Integer, String>[] expected = new Node[5];
        expected[4] = new Node<>(1, "A");
        expected[3] = new Node<>(1, "C");
        expected[2] = new Node<>(2, "H");
        expected[1] = new Node<>(2, "B");
        expected[0] = new Node<>(5, "E");

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i].getValue().toString(), smallHeap.getArray()[i].getValue().toString());
        }

    }

}