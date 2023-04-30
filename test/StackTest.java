package test;

import static org.junit.Assert.*;

import org.junit.*;

import dataStructures.Stack;
import dataStructures.Node;

public class StackTest {

  
    private Stack<Integer, String> stack;

    @Before
    public void setUp() {
        stack = new Stack<>();
    }


    @Test
    public void testIsEmpty1() {
        assertTrue(stack.isEmpty());
    }

    @Test
    public void testIsEmpty2() {
        Node<Integer, String> node = new Node<>(1, "one");
        stack.push(node);
        assertFalse(stack.isEmpty());
    }

    @Test
    public void testIsEmpty3() {
        Node<Integer, String> node = new Node<>(1, "one");
        stack.push(node);
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    // Test Push

    @Test
    public void testPush1() {
        Node<Integer, String> node1 = new Node<>(1, "one");
        stack.push(node1);
        assertEquals("one", stack.top());
    }

    @Test
    public void testPush2() {
        Node<Integer, String> node1 = new Node<>(1, "one");
        Node<Integer, String> node2 = new Node<>(2, "two");
        stack.push(node1);
        stack.push(node2);
        assertEquals("two", stack.top());
    }

    @Test
    public void testPush3() {
        Node<Integer, String> node1 = new Node<>(1, "one");
        Node<Integer, String> node2 = new Node<>(2, "two");

        assertTrue(stack.isEmpty());

        stack.push(node1);

        assertFalse(stack.isEmpty());
        assertEquals("one", stack.top());

        stack.push(node2);

        assertFalse(stack.isEmpty());
        assertEquals("two", stack.top());
    }

    /// Test Top

    @Test
    public void testTop1() {
        
        assertThrows(NullPointerException.class, stack::top);
    }

    @Test
    public void testTop2() {
        
        Node<Integer, String> node1 = new Node<>(1, "one");
        stack.push(node1);
        assertEquals("one", stack.top());
    }

    @Test
    public void testTop3() {
        
        Node<Integer, String> node1 = new Node<>(1, "one");
        Node<Integer, String> node2 = new Node<>(2, "two");
        stack.push(node1);
        stack.push(node2);
        assertEquals("two", stack.top());
    }

    // Test Pop
    
    @Test
    public void testPop1() {
        
        assertThrows(NullPointerException.class, stack::pop);
    }
    @Test
    public void testPop2() {
        
        Node<Integer, String> node1 = new Node<>(1, "one");
        stack.push(node1);
        stack.pop();
        assertTrue(stack.isEmpty());
    }
    @Test
    public void testPop3() {
        
        Node<Integer, String> node1 = new Node<>(1, "one");
        Node<Integer, String> node2 = new Node<>(2, "two");
        stack.push(node1);
        stack.push(node2);
        stack.pop();
        assertEquals("one", stack.top());
    }

}
