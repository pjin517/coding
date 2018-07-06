package com.jin.cmp.gg;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AutoCompleteWithTrie {
    private String[] dictionary;
    Trie trie;

    public AutoCompleteWithTrie(String[] dictionary) {
        this.dictionary = dictionary;
        this.trie = new Trie();
        for (String word: dictionary) {
            trie.insert(word);
        }
    }

    public List<String> searchPrefix(String prefix) {
        return trie.searchPrefix(prefix);
    }


    public static void main(String args[])
    {
        // Input keys (use only 'a' through 'z' and lower case)
        String keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};

        String output[] = {"Not present in trie", "Present in trie"};


        Trie trie = new Trie();

        // Construct trie
        int i;
        for (i = 0; i < keys.length ; i++)
            trie.insert(keys[i]);

        // Search for different keys
        if(trie.search("the") == true)
            System.out.println("the --- " + output[1]);
        else System.out.println("the --- " + output[0]);

        if(trie.search("these") == true)
            System.out.println("these --- " + output[1]);
        else System.out.println("these --- " + output[0]);

        if(trie.search("their") == true)
            System.out.println("their --- " + output[1]);
        else System.out.println("their --- " + output[0]);

        if(trie.search("thaw") == true)
            System.out.println("thaw --- " + output[1]);
        else System.out.println("thaw --- " + output[0]);


        AutoCompleteWithTrie autoCompleteWithTrie = new AutoCompleteWithTrie(keys);
        //search prefix
        System.out.println("Words starts with th: " + autoCompleteWithTrie.searchPrefix("th"));

    }
}

class Trie {
    static final int ALPHABET_SIZE = 26;

    TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode ptr = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (ptr.children[idx] == null) {
                ptr.children[idx] = new TrieNode();
            }
            ptr = ptr.children[idx];
        }
        ptr.isEndOfWord = true;
        ptr.word = word;
    }

    public boolean search(String word) {
        TrieNode ptr = root;
        for (char c: word.toCharArray()) {
            int idx = c - 'a';
            if (ptr.children[idx] == null)
                return false;
            ptr = ptr.children[idx];
        }
        return ptr.isEndOfWord;
    }

    public List<String> searchPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode ptr = root;
        for (char c: prefix.toCharArray()) {
            int idx = c - 'a';
            if (ptr.children[idx] == null)
                return result;
            ptr = ptr.children[idx];
        }

        result = getAlChildWords(ptr);

        return result;
    }

    private List<String> getAlChildWords(TrieNode node) {
        LinkedList<String> result = new LinkedList<>();
        LinkedList<TrieNode> queue = new LinkedList<>();
        if (node == null)
            return result;

        queue.offer(node);
        while (!queue.isEmpty()) {
            TrieNode n = queue.poll();
            if (n.isEndOfWord)
                result.addLast(n.word);
            for (TrieNode k: n.children) {
                if (k!=null)
                    queue.offer(k);
            }
        }
        return result;
    }
}

class TrieNode {
    TrieNode[] children = new TrieNode[26];
    String word;
    boolean isEndOfWord;
}