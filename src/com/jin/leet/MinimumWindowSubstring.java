package com.jin.leet;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * LeetCode 76. Minimum Window Substring
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 *
 * Example:
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * Note:
 *
 * If there is no such window in S that covers all characters in T, return the empty string "".
 * If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
 *
 * TODO： wrong
 */
public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        if (t.length()> s.length())
            return "";
        HashMap<Character, Integer> numMap = new HashMap<>();
        HashMap<Character, Integer> dict = new HashMap<>();
        for (Character c: t.toCharArray()) {
            numMap.put(c, 0);
            if (dict.containsKey(c))
                dict.put(c, dict.get(c)+1);
            else
                dict.put(c, 1);
        }

        char[] chars = s.toCharArray();
        for (int i=0; i< s.length(); i++) {
            if (numMap.containsKey(chars[i]))
                numMap.put(chars[i], numMap.get(chars[i])+1);
        }

        for (char c: dict.keySet())
            if (dict.get(c)>numMap.get(c))
                return "";

        int start = 0;
        int end = s.length()-1;

        while (start< end) {
            if (!numMap.containsKey(chars[start]))
                start++;
            else {
                int count = numMap.get(chars[start]);
                if (count==dict.get(chars[start])){
                    break;
                } else {
                    numMap.put(chars[start],count-1);
                    start++;
                }
            }
        }

        while (end > start) {
            if (!numMap.containsKey(chars[end]))
                end--;
            else {
                int count = numMap.get(chars[end]);
                if (count==dict.get(chars[end])){
                    break;
                } else {
                    numMap.put(chars[end],count-1);
                    end--;
                }
            }
        }

        return s.substring(start, end+1);
    }

    public static void main(String[] args) {
        MinimumWindowSubstring ms = new MinimumWindowSubstring();
        System.out.println(ms.minWindow("aa", "aa"));
    }
}
