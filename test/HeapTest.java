package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import dataStructures.*;
import exceptions.KeyIsSmaller;

public class HeapTest {
    private Heap<Integer,String> heap;
    
   

    //Test GeftLeft

    @Test
    public void testGetLeft1() {
        
        int i = 3;
        int expected = 6;
        int result = heap.getLeft(i);
        assertEquals(expected, result);
    }
    @Test
    public void testGetLeft2() {
        
        int i = 0;
        int expected = 1;
        int result = heap.getLeft(i);
        assertEquals(expected, result);
    }
    @Test
    public void testGetLeft3() {
        Heap<Integer, String> heap = new Heap<>(1);
        int i = 0;
        int expected = 1;
        int result = heap.getLeft(i);
        assertEquals(expected, result);
    }

    //Test GeftRight

    @Test
    public void testGetRight1() {
        
        int i = 3;
        int expected = 7;
        int result = heap.getRight(i);
        assertEquals(expected, result);
    }
    @Test
    public void testGetRight2() {
        
        int i = 0;
        int expected = 2;
        int result = heap.getRight(i);
        assertEquals(expected, result);
    }
    @Test
    public void testGetRight3() {
        Heap<Integer, String> heap = new Heap<>(1);
        int i = 0;
        int expected = 2;
        int result = heap.getRight(i);
        assertEquals(expected, result);
    }

    //Test GeftParent

    @Test
    public void testGetParent1() {
        
        int i = 3;
        int expected = 1;
        int result = heap.getParent(i);
        assertEquals(expected, result);
    }
    @Test
    public void testGetParent2() {
        
        int i = 0;
        int expected = 0;
        int result = heap.getParent(i);
        assertEquals(expected, result);
    }
    @Test
    public void testGetParent3() {
        
        assertEquals(3, heap.getParent(7));
    }

    //Test MaxHeapify

    @Test
    public void testMaxHeapify1() throws KeyIsSmaller {
        Node<Integer, String>[] heap = new Node[3];
        heap[0] = new Node<>(1, "A");
        heap[1] = new Node<>(2, "B");
        heap[2] = new Node<>(3, "C");
        Heap<Integer, String> maxHeap = new Heap<>(heap.length);
        for (int i = 0; i < heap.length; i++) {
            maxHeap.maxHeapInsert(maxHeap.getArray(), heap[i]);
        }
       
       
        assertEquals(3, maxHeap.getArray()[0].getKey().intValue());
    }

   

    

}
