package com.company;

import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

public class SimpleTreeNode<T> {
    public T NodeValue;
    public SimpleTreeNode<T> Parent;
    public List<SimpleTreeNode<T>> Children;

    public SimpleTreeNode(T val, SimpleTreeNode<T> parent) {
        NodeValue = val;
        Parent = parent;
        Children = null;
    }
}

class SimpleTree<T> {
    public SimpleTreeNode<T> Root;
    public int count;


    public SimpleTree(SimpleTreeNode<T> root) {
        Root = root;
        count = 0;
        if (root != null) {
            count++;
        }
    }

    public void AddChild(SimpleTreeNode<T> ParentNode, SimpleTreeNode<T> NewChild) {
        if (ParentNode.Children == null) {
            ParentNode.Children = new LinkedList<>();

        }
        ParentNode.Children.add(NewChild);
        NewChild.Parent = ParentNode;
        count++;
    }

    public void DeleteNode(SimpleTreeNode<T> NodeToDelete) {
        if (NodeToDelete.Children != null) {
            for (SimpleTreeNode<T> a : NodeToDelete.Children) {
                a.Parent = NodeToDelete.Parent;
                NodeToDelete.Parent.Children.add(a);
            }
        }
        NodeToDelete.Parent.Children.remove(NodeToDelete);
        count--;
        if (NodeToDelete.Parent.Children.size() == 0) {
            NodeToDelete.Parent.Children = null;
        }
    }

    public List<SimpleTreeNode<T>> GetAllNodes() {
        Queue<SimpleTreeNode<T>> queue = new LinkedList<>();
        LinkedList<SimpleTreeNode<T>> linkedList = new LinkedList<>();
        if (Root == null) {
            return linkedList;
        }
        queue.add(Root);
        while (!queue.isEmpty()) {
            if (queue.peek().Children != null) {
                queue.addAll(queue.peek().Children);
            }
            linkedList.add(queue.poll());
        }
        return linkedList;
    }

    public List<SimpleTreeNode<T>> FindNodesByValue(T val) {
        Queue<SimpleTreeNode<T>> queue = new LinkedList<>();
        LinkedList<SimpleTreeNode<T>> linkedList = new LinkedList<>();
        if (Root == null) {
            return linkedList;
        }
        queue.add(Root);
        while (!queue.isEmpty()) {
            if (queue.peek().Children != null) {
                queue.addAll(queue.peek().Children);
            }
            if (queue.peek() != null && queue.peek().NodeValue.equals(val)) {
                linkedList.add(queue.poll());
            } else {
                queue.poll();
            }
        }
        return linkedList;
    }

    public void MoveNode(SimpleTreeNode<T> OriginalNode, SimpleTreeNode<T> NewParent) {
        OriginalNode.Parent.Children.remove(OriginalNode);
        if (OriginalNode.Parent.Children.size() == 0) {
            OriginalNode.Parent.Children = null;

        }
        OriginalNode.Parent = NewParent;
        if (NewParent.Children == null) {
            NewParent.Children = new LinkedList<>();

        }
        NewParent.Children.add(OriginalNode);
    }

    public int Count() {
        return count;
    }

    public int LeafCount() {
        int leafcount = 0;
        Queue<SimpleTreeNode<T>> queue = new LinkedList<>();
        if (Root == null) {
            return 0;
        }
        queue.add(Root);
        while (!queue.isEmpty()) {
            if (queue.peek().Children != null) {
                queue.addAll(queue.peek().Children);
            } else {
                leafcount++;
            }
            queue.poll();
        }
        return leafcount;
    }

    public ArrayList<T> EvenTrees() {
        ArrayList<T> arrayList = new ArrayList<>();
        if (count % 2 != 0 || count == 0) {
            return arrayList;
        }
        Queue<SimpleTreeNode<T>> queue = new LinkedList<>();
        queue.add(Root);
        while (!queue.isEmpty()) {
            SimpleTreeNode<T> temp = queue.poll();
            if (temp.Children != null) {
                for (var i : temp.Children) {
                    if (needCut(i)) {
                        arrayList.add(temp.NodeValue);
                        arrayList.add(i.NodeValue);
                        if(i.Children != null){
                            queue.addAll(i.Children);
                        }
                    } else {
                        queue.add(i);
                    }
                }
            }
        }
        return arrayList;
    }

    public boolean needCut(SimpleTreeNode<T> a) {
        if (a.Children == null) {
            return false;
        }
        int child = 0;
        Queue<SimpleTreeNode<T>> queue = new LinkedList<>();
        queue.add(a);
        while (!queue.isEmpty()) {
            SimpleTreeNode<T> temp = queue.poll();
            child++;
            if (temp.Children != null) {
                queue.addAll(temp.Children);
            }
        }
        return child % 2 == 0;
    }
}