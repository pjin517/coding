package com.jin.leet;

/** LeetCode 549 - Binary Tree Longest Consecutive Sequence II
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
 * Especially, this path can be either increasing or decreasing.
 * For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid.
 * On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.
 *
 * Example 1:
 * Input:
 *         1
 *        / \
 *       2   3
 * Output: 2
 * Explanation: The longest consecutive path is [1, 2] or [2, 1].
 * Example 2:
 * Input:
 *         2
 *        / \
 *       1   3
 * Output: 3
 * Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 *
 *
 * http://www.cnblogs.com/grandyang/p/6864398.html
 *
 * TODO: submit
 */
public class LongestConsecutiveSequenceInBinaryTreeII {
    public int longestConsecutive(TreeNode root) {
        if (root == null)
            return 0;
        int max = helper(root, 1) + helper(root, -1) + 1;
        return Math.max(max, Math.max(longestConsecutive(root.left), longestConsecutive(root.right)));

    }

    public int helper(TreeNode root, int diff) {
        if (root == null)
            return 0;

        int left = 0;
        int right = 0;
        if (root.left!=null && root.val - root.left.val == diff )
            left = helper(root.left, diff) + 1;
        if (root.right!=null && root.val - root.right.val == diff)
            right = helper(root.right, diff) + 1;
        return Math.max(left, right);
    }
}
