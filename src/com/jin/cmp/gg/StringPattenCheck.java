package com.jin.cmp.gg;

import java.util.HashMap;

/**
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=425238&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26sortid%3D311
 * 给一个pattern, check true or false， 比如abbba->cdddc就是true,每个字母一种映射（两个hashmap),
 * follow up: 一次check很多string, 比如给abbba,check一个字典里面所有跟他pattern相同的，返回一个List (编码， abbba->12221，然后check)
 *
 *
 * http://www.1point3acres.com/bbs/thread-425189-1-1.html
 * 面经题，找了半天利口好像没有，e.g. “hololo”可以encode为“banana”，就是两个string里的char需是一一对应的encoding关系，
 * 不能一对多也不能多对一，这轮卡了一下，开始用的一个Map+一个Set，面试官让不用Set怎么做，其实就是把两个string的role交换一下，
 * 然后followup问要是有millions of original string，给一个encoded string问能不能从millions of string里面一个encode来，说了MapReduce的做法，时间就到了
 *
 */
public class StringPattenCheck {
    public static boolean patternMatch(String a, String b) {
        if (a==null || b==null)
            return false;

        if (a.length()!=b.length())
            return false;

        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        char[] c1 = a.toCharArray();
        char[] c2 = b.toCharArray();

        for (int i=0; i<a.length(); i++) {
            if (map1.containsKey(c1[i])) {
                if (map1.get(c1[i]) != c2[i])
                    return false;
                if (!map2.containsKey(c2[i]) || map2.get(c2[i]) != c1[i])
                    return false;
            }
            map1.put(new Character(c1[i]), new Character(c2[i]));
            map2.put(new Character(c2[i]), new Character(c1[i]));
        }
        return true;
    }

    public static void main(String args[]) {
        String a = "hololo";
        String b = "banana";
        String c = "bananb";
        System.out.println(a +" : " + b +" = " + patternMatch(a, b));
        System.out.println(a +" : " + c +" = " + patternMatch(a, c));
    }
}
