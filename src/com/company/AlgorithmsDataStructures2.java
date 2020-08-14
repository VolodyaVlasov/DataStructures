package com.company;

import java.util.*;
import java.util.Arrays;


public class AlgorithmsDataStructures2 {
    public static int[] b;

    public static int[] GenerateBBSTArray(int[] a) {
        b = new int[a.length];
        Arrays.sort(a);
        balance(a, 0, 0);
        return b;
    }

    public static void balance(int[] a, int level, int l) {
        if (a.length == 1) {
            b[2 * level + l] = a[0];
            return;
        }
        b[2 * level + l] = a[a.length / 2];
        int[] left = new int[a.length / 2];
        System.arraycopy(a, 0, left, 0, left.length);
        balance(left, 2 * level + l, 1);
        if (a.length > 2) {
            int[] right = new int[a.length - a.length / 2 - 1];
            System.arraycopy(a, a.length - right.length, right, 0, right.length);
            balance(right, 2 * level + l, 2);
        }
    }
}