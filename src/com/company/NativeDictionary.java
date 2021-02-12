package com.company;

import java.lang.reflect.Array;

class NativeDictionary<T> {
    private int size;
    private String[] slots;
    private T[] values;

    public NativeDictionary(int sz, Class clazz) {
        size = sz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
    }

    public int hashFun(String key) {
        return key.hashCode() % size;
    }

    public boolean isKey(String key) {
        int start = hashFun(key);
        int index;
        for (int i = 0; i < size; i++) {
            index = (start + 7 * i) % size;
            if (slots[index] != null && slots[index].equals(key)) {
                return true;
            }
            if (slots[index] == null) {
                break;
            }
        }
        return false;
    }

    public void put(String key, T value) {
        int start = hashFun(key);
        int index;
        for (int i = 0; i < size; i++) {
            index = (start + 7 * i) % size;
            if (slots[index] == null || slots[index].equals(key)) {
                slots[index] = key;
                values[index] = value;
                break;
            }
        }
    }

    public T get(String key) {
        int start = hashFun(key);
        int index;
        for (int i = 0; i < size; i++) {
            index = (start + 7 * i) % size;
            if (slots[index] != null && slots[index].equals(key)) {
                return values[index];
            }
            if (slots[index] == null) {
                return null;
            }
        }
        return null;
    }
}