package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    public static void main(String[] args) {
        SimpleTreeNode<Integer> s1 = new SimpleTreeNode<>(1, null);
        SimpleTreeNode<Integer> s2 = new SimpleTreeNode<>(2, null);
        SimpleTreeNode<Integer> s3 = new SimpleTreeNode<>(3, null);
        SimpleTreeNode<Integer> s4 = new SimpleTreeNode<>(4, null);
        SimpleTreeNode<Integer> s5 = new SimpleTreeNode<>(5, null);
        SimpleTreeNode<Integer> s6 = new SimpleTreeNode<>(6, null);
        SimpleTreeNode<Integer> s7 = new SimpleTreeNode<>(7, null);
        SimpleTreeNode<Integer> s8 = new SimpleTreeNode<>(8, null);
        SimpleTreeNode<Integer> s9 = new SimpleTreeNode<>(9, null);
        SimpleTreeNode<Integer> s10 = new SimpleTreeNode<>(10, null);
        SimpleTreeNode<Integer> s17 = new SimpleTreeNode<>(17, null);
        SimpleTreeNode<Integer> s22 = new SimpleTreeNode<>(22, null);
        SimpleTreeNode<Integer> s20 = new SimpleTreeNode<>(20, null);


        SimpleTree<Integer> stn = new SimpleTree<>(s9);
        stn.AddChild(s9, s4);
        stn.AddChild(s9, s17);
        stn.AddChild(s4, s3);
        stn.AddChild(s4, s6);
        stn.AddChild(s6, s5);
        stn.AddChild(s6, s7);
        stn.AddChild(s17, s22);
       // stn.AddChild(s22, s20);







        ArrayList arrayList = stn.EvenTrees();
        for (var i : arrayList) {
            System.out.print(i + " ");
        }


    }
}
