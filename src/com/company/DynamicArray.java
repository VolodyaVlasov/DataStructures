package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;

public class DynamicArray<T> {
    public T[] array;
    public int count;
    public int capacity;
    Class clazz;

    public DynamicArray(Class clz) {
        clazz = clz;
        count = 0;
        makeArray(16);
    }

    public void makeArray(int new_capacity) {
        if (new_capacity < 16) {
            new_capacity = 16;
        }
        array = Arrays.copyOf((T[]) Array.newInstance(this.clazz, new_capacity), new_capacity);
        capacity = array.length;
    }

    public T getItem(int index) {
        if (index >= count) {
            throw new ArrayIndexOutOfBoundsException("Exception: ArrayIndexOutOfBoundsException");
        } else {
            return array[index];
        }
    }

    public void append(T itm) {
        if (count >= capacity) {
            makeArray(capacity * 2);
        }
        array[count] = itm;
        count++;
    }

    public void insert(T itm, int index) {
        if (index > count) {
            throw new ArrayIndexOutOfBoundsException("Exception: ArrayIndexOutOfBoundsException");
        }
        if (count >= capacity) {
            makeArray(capacity * 2);
        }
        if (count - index >= 0) System.arraycopy(array, index, array, index + 1, count - index);
        array[index] = itm;
        count++;
    }

    public void remove(int index) {
        if (index >= count) {
            throw new ArrayIndexOutOfBoundsException("Exception: ArrayIndexOutOfBoundsException");
        }
        if (count - 1 - index >= 0) System.arraycopy(array, index + 1, array, index, count - 1 - index);
        array[count - 1] = null;
        count--;
        if (count < capacity / 2) {
            makeArray((int) (capacity / 1.5));
        }
    }
}