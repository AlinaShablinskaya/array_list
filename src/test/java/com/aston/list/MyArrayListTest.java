package com.aston.list;

import com.aston.list.impl.MyArrayListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MyArrayListTest {
    private MyArrayList<String> list;

    @BeforeEach
    public void setUp() {
        list = new MyArrayListImpl<>();
        createTestData();
    }

    @Test
    @DisplayName("Should add element to the list")
    void addTest() {
        String expected = "Ron";
        list.add(expected);
        assertThat(expected).isEqualTo(list.get(3));
    }

    @Test
    @DisplayName("Should add element to the list at a specific index")
    void addByIndexTest() {
        String expected = "Ron";
        list.addByIndex(1, expected);
        assertThat(expected).isEqualTo(list.get(1));
    }

    @Test
    @DisplayName("Should throw IndexOutOfBoundsException when adding element at invalid index")
    void addByIndexWithExceptionTest() {
        int invalidIndex = 10;
        String element = "Ron";

        assertThatThrownBy(() -> list.addByIndex(invalidIndex, element))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Index is out of bounds. Size: 3");
    }

    @Test
    @DisplayName("Should return element by index")
    void getTest() {
        String expected = list.get(0);
        assertThat(expected).isEqualTo("Tom");
    }

    @Test
    @DisplayName("Should throw IndexOutOfBoundsException when when accessing with invalid index")
    void getWithExceptionTest() {
        int invalidIndex = 10;

        assertThatThrownBy(() -> list.get(invalidIndex))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Index is out of bounds. Size: 3");
    }

    @Test
    @DisplayName("Should remove element by index")
    void removeTest() {
        list.remove(1);
        int expectedSize = 2;

        assertThat(expectedSize).isEqualTo(list.size());
    }

    @Test
    @DisplayName("Should throw IndexOutOfBoundsException when removing by invalid index")
    void removeWithExceptionTest() {
        int invalidIndex = 10;

        assertThatThrownBy(() -> list.remove(invalidIndex))
                .isInstanceOf(IndexOutOfBoundsException.class)
                .hasMessage("Index is out of bounds. Size: 3");
    }

    @Test
    @DisplayName("Should clear all elements")
    void clear() {
        list.clear();
        assertThat(0).isEqualTo(list.size());
    }

    @Test
    @DisplayName("Should sort elements in the list")
    void sort() {
        list.sort();
        assertThat("Alex").isEqualTo(list.get(0));
        assertThat("Bobby").isEqualTo(list.get(1));
        assertThat("Tom").isEqualTo(list.get(2));
    }

    @Test
    @DisplayName("Should return the correct size of the list")
    void sizeTest() {
        int expectedSize = 3;
        assertThat(expectedSize).isEqualTo(list.size());
    }

    @Test
    @DisplayName("Should perform quick sort on the list")
    void quickSortTest() {
        Comparator<String> comparator = String::compareTo;
        list.quickSort(comparator);

        assertThat("Alex").isEqualTo(list.get(0));
        assertThat("Bobby").isEqualTo(list.get(1));
        assertThat("Tom").isEqualTo(list.get(2));
    }

    private void createTestData() {
        list.add("Tom");
        list.add("Alex");
        list.add("Bobby");
    }
}