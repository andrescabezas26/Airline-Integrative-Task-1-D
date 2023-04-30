package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import dataStructures.HashTable;

public class HashTableTest {

    private HashTable<String, Integer> table;

    @Before
    public void setUp() {
        table = new HashTable<>(5);
    }

    @Test
    public void testAdd1() {

        table.add("one", 1);
        table.add("two", 2);
        assertEquals(1, (int) table.getValue("one"));
        assertEquals(2, (int) table.getValue("two"));
    }

    @Test
    public void testAdd2() {

        table.add("one", 1);
        table.add("two", 2);
        table.add("neo", 3);
        assertEquals(1, (int) table.getValue("one"));
        assertEquals(2, (int) table.getValue("two"));
        assertEquals(3, (int) table.getValue("neo"));
    }

    @Test
    public void testAdd3() {

        table.add("one", 1);
        table.add("two", 2);
        table.add("three", 3);
        table.add("four", 4);
        table.add("five", 5);

        assertEquals(5, table.getSizeTable());
        assertEquals(2, (int) table.getValue("two"));
        assertNull(table.getValue("six"));
    }

    @Test
    public void testGetValue1() {

        table.add("one", 1);
        table.add("two", 2);
        assertEquals(1, (int) table.getValue("one"));
        assertEquals(2, (int) table.getValue("two"));
    }

    @Test
    public void testGetValue2() {

        table.add("one", 1);
        table.add("two", 2);
        assertNull(table.getValue("three"));
    }

    @Test
    public void testGetValue3() {

        table.add("one", 1);
        table.add("two", 2);
        table.add("three", 3);
        table.add("four", 4);
        table.add("five", 5);

        assertEquals(3, (int) table.getValue("three"));
        assertNull(table.getValue("six"));
    }

    @Test
    public void testRemove1() {

        table.add("one", 1);
        table.add("two", 2);
        table.remove("one");
        assertNull(table.getValue("one"));
        assertEquals(2, (int) table.getValue("two"));
    }

    @Test
    public void testRemoveNotFound2() {

        table.add("one", 1);
        table.add("two", 2);
        table.remove("three");
        assertEquals(1, (int) table.getValue("one"));
        assertEquals(2, (int) table.getValue("two"));
    }

    @Test
    public void testRemove3() {

        table.add("one", 1);
        table.add("two", 2);
        table.add("three", 3);
        table.add("four", 4);
        table.add("five", 5);

        table.remove("two");
        table.remove("four");

        assertNull(table.getValue("two"));
        assertNull(table.getValue("four"));
    }

}