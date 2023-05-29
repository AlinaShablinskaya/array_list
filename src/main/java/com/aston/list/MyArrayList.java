package com.aston.list;

import java.util.Comparator;

public interface MyArrayList<E> {
    /**
     * Adds an element to the end of the list.
     * @param element the element to add
     */
    void add(E element);

    /**
     * Inserts an element at the specified index in the list.
     * @param index the index at which the element should be inserted
     * @param element element the element to insert
     */
    void addByIndex(int index, E element);

    /**
     * Retrieves the element at the specified index in the list.
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     */
    E get(int index);

    /**
     * Removes the element at the specified index from the list
     * @param index the index of the element to remove
     */
    void remove(int index);

    /**
     * Removes all elements from the list.
     */
    void clear();

    /**
     * Sorts the elements in the list in ascending order.
     */
    void sort();

    /**
     * Returns the number of elements in the list.
     * @return the number of elements in the list
     */
    int size();

    /**
     * Sorts the elements of the list using the QuickSort algorithm.
     * @param comparator the comparator to determine the order of the elements.
     */
    void quickSort(Comparator<? super E> comparator);
}
