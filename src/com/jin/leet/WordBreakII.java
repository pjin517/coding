package com.jin.leet;

import java.util.*;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the segmentation.
 * You may assume the dictionary does not contain duplicate words.
 * Example 1:
 *
 * Input:
 * s = "catsanddog"
 * wordDict = ["cat", "cats", "and", "sand", "dog"]
 * Output:
 * [
 *   "cats and dog",
 *   "cat sand dog"
 * ]
 * Example 2:
 *
 * Input:
 * s = "pineapplepenapple"
 * wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 * Output:
 * [
 *   "pine apple pen apple",
 *   "pineapple pen apple",
 *   "pine applepen apple"
 * ]
 * Explanation: Note that you are allowed to reuse a dictionary word.
 * Example 3:
 *
 * Input:
 * s = "catsandog"
 * wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output:
 * []
 */
public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return backtrack(s, wordDict, new HashMap<>());
    }

    private List<String> backtrack(String s, List<String> wordDict, Map<String, List<String>> mem) {
        List<String> result = new LinkedList<>();

        if (mem.containsKey(s))
            return mem.get(s);

        for (String word : wordDict) {
            if (s.startsWith(word)) {
                if (word.length() == s.length()) {
                    result.add(s);   //NOTE: can't return here, there may be other shorter words in dict that are also prefix
                }
                List<String> subResult = backtrack(s.substring(word.length()), wordDict, mem);
                if (subResult.size()>0) {
                    for (String sub : subResult) {
                        result.add(word + " " + sub);
                    }
                }
            }
        }

        mem.put(s, result);
        return result;
    }

    public static void main(String[] args) {
        WordBreakII wb = new WordBreakII();
        System.out.println("\npineapplepenapple: ");
        for (String s: wb.wordBreak("pineapplepenapple", Arrays.asList("apple", "pen", "applepen", "pine", "pineapple"))) {
            System.out.println(s);
        }
        System.out.println("\ncatsanddog: ");
        for (String s: wb.wordBreak("catsanddog", Arrays.asList("cat", "cats", "and", "sand", "dog"))) {
            System.out.println(s);
        }
        System.out.println("\ncatsandog: ");
        for (String s: wb.wordBreak("catsandog", Arrays.asList("cat", "cats", "and", "sand", "dog"))) {
            System.out.println(s);
        }
        System.out.println("\naaaaaaa: ");
        for (String s: wb.wordBreak("aaaaaaa", Arrays.asList("aaaa", "aa", "a"))) {
            System.out.println(s);
        }
    }
}

