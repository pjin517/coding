package com.jin.cmp.pin;

public class LongestIncreasingSequenceInBinaryTree {

    public int longestConsecutive(TreeNode root) {
        if (root==null) return 0;
        int max = helper(root, 1) + helper(root, -1) +1;
        return Math.max(max, Math.max(longestConsecutive(root.left), longestConsecutive(root.right)));
    }

    private int helper(TreeNode node, int diff) {
        if (node == null)
            return 0;
        int left = 0;
        int right = 0;
        if (node.left!=null && node.val-node.left.val == diff)
            left = helper(node.left, diff) + 1;
        if (node.right!=null && node.val-node.right.val == diff)
            right = helper(node.right, diff) + 1;
        return Math.max(left, right);

    }

    public static void main(String args[]) {

    }
}

// Definition for binary tree
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}