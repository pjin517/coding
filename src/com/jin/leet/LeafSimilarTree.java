package com.jin.leet;

import java.util.LinkedList;
import java.util.List;

/**
 * 872. Leaf-Similar Trees
 * Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.
 *
 *
 *
 * For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).
 *
 * Two binary trees are considered leaf-similar if their leaf value sequence is the same.
 *
 * Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.
 *
 *
 *
 * Note:
 *
 * Both of the given trees will have between 1 and 100 nodes.
 *
 */
public class LeafSimilarTree {
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> l1 = new LinkedList<>();
        List<Integer> l2 = new LinkedList<>();
        dfs(root1, l1);
        dfs(root2, l2);
        return (l1.equals(l2));
    }

    private void dfs(TreeNode treeNode, List<Integer> leaves) {
        if (treeNode == null)
            return;
        if (treeNode.left==null && treeNode.right==null) {
            leaves.add(treeNode.val);
        } else {
            dfs(treeNode.left, leaves);
            dfs(treeNode.right, leaves);
        }
    }

}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */