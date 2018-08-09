package com.jin.leet;

import java.util.LinkedList;
import java.util.List;

/**
 * 784. Letter Case Permutation
 *
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.
 *
 * Examples:
 * Input: S = "a1b2"
 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * Input: S = "3z4"
 * Output: ["3z4", "3Z4"]
 *
 * Input: S = "12345"
 * Output: ["12345"]
 *
 * Note:
 *
 * S will be a string with length at most 12.
 * S will consist only of letters or digits.
 */
public class LetterCasePermutation {
    public  List<String> letterCasePermutation(String S) {
        char[] chars = S.toCharArray();
        List<String> result = new LinkedList<>();
        helper(chars, 0, result);
        return result;
    }

    private  void helper(char[] chars, int idx, List<String> result) {
        if (idx == chars.length) {
            result.add(new String(chars));
            return;
        }

        if (chars[idx] >='a' && chars[idx] <= 'z') {
            helper(chars, idx+1, result);
            chars[idx] = Character.toUpperCase(chars[idx]);
            helper(chars, idx+1, result);
        } else if (chars[idx] >='A' && chars[idx] <= 'Z') {
            helper(chars, idx+1, result);
            chars[idx] = Character.toLowerCase(chars[idx]);
            helper(chars, idx+1, result);
        } else {
            helper(chars, idx+1, result);
        }
    }

    public static void main(String[] args) {
        LetterCasePermutation lcp = new LetterCasePermutation();
        for (String s: lcp.letterCasePermutation("a1b2"))
            System.out.println(s);
    }
}
