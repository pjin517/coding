package com.jin.leet;

import java.util.LinkedList;

public class Leet346MovingAverageFromStream {
    int windowSize;
    LinkedList<Integer> buf;

    /**
     * Initialize your data structure here.
     */
    public Leet346MovingAverageFromStream(int size) {
        this.windowSize = size;
        this.buf = new LinkedList<Integer>();
    }

    public double next(int val) {
        buf.addLast(val);
        if (buf.size() > this.windowSize) {
            buf.removeFirst();
        }
        double sum = 0d;
        for (Integer i : buf
                ) {
            sum += i.intValue();
        }
        return sum / buf.size();
    }

    public static void main(String args[]) {
        Leet346MovingAverageFromStream obj = new Leet346MovingAverageFromStream(3);
        System.out.println( obj.next(1) == 1);
        System.out.println( obj.next(10) == 5.5d);
        System.out.println( obj.next(3) == (1+10+3)/3d);
        System.out.println( obj.next(5) == (10+3+5)/3d);
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
