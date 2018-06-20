package com.jin.leet.unionfind;

import java.util.HashMap;

/** LeetCode128
 *
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * Your algorithm should run in O(n) complexity.
 *
 * Example:
 *
 * Input: [100, 4, 200, 1, 3, 2]
 * Output: 4
 * Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        if (nums.length <= 1)
            return nums.length;

        SequenceUnionFind unionFind = new SequenceUnionFind(nums);
        HashMap<Integer, Integer> numSet = new HashMap<>();
        for (int i=0; i<nums.length; i++) {
            if (numSet.containsKey(nums[i]))
                continue;
            if (numSet.containsKey(nums[i] - 1)) {
                unionFind.union(numSet.get(nums[i] - 1), i);
            }
            if (numSet.containsKey(nums[i] + 1)) {
                unionFind.union(i, numSet.get(nums[i]+1));
            }
            numSet.put(nums[i], i);
        }
        return unionFind.highestRank;
    }

    public static void main(String args[]) {
        int nums[] = new int[] {100, 4, 200, 1, 3, 2};
        LongestConsecutiveSequence sequence = new LongestConsecutiveSequence();
        System.out.println("Longest consecutive sequence of " + nums + " is " + sequence.longestConsecutive(nums));
    }
}

class SequenceUnionFind {
    int [] nums;
    int [] sequencesRoots;
    int [] ranks;
    int highestRank;
    int size;

    public SequenceUnionFind(int[] nums) {
        this.nums = nums;
        this.size = nums.length;
        sequencesRoots = new int[size];
        ranks = new int[size];
        for (int i=0; i<size; i++) {
            sequencesRoots[i] = i;
            ranks[i] = 1;
        }
        highestRank = 1;
    }

    public int find(int x) {
        while (x != sequencesRoots[x]) {
            sequencesRoots[x] = sequencesRoots[sequencesRoots[x]];
            x = sequencesRoots[x];
        }
        return x;
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        int localHighestRank;
        if (rootX == rootY) {
            return; // no merge needed
        }

        if (ranks[rootX] >= ranks[rootY]) {
            sequencesRoots[rootY] = rootX;
            ranks[rootX] += ranks[rootY];
            localHighestRank = ranks[rootX];
        } else {
            sequencesRoots[rootX] = rootY;
            ranks[rootY] += ranks[rootX ];
            localHighestRank = ranks[rootY];
        }

        highestRank = Math.max(localHighestRank, highestRank);
    }
}
