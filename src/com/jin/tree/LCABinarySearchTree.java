package com.jin.tree;

/**
 * LeetCode 235. Lowest Common Ancestor of a Binary Search Tree
 *
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 *
 * According to the definition of LCA on Wikipedia:
 * “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”
 *
 * Given binary search tree:  root = [6,2,8,0,4,7,9,null,null,3,5]
 *
 *         _______6______
 *        /              \
 *     ___2__          ___8__
 *    /      \        /      \
 *    0      _4       7       9
 *          /  \
 *          3   5
 * Example 1:
 *
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * Output: 6
 * Explanation: The LCA of nodes 2 and 8 is 6.
 * Example 2:
 *
 * Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * Output: 2
 * Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself
 *              according to the LCA definition.
 * Note:
 *
 * All of the nodes' values will be unique.
 * p and q are different and both values will exist in the BST.
 *
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

/**
 *  We can recursively traverse the BST from root. The main idea of the solution is, while traversing from top to bottom,
 *  the first node n we encounter with value between n1 and n2, i.e., n1 < n < n2 or same as one of the n1 or n2, is LCA of n1 and n2 (assuming that n1 < n2).
 *  So just recursively traverse the BST in, if node’s value is greater than both n1 and n2 then our LCA lies in left side of the node,
 *  if it’s is smaller than both n1 and n2, then LCA lies on right side. Otherwise root is LCA (assuming that both n1 and n2 are present in BST)
 */
class LCABinarySearchTree {
        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            if (root==null)
                return null;
            if (p.val > q.val)
                return lowestCommonAncestor(root, q, p);
            if (root.val == p.val || root.val == q.val || (root.val>p.val && root.val<q.val))
                return root;
            if (root.val > q.val)
                return lowestCommonAncestor(root.left, p, q);
            else
                return lowestCommonAncestor(root.right, p, q);
        }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}