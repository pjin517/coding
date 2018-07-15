package com.jin.leet;

import java.util.Arrays;

/**
 * LeetCode 307 Range Sum Query - Mutable
 *
 *Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * The update(i, val) function modifies nums by updating the element at index i to val.
 *
 * Example:
 *
 * Given nums = [1, 3, 5]
 *
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * Note:
 *
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is distributed evenly.
 */
public class RangeSumQuery {
    int len;
    int[] nums;
    int[] sums;

    public RangeSumQuery(int[] nums) {
        this.nums = nums;
        this.len = nums.length;
        this.sums = new int[len];
        if (len > 0)
            this.sums[0] = nums[0];
        for (int i=1; i<len; i++) {
            this.sums[i] = this.sums[i-1] + this.nums[i];
        }
    }

    public void update(int i, int val) {
        int old = nums[i];
        nums[i] = val;
        int diff = val - old;
        for (int k = i; k< len; k++) {
            sums[k] += diff;
        }
    }

    public int sumRange(int i, int j) {
        if (i==0)
            return sums[j];
        return sums[j] - sums[i-1];
    }


    public static void main(String[] args) {
        /**
         * ["NumArray","sumRange","update","sumRange"]
         * [[[1,3,5]],  [0,2],     [1,2],  [0,2]]
         */
        int a[] = {1, 3, 5};
        RangeSumQueryWithSegmentTree rs = new RangeSumQueryWithSegmentTree(a);
        System.out.println("Sum 0-2: " + rs.sumRange(0, 2));
        rs.update(1, 2);
        System.out.println("Sum 0-2: " + rs.sumRange(0, 2));

    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */

class RangeSumQueryWithSegmentTree {
    SegmentTree tree;
    int[] nums;

    public RangeSumQueryWithSegmentTree(int[] nums) {
        this.nums = nums;
        this.tree = new SegmentTree(nums);
    }

    public void update(int i, int val) {
        this.tree.update(i, val);
    }

    public int sumRange(int i, int j) {
        if (nums==null || nums.length == 0)
            return 0;
        return this.tree.sumRange(i, j, tree.root);
    }

}

class SegmentTree {
    int[] array;
    SegmentTreeNode root;

    public SegmentTree(int[] array) {
        this.array = array;
        this.root = new SegmentTreeNode();
        build(array, 0, array.length-1, root);
    }

    private void build(int[] array, int l, int r, SegmentTreeNode node) {
        if (array==null || array.length==0)
            return;
        node.l = l;
        node.r = r;
        if (l == r) {
            node.sum = array[l];
        } else {
            int mid = (l+r)/2;
            node.leftChild = new SegmentTreeNode();
            build(array, l, mid, node.leftChild);
            node.rightChild = new SegmentTreeNode();
            build(array, mid+1, r, node.rightChild);
            node.sum = node.leftChild.sum + node.rightChild.sum;
        }
    }

    public int sumRange(int start, int end, SegmentTreeNode root) {
        if (start == root.l && end == root.r)
            return root.sum;
        int mid = (root.l+root.r)/2;
        if (end <= mid)
            return sumRange(start, end, root.leftChild);
        if (start >= mid+1)
            return sumRange(start, end, root.rightChild);
        return sumRange(start, mid, root.leftChild) + sumRange(mid+1, end, root.rightChild);
    }

    public void update(int i, int val) {
        int old = this.array[i];
        this.array[i] = val;
        update(i, val - old, root);
    }

    private void update(int i, int diff, SegmentTreeNode node) {
        node.sum += diff;
        if (i == node.l && i==node.r)
            return;

        int mid = (node.l + node.r)/2;
        if (i <= mid)
            update(i, diff, node.leftChild);
        else
            update(i, diff, node.rightChild);
    }
}

class SegmentTreeNode {
    int sum;
    int l;
    int r;
    SegmentTreeNode leftChild;
    SegmentTreeNode rightChild;
}