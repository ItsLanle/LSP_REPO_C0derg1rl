package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A class representing a mathematical set of integers.
 * Internally backed by an ArrayList but enforces set properties 
 * (no duplicates, unordered).
 */
public class IntegerSet  {
    private List<Integer> set = new ArrayList<>();

    /**
     * Clears the set by removing all elements.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return number of elements
     */
    public int length() {
        return set.size();
    }

    /**
     * Determines whether two IntegerSet objects are equal.
     * Two sets are equal if they contain exactly the same elements,
     * regardless of order.
     *
     * @param o the object to compare to
     * @return true if sets contain the same elements, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerSet)) return false;

        IntegerSet other = (IntegerSet) o;
        if (this.set.size() != other.set.size()) return false;
        return this.set.containsAll(other.set);
    }

    /**
     * Checks if a given value is contained in the set.
     *
     * @param value the integer to check
     * @return true if contained, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest element in the set.
     *
     * @return the maximum value
     * @throws IllegalStateException if the set is empty
     */
    public int largest()  {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty. Cannot determine largest element.");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest element in the set.
     *
     * @return the minimum value
     * @throws IllegalStateException if the set is empty
     */
    public int smallest()  {
        if (set.isEmpty()) {
            throw new IllegalStateException("Set is empty. Cannot determine smallest element.");
        }
        return Collections.min(set);
    }

    /**
     * Adds an item to the set if it is not already present.
     *
     * @param item the value to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set if present.
     *
     * @param item the value to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Performs a union with another IntegerSet.
     * This set becomes the set of all elements found in either set.
     *
     * @param other the other set to union with
     */
    public void union(IntegerSet other) {
        for (int value : other.set) {
            if (!set.contains(value)) {
                set.add(value);
            }
        }
    }

    /**
     * Performs an intersection with another IntegerSet.
     * This set becomes the set of elements found in both sets.
     *
     * @param other the other set to intersect with
     */
    public void intersect(IntegerSet other) {
        set.retainAll(other.set);
    }

    /**
     * Computes the set difference (this \ other).
     * Removes any element from this set that also exists in the other.
     *
     * @param other the set whose elements should be removed
     */
    public void diff(IntegerSet other) {
        set.removeAll(other.set);
    }

    /**
     * Computes the complement (other \ this).
     * This set becomes the elements in the other set that are not in this one.
     *
     * @param other the set to compare against
     */
    public void complement(IntegerSet other) {
        List<Integer> temp = new ArrayList<>(other.set);
        temp.removeAll(this.set);
        this.set = temp;
    }

    /**
     * Checks whether the set is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of the set.
     *
     * @return string format of the internal set
     */
    @Override
    public String toString() {
        return set.toString();
    }
}
