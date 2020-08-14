package com.company;

import java.util.*;

class BSTNode1 {
    public int NodeKey; // ключ узла
    public BSTNode1 Parent; // родитель или null для корня
    public BSTNode1 LeftChild; // левый потомок
    public BSTNode1 RightChild; // правый потомок
    public int Level; // глубина узла

    public BSTNode1(int key, BSTNode1 parent) {
        NodeKey = key;
        Parent = parent;
        LeftChild = null;
        RightChild = null;
    }
}

class BalancedBST {
    public BSTNode1 Root; // корень дерева

    public BalancedBST() {
        Root = null;
    }

    public void GenerateTree(int[] a) {
        Arrays.sort(a);
        makeTree(a, null);
    }

    public boolean IsBalanced(BSTNode1 root_node) {
        int heightLeft;
        int heightRight;
        if (root_node == null) {
            return true;
        }
        heightLeft = height(root_node.LeftChild);
        heightRight = height(root_node.RightChild);
        if (Math.abs(heightLeft - heightRight) <= 1 && IsBalanced(root_node.LeftChild) && IsBalanced(root_node.RightChild)) {
            return true;
        }

        return false; // сбалансировано ли дерево с корнем root_node
    }

    public BSTNode1 makeTree(int[] a, BSTNode1 parent) {
        if (a.length == 1) {
            BSTNode1 bstNode1 = new BSTNode1(a[0], parent);
            bstNode1.Level = bstNode1.Parent.Level + 1;
            return bstNode1;
        }
        BSTNode1 bstNode1 = new BSTNode1(a[a.length / 2], parent);
        if (Root == null) {
            Root = bstNode1;
            bstNode1.Level = 1;
        } else {
            bstNode1.Level = bstNode1.Parent.Level + 1;
        }

        int[] left = new int[a.length / 2];
        System.arraycopy(a, 0, left, 0, left.length);
        bstNode1.LeftChild = makeTree(left, bstNode1);
        if (a.length > 2) {
            int[] right = new int[a.length - a.length / 2 - 1];
            System.arraycopy(a, a.length - right.length, right, 0, right.length);
            bstNode1.RightChild = makeTree(right, bstNode1);
        }
        return bstNode1;
    }

    public int height(BSTNode1 bstNode1) {
        if (bstNode1 == null) {
            return 0;
        }
        return 1 + Math.max(height(bstNode1.LeftChild), height(bstNode1.RightChild));
    }
}