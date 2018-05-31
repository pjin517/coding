package com.jin.cmp.gg;
/*
'.' Matches any single character.
'*' Matches zero or more of the preceding element.

The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

Some examples:
isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
isMatch("aab", "c*a*b") → true

https://leetcode.com/problems/regular-expression-matching/description/
https://www.cs.princeton.edu/courses/archive/spr09/cos333/beautiful.html
 */
public class Leet10ReularExp {
    public boolean isMatch(String text, String pattern) {
        if (text == "" && pattern == "")
            return true;
        return isMatch(text.toCharArray(), pattern.toCharArray(), 0, 0);
    }

    public boolean isMatch(char[] text, char[] pattern, int i, int j) {
        if (i == text.length-1 && j == pattern.length-1) {
            if (text[i] == pattern[j])
                return true;
        }

        if (pattern[j+1] == '*') {
            return matchStar(pattern[j], text, pattern, i, j);
        }

        if (pattern[j] == '.' || pattern[j] == text[i]) {
            return isMatch(text, pattern, i++, j++);
        }

        return false; // to pass compile
    }

    private boolean matchStar(char previous, char[] text, char[] pattern, int i, int j) {
        return false; // to pass compile
    }
}
