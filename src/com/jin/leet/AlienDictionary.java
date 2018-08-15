package com.jin.leet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 269	Alien Dictionary
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
 *
 * For example, Given the following words in dictionary,
 *
 * [
 *   "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"
 * ]
 * The correct order is: "wertf".
 *
 * Note: You may assume all letters are in lowercase. If the order is invalid, return an empty string. There may be multiple valid order of letters, return any one of them is fine.
 *
 */
public class AlienDictionary {
    public String alienOrder(String[] words) {
        if (words.length == 1)
            return words[0];
        CharGraph graph = new CharGraph();
        for (int i=0; i<words.length-1; i++) {
            char[] w1 = words[i].toCharArray();
            char[] w2 = words[i+1].toCharArray();

            for (char c: w1)
                graph.addNode(c);

            for (char c: w2)
                graph.addNode(c);

            if (words[i].equals(words[i+1]))
                continue;

            int j = 0;
            while (j<w1.length && j<w2.length) {
                if (w1[j] == w2[j])
                    j++;
                else {
                    graph.addEdge(w1[j], w2[j]);
                    break;
                }
            }
        }
        return graph.topoSort();
    }

    public List<String> allAlienOrder(String[] words) {
        CharGraph graph = new CharGraph();
        for (int i=0; i<words.length-1; i++) {
            char[] w1 = words[i].toCharArray();
            char[] w2 = words[i+1].toCharArray();
            int j = 0;
            while (j<w1.length && j<w2.length) {
                if (w1[j] == w2[j])
                    j++;
                else
                    break;
            }
            graph.addEdge(w1[j], w2[j]);
        }
        List<String> result = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        graph.allTopoSort(sb, result);
        return result;
    }

    public static void main(String[] args) {
        AlienDictionary ad1 = new AlienDictionary();
        AlienDictionary ad2 = new AlienDictionary();
        System.out.println(ad1.alienOrder(new String[] {"z", "z"}));
        System.out.println(ad2.alienOrder(new String[] {"wrt", "wrf",  "er", "ett", "rftt"}));

//        AlienDictionary ad = new AlienDictionary();
//        System.out.println("+++++++++++++++++++all:");
//        for (String s: ad.allAlienOrder(new String[] {"a1", "ab", "b1", "b2"})) {
//            System.out.println(s);
//        }
    }
}

class CharGraph {
    HashMap<Character, List<Character>> adj;
    HashMap<Character, Integer> indegree;

    public CharGraph() {
        adj = new HashMap<>();
        indegree = new HashMap<>();
    }

    public void addEdge(char u, char v) {
        if (!adj.containsKey(u))
            adj.put(u, new LinkedList<>());
        if (!adj.containsKey(v))
            adj.put(v, new LinkedList<>());
        if (!indegree.containsKey(u))
            indegree.put(u, 0);
        if (!indegree.containsKey(v))
            indegree.put(v, 0);

        List<Character> a = adj.get(u);
        a.add(v);
        indegree.put(v, indegree.get(v)+1);
    }

    public void addNode(char u) {
        if (!adj.containsKey(u))
            adj.put(u, new LinkedList<>());
        if (!indegree.containsKey(u))
            indegree.put(u, 0);
    }

    public String topoSort() {
        StringBuilder result = new StringBuilder();
        LinkedList<Character> roots = new LinkedList<>();
        for (char v: indegree.keySet()) {
            if (indegree.get(v) == 0)
                roots.add(v);
        }
        if (roots.isEmpty())
            return "";

        while (!roots.isEmpty()) {
            char v = roots.poll();
            result.append(v);
            List<Character> l = adj.get(v);
            for (char c: l) {
                indegree.put(c, indegree.get(c)-1);
                if (indegree.get(c) == 0)
                    roots.add(c);
            }
        }

        if (result.length()!=indegree.size())
            return "";
        return result.toString();
    }

    public void allTopoSort(StringBuilder sb, List<String> result) {
        if (indegree.keySet().isEmpty()) {
            result.add(sb.toString());
            return;
        }

        LinkedList<Character> roots = new LinkedList<>();
        for (char v: indegree.keySet()) {
            if (indegree.get(v) == 0)
                roots.add(v);
        }

        for (char v: roots) {
            sb.append(v);
            List<Character> nodes = adj.get(v);
            for (char c: nodes) {
                indegree.put(c, indegree.get(c) - 1);
            }
            adj.remove(v);
            indegree.remove(v);
            allTopoSort(sb, result);
            sb.deleteCharAt(sb.length()-1);
            adj.put(v, nodes);
            indegree.put(v, 0);
            for (char c: nodes) {
                indegree.put(c, indegree.get(c) + 1);
            }
        }
    }
}