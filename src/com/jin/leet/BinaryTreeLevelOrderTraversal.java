package com.jin.leet;

/**
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its level order traversal as:
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BinaryTreeLevelOrderTraversal {
    public static List<List<Integer>> levelOrderWithQueue(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root==null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> l = new LinkedList<>();
            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                l.add(node.val);
                if (node.left!=null)
                    queue.offer(node.left);
                if (node.right!=null)
                    queue.offer(node.right);
            }
            result.add(l);
        }
        return result;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList();
        levelTraverse(root, 1, result);
        return result;
    }

    public static void levelTraverse(TreeNode node, int level, List<List<Integer>> result) {
        if (node == null)
            return;
        if (result.size()<level) {
            result.add(new LinkedList<Integer>());
        }
        result.get(level-1).add(node.val);
        levelTraverse(node.left, level+1, result);
        levelTraverse(node.right, level+1, result);
    }

}
