package com.jin.leet;

import java.util.HashSet;

/**
 * LeetCode345
 *
 * Write a function that takes a string as input and reverse only the vowels of a string.
 *
 * Example 1:
 * Given s = "hello", return "holle".
 *
 * Example 2:
 * Given s = "leetcode", return "leotcede".
 *
 * Note:
 * The vowels does not include the letter "y".
 *
 */
public class ReverseVowels {
    HashSet<Character> vowels;

    public ReverseVowels() {
        this.vowels = new HashSet<>();
        vowels.add('A');
        vowels.add('a');
        vowels.add('E');
        vowels.add('e');
        vowels.add('I');
        vowels.add('i');
        vowels.add('O');
        vowels.add('o');
        vowels.add('U');
        vowels.add('u');
    }

    public String reverseVowels(String s) {
        if (s.length()<2)
            return s;
        char[] chars = s.toCharArray();
        int i = 0;
        int j = s.length()-1;
        while(i<j) {
            if (!isVowels(chars[i])) {
                i++;
                continue;
            }
            if (!isVowels(chars[j])) {
                j--;
                continue;
            }
            char tmp = chars[i];
            chars[i] = chars[j];
            chars[j] = tmp;
            i++;
            j--;
        }
        return new String(chars);
    }

    private boolean isVowels(char c) {
        return vowels.contains(c);
    }

    public static void main(String args[]) {
        ReverseVowels rv = new ReverseVowels();
        String s = "hello";
        System.out.println("Reverse "+ s + ": " + rv.reverseVowels(s));
    }
}
