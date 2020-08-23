

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
        Queue<Vertex> queue = new LinkedList<>();
        ArrayList<Vertex> path = new ArrayList<>();
        if (m_adjacency[VFrom][VTo] == 1) {
            path.add(vertex[VFrom]);
            path.add(vertex[VTo]);
            return path;
        }
        queue.add(vertex[VFrom]);
        for (Vertex i : vertex) {
            i.Hit = false;
            i.parent = null;
        }
        boolean stop = true;
        while (!queue.isEmpty() && stop) {
            Vertex v = queue.poll();
            v.Hit = true;
            for (int i = 0; i < max_vertex; i++) {
                if (m_adjacency[v.index][i] == 1) {
                    if (vertex[i].Value == vertex[VTo].Value) {
                        stop = false;

                    }
                    if (!vertex[i].Hit) {
                        if (vertex[i].parent == null) {
                            vertex[i].parent = v;
                        }
                        queue.add(vertex[i]);
                    }
                }
            }
        }

        Vertex p = vertex[VTo];
        while (p.parent != null) {
            path.add(p);
            p = p.parent;
        }
        if (path.size() > 0) {
            path.add(vertex[VFrom]);
        }
        Collections.reverse(path);
        return path;
    }
}
