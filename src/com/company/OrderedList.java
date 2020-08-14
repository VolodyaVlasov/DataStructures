package com.company;

import java.util.*;


class Node<T> {
    public T value;
    public Node<T> next, prev;

    public Node(T _value) {
        value = _value;
        next = null;
        prev = null;
    }
}

public class OrderedList<T> {
    public Node<T> head, tail;
    private boolean _ascending;

    public OrderedList(boolean asc) {
        head = null;
        tail = null;
        _ascending = asc;
    }

    public int compare(T v1, T v2) {
        if (v1 instanceof String) {
            String first = ((String) v1).trim();
            String second = ((String) v2).trim();
            return first.compareTo(second);
        } else if (v1 instanceof Number) {
            if ((Integer) v1 < (Integer) v2) {
                return -1;
            } else if ((Integer) v1 > (Integer) v2) {
                return 1;
            }
        }
        return 0;
    }

    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (head == null) {
            head = node;
            tail = node;
        } else {
            Node<T> start = head;
            while (true) {
                if ((_ascending && compare(start.value, node.value) > 0) || (!_ascending && compare(start.value, node.value) < 0)) {
                    node.next = start;
                    node.prev = start.prev;
                    start.prev = node;
                    if (node.prev == null) {
                        head = node;
                    } else {
                        node.prev.next = node;
                    }
                    break;
                } else if (start.next == null) {
                    node.prev = tail;
                    tail.next = node;
                    tail = node;
                    break;
                } else {
                    start = start.next;
                }
            }
        }
    }


    public Node<T> find(T val) {
        Node<T> start = head;
        while (start != null) {
            if (start.value.equals(val)) {
                return start;
            } else {
                if ((_ascending && compare(start.value, val) > 0) || (!_ascending && compare(start.value, val) < 0)) {
                    return null;
                }
                start = start.next;
            }
        }
        return null;
    }

    public void delete(T val) {
        Node<T> start = head;
        while (start != null) {
            if (start.value.equals(val)) {
                if (start.prev == null) {
                    start = start.next;
                    head = start;
                    if (start != null) {
                        start.prev = null;
                        if (start.next == null) {
                            tail = start;
                        }
                    } else {
                        tail = null;
                    }
                } else {
                    start.prev.next = start.next;
                    if (start.next != null) {
                        start.next.prev = start.prev;
                    } else {
                        tail = start.prev;
                    }
                }
                break;
            } else {
                start = start.next;
            }
        }
    }

    public void clear(boolean asc) {
        _ascending = asc;
        head = null;
        tail = null;
    }

    public int count() {
        int temp = 0;
        Node<T> start = head;
        while (start != null) {
            temp++;
            start = start.next;
        }
        return temp;
    }

    ArrayList<Node<T>> getAll() {
        ArrayList<Node<T>> r = new ArrayList<Node<T>>();
        Node<T> node = head;
        while (node != null) {
            r.add(node);
            node = node.next;
        }
        return r;
    }

    public void show() {
        Node<T> start = head;
        while (start != null) {
            System.out.println(start.value);
            start = start.next;
        }
        System.out.println();
        System.out.println(head.value + " " + tail.value);
    }

}