package com.company;

import java.util.*;
import java.util.LinkedList;
import java.util.Queue;

class BSTNode<T> {
    protected int nodeKey;
    protected T nodeValue;
    protected BSTNode<T> parent;
    protected BSTNode<T> leftChild;
    protected BSTNode<T> rightChild;

    public BSTNode(int key, T val, BSTNode<T> parent) {
        nodeKey = key;
        nodeValue = val;
        this.parent = parent;
        leftChild = null;
        rightChild = null;
    }
}

class BSTFind<T> {
    protected BSTNode<T> Node;
    protected boolean NodeHasKey;
    protected boolean ToLeft;

    public BSTFind() {
        Node = null;
    }
}

class BST<T> {
    BSTNode<T> root; // корень дерева, или null
    int count;
    ArrayList<BSTNode> arrayList;

    public BST(BSTNode<T> node) {
        root = node;
        count = root != null ? 1 : 0;
    }

    public BSTFind<T> findNodeByKey(int key) {
        BSTFind<T> bstFind = new BSTFind<>();
        BSTNode<T> start = root;
        BSTNode<T> temp = start;
        while (start != null) {
            if (start.nodeKey == key) {
                bstFind.Node = start;
                bstFind.NodeHasKey = true;
                return bstFind;
            } else if (start.nodeKey > key) {
                temp = start;
                start = start.leftChild;
            } else {
                temp = start;
                start = start.rightChild;
            }
        }
        bstFind.Node = temp;
        bstFind.NodeHasKey = false;
        bstFind.ToLeft = temp.nodeKey > key;
        return bstFind;
    }

    public boolean addKeyValue(int key, T val) {
        BSTNode<T> bstNode = new BSTNode<>(key, val, null);
        if (root == null) {
            root = bstNode;
            count++;
            return true;
        }
        BSTFind<T> bstFind = findNodeByKey(key);
        if (bstFind.NodeHasKey) {
            return false;
        }
        bstNode.parent = bstFind.Node;
        if (bstFind.ToLeft) {
            bstFind.Node.leftChild = bstNode;
        } else {
            bstFind.Node.rightChild = bstNode;
        }
        count++;
        return true;
    }

    public BSTNode<T> finMinMax(BSTNode<T> FromNode, boolean FindMax) {
        BSTNode<T> start = FromNode;
        BSTNode<T> temp = start;
        while (start != null) {
            if (FindMax) {
                temp = start;
                start = start.rightChild;
            } else {
                temp = start;
                start = start.leftChild;
            }
        }
        return temp;
    }

    public boolean deleteNodeByKey(int key) {
        BSTFind<T> bstFind = findNodeByKey(key);
        if (!bstFind.NodeHasKey) {
            return false;
        }
        BSTNode<T> bstNode = bstFind.Node;
        if (bstNode.rightChild == null) {
            if (bstNode.parent == null) {
                root = bstNode.leftChild;
                bstNode.parent = null;
            } else {
                if (bstNode.parent.leftChild == bstNode) {
                    bstNode.parent.leftChild = bstNode.leftChild;
                } else {
                    bstNode.parent.rightChild = bstNode.leftChild;
                }
                if (bstNode.leftChild != null) {
                    bstNode.leftChild.parent = bstNode.parent;
                }
            }
        } else {
            BSTNode<T> minRight = bstNode.rightChild;
            while (minRight.leftChild != null) {
                minRight = minRight.leftChild;
            }
            if (minRight.rightChild != null) {
                if (minRight.parent.leftChild == minRight) {
                    minRight.parent.leftChild = minRight.rightChild;
                } else {
                    minRight.parent.rightChild = minRight.rightChild;
                }
            } else {
                if (minRight.parent.leftChild == minRight) {
                    minRight.parent.leftChild = null;
                } else {
                    minRight.parent.rightChild = null;
                }
            }
            bstNode.nodeKey = minRight.nodeKey;
            bstNode.nodeValue = minRight.nodeValue;
        }
        count--;
        return true;
    }

    public int count() {
        return count;
    }

    public ArrayList<BSTNode> wideAllNodes() {
        arrayList = new ArrayList<>(count);
        if (root == null) {
            return arrayList;
        }
        Queue<BSTNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BSTNode<T> temp = queue.poll();
            arrayList.add(temp);
            if (temp.leftChild != null) {
                queue.add(temp.leftChild);
            }
            if (temp.rightChild != null) {
                queue.add(temp.rightChild);
            }
        }
        return arrayList;
    }

    public ArrayList<BSTNode> deepAllNodes(int Order) {
        arrayList = new ArrayList<>(count);
        if (root == null) {
            return arrayList;
        }
        if (Order == 0) {
            inOrder(root);
        }
        if (Order == 1) {
            postOrder(root);
        }
        if (Order == 2) {
            preOrder(root);
        }
        return arrayList;
    }


    void inOrder(BSTNode<T> root) { // left root right
        if (root.leftChild != null) {
            inOrder(root.leftChild);
        }
        arrayList.add(root);
        if (root.rightChild != null) {
            inOrder(root.rightChild);
        }
    }

    void postOrder(BSTNode<T> root) { // left rigth root
        if (root.leftChild != null) {
            postOrder(root.leftChild);
        }
        if (root.rightChild != null) {
            postOrder(root.rightChild);
        }
        arrayList.add(root);
    }

    void preOrder(BSTNode<T> root) { // root right left
        arrayList.add(root);
        if (root.leftChild != null) {
            preOrder(root.leftChild);
        }
        if (root.rightChild != null) {
            preOrder(root.rightChild);
        }
    }
}
