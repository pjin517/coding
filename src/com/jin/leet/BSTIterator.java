package com.jin.leet;

import java.util.Stack;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
 *
 * Calling next() will return the next smallest number in the BST.
 *
 * Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
 *
 */

 // Definition for binary tree
 class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) {
         val = x;
     }
 }

 class BinarySearchTree {
     TreeNode root;

     public BinarySearchTree(TreeNode root) {
         this.root = root;
     }

     public void add(int v) {
         if (root == null) {
             root = new TreeNode(v);
             return;
         }

         add(root, v);
     }

     private void add(TreeNode node, int v) {
         if (v < node.val) {
             if (node.left == null) {
                 node.left = new TreeNode(v);
                 return;
             } else {
                 add(node.left, v);
             }
         } else {
             if (node.right == null) {
                 node.right = new TreeNode(v);
                 return;
             } else {
                 add(node.right, v);
             }
         }
     }

 }

public class BSTIterator {
    TreeNode root;
    Stack<TreeNode> stack;


    public BSTIterator(TreeNode root) {
        this.root = root;
        this.stack = new Stack<>();
        TreeNode node = root;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    /**
     * @return whether we have a next smallest number
     */
    public boolean hasNext() {
        return !stack.empty();
    }

    /**
     * @return the next smallest number
     */
    public int next() {
        TreeNode cur = stack.pop();
        TreeNode node = cur.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        return cur.val;
    }

    public static void main(String args[]) {
        TreeNode root = new TreeNode(5);
        BinarySearchTree bst = new BinarySearchTree(root);
        bst.add(3);
        bst.add(2);
        bst.add(4);
        bst.add(7);
        bst.add(6);
        bst.add(8);
        
        BSTIterator bstIterator = new BSTIterator(root);

        while (bstIterator.hasNext()) {
            System.out.println(bstIterator.next());
        }

    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */


/*
My idea comes from this: My first thought was to use inorder traversal to put every node into an array, and then make an index pointer for the next() and hasNext().
That meets the O(1) run time but not the O(h) memory. O(h) is really much more less than O(n) when the tree is huge.

This means I cannot use a lot of memory, which suggests that I need to make use of the tree structure itself.
 And also, one thing to notice is the "average O(1) run time". It's weird to say average O(1), because there's nothing below O(1) in run time,
 which suggests in most cases, I solve it in O(1), while in some cases, I need to solve it in O(n) or O(h). These two limitations are big hints.

Before I come up with this solution, I really draw a lot binary trees and try inorder traversal on them. We all know that,
once you get to a TreeNode, in order to get the smallest, you need to go all the way down its left branch.
So our first step is to point to pointer to the left most TreeNode. The problem is how to do back trace.
Since the TreeNode doesn't have father pointer, we cannot get a TreeNode's father node in O(1) without store it beforehand.
Back to the first step, when we are traversal to the left most TreeNode, we store each TreeNode we met ( They are all father nodes for back trace).

After that, I try an example, for next(), I directly return where the pointer pointing at, which should be the left most TreeNode I previously found. What to do next?
After returning the smallest TreeNode, I need to point the pointer to the next smallest TreeNode.
 When the current TreeNode has a right branch (It cannot have left branch, remember we traversal to the left most),
 we need to jump to its right child first and then traversal to its right child's left most TreeNode.
 When the current TreeNode doesn't have a right branch, it means there cannot be a node with value smaller than itself father node, point the pointer at its father node.

The overall thinking leads to the structure Stack, which fits my requirement so well.

 */
