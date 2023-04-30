package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import dataStructures.*;
import exceptions.KeyIsSmaller;

public class HeapTest {
    private Heap<Integer, String> bigHeap;
    private Heap<Integer, String> smallHeap;

    public void setUpBig() {
        bigHeap = new Heap<>(10);
    }

    public void setUpSmall() {
        smallHeap = new Heap<>(4);
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
        int i = 0;
        int expected = 1;
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
        int i = 0;
        int expected = 2;
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
        Node<Integer, String>[] array = new Node[3];
        array[0] = new Node<>(1, "A");
        array[1] = new Node<>(2, "B");
        array[2] = new Node<>(3, "C");

        for (int i = 0; i < array.length; i++) {
            smallHeap.maxHeapInsert(smallHeap.getArray(), array[i]);
        }
        assertEquals(3, smallHeap.getArray()[0].getKey().intValue());
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
        smallHeap.maxHeapInsert(smallHeap.getArray(), new Node<>(4, "D"));
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
            bigHeap.maxHeapInsert(bigHeap.getArray(), array[i]);
        }
        bigHeap.buildMaxHeap(bigHeap.getArray());
        assertEquals(8, bigHeap.getArray()[0].getKey().intValue());
        assertEquals(6, bigHeap.getArray()[1].getKey().intValue());
        assertEquals(5, bigHeap.getArray()[2].getKey().intValue());
        assertEquals(4, bigHeap.getArray()[3].getKey().intValue());
        assertEquals(1, bigHeap.getArray()[4].getKey().intValue());
        assertEquals(2, bigHeap.getArray()[5].getKey().intValue());
    }

    @Test
    public void testBuildMaxHeap2() {
        setUpSmall();
        smallHeap.getArray()[0]= new Node<>(5, "five");
        smallHeap.buildMaxHeap(smallHeap.getArray());
        assertEquals(5, smallHeap.getArray()[0].getKey().intValue());
    }

    @Test
    public void testBuildMaxHeap3() throws KeyIsSmaller {
        setUpSmall();
        Node<Integer, String>[] array = new Node[4];
        array[0] = new Node<>(1, "one");
        array[1] = new Node<>(2, "two");
        array[2] = new Node<>(3, "three");
        array[3] = new Node<>(4, "four");
        for (int i = 0; i < array.length; i++) {
            smallHeap.maxHeapInsert(smallHeap.getArray(), array[i]);
        }
        smallHeap.buildMaxHeap(smallHeap.getArray());
       //Mal Hecho creo revisar
        assertEquals(4, smallHeap.getArray()[0].getKey().intValue());
        assertEquals(2, smallHeap.getArray()[1].getKey().intValue());
        assertEquals(3, smallHeap.getArray()[2].getKey().intValue());
        assertEquals(1, smallHeap.getArray()[3].getKey().intValue());
    }

}
