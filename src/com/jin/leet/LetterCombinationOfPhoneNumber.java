package com.jin.leet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 17. Letter Combinations of a Phone Number
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *
 *
 *
 * Example:
 *
 * Input: "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * Note:
 *
 * Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
public class LetterCombinationOfPhoneNumber {
    HashMap<Character, char[]> mapping;

    public LetterCombinationOfPhoneNumber() {
        this.mapping = new HashMap<>();
        mapping.put('2', new char[]{'a', 'b', 'c'});
        mapping.put('3', new char[]{'d', 'e', 'f'});
        mapping.put('4', new char[]{'g', 'h', 'i'});
        mapping.put('5', new char[]{'j', 'k', 'l'});
        mapping.put('6', new char[]{'m', 'n', 'o'});
        mapping.put('7', new char[]{'p', 'q', 'r', 's'});
        mapping.put('8', new char[]{'t', 'u', 'v'});
        mapping.put('9', new char[]{'w', 'x', 'y', 'z'});
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new LinkedList<>();
        if (digits==null || digits.length()==0)
            return result;
        util("", digits.toCharArray(), 0, result);
        return result;
    }

    private void util(String prefix, char[] digits, int idx, List<String> result) {
        if (idx == digits.length) {
            result.add(prefix);
            return;
        }
        char c = digits[idx];
        char[] letters = mapping.get(c);
        for (char letter: letters) {
            util(prefix+letter, digits, idx+1, result);
        }
    }

    public static void main(String[] args){
        LetterCombinationOfPhoneNumber l = new LetterCombinationOfPhoneNumber();
        List<String> list = l.letterCombinations("23");
        for (String s: list) {
            System.out.print(" " +s);
        }
    }
}

