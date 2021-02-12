package com.company;

import java.util.LinkedList;

public class Set {
    private LinkedList<String>[] storage;
    private int count;

    public Set() {
        storage = new LinkedList[20000];
        for (int i = 0; i < storage.length; i++) {
            storage[i] = new LinkedList<>();
        }
        count = 0;
    }

    public int size() {
        return count;
    }

    public void put(String value) {
        int index = value.hashCode() % storage.length;
        for (String a : storage[index]) {
            if (a.equals(value)) {
                return;
            }
        }
        storage[index].add(value);
        count++;
    }

    public boolean get(String value) {
        int index = value.hashCode() % storage.length;
        for (String a : storage[index]) {
            if (a.equals(value)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(String value) {
        int index = value.hashCode() % storage.length;
        if (storage[index].remove(value)) {
            count--;
            return true;
        }
        return false;
    }

    public Set intersection(Set set2) {
        Set set = new Set();
        for (LinkedList<String> a : set2.storage) {
            for (String b : a) {
                if (get(b)) {
                    set.put(b);
                }
            }
        }
        return set;
    }

    public Set union(Set set2) {
        Set set = new Set();
        for (LinkedList<String> a : storage) {
            for (String b : a) {
                set.put(b);
            }
        }
        for (LinkedList<String> a : set2.storage) {
            for (String b : a) {
                set.put(b);
            }
        }
        return set;
    }

    public Set difference(Set set2) {
        Set set = new Set();
        for (LinkedList<String> a : storage) {
            for (String b : a) {
                if (!set2.get(b)) {
                    set.put(b);
                }
            }
        }
        return set;

    }

    public boolean isSubset(Set set2) {
        for (LinkedList<String> a : set2.storage) {
            for (String b : a) {
                if (!get(b)) {
                    return false;
                }
            }
        }
        return true;
    }
}