package com.jin.leet;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 792. Number of Matching Subsequences
 *
 * Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.
 *
 * Example :
 * Input:
 * S = "abcde"
 * words = ["a", "bb", "acd", "ace"]
 * Output: 3
 * Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
 * Note:
 *
 * All words in words and S will only consists of lowercase letters.
 * The length of S will be in the range of [1, 50000].
 * The length of words will be in the range of [1, 5000].
 * The length of words[i] will be in the range of [1, 50].
 *
 **
 */
public class NumberOfMatchingSubSequences {
    public static int numMatchingSubseqBrutalForce(String S, String[] words) {
        int n = 0;
        int[] indexes = new int[words.length];
        for (int i=0; i<S.length(); i++) {
            char c = S.charAt(i);
            for (int j=0; j<words.length; j++) {
                if (indexes[j] < words[j].length()) {
                    if (words[j].charAt(indexes[j]) == c) {
                        indexes[j]++;
                        if (indexes[j] == words[j].length())
                            n++;
                    }
                }
            }
        }
        return n;
    }

    public static int numMatchingSubseq(String S, String[] words) {
        int matchNumber = 0;
        HashMap<Character, ArrayList<Integer>> indexMap = new HashMap<>();
        char[] s = S.toCharArray();

        //Build index list for each char
        for (int i=0; i<S.length(); i++) {
            ArrayList<Integer> a = indexMap.getOrDefault(s[i], new ArrayList<>());
            a.add(i);
            indexMap.put(s[i], a);
        }

        for (String word: words) {
            char[] chars = word.toCharArray();
            int idx = -1;
            boolean hasSeq = true;
            for (char c: chars) {
                ArrayList<Integer> a = indexMap.get(c);
                if (a == null) {
                    hasSeq = false;
                    break;
                }
                int i;
                for (i=0; i<a.size(); i++) {
                    if (a.get(i) > idx) {
                        idx = a.get(i);
                        break;
                    }
                }
                if (i==a.size()) {
                    hasSeq = false;
                    break;
                }
            }
            if (hasSeq)
                matchNumber++;
        }

        return matchNumber;
    }

    public static void main(String[] args) {
        String S = "abcde";
        String[] words = new String[] { "a", "bb", "acd", "ace"};
        System.out.println(numMatchingSubseq(S, words));
    }
}
