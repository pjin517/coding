package com.jin.leet;

/**
 * LeetCode 298 Binary Tree Longest Consecutive Sequence
 *
 * Given a binary tree, find the length of the longest consecutive sequence path.
 * The path refers to any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The longest consecutive path need to be from parent to child (cannot be the reverse).
 * For example,
 *    1
 *     \
 *      3
 *     / \
 *    2   4
 *         \
 *          5
 * Longest consecutive sequence path is 3-4-5, so return 3.
 *    2
 *     \
 *      3
 *     /
 *    2
 *   /
 *  1
 * Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
 *
 *
 * TODO: submit
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class LongestConsecutiveSequenceInBinaryTree {
    int max = 0;
    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        dfs(root, root.val, 0);
        return max;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.left = new TreeNode(2);
        LongestConsecutiveSequenceInBinaryTree lcsbt = new LongestConsecutiveSequenceInBinaryTree();
        System.out.println(lcsbt.longestConsecutive(root));
    }

    private void dfs(TreeNode node, int parentValue, int length) {
        if (node == null) {
            return;
        }
        if ( node.val == parentValue+1) {
            length++;
        } else {
            length = 1;
        }
        this.max = Math.max(this.max, length);
        dfs(node.left, node.val, length);
        dfs(node.right, node.val, length);
    }
}
