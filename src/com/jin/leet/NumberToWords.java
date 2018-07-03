package com.jin.leet;

import java.util.HashMap;

/**
 * LeetCode273
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.
 *
 * Example 1:
 *
 * Input: 123
 * Output: "One Hundred Twenty Three"
 * Example 2:
 *
 * Input: 12345
 * Output: "Twelve Thousand Three Hundred Forty Five"
 * Example 3:
 *
 * Input: 1234567
 * Output: "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 * Example 4:
 *
 * Input: 1234567891
 * Output: "One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 *
 * https://leetcode.com/problems/integer-to-english-words/discuss/70625/My-clean-Java-solution-very-easy-to-understand
 *
 */


/**
 * private final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
 * private final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
 * private final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};
 *
 * public String numberToWords(int num) {
 *     if (num == 0) return "Zero";
 *
 *     int i = 0;
 *     String words = "";
 *
 *     while (num > 0) {
 *         if (num % 1000 != 0)
 *     	    words = helper(num % 1000) +THOUSANDS[i] + " " + words;
 *     	num /= 1000;
 *     	i++;
 *     }
 *
 *     return words.trim();
 * }
 *
 * private String helper(int num) {
 *     if (num == 0)
 *         return "";
 *     else if (num < 20)
 *         return LESS_THAN_20[num] + " ";
 *     else if (num < 100)
 *         return TENS[num / 10] + " " + helper(num % 10);
 *     else
 *         return LESS_THAN_20[num / 100] + " Hundred " + helper(num % 100);
 * }
 */


public class NumberToWords {
    final int THOUSAND = 1000;
    final int MILLION = 1000000;
    final int BILLION = 1000000000;
    HashMap<Integer, String> digitMap = new HashMap<>();
    HashMap<Integer, String> rank = new HashMap<>();

    public NumberToWords() {
        rank.put(1, "Thousand");
        rank.put(2, "Million");
        rank.put(3, "Billion");

        digitMap.put(1, "One");
        digitMap.put(2, "Two");
        digitMap.put(3, "Three");
        digitMap.put(4, "Four");
        digitMap.put(5, "Five");
        digitMap.put(6, "Six");
        digitMap.put(7, "Seven");
        digitMap.put(8, "Eight");
        digitMap.put(9, "Nine");
        digitMap.put(10, "Ten");
        digitMap.put(11, "Eleven");
        digitMap.put(12, "Twelve");
        digitMap.put(13, "Thirteen");
        digitMap.put(14, "Fourteen");
        digitMap.put(15, "Fifteen");
        digitMap.put(16, "Sixteen");
        digitMap.put(17, "Seventeen");
        digitMap.put(18, "Eighteen");
        digitMap.put(19, "Nineteen");
        digitMap.put(20, "Twenty");
        digitMap.put(30, "Thirty");
        digitMap.put(40, "Forty");
        digitMap.put(50, "Fifty");
        digitMap.put(60, "Sixty");
        digitMap.put(70, "Seventy");
        digitMap.put(80, "Eighty");
        digitMap.put(90, "Ninety");

    }

    public String numberToWords(int num) {
        StringBuilder sb = new StringBuilder();
        int billion = num/BILLION;
        if (billion>0)
            sb.append(numberToWordsUtil(billion) + " Billion" );
        int million = (num%BILLION)/MILLION;
        if (million>0) {
            if (sb.length()>0)
                sb.append(" ");
            sb.append(numberToWordsUtil(million) + " Million");
        }
        int thousand = (num%MILLION)/THOUSAND;
        if (thousand>0) {
            if (sb.length()>0)
                sb.append(" ");
            sb.append(numberToWordsUtil(thousand) + " Thousand");
        }
        num = num % THOUSAND;
        if (num>0) {
            if (sb.length()>0)
                sb.append(" ");
            sb.append(numberToWordsUtil(num));
        }
        return sb.toString();
    }

    // convert 3-digit number to words
    private String numberToWordsUtil(int num) {
        int hundred = num/100;
        int ten = (num%100)/10;
        int one = (num%10);
        StringBuilder sb = new StringBuilder();
        if (hundred>0) {
            sb.append(digitMap.get(hundred) + " Hundred");
        }
        if (ten == 0) {
            if (one!=0)
                sb.append(" and");
        } else if (ten>0) {
            if (sb.length()>0)
                sb.append(" ");
            if (ten==1)
                sb.append(digitMap.get(num%100));
            else
                sb.append(digitMap.get(ten *10));
        }
        if (one>0 && ten!=1) {
            if (sb.length()>0)
                sb.append(" ");
            sb.append(digitMap.get(one));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        NumberToWords ntw = new NumberToWords();
        System.out.println("123: " + ntw.numberToWords(123));
        System.out.println("12345: " + ntw.numberToWords(12345));
        System.out.println("1234567: " + ntw.numberToWords(1234567));
        System.out.println("1234567891: " + ntw.numberToWords(1234567891));
        System.out.println("101101101: " + ntw.numberToWords(101101101));
    }
}
