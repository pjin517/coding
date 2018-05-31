package com.jin.graph;

import java.util.Iterator;
import java.util.LinkedList;

public class Graph<T> {
    private int size;
    private Node<T> nodes[];
    private LinkedList<Node<T>> adj[];

    public Graph(int size) {
        this.size = size;
        this.nodes = new Node[size];
        this.adj = new LinkedList[size];
        for (int i=0; i<size; i++) {
            adj[i] = new LinkedList<>();
        }
    }

    public int getSize() {
        return size;
    }

    public Node<T>[] getNodes() {
        return nodes;
    }

    public LinkedList<Node<T>>[] getAdj() {
        return adj;
    }

    public void addNode(Node<T> node) {
        this.nodes[node.getId()] = node;
    }

    public void addEdge(int src, int target) {
        if (this.nodes[src] == null || this.nodes[target] == null ) {
            throw new RuntimeException("src or target node can not be null");
        }
        this.adj[src].add(nodes[target]);
    }

    public void DFS(int nodeId) {
        boolean visited[] = new boolean[this.size];
        DFSUtil(nodeId, visited);
    }

    private void DFSUtil(int nodeId, boolean visited[]) {
        if (visited[nodeId]) {
            return;
        }

        System.out.print(this.nodes[nodeId].getValue() + " ");
        visited[nodeId] = true;

        for (Node n: adj[nodeId]) {
            DFSUtil(n.getId(), visited);
        }
    }

    public static void main(String args[])
    {
        Graph g = new Graph<Integer>(4);
        g.addNode(new Node(0, 0));
        g.addNode(new Node(1, 1));
        g.addNode(new Node(2, 2));
        g.addNode(new Node(3, 3));

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println("Following is Depth First Traversal "+
                "(starting from vertex 2)");
        for (Node n : g.nodes) {
            Iterator<Node<Integer>> i = g.adj[n.getId()].listIterator();
            while (i.hasNext()) {
                Node<Integer> o = i.next();
                System.out.print(o.getValue() + " ");
            }
            System.out.println();
        }

        System.out.println("\n>>>>>>>>> DFS of 0 <<<<<<<<<<<<<<");
        g.DFS(0);

        System.out.println("\n>>>>>>>>> DFS of 1 <<<<<<<<<<<<<<");
        g.DFS(1);

        System.out.println("\n>>>>>>>>> DFS of 2 <<<<<<<<<<<<<<");
        g.DFS(2);

        System.out.println("\n>>>>>>>>> DFS of 3 <<<<<<<<<<<<<<");
        g.DFS(3);
    }
}



