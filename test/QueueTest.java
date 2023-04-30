package test;

import static org.junit.Assert.*;

import org.junit.*;

import dataStructures.*;

public class QueueTest {
    private Queue<Integer,String> queue;

    @Before
    public void setUp() {
         queue = new Queue<>();
    }

    // Test isEmpty
    @Test
    public void testIsEmpty1() {
        
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testIsEmpty2() {
        
        Node<Integer, String> node = new Node<>(1, "one");
        queue.offer(node);
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testIsEmpty3() {
        
        Node<Integer, String> node = new Node<>(1, "one");
        queue.offer(node);
        queue.poll();
        assertTrue(queue.isEmpty());
    }

    // Test Poll
    @Test
    public void testPoll1() {
        
        assertNull(queue.poll());
    }

    @Test
    public void testPoll2() {
        
        Node<Integer, String> node1 = new Node<>(1, "one");
        queue.offer(node1);
        assertEquals("one", queue.poll());
        assertTrue(queue.isEmpty());
    }

    @Test
    public void testPoll3() {
        
        Node<Integer, String> node1 = new Node<>(1, "one");
        Node<Integer, String> node2 = new Node<>(2, "two");
        queue.offer(node1);
        queue.offer(node2);
        assertEquals("one", queue.poll());
        assertEquals("two", queue.peek());
    }

    // Test Peek

    @Test
    public void testPeek1() {
        
        assertNull(queue.peek());
    }

    @Test
    public void testPeek2() {
        
        Node<Integer, String> node1 = new Node<>(1, "one");
        queue.offer(node1);
        assertEquals("one", queue.peek());
        assertFalse(queue.isEmpty());
    }

    @Test
    public void testPeek3() {
        
        Node<Integer, String> node1 = new Node<>(1, "one");
        Node<Integer, String> node2 = new Node<>(2, "two");
        queue.offer(node1);
        queue.offer(node2);
        assertEquals("one", queue.peek());
        assertFalse(queue.isEmpty());
    }

    // Test Offer

    public void testOffer1() {
        
        Node<Integer, String> node1 = new Node<>(1, "one");
        queue.offer(node1);
        assertEquals("one", queue.peek());
        assertFalse(queue.isEmpty());
    }

    public void testOffer2() {
        
        Node<Integer, String> node1 = new Node<>(1, "one");
        Node<Integer, String> node2 = new Node<>(2, "two");
        queue.offer(node1);
        queue.offer(node2);
        assertEquals("one", queue.peek());
        assertFalse(queue.isEmpty());
    }

    public void testOffer3() {
        
        Node<Integer, String> node1 = new Node<>(1, "one");
        Node<Integer, String> node2 = new Node<>(2, "two");
        Node<Integer, String> node3 = new Node<>(3, "three");
        Node<Integer, String> node4 = new Node<>(4, "four");
        queue.offer(node1);
        queue.offer(node2);
        queue.poll();
        queue.offer(node3);
        queue.poll();
        queue.offer(node4);
        assertEquals("three", queue.peek());
        assertFalse(queue.isEmpty());
    }

}