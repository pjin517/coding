package com.jin.leet;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 103. Binary Tree Zigzag Level Order Traversal
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).
 *
 * For example:
 * Given binary tree [3,9,20,null,null,15,7],
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * return its zigzag level order traversal as:
 * [
 *   [3],
 *   [20,9],
 *   [15,7]
 * ]
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null)
            return result;
        Queue<TreeNode> queue = new LinkedList<>();
        boolean leftToRight = true;
        queue.offer(root);
        while (queue.size()>0) {
            int n=0;
            int size = queue.size();
            LinkedList<Integer> list = new LinkedList<>();
            result.add(list);
            while (n++<size) {
                TreeNode node = queue.poll();
                if (leftToRight)
                    list.add(node.val);
                else
                    list.addFirst(node.val);
                if (node.left!=null)
                    queue.add(node.left);
                if (node.right!=null)
                    queue.add(node.right);
            }
            leftToRight = !leftToRight;
        }
        return result;

    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        BinaryTreeZigzagLevelOrderTraversal b = new BinaryTreeZigzagLevelOrderTraversal();
        List<List<Integer>> r = b.zigzagLevelOrder(root);
        for (List<Integer> l: r) {
            for (int i : l)
                System.out.print(" " + i);
            System.out.println();
        }
    }
}
