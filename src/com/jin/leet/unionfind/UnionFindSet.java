package com.jin.leet.unionfind;

/**
 *  http://zxi.mytechroad.com/blog/data-structure/sp1-union-find-set/
 */
public class UnionFindSet {
    private int size;
    private int[] parents;
    private int[] ranks;

    public UnionFindSet(int size) {
        this.size = size;
        parents = new int[size];
        ranks = new int[size];
        for (int i=0; i<size; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }
    }

    /**
     * Union(x,y) uses Find to determine the roots of the trees x and y belong to.
     * If the roots are distinct, the trees are combined by attaching the root of one to the root of the other.
     * If this is done naively, such as by always making x a child of y, the height of the trees can grow as O(n).
     * To prevent this union by rank is used.
     *
     * @return True if x, y is merged. False if x, y already belong to same root.
     */
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY)
            return false;

        //merge lower rank tree to higher rank tree
        if (ranks[rootX] > ranks[rootY]) {
            parents[rootY] = rootX;
            ranks[rootX]++;
        }
        if (ranks[rootX] < ranks[rootY]) {
            parents[rootX] = rootY;
            ranks[rootY]++;
        }
        if (ranks[rootX] == ranks[rootY]) {
            parents[rootY] = rootX;
            ranks[rootX]++;
        }

        return true;
    }

    /**
     * find(x) follows the chain of parent pointers from x up the tree until it reaches a root element,
     * whose parent is itself. This root element is the representative member of the set to which x belongs,
     * and may be x itself. Path compression is done during this process.
     */
    public int find(int x) {
        while(parents[x] != x) {
            parents[x] = parents[parents[x]];
            x = parents[x];
        }
        return x;
    }

    public static void main(String[] args){
        UnionFindSet ufs = new UnionFindSet(6);
        ufs.union(0, 1);
        ufs.union(0, 2);
        ufs.union(2, 3);
    }
}
