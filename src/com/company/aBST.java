package com.company;

import java.util.*;

class aBST {
    public Integer[] Tree;
    public int tree_size;

    public aBST(int depth) {
        tree_size = (int) (Math.pow(2, depth + 1) - 1);
        Tree = new Integer[tree_size];
        for (int i = 0; i < tree_size; i++) Tree[i] = null;
    }

    public Integer FindKeyIndex(int key) {
        int index = 0;
        while (index < tree_size) {
            Integer check = Tree[index];
            if (check == null) {
                return -index;
            }
            if (check == key) {
                return index;
            }
            if (check > key) {
                index = 2 * index + 1;
            } else {
                index = 2 * index + 2;
            }
        }
        return null;
    }

    public int AddKey(int key) {
        Integer index = FindKeyIndex(key);
        if (index == null) {
            return -1;
        }
        if (index == 0) {
            if (Tree[index] == null) {
                Tree[index] = key;
            }
        } else if (index < 0) {
            index = Math.abs(index);
            Tree[index] = key;
        }
        return index;
    }
}