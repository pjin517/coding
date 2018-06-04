package com.jin.leet;

import java.util.HashMap;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LRUCache cache = new LRUCache( 2 ); //capacity
 *
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4, 4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 *
 */

public class LRUCache {
    HashMap<Integer, Node> cache;
    DoublyLinkedList list = new DoublyLinkedList();
    int capacity;


    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new HashMap<>(capacity);
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            refresh(cache.get(key));
            return cache.get(key).value;

        }
        return -1;

    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;
            list.moveFirst(node);
        } else {
            if (cache.size() == capacity) {
                evict();
            }
            Node node = list.add(new Node(key, value));
            cache.put(key, node);
        }
    }


    private void refresh(Node n) {
        if (n != list.head)
            list.moveFirst(n);
    }

    private void evict() {
        Node node = list.removeLast();
        cache.remove(node.key);
    }
}

class Node {
    int key;
    int value;
    Node pre;
    Node post;

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
        this.pre = null;
        this.post = null;
    }
}

class DoublyLinkedList {
    private int size = 0;
    Node head;
    Node tail;

    // always add to head
    public Node add(Node node) {
        node.post = head;
        if (head != null) {
            head.pre = node;
        }
        head = node;
        if (tail == null)
            tail = node;

        size++;
        return node;
    }

    public void moveFirst(Node node) {
        this.remove(node);
        node.pre = null;
        node.post = null;
        this.add(node);
    }

    public void remove(Node node) {
        if (size ==1 ) {
            head = tail = null;
        } else {

            if (head == node) {
                head = node.post;
                head.pre = null;
            } else if (tail == node) {
                tail = node.pre;
                tail.post = null;
            } else {
                node.pre.post = node.post;
                node.post.pre = node.pre;
            }
        }
        size --;
    }

    public Node removeLast() {
        if (size == 0)
            return null;

        Node ret = tail;
        tail = tail.pre;
        if (tail != null)
            tail.post = null;

        size--;
        if (size == 0) {
            head = null;
            tail = null;
        }
        return ret;
    }

    public static void main(String args[]) {
        LRUCache obj = new LRUCache(2);
        obj.put(2, 1);
        obj.put(1, 1);
        obj.put(2, 3);
        obj.put(4, 1);
        System.out.println("Cache get 2: " + obj.get(1));
        System.out.println("Cache get 1: " + obj.get(2));

    }

}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */