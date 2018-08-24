package com.jin.leet;

/**
 * 261. Graph Valid Tree
 * Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
 *
 * Example 1:
 *
 * Input: n = 5, and edges = [[0,1], [0,2], [0,3], [1,4]]
 * Output: true
 * Example 2:
 *
 * Input: n = 5, and edges = [[0,1], [1,2], [2,3], [1,3], [1,4]]
 * Output: false
 * Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0,1] is the same as [1,0] and thus will not appear together in edges.
 */
public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        NodeUnion union = new NodeUnion(n);
        int count = n;
        for (int[] edge: edges) {
            int rootX = union.find(edge[0]);
            int rootY = union.find(edge[1]);

            // find a cycle
            if (rootX == rootY)
                return false;

            union.union(rootX, rootY);
            count--;
        }
        return count == 1;
    }

    public static void main(String args[]) {

    }
}

class NodeUnion {
        int n;
        int[] roots;

    public NodeUnion(int n) {
        this.n = n;
        this.roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = i;
        }
    }

    public int find(int x) {
        while (x != roots[x]) {
            roots[x] = roots[roots[x]];
            x = roots[x];
        }
        return x;
    }

    public void union(int x, int y){
        int rX = find(x);
        int rY = find(y);
        if (rX != rY)
        roots[rY] = roots[rX];
    }
}

