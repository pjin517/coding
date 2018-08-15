package com.jin.leet;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 460. LFU Cache
 * DescriptionHintsSubmissionsDiscussSolution
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would be evicted.
 *
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 *
 * Example:
 *
 * LFUCache cache = new LFUCache(2);
        *
        *cache.put(1,1);
        *cache.put(2,2);
        *cache.get(1);       // returns 1
        *cache.put(3,3);    // evicts key 2
        *cache.get(2);       // returns -1 (not found)
        *cache.get(3);       // returns 3.
        *cache.put(4,4);    // evicts key 1.
        *cache.get(1);       // returns -1 (not found)
        *cache.get(3);       // returns 3
        *cache.get(4);       // returns 4
 *
 *
 *TODO: submit
 * https://medium.com/@epicshane/a-python-implementation-of-lfu-least-frequently-used-cache-with-o-1-time-complexity-e16b34a3c49b
 */

public class LFUCache {
    int capacity = 0;
    HashMap<Integer, DlinkedNode> data;
    HashMap<Integer, DlinkedList> frequencies;
    PriorityQueue<Integer> freqQue;


    public LFUCache(int capacity) {
        this.capacity = capacity;
        data = new HashMap<>();
        frequencies = new HashMap<>();
        DlinkedList freq1 = new DlinkedList();
        frequencies.put(1, freq1);
        freqQue = new PriorityQueue<>();
        freqQue.offer(1);
    }

    public int get(int key) {
        DlinkedNode e = data.get(key);
        if (e == null)
            return -1;

        // increase the frequency of this data and move it to coresponding frequency list
        DlinkedList list = frequencies.get(e.frequency);
        list.remove(e);
        if (list.size() == 0) {
            frequencies.remove(e.frequency);
            freqQue.remove(e.frequency);
        }
        e.frequency++;
        DlinkedList newList = frequencies.get(e.frequency);
        if (newList == null) {
            newList = new DlinkedList();
        }
        newList.addLast(e);
        frequencies.put(e.frequency, newList);
        freqQue.offer(e.frequency);

        return e.value;
    }

    public void put(int key, int value) {
        if (capacity == 0)
            return;

        if (data.containsKey(key)){
            //update value and add to the end of list
            int v = get(key);
            if (v!=value) {
                DlinkedNode node = data.get(key);
                node.value = value;
            }
        } else {
            if (data.size() == capacity) {
                int lowestFreq = freqQue.peek();
                DlinkedList freq1 = frequencies.get(lowestFreq);
                DlinkedNode node = freq1.removeFirst();
                if (freq1.size() == 0) {
                    frequencies.remove(lowestFreq);
                    freqQue.poll();
                }
                data.remove(node.key);
            }
            // add the new cache value to the end of frequency 1 list
            DlinkedList freqList = frequencies.get(1);
            if (freqList == null) {
                freqList = new DlinkedList();
                frequencies.put(1, freqList);
                freqQue.offer(1);
            }
            DlinkedNode node = freqList.addLast(key, value);
            data.put(key, node);
        }
    }

    class DlinkedList {
        int size;
        DlinkedNode head;
        DlinkedNode tail;

        public DlinkedList() {
            size = 0;
            head = new DlinkedNode(-1, -1);
            tail = new DlinkedNode(-1, -1, head, null);
            head.post = tail;
        }

        public int size() {
            return size;
        }

        public void addFirst(DlinkedNode node) {
            node.post = head.post;
            node.pre = head;
            head.post = head;
            node.post.pre = node;
            size++;
        }

        public DlinkedNode addFirst(int key, int val) {
            DlinkedNode node = new DlinkedNode(key, val);
            addFirst(node);
            return node;
        }

        public DlinkedNode addLast(int key, int val) {
            DlinkedNode node = new DlinkedNode(key, val);
            addLast(node);
            return node;
        }

        public void addLast(DlinkedNode node) {
            node.post = tail;
            node.pre = tail.pre;
            tail.pre = node;
            node.pre.post = node;
            size++;
        }

        public DlinkedNode removeFirst() {
            if (head.post == tail)
                return null;
            DlinkedNode result = head.post;
            head.post = head.post.post;
            head.post.pre = head;
            size--;
            return result;
        }

        public DlinkedNode removeLast() {
            if (tail.pre == head)
                return null;
            DlinkedNode result = tail.pre;
            tail.pre = tail.pre.pre;
            tail.pre.post = tail;
            size--;
            return result;
        }

        public Integer getFirst() {
            if (head.post == tail)
                return null;
            return  head.post.value;
        }

        public DlinkedNode getFirstNode() {
            if (head.post == tail)
                return null;
            return head.post;
        }

        public Integer getLast() {
            if (tail.pre == head)
                return null;
            return tail.pre.value;
        }

        public DlinkedNode getLastNode() {
            if (tail.pre == head)
                return null;
            return tail.pre;
        }

        public void remove(DlinkedNode node) {
            if (node == null || node==head || node==tail)
                return;
            node.pre.post = node.post;
            node.post.pre = node.pre;
            size--;
        }
    }
    class DlinkedNode {
        int key;
        int value;
        DlinkedNode pre;
        DlinkedNode post;
        int frequency;

        public DlinkedNode(int key, int value, DlinkedNode pre, DlinkedNode post) {
            this.key = key;
            this.value = value;
            this.pre = pre;
            this.post = post;
            this.frequency = 1;
        }

        public DlinkedNode(int key, int value) {
            this(key, value, null, null);
        }
    }

    public static void main(String[] args) {
        LFUCache obj = new LFUCache(3);

        obj.put(2, 2);
        System.out.println("obj.put(2, 2), data size: " + obj.data.size());
        obj.put(1, 1);
        System.out.println("obj.put(1, 1), data size: " + obj.data.size());
        System.out.println("get(2): " + obj.get(2));
        System.out.println("get(1): " + obj.get(1));
        System.out.println("get(2): " + obj.get(2));
        obj.put(3, 3);
        System.out.println("obj.put(3, 3), data size: " + obj.data.size());
        obj.put(4, 4);
        System.out.println("obj.put(4, 4), data size: " + obj.data.size());
        System.out.println("get(3): " + obj.get(3));
        System.out.println("get(2): " + obj.get(2));
        System.out.println("get(1): " + obj.get(1));
        System.out.println("get(4): " + obj.get(4));
    }
}
