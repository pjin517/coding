package com.jin.cmp.gg;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

/**
 * 设计股票价格的记录系统。只观察一支股票的价格，实现几个function：
 * 以下内容需要积分高于 100 才可浏览
 *
 * void update(double price, int timestamp)
 * 更新/修正/删除股票价格，其中timestamp大部分时间是递增的，但是有时候发现过去的记录有错误，
 * 所以会对过去数据修正或invalidate。对过去数据修正是指input argument中的timestamp是一个已经记录在案的timestamp，
 * 让price为function新提供的price；invalidat时候function argument中的price为-1，删除timestamp对应的记录
 *
 * double max() 返回历史最大值
 *
 * double min() 返回历史最低值
 *
 * double current() 返回最近一次的记录。
 *
 * 针对各种情况进行实现和算法复杂度讨论。（大量更新？大量查询？invalidate很多很少？etc.）
 *
 * http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=429208&ctid=483
 *
 */

class PriceNode {
    long timestamp;
    double price;

    PriceNode pre;
    PriceNode post;

    public PriceNode(long timestamp, double price) {
        this.timestamp = timestamp;
        this.price = price;
    }
}

class PriceList {
    PriceNode head;
    PriceNode tail;

    // always add at tail
    public void add(PriceNode node) {
        if (tail == null) {
            tail = node;
            head = node;
        } else {
            node.pre = tail;
            tail.post = node;
            tail = node;
        }
    }

    public PriceNode getLast() {
        return tail;
    }

    public void remove(PriceNode node) {
        if (node == head) {
            head = null;
            tail = null;
            return;
        }

        if (node == tail) {
            tail = node.pre;
            tail.post = null;
        }

        node.pre.post = node.post;
        node.post.pre = node.pre;
    }
}

public class StockPriceHistoryStore {
    PriceList history = new PriceList();
    HashMap<Long, PriceNode> store = new HashMap<>();
    PriorityQueue<PriceNode> minQueue = new PriorityQueue<>(100, new Comparator<PriceNode>() {
        @Override
        public int compare(PriceNode o1, PriceNode o2) {
            return (int) (o1.price - o2.price)*100;
        }
    });
    PriorityQueue<PriceNode> maxQueue = new PriorityQueue<>(100, new Comparator<PriceNode>() {
        @Override
        public int compare(PriceNode o1, PriceNode o2) {
            return (int) (o2.price - o1.price)*100;
        }
    });

    public double max() {
        if (maxQueue.isEmpty())
            return -1;
        return maxQueue.peek().price;
    }

    public double min() {
        if (minQueue.isEmpty())
            return -1;
        return minQueue.peek().price;
    }

    public double current() {
        PriceNode last = history.getLast();
        if (last != null)
            return last.price;
        return -1;
    }

    void update(long timestamp, double price) {
        // new timestamp, add new price record
        if (!store.containsKey(timestamp)) {
            PriceNode node = new PriceNode(timestamp, price);
            history.add(node);
            store.put(timestamp, node);
            maxQueue.offer(node);
            minQueue.offer(node);
        } else {
            // delete the record
            if (price == -1) {
                PriceNode node = store.remove(timestamp);
                history.remove(node);
                maxQueue.remove(node);
                minQueue.remove(node);
            } else {  // change a existing price record
                PriceNode node = store.get(timestamp);
                maxQueue.remove(node);
                minQueue.remove(node);
                node.price = price;
                maxQueue.offer(node);
                minQueue.offer(node);
            }
        }
    }

    public void report() {
        System.out.println("===================Status Report=======================");
        System.out.println("Number of records: " + store.size());
        System.out.println("Max price: " + this.max());
        System.out.println("Min price: " + this.min());
        System.out.println("Current price: " + this.current());
        System.out.println("=======================================================");
    }

    //TODO: Problems
    // Min and Max heap has bug


    public static void main(String[] args) {
        StockPriceHistoryStore store = new StockPriceHistoryStore();
        store.report();
        store.update(1000L, 11.3d);
        store.report();
        store.update(2000L, 12.4d);
        store.update(3000L, 14.4d);
        store.report();
        store.update(1000L, 15.0d);
        store.report();
        store.update(2000L, -1d);
        store.report();
        store.update(4000L, 16.0d);
        store.report();
    }
}
