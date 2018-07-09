package com.jin.leet;

/** LeetCode616 and LeetCode758
 *
 * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in dict.
 * If two such substrings overlap, you need to wrap them together by only one pair of closed bold tag.
 * Also, if two substrings wrapped by bold tags are consecutive, you need to combine them.
 *
 * Example 1:
 * Input:
 * s = "abcxyz123"
 * dict = ["abc","123"]
 * Output:
 * "<b>abc</b>xyz<b>123</b>"
 * Example 2:
 * Input:
 * s = "aaabbcc"
 * dict = ["aaa","aab","bc"]
 * Output:
 * "<b>aaabbc</b>c"
 */


import java.util.Arrays;

/**
 *  Hint:
 *  Use a boolean array to mark if character at each position is bold or not. After that, things will become simple.
 */
public class AddBoldTagInString {
    public static String addBoldTag(String s, String[] dict) {
        int length = s.length();
        boolean[] bold = new boolean[length];

        int end = -1;
        for (int i=0; i<length; i++) {
            for(String word: dict) {
                if (s.startsWith(word, i)) {
                    if (i + word.length() -1 > end ) {
                        end = i + word.length() -1;
                        Arrays.fill(bold, i, end + 1, true);
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i< length; i++) {
            if (bold[i] && (i==0 || !bold[i-1])) {
                sb.append("<b>");
            }
            if (i>0 && !bold[i] && bold[i-1]) {
                sb.append("</b>");
            }

            sb.append(s.charAt(i));

            if (bold[i] && i == length-1) {
                sb.append("</b>");
            }
        }
        return sb.toString();
    }

    public static void main(String args[]) {
        String s1 = "abcxyz123";
        System.out.println("abcxyz123 => " + addBoldTag(s1, new String[] {"abc","123"}));
        String s2 = "aaabbcc";
        System.out.println("aaabbcc => " + addBoldTag(s2, new String[] {"aaa","aab","bc"}));
        String s3 = "aaabbcc";
        System.out.println("aaabbcc => " + addBoldTag(s3, new String[] {}));
        String s4 = "aaabbcc";
        System.out.println("aaabbcc => " + addBoldTag(s4, new String[] {"a", "b", "c"}));
    }
}
