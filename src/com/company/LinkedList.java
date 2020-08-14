package com.company;

import java.util.ArrayList;

public class LinkedList {
    public Node1 head;
    public Node1 tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public void addInTail(Node1 item) {
        if (this.head == null)
            this.head = item;
        else
            this.tail.next = item;
        this.tail = item;
    }

    public Node1 find(int value) {
        Node1 node = this.head;
        while (node != null) {
            if (node.value == value)
                return node;
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node1> findAll(int _value) {
        ArrayList<Node1> nodes = new ArrayList<Node1>();
        Node1 start = head;
        while (start != null) {
            if (start.value == _value) {
                nodes.add(start);
            }
            start = start.next;
        }
        return nodes;
    }

    public boolean remove(int _value) {
        if (head != null && head.value == _value) {
            head = head.next;
            if (head == null) {
                tail = null;
            }
            return true;
        }
        Node1 curr = head;
        Node1 prev;
        while (curr != null) {
            prev = curr;
            curr = curr.next;
            if (curr.value == _value) {
                prev.next = curr.next;
                if (prev.next == null) {
                    tail = prev;
                }
                return true;
            }
        }
        return false;
    }

    public void removeAll(int _value) {
        while (head != null && head.value == _value) {
            head = head.next;
            if (head == null) {
                tail = null;
                break;
            }
        }
        if (head != null) {
            Node1 curr = head.next;
            Node1 prev = head;
            while (curr != null) {
                if (curr.value == _value){
                    prev.next = curr.next;
                    curr = curr.next;
                } else {
                    prev = curr;
                    curr = curr.next;
                }
                if(curr == null){
                    tail = prev;
                }
            }
        }
    }

    public void clear() {
        head = null;
        tail = null;
    }

    public int count() {
        Node1 start = head;
        int numb = 0;
        while (start != null) {
            numb++;
            start = start.next;
        }
        return numb;
    }

    public void insertAfter(Node1 _nodeAfter, Node1 _nodeToInsert) {
        if (_nodeAfter == null && _nodeToInsert != null) {
            _nodeToInsert.next = head;
            head = _nodeToInsert;
            if (head.next == null) {
                tail = head;
            }
        } else if (_nodeToInsert != null) {
            Node1 after = find(_nodeAfter.value);
            _nodeToInsert.next = after.next;
            after.next = _nodeToInsert;
            if (_nodeToInsert.next == null) {
                tail = _nodeToInsert;
            }
        }
    }

}

class Node1 {
    public int value;
    public Node1 next;

    public Node1(int _value) {
        value = _value;
        next = null;
    }
}