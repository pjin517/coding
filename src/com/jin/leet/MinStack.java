package com.jin.leet;

/**
 * 155. Min Stack
 *
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.
 * Example:
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> Returns -3.
 * minStack.pop();
 * minStack.top();      --> Returns 0.
 * minStack.getMin();   --> Returns -2.
 *
 */
public class MinStack {
    Node head;

    /** initialize your data structure here. */
    public MinStack() {

    }

    public void push(int x) {
        if (this.head == null) {
            this.head = new Node(x, x);
        } else {
            Node node = new Node(x, Math.min(x, head.min), head);
            head = node;
        }
    }

    public void pop() {
        Node tmp = head;
        head = head.next;
        tmp.next = null;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }

    private class Node {
        int val;
        int min;
        Node next;

        private Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }

        private Node(int val, int min) {
            this(val, min, null);
        }
    }
}



/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */