package com.jin.leet;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 49. Group Anagrams
 * Given an array of strings, group anagrams together.
 *
 * Example:
 *
 * Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * Output:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * Note:
 *
 * All inputs will be in lowercase.
 * The order of your output does not matter.
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = new String(chars);
            List<String> l = map.getOrDefault(key, new LinkedList<>());
            l.add(str);
            map.put(key, l);
        }
        List<List<String>> result = new LinkedList<>();
        for (List<String> l: map.values())
            ((LinkedList<List<String>>) result).push(l);
        return result;
    }

    public static void main(String args[]) {
        String[] strings = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        GroupAnagrams g = new GroupAnagrams();
        List<List<String>> r = g.groupAnagrams(strings);
        for (List<String> l: r) {
            for (String s : l)
                System.out.print(" " + s);
            System.out.println();
        }
    }
}
