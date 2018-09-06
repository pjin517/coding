package com.jin.cmp.fb;

/**
 * 10. Regular Expression Matching
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like . or *.
 * Example 1:
 *
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input:
 * s = "aa"
 * p = "a*"
 * Output: true
 * Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
 * Example 3:
 *
 * Input:
 * s = "ab"
 * p = ".*"
 * Output: true
 * Explanation: ".*" means "zero or more (*) of any character (.)".
 * Example 4:
 *
 * Input:
 * s = "aab"
 * p = "c*a*b"
 * Output: true
 * Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
 * Example 5:
 *
 * Input:
 * s = "mississippi"
 * p = "mis*is*p*."
 * Output: false
 */
public class RegExp {
    public boolean isMatch(String s, String p) {
        // when pattern is empty, the string has to be empty too, otherwise is not a match
        if (p.isEmpty()) return s.isEmpty();

        // first chars has to match or first char in pattern is '.'
        boolean matchFirst = (!s.isEmpty()) && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');

        boolean isMatch = false;
        if (p.length()> 1 && p.charAt(1) == '*') {
            // '*' matches 0 or multiple times of the preceding element
            isMatch = isMatch(s, p.substring(2)) || (matchFirst && isMatch(s.substring(1), p));
        } else {
            isMatch = matchFirst && isMatch(s.substring(1), p.substring(1));
        }
        return isMatch;
    }

    public static void main(String args[]) {

    }
}
