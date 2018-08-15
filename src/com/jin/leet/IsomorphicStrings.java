package com.jin.leet;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two characters may map to the same character but a character may map to itself.
 *
 * Example 1:
 *
 * Input: s = "egg", t = "add"
 * Output: true
 * Example 2:
 *
 * Input: s = "foo", t = "bar"
 * Output: false
 * Example 3:
 *
 * Input: s = "paper", t = "title"
 * Output: true
 * Note:
 * You may assume both s and t have the same length.
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> map = new HashMap<>();
        HashSet<Character> used = new HashSet<>();

        for (int i=0; i< s.length(); i++){
            char a = s.charAt(i);
            char b = t.charAt(i);
            if (map.get(a) == null) {
                if (used.contains(b))
                    return false;
                map.put(a, b);
                used.add(b);
            } else {
                if (map.get(a) != b)
                    return false;
            }
        }
        return true;
    }
}
