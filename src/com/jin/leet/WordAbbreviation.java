package com.jin.leet;

import java.util.*;

/**
 * LeetCode527. Word Abbreviation
 *
 * Given an array of n distinct non-empty strings, you need to generate minimal possible abbreviations for every word following rules below.
 *
 * Begin with the first character and then the number of characters abbreviated, which followed by the last character.
 * If there are any conflict, that is more than one words share the same abbreviation, a longer prefix is used instead of
 * only the first character until making the map from word to abbreviation become unique. In other words, a final abbreviation
 * cannot map to more than one original words.
 *
 * If the abbreviation doesn't make the word shorter, then keep it as original.
 * Example:
 *
 * Input: ["like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion"]
 * Output: ["l2e","god","internal","me","i6t","interval","inte4n","f2e","intr4n"]
 * Note:
 *
 * Both n and the length of each word will not exceed 400.
 * The length of each word is greater than 1.
 * The words consist of lowercase English letters only.
 * The return answers should be in the same order as the original array.
 *
 * http://www.cnblogs.com/grandyang/p/6818742.html
 * http://massivealgorithms.blogspot.com/2017/04/leetcode-527-word-abbreviation.html
 */
public class WordAbbreviation {
    public List<String> wordsAbbreviation(List<String> dict) {
        List<String> origDict = dict;
        HashMap<String, String> abbr2word = new HashMap<>();
        HashMap<String, String> word2abbr = new HashMap<>();
        List<String> conflicts;
        int level = 1;
        while (!dict.isEmpty()) {
            conflicts = new LinkedList<>();
            for (String word : dict) {
                String abbr = getAbbrevation(word, level);
                if (abbr2word.containsKey(abbr)) {
                    conflicts.add(word);
                    conflicts.add(abbr2word.get(abbr));
                    abbr2word.remove(abbr);
                    word2abbr.remove(abbr2word.get(abbr));
                } else {
                    abbr2word.put(abbr, word);
                    word2abbr.put(word, abbr);
                }
            }
            level++;
            dict = conflicts;
        }
        List<String> result = new LinkedList<>();
        for (String w: origDict)
            result.add(word2abbr.get(w));
        return result;
    }

    private String getAbbrevation(String word, int prefixLen) {
        if (word.length()<=3)
            return word;
        if (prefixLen + 2 >= word.length())
            return word;
        char[] wordChars = word.toCharArray();
        StringBuilder sb = new StringBuilder();
        sb.append(Arrays.copyOfRange(wordChars, 0, prefixLen));
        sb.append(word.length()-prefixLen-1);
        sb.append(wordChars[word.length()-1]);
        return sb.toString();
    }

    public static void main(String[] argv) {
        WordAbbreviation wordAbbreviation = new WordAbbreviation();
        List<String> dict =  Arrays.asList("like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion");
        System.out.println(wordAbbreviation.wordsAbbreviation(dict));
    }
}
