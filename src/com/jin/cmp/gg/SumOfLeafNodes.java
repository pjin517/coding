package com.jin.cmp.gg;

/**
 * 求二叉树所有leaf node之和，要用O(1) space.
 * 面试官给我了两次提示才想出来...就是要记一个PreviousNode，然后根据上一次访问和当前访问的node的关系，决定下一步往哪边走
 *
 */
public class SumOfLeafNodes {
    public int sumOfLeafNodesO1Space(TreeNode root) {
        int sum = 0;
        TreeNode previous = null;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left==null && cur.right==null) {
                sum += cur.value;
                previous = cur;
                cur = cur.parent;
            } else {
                if (previous == cur.left) {
                    previous = cur;
                    cur = cur.right;
                } else if (previous == cur.right) {
                    previous = cur;
                    cur = cur.parent;
                } else if (previous == cur.parent) {
                    previous = cur;
                    if (cur.left!=null)
                        cur = cur.left;
                    else
                        cur = cur.right;
                }
            }
        }
        return sum;
    }

    // O(n) space
    public int sumOfLeafNodes(TreeNode root) {
        int[] sum = new int[1];
        dfs(root, sum);
        return sum[0];
    }

    private void dfs(TreeNode node, int[] sum) {
        if (node == null)
            return;
        if (node.left==null && node.right==null)
            sum[0] += node.value;
        else {
            dfs(node.left, sum);
            dfs(node.right, sum);
        }
    }

    public static void main(String[] args) {
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n3 = new TreeNode(3, n4, n5, null);
        n4.parent = n3;
        n5.parent = n3;
        TreeNode n2 = new TreeNode(2);
        TreeNode root = new TreeNode(1, n2, n3, null);
        n2.parent = root;
        n3.parent = root;
        SumOfLeafNodes s = new SumOfLeafNodes();
        System.out.println(s.sumOfLeafNodesO1Space(root));
    }


}


class TreeNode {
    int value;
    TreeNode left;
    TreeNode right;
    TreeNode parent;

    public TreeNode(int value, TreeNode left, TreeNode right, TreeNode parent) {
        this.value = value;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public TreeNode(int value) {
        this(value, null, null, null);
    }
}

