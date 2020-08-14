package com.company;

import java.util.*;

public class LinkedList2 {
    public Node2 head;
    public Node2 tail;

    public LinkedList2() {
        head = null;
        tail = null;
    }

    public void addInTail(Node2 _item) {
        if (head == null) {
            this.head = _item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = _item;
            _item.prev = tail;
        }
        this.tail = _item;
    }

    public Node2 find(int _value) {
        Node2 start = head;
        while (start != null) {
            if (start.value == _value) {
                return start;
            }
            start = start.next;
        }
        return null;
    }

    public ArrayList<Node2> findAll(int _value) {
        ArrayList<Node2> nodes = new ArrayList<Node2>();
        Node2 start = head;
        while (start != null) {
            if (start.value == _value) {
                nodes.add(start);
            }
            start = start.next;
        }
        return nodes;
    }

    public boolean remove(int _value) {
        Node2 start = head;
        while (start != null) {
            if (start.value == _value) {
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
                return true;
            } else {
                start = start.next;
            }
        }
        return false;
    }

    public void removeAll(int _value) {
        Node2 start = head;
        while (start != null) {
            if (start.value == _value) {
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
                    start = start.next;
                }
            } else {
                start = start.next;
            }
        }
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public int count() {
        int temp = 0;
        Node2 start = head;
        while (start != null) {
            temp++;
            start = start.next;
        }

        return temp;
    }

    public void insertAfter(Node2 _nodeAfter, Node2 _nodeToInsert) {
        if (_nodeAfter == null && _nodeToInsert != null) {
            if (head == null) {
                head = _nodeToInsert;
                head.prev = null;
                tail = _nodeToInsert;
                tail.next = null;
            } else {
                _nodeToInsert.next = head;
                head.prev = _nodeToInsert;
                head = _nodeToInsert;
                head.prev = null;
            }
        } else if (_nodeToInsert != null) {
            if (_nodeAfter.next == null) {
                _nodeAfter.next = _nodeToInsert;
                _nodeToInsert.prev = _nodeAfter;
                tail = _nodeToInsert;
                tail.next = null;
            } else {
                _nodeAfter.next.prev = _nodeToInsert;
                _nodeToInsert.next = _nodeAfter.next;
                _nodeAfter.next = _nodeToInsert;
                _nodeToInsert.prev = _nodeAfter;
            }
        }
    }

}

class Node2 {
    public int value;
    public Node2 next;
    public Node2 prev;

    public Node2(int _value) {
        value = _value;
        next = null;
        prev = null;
    }
}