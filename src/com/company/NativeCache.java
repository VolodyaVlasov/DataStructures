package com.company;

import java.lang.reflect.Array;

class NativeCache<T> {
    private int size;
    private String[] slots;
    private T[] values;
    private int[] hits;
    private int count;

    public NativeCache(int sz, Class clazz) {
        size = sz;
        slots = new String[size];
        values = (T[]) Array.newInstance(clazz, this.size);
        hits = new int[size];
        count = 0;
    }

    public int hashFun(String key) {
        return key.hashCode() % size;
    }

    public boolean isKey(String key) {
        int start = key.hashCode() % size;
        int index;
        for (int i = 0; i < size; i++) {
            index = (start + 7 * i) % size;
            if (slots[index] != null && slots[index].equals(key)) {
                hits[index]++;
                return true;
            }
            if (slots[index] == null) {
                break;
            }
        }
        return false;
    }

    public void put(String key, T value) {
        int start;
        if (count == size) {
            start = whoIs();
            slots[start] = key;
            values[start] = value;
            hits[start] = 0;
            return;
        }
        start = key.hashCode() % size;
        int index;
        for (int i = 0; i < size; i++) {
            index = (start + 7 * i) % size;
            if (slots[index] == null || slots[index].equals(key)) {
                slots[index] = key;
                values[index] = value;
                count++;
                break;
            }
        }
    }

    public T get(String key) {
        int start = key.hashCode() % size;
        int index;
        for (int i = 0; i < size; i++) {
            index = (start + 7 * i) % size;
            if (slots[index] != null && slots[index].equals(key)) {
                hits[index]++;
                return values[index];
            }
            if (slots[index] == null) {
                return null;
            }
        }
        return null;
    }

    public int whoIs() {
        int hit = 0;
        for (int i = 1; i < size; i++) {
            if (hits[hit] > hits[i]) {
                hit = i;
            }
        }
        return hit;
    }
}