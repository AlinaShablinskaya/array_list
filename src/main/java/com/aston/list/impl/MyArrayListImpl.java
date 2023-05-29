package com.aston.list.impl;

import com.aston.list.MyArrayList;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Custom ArrayList implementation.
 * @param <E> the type of elements in the list
 */
public class MyArrayListImpl<E> implements MyArrayList<E> {
    private static final String INDEX_OUT_OF_BOUNDS_EXCEPTION = "Index is out of bounds. Size: ";
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size;

    /**
     * Constructs an empty ArrayList with the default initial capacity.
     */
    public MyArrayListImpl() {
        elements = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Adds an element to the end of the list.
     * @param element the element to add
     */
    @Override
    public void add(E element) {
        ensureCapacity(size + 1);
        elements[size] = element;
        size++;
    }

    /**
     * Inserts an element at the specified index in the list.
     * @param index the index at which the element should be inserted
     * @param element the element to insert
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size)
     */
    @Override
    public void addByIndex(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_EXCEPTION + size);
        }
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Retrieves the element at the specified index in the list.
     * @param index the index of the element to retrieve
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     * @return the element at the specified index
     */
    @Override
    @SuppressWarnings("unchecked")
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_EXCEPTION + size);
        }
        return (E) elements[index];
    }

    /**
     * Removes the element at the specified index from the list
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size)
     * @param index the index of the element to remove
     */
    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(INDEX_OUT_OF_BOUNDS_EXCEPTION + size);
        }
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null;
    }

    /**
     * Removes all elements from the list.
     */
    @Override
    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
    }

    /**
     * Sorts the elements in the list in ascending order.
     */
    @Override
    public void sort() {
        Arrays.sort(elements, 0, size);
    }

    /**
     * Returns the number of elements in the list.
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Sorts the elements of the list using the QuickSort algorithm.
     * @param comparator the comparator to determine the order of the elements.
     */
    @Override
    public void quickSort(Comparator<? super E> comparator) {
        sortRecursive(0, size - 1, comparator);
    }

    private void ensureCapacity(int capacity) {
        if (capacity > elements.length) {
            int newCapacity = elements.length * 2;
            if (newCapacity < capacity) {
                newCapacity = capacity;
            }
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    private void sortRecursive(int low, int high, Comparator<? super E> comparator) {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);
            sortRecursive(low, pivotIndex - 1, comparator);
            sortRecursive(pivotIndex + 1, high, comparator);
        }
    }

    private int partition(int low, int high, Comparator<? super E> comparator) {
        E pivot = get(high);
        int pivotIndex = low - 1;

        for (int j = low; j < high; j++) {
            if (comparator.compare(get(j), pivot) <= 0) {
                pivotIndex++;
                swap(pivotIndex, j);
            }
        }

        swap(pivotIndex + 1, high);
        return pivotIndex + 1;
    }

    private void swap(int i, int j) {
        Object temp = elements[i];
        elements[i] = elements[j];
        elements[j] = temp;
    }
}
