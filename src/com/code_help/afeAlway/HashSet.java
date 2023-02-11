//package com.example.afeAlway;
//
//import java.util.*;
//
///**
// * A hash table-based implementation of the Set interface.
// */
//public class HashSet<T>  implements Set<T>{
//    private LinkedList <T>[] table;
//    // private ArrayList<LinkedList<T>> table;
//    int size = 0;
//
//    /**
//     * Creates a hash table with the given capacity (amount of buckets).
//     */
//    public HashSet(int capacity) {
//        if (capacity <= 0) {
//            throw new IllegalArgumentException(
//                "capacity must be a positive, non-zero value! Provided: " + capacity);
//        }
//
//        @SuppressWarnings("unchecked") // for this declaration only
//        LinkedList<T>[] t = new LinkedList[capacity];
//
//        table = t;
//
//        // I added this
//        for (int i = 0; i < capacity; i++) {
//            table[i] = new LinkedList<T>();
//        }
//
//
//    }
//
//    @Override
//    public int size() {
//        return this.size;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return false;
//    }
//
//    @Override
//    public Iterator<T> iterator() {
//        return null;
//    }
//
//    @Override
//    public Object[] toArray() {
//        return new Object[0];
//    }
//
//    @Override
//    public <T1> T1[] toArray(T1[] a) {
//        return null;
//    }
//
//    public boolean add(T elem) {
//        int hashCode = elem.hashCode();
//        int index = hashCode % table.length;
//        LinkedList<T> boxList = table[index];
//
//        if (!boxList.contains(elem)) {
//            boxList.add(elem);
//            size++;
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//
//    @Override
//    public boolean containsAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public boolean addAll(Collection<? extends T> c) {
//        return false;
//    }
//
//    @Override
//    public boolean retainAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public boolean removeAll(Collection<?> c) {
//        return false;
//    }
//
//    @Override
//    public void clear() {
//
//    }
//
//    @Override
//    public Spliterator<T> spliterator() {
//        return Set.super.spliterator();
//    }
//
//    public boolean remove(Object elem) {
//        int hashCode = elem.hashCode();
//        int index = hashCode % table.length;
//        LinkedList<T> boxList = table[index];
//        if(boxList.contains(elem)){
//            boxList.remove(elem);
//            size--;
//            return true;
//        }
//        else{
//            return false;
//        }
//
//    }
//    public boolean contains(Object elem) {
//        // throw new UnsupportedOperationException("Not implemented!");
//        int hashCode = elem.hashCode();
//        int index = hashCode % table.length;
//        LinkedList<T> boxList = table[index];
//        if (boxList.contains(elem)){
//            return true;
//        }
//        else{
//            return false;
//        }
//
//    }
//
//}