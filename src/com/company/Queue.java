package com.company;

import java.util.LinkedList;

public class Queue<T> {
    final LinkedList<T> storage;
    int count;

    public Queue() {
        storage = new LinkedList<T>();
        count = 0;
    }

    public void enqueue(T item) {
        storage.addLast(item);
        count++;
    }

    public T dequeue() {
        if (count != 0) {
            count--;
            return (T) storage.removeFirst();
        } else {
            return null;
        }
    }

    public int size() {
        return count;
    }
}