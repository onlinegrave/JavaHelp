package com.code_help.afeAlway;

import org.junit.Before;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
@RunWith(JUnit4.class)
public class HashSetTest extends SetTest {
    private Set<SingleHashUnequal> set;
    private SingleHashUnequal[] uniqueObjsWithEqualHashes;

    protected Set<Integer> getIntegerSet(int minCapacity) {
        return new HashSet<Integer>(minCapacity);
    }

    @Before
    public void setUp() {
        super.setUp();

        int numDummies = 10;
        uniqueObjsWithEqualHashes = new SingleHashUnequal[numDummies];
        set = new HashSet<SingleHashUnequal>(numDummies * 2);
        for (int i = 0; i < numDummies; i++) {
            SingleHashUnequal dummy = new SingleHashUnequal();
            set.add(dummy);
            uniqueObjsWithEqualHashes[i] = dummy;
        }
    }

    @Test
    public void addIsTrueForUniqueElementsWithEqualHashes() {

        int capacity = uniqueObjsWithEqualHashes.length;
        Set<SingleHashUnequal> set = new HashSet<SingleHashUnequal>(capacity);
        Arrays
            .stream(uniqueObjsWithEqualHashes)

            .map(elem -> set.add(elem))

            .forEach(wasAdded -> assertThat(wasAdded, is(true)));
    }

    @Test
    public void addUniqueElementsWithEqualHashesIncrementsSize() {
        // Arrange
        int capacity = uniqueObjsWithEqualHashes.length;
        Set<SingleHashUnequal> set = new HashSet<SingleHashUnequal>(capacity);
        int expectedSize = 0;
        for (SingleHashUnequal elem : uniqueObjsWithEqualHashes) {
            expectedSize++;
            // Act
            set.add(elem);
            // Assert
            assertThat(set.size(), equalTo(expectedSize));
        }
    }

    /**
     * A helper class for testing hash collisions. Instances equal only
     * themselves.
     */
    private static class SingleHashUnequal {
        private static final int HASH = 0;

        @Override
        public boolean equals(Object o) {
            return this == o;
        }

        @Override
        public int hashCode() {
            return HASH;
        }
    }
}

abstract  class SetTest {
    int []uniqueSetElements;
    int []elementsNotInSet;
    private final int CAPACITY = 10;
    Set<Integer> set;

    protected abstract Set<Integer> getIntegerSet(int minCapacity);

    @Before
    public void setUp() {
        // Arrange
        set = getIntegerSet(CAPACITY);
        uniqueSetElements =
            new int[] {-234, 32, 443, Integer.MAX_VALUE, Integer.MIN_VALUE, 0, -231};
        // Works because all elements in uniqueSetElements are more than 2 values apart
        // -2 as Integer.MIN_VALUE - 1 == Integer.MAX_VALUE because of underflow
        elementsNotInSet = Arrays.stream(uniqueSetElements).map(elem -> elem - 2).toArray();

        for (int elem : uniqueSetElements) {
            set.add(elem);
        }
    }

    @Test
    public void addDuplicatesDoesNotIncreaseSize() {
        // Arrange
        int expectedSize = uniqueSetElements.length;
        for (int elem : uniqueSetElements) {
            // Act
            set.add(elem);
        }
        // Assert
        assertThat(set.size(), equalTo(expectedSize));
    }

//    @Test
//    public void removeElementsNotInSetDoesNotDecrementSize() {
//        // Arrange
//        int expectedSize = uniqueSetElements.length;
//        for (int elem : elementsNotInSet) {
//            // Act
//            set.remove(elem);
//        }
//        // Assert
//        assertThat(set.size(), equalTo(expectedSize));
//    }
//
//    @Test
//    public void addIsTrueWhenElementNotInSet() {
//        // Arrange
//        Set<Integer> set = getIntegerSet(CAPACITY);
//        Arrays
//            .stream(uniqueSetElements)
//            // Act
//            .mapToObj(elem -> set.add(elem))
//            // Assert
//            .forEach(wasAdded -> assertThat(wasAdded, is(true)));
//    }


}
