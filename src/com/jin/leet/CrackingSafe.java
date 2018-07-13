package com.jin.leet;

import java.util.HashSet;

/** LeetCode 753. Cracking the Safe
 *
 * There is a box protected by a password. The password is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.
 *
 * You can keep inputting the password, the password will automatically be matched against the last n digits entered.
 *
 * For example, assuming the password is "345", I can open it when I type "012345", but I enter a total of 6 digits.
 *
 * Please return any string of minimum length that is guaranteed to open the box after the entire string is inputted.
 *
 * Example 1:
 * Input: n = 1, k = 2
 * Output: "01"
 * Note: "10" will be accepted too.
 * Example 2:
 * Input: n = 2, k = 2
 * Output: "00110"
 * Note: "01100", "10011", "11001" will be accepted too.
 * Note:
 * n will be in the range [1, 4].
 * k will be in the range [1, 10].
 * k^n will be at most 4096.
 *
 *
 * Tip:
 * This is kinda greedy approach.
 * So straight up we can tell that we have k^n combinations of the lock.
 * So the best way to generate the string is reusing last n-1 digits of previous lock << I can't really prove this but I realized this after writing down some examples.
 *
 * Hence, we'll use dfs to generate the string with goal is when our string contains all possible combinations.
 *
 */
public class CrackingSafe {
    public String crackSafe(int n, int k) {
        if (n==1 && k==1)
            return "0";
        StringBuilder ans = new StringBuilder();
        HashSet<String> visited = new HashSet<>(n*k);
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n-1; i++)
            sb.append("0");
        String start = sb.toString();
        ans.append(start);
        dfs(start, n, k, visited, ans);
        return ans.toString();
    }

    private boolean dfs(String start, int n, int k, HashSet<String> visited, StringBuilder ans) {
        int total = (int) (Math.pow(k, n));
        if (visited.size() == total)
            return true;

        for (int i=0; i<k; i++) {
            String comb = start+i;
            if (!visited.contains(comb)) {
                visited.add(comb);
                ans.append(i);
                if (!dfs(comb.substring(1), n, k, visited, ans)) {
                    visited.remove(comb);
                    ans.deleteCharAt(ans.length()-1);
                    continue;
                } else {
                    return true;
                }
            }
        }
        return false;
    }


    public static void main(String[] args) {
        CrackingSafe cs = new CrackingSafe();
        System.out.println(cs.crackSafe(2, 1));
    }
}
