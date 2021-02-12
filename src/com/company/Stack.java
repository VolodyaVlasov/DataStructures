package com.company;

import java.util.*;
import java.util.LinkedList;

public class Stack<T> {
    int count;
    final LinkedList<T> storage;

    public Stack() {
        count = 0;
        storage = new LinkedList<>();
    }

    public int size() {
        return count;
    }

    public T pop() {
        if (count > 0) {
            count--;
            return (T) storage.removeLast();
        } else {
            return null;
        }
    }

    public void push(T val) {
        storage.addLast(val);
        count++;
    }

    public T peek() {
        if (count > 0) {
            return (T) storage.getLast();
        } else {
            return null;
        }
    }
}
