package com.jin.leet;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 * Example 1:
 *      3
 *     / \
 *    2   3
 *     \   \
 *      3   1
 * Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 * Example 2:
 *      3
 *     / \
 *    4   5
 *   / \   \
 *  1   3   1
 * Maximum amount of money the thief can rob = 4 + 5 = 9.
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


/** class gain is data structure reprensent the gain information at each node..
  * we use bottom - up manner to collect the maximum gain information during backtracking
  * the field rob means the maximum profit the robber can gain by robbing the current node
  * the field noRob means the maximum profit the robber can gain by not robbing the current node
  *
  * so if we choose to rob the current node, then its left and right child should be norob;
  * so Gain.rob = L.noRob + R.noRob + node.val;
  *
  * else if we choose not to rob the current node, then we can either choose to rob or not rob its children
  * depend on which way can provide the largest profit
  * so Gain.noRob = Math.max(L.noRob, L.rob) + Math.max(R.noRob, R.rob);
  */

public class HouseRobberIII {
    public int rob(TreeNode root) {
        Gain gain = robTree(root);
        return Math.max(gain.rob, gain.noRob);
    }

    Gain robTree(TreeNode node) {
        Gain gain = new Gain(0,0);
        if (node == null)
            return gain;
        Gain left = robTree(node.left);
        Gain right = robTree(node.right);

        gain.rob = node.val + left.noRob + right.noRob;
        gain.noRob = Math.max(left.rob,  left.noRob) + Math.max(right.rob, right.noRob);
        return gain;
    }

    class Gain {
        int rob;
        int noRob;

        public Gain(int rob, int noRob) {
            this.rob = rob;
            this.noRob = noRob;
        }
    }

}
