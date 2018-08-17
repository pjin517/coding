package com.jin.leet;

import java.util.HashSet;

/**
 * 720. Longest Word in Dictionary
 * Given a list of strings words representing an English Dictionary, find the longest word in words that can be built one character at a time by other words in words. If there is more than one possible answer, return the longest word with the smallest lexicographical order.
 *
 * If there is no answer, return the empty string.
 * Example 1:
 * Input:
 * words = ["w","wo","wor","worl", "world"]
 * Output: "world"
 * Explanation:
 * The word "world" can be built one character at a time by "w", "wo", "wor", and "worl".
 * Example 2:
 * Input:
 * words = ["a", "banana", "app", "appl", "ap", "apply", "apple"]
 * Output: "apple"
 * Explanation:
 * Both "apply" and "apple" can be built from other words in the dictionary. However, "apple" is lexicographically smaller than "apply".
 * Note:
 *
 * All the strings in the input will only contain lowercase letters.
 * The length of words will be in the range [1, 1000].
 * The length of words[i] will be in the range [1, 30].
 */
public class LongestWordInDictionary {
    public String longestWord(String[] words) {
        HashSet<String> set = new HashSet<>();
        for (String w: words)
            set.add(w);

        String ans = "";
        for (String word: words) {
            if (word.length()>ans.length()||word.length()==ans.length() && word.compareTo(ans)<0) {
                boolean good = true;
                for (int i=1; i<=word.length(); i++) {
                    if (!set.contains(word.substring(0, i))) {
                        good = false;
                        break;
                    }
                }
                if (good)
                    ans = word;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] words = new String[] {"m","mo","moc","moch","mocha","l","la","lat","latt","latte","c","ca","cat"};
        LongestWordInDictionary l = new LongestWordInDictionary();
        System.out.print(l.longestWord(words));
    }
}
