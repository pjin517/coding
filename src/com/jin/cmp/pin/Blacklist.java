package com.jin.cmp.pin;

/**
 * 	blacklist of words, scan 某个query里面存不存在blacklist 里面的word。（用trie可做）
 * From <http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=417745&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3046%5D%5Bvalue%5D%3D33%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
 */
public class Blacklist {
    String[] blacklistWords;
    BlacklistTrie trie;

    public Blacklist(String[] blacklistWords) {
        this.blacklistWords = blacklistWords;
        trie = new BlacklistTrie();
        for (String word: blacklistWords) {
            trie.add(word);
        }
    }

    public String hasBlacklistWord(String query) {
        String[] words = query.split(" ");
        for (String w: words) {
            if (trie.hasWord(w))
                return w;
        }
        return "N/A";
    }

    public static void main(String args[]) {
        Blacklist bl = new Blacklist(new String[] {"fxxk", "shxt"});
        System.out.println(bl.hasBlacklistWord("i am very happy today"));
        System.out.println(bl.hasBlacklistWord("this is fxxk stupid"));
    }
}

class BlacklistTrie {
    BlacklistTrieNode root;

    public BlacklistTrie() {
        this.root = new BlacklistTrieNode();
    }

    public void add(String word) {
        word = word.toLowerCase();
        BlacklistTrieNode node = root;
        for (char c: word.toCharArray()) {
            if (node.children[c-'a'] == null)
                node.children[c-'a'] = new BlacklistTrieNode();
            node = node.children[c-'a'];
        }
        node.isWord = true;
        node.word = word;
    }

    public boolean hasWord(String word) {
        word = word.toLowerCase();
        BlacklistTrieNode node = root;
        for (char c: word.toCharArray()) {
            if (node.children[c-'a'] == null)
                return false;
            node = node.children[c-'a'];
        }
        return node.isWord;
    }
}

class BlacklistTrieNode{
    BlacklistTrieNode[] children;
    boolean isWord;
    String word;

    public BlacklistTrieNode() {
        this.children = new BlacklistTrieNode[26];
    }
}