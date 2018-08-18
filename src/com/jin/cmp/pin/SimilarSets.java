package com.jin.cmp.pin;

import java.util.*;

/**
 * 输入一个data stream，比如：
 * 	a -> [b, c, d]
 * 	b -> [a, c, d, f]
 * 	c -> [b, d, j]
 * 	.........  
 * 比如说第一行，表示 a 和 b, c, d 相似；第二行表示 b 和 a, c, d, f 相似。。相似是可以传递的，那么a和f也相似，最后输出所有相似的set
 */
public class SimilarSets {
    List<Set<Character>> similarSets(HashMap<Character, char[]> input) {
        HashSet<Character> set = new HashSet<>();
        UnionFind union = new UnionFind(26);
        for (char key: input.keySet()) {
            set.add(key);
            for (char c: input.get(key)) {
                set.add(c);
                union.union(key-'a', c-'a');
            }
        }

        HashMap<Character, Set<Character>> result = new HashMap<>();
        for (char c: set) {
            char parent = (char)('a' + union.parents[c-'a']);
            Set<Character> l = result.get(parent);
            if (l == null) {
                l = new HashSet<>();
                result.put(parent, l);
            }
            l.add(c);
        }
        List<Set<Character>> r= new LinkedList<>();
        for (Set<Character> s: result.values()) {
            r.add(s);
        }
        return r;
    }

    public static void main(String args[]) {
        HashMap<Character, char[]> map = new HashMap<>();
        map.put('a', new char[]{'b', 'c'});
        map.put('b', new char[]{'c', 'd', 'f'});
        map.put('e', new char[]{'g', 'h'});

        SimilarSets similarSets = new SimilarSets();
        for (Set<Character> s: similarSets.similarSets(map)) {
            for (char c: s) {
                System.out.print(c+" ");
            }
            System.out.println();
        }
    }
}

class UnionFind {
    int size;
    int[] parents;
    int[] ranks;

    public UnionFind(int size) {
        this.size = size;
        parents = new int[size];
        ranks = new int[size];
        for (int i=0; i<size; i++) {
            parents[i] = i;
            ranks[i] = 1;
        }
    }

    public void union(int x, int y){
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY)
            return;
        if (ranks[rootX]>=ranks[rootY]) {
            parents[rootY] = rootX;
            ranks[rootX]++;
        }
    }

    public int find(int x) {
        while (x!=parents[x]) {
            parents[x] = parents[parents[x]];
            x=parents[x];
        }
        return x;
    }
}
