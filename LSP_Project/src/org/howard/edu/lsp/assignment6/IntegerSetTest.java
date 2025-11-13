package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class IntegerSetTest {

    @Test
    public void testClearAndLength() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        assertEquals(2, set.length());

        set.clear();
        assertEquals(0, set.length());
        assertTrue(set.isEmpty());
    }

    @Test
    public void testEquals() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);

        IntegerSet set2 = new IntegerSet();
        set2.add(2);
        set2.add(1);

        assertTrue(set1.equals(set2));  // same values, different order

        set2.add(3);
        assertFalse(set1.equals(set2));  // different contents
    }

    @Test
    public void testContains() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        assertTrue(set.contains(10));
        assertFalse(set.contains(5));
    }

    @Test
    public void testLargestAndSmallest() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(9);
        set.add(5);

        assertEquals(9, set.largest());
        assertEquals(1, set.smallest());
    }

    @Test
    public void testLargestThrowsExceptionOnEmpty() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, () -> {
            set.largest();
        });
    }

    @Test
    public void testSmallestThrowsExceptionOnEmpty() {
        IntegerSet set = new IntegerSet();
        assertThrows(IllegalStateException.class, () -> {
            set.smallest();
        });
    }

    @Test
    public void testAddAndRemove() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(1); // should not add duplicate
        assertEquals(1, set.length());

        set.remove(1);
        assertTrue(set.isEmpty());
    }

    @Test
    public void testUnion() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);

        IntegerSet set2 = new IntegerSet();
        set2.add(2);
        set2.add(3);

        set1.union(set2);

        assertTrue(set1.contains(1));
        assertTrue(set1.contains(2));
        assertTrue(set1.contains(3));

        // Ensure set2 is unchanged
        assertTrue(set2.contains(2));
        assertTrue(set2.contains(3));
    }

    @Test
    public void testIntersect() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        IntegerSet set2 = new IntegerSet();
        set2.add(2);
        set2.add(4);

        set1.intersect(set2);

        assertEquals(1, set1.length());
        assertTrue(set1.contains(2));
        assertFalse(set1.contains(1));
    }

    @Test
    public void testDiff() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);
        set1.add(3);

        IntegerSet set2 = new IntegerSet();
        set2.add(2);

        set1.diff(set2);

        assertTrue(set1.contains(1));
        assertTrue(set1.contains(3));
        assertFalse(set1.contains(2));
    }

    @Test
    public void testComplement() {
        IntegerSet set1 = new IntegerSet();
        set1.add(1);
        set1.add(2);

        IntegerSet set2 = new IntegerSet();
        set2.add(2);
        set2.add(3);

        set1.complement(set2);

        assertTrue(set1.contains(3));
        assertFalse(set1.contains(1));
        assertFalse(set1.contains(2));
    }

    @Test
    public void testIsEmptyAndToString() {
        IntegerSet set = new IntegerSet();
        assertTrue(set.isEmpty());

        set.add(1);
        set.add(2);

        assertFalse(set.isEmpty());
        String output = set.toString();
        assertTrue(output.contains("1"));
        assertTrue(output.contains("2"));
    }
}
