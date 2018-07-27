package com.jin.leet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/** LeetCode 46. Permutations
 * Given a collection of distinct integers, return all possible permutations.
 *
 * Example:
 *
 * Input: [1,2,3]
 * Output:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 *
 */
public class Permutation {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        permutateUtil(nums, result, new ArrayList<>(), new HashSet<>());

        return result;
    }

    private void permutateUtil(int[] nums,List<List<Integer>> result, List<Integer> temp, HashSet<Integer> used) {
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));  // NOTE: need to create a copy
            return;
        }

        for (int v : nums) {
            if (!used.contains(v)) {
                temp.add(v);
                used.add(v);
                permutateUtil(nums, result, temp, used);
                used.remove(v);
                temp.remove(temp.size()-1);
            }
        }
    }


    public static void main(String[] args) {
        Permutation p = new Permutation();
        int[] nums = new int[] {1, 2, 3};
        List<List<Integer>> result = p.permute(nums);
        for (List<Integer> l: result) {
            for (int v: l)
                System.out.print(" "+ v);
            System.out.println();
        }
    }
}
