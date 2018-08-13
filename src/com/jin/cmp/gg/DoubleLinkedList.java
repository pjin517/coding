package com.jin.cmp.gg;

public class DoubleLinkedList {
    DLLNode head;
    DLLNode end;

    public void add(int val) {
        DLLNode node = new DLLNode(val);
        if (head == null) {
            head = node;
            end = node;
            return;
        }

        end.post = node;
        node.pre = end;
        end = node;
    }

    public void addLast(int val) {
        add(val);
    }

    public void addFrist(int val) {
        DLLNode node = new DLLNode(val);
        if (head == null) {
            head = node;
            end = node;
            return;
        }

        node.post = head;
        head.pre = node;
        head = node;
    }

    public void remove(int val) {
        // find node with value val
        DLLNode p = head;
        while (p != null) {
            if (p.value == val)
                break;
            p = p.post;
        }
        if (p == null)
            return;

        if (p.pre!=null)
            p.pre.post = p.post;
        if (p.post!=null)
            p.post.pre = p.pre;
        if (p==head)
            head = p.post;
        if (p==end)
            end = p.pre;
        p.pre = null;
        p.post = null;
    }

    public void print() {
        DLLNode p = head;
        while (p!=null) {
            System.out.print(p.value + " ");
            p = p.post;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoubleLinkedList list = new DoubleLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.print();
        list.addFrist(0);
        list.print();
        list.addLast(4);
        list.print();
        list.remove(3);
        list.print();
        list.remove(0);
        list.print();
        list.remove(4);
        list.print();
        list.add(3);
        list.print();
        list.addFrist(0);
        list.print();
        list.remove(0);
        list.remove(1);
        list.remove(2);
        list.print();
        list.remove(3);
        list.print();
    }
}

class DLLNode {
    int value;
    DLLNode pre;
    DLLNode post;

    public DLLNode(int value, DLLNode pre, DLLNode post) {
        this.value = value;
        this.pre = pre;
        this.post = post;
    }

    public DLLNode(int value) {
        this(value, null, null);
    }
}
