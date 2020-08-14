package com.company;

import java.io.*;
import java.util.*;
import java.util.LinkedList;
import java.util.Queue;


class BSTNode<T> {
    public int NodeKey; // ключ узла
    public T NodeValue; // значение в узле
    public BSTNode<T> Parent; // родитель или null для корня
    public BSTNode<T> LeftChild; // левый потомок
    public BSTNode<T> RightChild; // правый потомок


    public BSTNode(int key, T val, BSTNode<T> parent) {
        NodeKey = key;
        NodeValue = val;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

// промежуточный результат поиска
class BSTFind<T> {
    // null если в дереве вообще нету узлов
    public BSTNode<T> Node;

    // true если узел найден
    public boolean NodeHasKey;

    // true, если родительскому узлу надо добавить новый левым
    public boolean ToLeft;

    public BSTFind() {
        Node = null;
    }
}

class BST<T> {
    BSTNode<T> Root; // корень дерева, или null
    int count;
    ArrayList<BSTNode> arrayList;

    public BST(BSTNode<T> node) {
        Root = node;
        count = Root != null ? 1 : 0;
    }

    public BSTFind<T> FindNodeByKey(int key) {
        BSTFind<T> bstFind = new BSTFind<>();
        BSTNode<T> start = Root;
        BSTNode<T> temp = start;
        while (start != null) {
            if (start.NodeKey == key) {
                bstFind.Node = start;
                bstFind.NodeHasKey = true;
                return bstFind;
            } else if (start.NodeKey > key) {
                temp = start;
                start = start.LeftChild;
            } else {
                temp = start;
                start = start.RightChild;
            }
        }
        bstFind.Node = temp;
        bstFind.NodeHasKey = false;
        bstFind.ToLeft = temp.NodeKey > key;
        return bstFind;
    }

    public boolean AddKeyValue(int key, T val) {
        BSTNode<T> bstNode = new BSTNode<>(key, val, null);
        if (Root == null) {
            Root = bstNode;
            count++;
            return true;
        }
        BSTFind<T> bstFind = FindNodeByKey(key);
        if (bstFind.NodeHasKey) {
            return false;// если ключ уже есть
        }
        bstNode.Parent = bstFind.Node;// добавляем ключ-значение в дерево
        if (bstFind.ToLeft) {
            bstFind.Node.LeftChild = bstNode;
        } else {
            bstFind.Node.RightChild = bstNode;
        }
        count++;
        return true;
    }

    public BSTNode<T> FinMinMax(BSTNode<T> FromNode, boolean FindMax) {
        BSTNode<T> start = FromNode;
        BSTNode<T> temp = start;
        while (start != null) {
            if (FindMax) {
                temp = start;
                start = start.RightChild;
            } else {
                temp = start;
                start = start.LeftChild;
            }
        }
        return temp;
    }

    public boolean DeleteNodeByKey(int key) {
        BSTFind<T> bstFind = FindNodeByKey(key);
        if (!bstFind.NodeHasKey) {
            return false;// если узел не найден
        }
        BSTNode<T> bstNode = bstFind.Node;
        if (bstNode.RightChild == null) {
            if (bstNode.Parent == null) {
                Root = bstNode.LeftChild;
                bstNode.Parent = null;
            } else {
                if (bstNode.Parent.LeftChild == bstNode) {
                    bstNode.Parent.LeftChild = bstNode.LeftChild;
                } else {
                    bstNode.Parent.RightChild = bstNode.LeftChild;
                }
                if (bstNode.LeftChild != null) {
                    bstNode.LeftChild.Parent = bstNode.Parent;
                }
            }
        } else {
            BSTNode<T> minRight = bstNode.RightChild;
            while (minRight.LeftChild != null) {
                minRight = minRight.LeftChild;
            }
            if (minRight.RightChild != null) {
                if (minRight.Parent.LeftChild == minRight) {
                    minRight.Parent.LeftChild = minRight.RightChild;
                } else {
                    minRight.Parent.RightChild = minRight.RightChild;
                }
            } else {
                if (minRight.Parent.LeftChild == minRight) {
                    minRight.Parent.LeftChild = null;
                } else {
                    minRight.Parent.RightChild = null;
                }
            }
            bstNode.NodeKey = minRight.NodeKey;
            bstNode.NodeValue = minRight.NodeValue;
        }
        count--;
        return true;
    }

    public int Count() {
        return count; // количество узлов в дереве
    }

    public ArrayList<BSTNode> WideAllNodes() {
        arrayList = new ArrayList<>(count);
        if (Root == null) {
            return arrayList;
        }
        Queue<BSTNode<T>> queue = new LinkedList<>();
        queue.add(Root);
        while (!queue.isEmpty()) {
            BSTNode<T> temp = queue.poll();
            arrayList.add(temp);
            if (temp.LeftChild != null) {
                queue.add(temp.LeftChild);
            }
            if (temp.RightChild != null) {
                queue.add(temp.RightChild);
            }
        }
        return arrayList;
    }

    public ArrayList<BSTNode> DeepAllNodes(int Order) {
        arrayList = new ArrayList<>(count);
        if (Root == null) {
            return arrayList;
        }
        if (Order == 0) {
            inOrder(Root);
        }
        if (Order == 1) {
            postOrder(Root);
        }
        if (Order == 2) {
            preOrder(Root);
        }
        return arrayList;
    }


    void inOrder(BSTNode<T> root) { // left root right
        if (root.LeftChild != null) {
            inOrder(root.LeftChild);
        }
        arrayList.add(root);
        if (root.RightChild != null) {
            inOrder(root.RightChild);
        }
    }

    void postOrder(BSTNode<T> root) { // left rigth root
        if (root.LeftChild != null) {
            postOrder(root.LeftChild);
        }
        if (root.RightChild != null) {
            postOrder(root.RightChild);
        }
        arrayList.add(root);
    }

    void preOrder(BSTNode<T> root) { // root right left
        arrayList.add(root);
        if (root.LeftChild != null) {
            preOrder(root.LeftChild);
        }
        if (root.RightChild != null) {
            preOrder(root.RightChild);
        }
    }

}