import java.util.*;
import java.util.Stack;

class Vertex {
    public int Value;
    public boolean Hit;
    public int index;

    public Vertex(int val, int i) {
        Value = val;
        Hit = false;
        index = i;
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

    public boolean DFS(Vertex VFrom, Vertex Vto) {
        VFrom.Hit = true;
        stack.push(VFrom);
        for (int i = 0; i < max_vertex; i++) {
            if (m_adjacency[VFrom.index][i] == 1 && vertex[i].Value == Vto.Value) {
                stack.add(vertex[i]);
                return true;
            }
        }
        for (int i = 0; i < max_vertex; i++) {
            if (m_adjacency[VFrom.index][i] == 1 && !vertex[i].Hit ) {
                return DFS(vertex[i], Vto);
            }
        }
        stack.pop();
        if (!stack.isEmpty()) {
            DFS(stack.pop(), Vto);
        }
        return false;
    }
}
