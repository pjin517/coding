package com.jin.cmp.gg;

import java.util.PriorityQueue;

/**
 *给一个长板凳，上面坐了人是1， 没坐人是0，（list of 0 and 1)
 * 求新来的人坐在什么地方可以与其他人的距离最远 （返回坐下位置的index）。
 *
 * Follow up：如果持续不停地有人来，要如何设计算法和数据结构。
 *  (	优先队列，每次弹出最大的间隔，折半加入队列。)
 * csprogramming 发表于 2018-6-14 00:04
 * 第一题的follow up没太明白，如果不知道一共要多少次操作的话能得到最优解吗。比如三次操作的话最近距离的 ...
 * 第一个放在0，第二个放在1，第三个放在1/2，第四个放在1/4,第五个放在3/4，一直这样写，求第n个放在哪里，推导出来一个数学表达式，没让写代码
 *
 *http://www.1point3acres.com/bbs/thread-425238-3-1.html
 *
 */
class BenchSittingUtil {
    public static int[] findFirstEmptySequence(int start, int[] bench) {
        while(start < bench.length && bench[start] == 1) {
            start ++;
        }
        if (start >= bench.length)
            return new int[]{};
        int end = start;
        while (end < bench.length && bench[end] == 0) {
            end++;
        }
        end = end -1;
        return new int[]{start, end};
    }

    public static int getPosition(int[] bench) {
        int start = 0;
        int maxDistance = -1;
        int maxIndex = -1;
        while (start < bench.length) {

            int[] emptySequence = findFirstEmptySequence(start, bench);
            if (emptySequence.length == 0)
                break;

            int distance;
            if (emptySequence[0] == 0 || emptySequence[1] == bench.length-1)
                distance = emptySequence[1] - emptySequence[0];
            else
                distance = (emptySequence[1] - emptySequence[0])/2;
            if (distance > maxDistance) {
                maxDistance = distance;
                if (emptySequence[0] == 0)
                    maxIndex = 0;
                else if (emptySequence[1] == bench.length - 1)
                    maxIndex = bench.length - 1;
                else
                    maxIndex = emptySequence[0] + maxDistance;
            }
            start = emptySequence[1] + 1;
        }
        return maxIndex;
    }

//    public static void main(String args[]) {
//        System.out.println("index for {1, 0, 0, 0, 1}: " + getPosition(new int[]{1, 0, 0, 0, 1}));
//        System.out.println("index for {1, 0, 1, 0, 1}: " + getPosition(new int[]{1, 0, 1, 0, 1}));
//        System.out.println("index for {0, 0, 0, 1, 0, 1}: " + getPosition(new int[]{0, 0, 0, 1, 0, 1}));
//        System.out.println("index for {1, 0, 1, 0, 1, 0, 0, 0}: " + getPosition(new int[]{1, 0, 1, 0, 1, 0, 0, 0}));
//    }
}

class EmptySequence implements Comparable{
    int start;
    int end;
    int distance;
    int midIndex;

    public EmptySequence(int start, int end, int benchLength) {
        this.start = start;
        this.end = end;
        if (start == 0) {
            this.distance = end + 1;
            this.midIndex = 0;
        }
        else if (end == benchLength-1) {
            this.distance = end - start + 1;
            this.midIndex = end;
        }
        else {
            this.distance = (end - start + 1) / 2;
            this.midIndex = start + this.distance;
        }

    }

    @Override
    public int compareTo(Object o) {
        return  ((EmptySequence)o).distance - this.distance;
    }
}

public class BenchSitting {
    int[] bench;
    PriorityQueue<EmptySequence> sequences;

    public BenchSitting(int[] bench) {
        this.bench = bench;
        this.sequences = new PriorityQueue<>();

        // Store all empty sequences into priority queue
        int start = 0;
        while (start < bench.length) {
            int[] emptySequence = BenchSittingUtil.findFirstEmptySequence(start, bench);
            if (emptySequence.length == 0)
                break;
            sequences.offer(new EmptySequence(emptySequence[0], emptySequence[1], bench.length));
            start = emptySequence[1] + 1;
        }
    }

    public int getNextPosition() {
        EmptySequence sequence = sequences.poll();
        if (sequence == null)
            return -1;

        if (sequence.midIndex == 0) {
            if (sequence.end>0) {
                sequences.offer(new EmptySequence(1, sequence.end, bench.length));
            }
        } else if (sequence.end == bench.length-1) {
            if (sequence.start < bench.length-1) {
                sequences.offer(new EmptySequence(sequence.start, sequence.end - 1, bench.length));
            }
        } else {
            if (sequence.start<sequence.midIndex)
                sequences.offer(new EmptySequence(sequence.start, sequence.midIndex-1, bench.length));
            if (sequence.end>sequence.midIndex)
                sequences.offer(new EmptySequence(sequence.midIndex+1, sequence.midIndex, bench.length));
        }
        return sequence.midIndex;
    }

    public static void main(String args[]) {
        BenchSitting benchSitting = new BenchSitting(new int[]{1, 0, 1, 0, 1, 0, 0, 0});
        int index = benchSitting.getNextPosition();
        while (index != -1) {
            System.out.println("Next index: " + index);
            index = benchSitting.getNextPosition();
        }

    }
}
