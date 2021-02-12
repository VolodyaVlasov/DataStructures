package com.company;

import java.util.LinkedList;

public class Deque<T> {
    LinkedList<T> storage;
    int count;

    public Deque() {
        storage = new LinkedList<>();
        count = 0;
    }

    public void addFront(T item) {
        storage.addFirst(item);
        count++;
    }

    public void addTail(T item) {
        storage.addLast(item);
        count++;
    }

    public T removeFront() {
        if (count != 0) {
            count--;
            return (T) storage.removeFirst();
        } else {
            return null;
        }
    }

    public T removeTail() {
        if (count != 0) {
            count--;
            return (T) storage.removeLast();
        } else {
            return null;
        }
    }

    public int size() {
        return count;
    }
}