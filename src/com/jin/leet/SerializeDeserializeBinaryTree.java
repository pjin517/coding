package com.jin.leet;

import java.util.LinkedList;

/** LeetCode 297. Serialize and Deserialize Binary Tree
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
 *
 * Example:
 *
 * You may serialize the following tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * as "[1,2,3,null,null,4,5]"
 * Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
 *
 * Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 *
 * Tip: https://www.geeksforgeeks.org/succinct-encoding-of-binary-tree/
 */
public class SerializeDeserializeBinaryTree  {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root==null)
            return "";
        StringBuilder sb = new StringBuilder();
        LinkedList<Integer> data = new LinkedList<>();
        encodeSuccinct(root, sb, data);
        sb.append('@');
        for (int v: data) {
            sb.append(v+ ",");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String input) {
        if (input==null|| input.isEmpty())
            return null;
        String[] result = input.split("@");
        LinkedList<Character> structure = new LinkedList<>();
        LinkedList<Integer> data = new LinkedList<>();
        for (char c: result[0].toCharArray())
            structure.addLast(c);
        for (String d: result[1].split(","))
            data.addLast(Integer.parseInt(d));
        return decodeSuccinct(structure, data);
    }

    private void encodeSuccinct(TreeNode node, StringBuilder sb, LinkedList<Integer> data) {
        if (node == null) {
            sb.append('0');
        } else {
            sb.append('1');
            data.add(node.val);
            encodeSuccinct(node.left, sb, data);
            encodeSuccinct(node.right, sb, data);
        }
    }

    private TreeNode decodeSuccinct(LinkedList<Character> structure,LinkedList<Integer> data) {
        char c = structure.removeFirst();
        if (c == '1') {
            TreeNode node = new TreeNode(data.removeFirst());
            node.left = decodeSuccinct(structure, data);
            node.right = decodeSuccinct(structure, data);
            return node;
        } else
            return null;

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        SerializeDeserializeBinaryTree codec = new SerializeDeserializeBinaryTree();
        String s = codec.serialize(root);
        codec.deserialize(s);
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

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
