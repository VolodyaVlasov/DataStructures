package com.company;

import java.util.LinkedList;

public class PowerSet {
    LinkedList<String>[] storage;
    int count;

    public PowerSet() {
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

    public PowerSet intersection(PowerSet set2) {
        PowerSet powerSet = new PowerSet();
        for (LinkedList<String> a : set2.storage) {
            for (String b : a) {
                if (get(b)) {
                    powerSet.put(b);
                }
            }
        }
        return powerSet;
    }

    public PowerSet union(PowerSet set2) {
        PowerSet powerSet = new PowerSet();
        for (LinkedList<String> a : storage) {
            for (String b : a) {
                powerSet.put(b);
            }
        }
        for (LinkedList<String> a : set2.storage) {
            for (String b : a) {
                powerSet.put(b);
            }
        }
        return powerSet;
    }

    public PowerSet difference(PowerSet set2) {
        PowerSet powerSet = new PowerSet();
        for (LinkedList<String> a : storage) {
            for (String b : a) {
                if (!set2.get(b)) {
                    powerSet.put(b);
                }
            }
        }
        return powerSet;

    }

    public boolean isSubset(PowerSet set2) {
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