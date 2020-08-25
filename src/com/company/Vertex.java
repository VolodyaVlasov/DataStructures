package com.company;

import java.util.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Vertex {
    public int Value;
    public boolean Hit;
    public int index;
    public Vertex parent;

    public Vertex(int val, int i) {
        Value = val;
        Hit = false;
        index = i;
        parent = null;
    }
}

class SimpleGraph {
    Vertex[] vertex;
    int[][] m_adjacency;
    int max_vertex;
    Stack<Vertex> stack;

    public SimpleGraph(int size) {
        max_vertex = size;
        m_adjacency = new int[size][size];
        vertex = new Vertex[size];
    }

    public void AddVertex(int value) {
        for (int i = 0; i < max_vertex; i++) {
            if (vertex[i] == null) {
                vertex[i] = new Vertex(value, i);
                break;
            }
        }
    }

    public void RemoveVertex(int v) {
        vertex[v] = null;
        for (int i = 0; i < max_vertex; i++) {
            m_adjacency[v][i] = 0;
            m_adjacency[i][v] = 0;
        }
    }

    public boolean IsEdge(int v1, int v2) {
        return m_adjacency[v1][v2] == 1;
    }

    public void AddEdge(int v1, int v2) {
        m_adjacency[v1][v2] = 1;
        m_adjacency[v2][v1] = 1;
    }

    public void RemoveEdge(int v1, int v2) {
        m_adjacency[v1][v2] = 0;
        m_adjacency[v2][v1] = 0;
    }

    public ArrayList<Vertex> DepthFirstSearch(int VFrom, int VTo) {
        stack = new Stack<>();
        for (Vertex i : vertex) {
            i.Hit = false;
        }
        DFS(vertex[VFrom], vertex[VTo]);
        return new ArrayList<>(stack);
    }

    public void DFS(Vertex VFrom, Vertex Vto) {
        VFrom.Hit = true;
        stack.push(VFrom);
        for (int i = 0; i < max_vertex; i++) {
            if (m_adjacency[VFrom.index][i] == 1 && vertex[i].Value == Vto.Value) {
                stack.add(vertex[i]);
                return;
            }
        }
        for (int i = 0; i < max_vertex; i++) {
            if (m_adjacency[VFrom.index][i] == 1 && !vertex[i].Hit) {
                DFS(vertex[i], Vto);
                return;
            }
        }
        stack.pop();
        if (!stack.isEmpty()) {
            DFS(stack.pop(), Vto);
        }
    }


    public ArrayList<Vertex> BreadthFirstSearch(int VFrom, int VTo) {
        ArrayList<Vertex> path = new ArrayList<>();
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(vertex[VFrom]);
        for (Vertex i : vertex) {
            i.Hit = false;
            i.parent = null;
        }
        outerloop:
        while (!queue.isEmpty()) {
            Vertex v = queue.poll();
            for (int i = 0; i < max_vertex; i++) {
                if (m_adjacency[v.index][i] == 1 && !vertex[i].Hit) {
                    Vertex temp = vertex[i];
                    temp.parent = v;
                    if (temp == vertex[VTo]) {
                        break outerloop;
                    }
                    temp.Hit = true;
                    queue.add(temp);
                }
            }
            v.Hit = true;
        }
        Vertex p = vertex[VTo].parent;
        if (p != null) {
            path.add(vertex[VTo]);
        }
        while (p != null) {
            path.add(p);
            if (p.parent != null && p.Value == p.parent.Value) {
                break;
            }
            p = p.parent;

        }
        Collections.reverse(path);
        return path;
    }

    public ArrayList<Vertex> WeakVertices() {
        ArrayList<Vertex> arrayList = new ArrayList<>();
        Vertex one;
        Vertex two;
        for (Vertex i : vertex) {
            i.Hit = false;
        }
        for (int i = 0; i < max_vertex; i++) {
            one = vertex[i];
            if (!one.Hit) {
                for (int j = 0; j < max_vertex; j++) {
                    if (j != i && m_adjacency[i][j] == 1) {
                        two = vertex[j];
                        Vertex temp = isTriangle(one, two);
                        if (temp != null) {
                            one.Hit = true;
                            two.Hit = true;
                            temp.Hit = true;
                            break;
                        }
                    }

                }
            }
        }
        for (Vertex i : vertex) {
            if (!i.Hit) {
                arrayList.add(i);
            }
        }
        return arrayList;
    }


    public Vertex isTriangle(Vertex one, Vertex two) {
        for (int i = 0; i < max_vertex; i++) {
            if (m_adjacency[one.index][i] == 1 && m_adjacency[two.index][i] == 1 && m_adjacency[one.index][two.index] == 1) {
                return vertex[i];
            }
        }
        return null;
    }
}
