package com.jin.leet;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/*
Given two 1d vectors, implement an iterator to return their elements alternately.

Example:

Input:
v1 = [1,2]
v2 = [3,4,5,6]

Output: [1,3,2,4,5,6]

Explanation: By calling next repeatedly until hasNext returns false,
             the order of elements returned by next should be: [1,3,2,4,5,6].
Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question:
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example:

Input:
[1,2,3]
[4,5,6,7]
[8,9]

Output: [1,4,8,2,5,9,3,6,7].

 */

public class ZigzagIterator {

    Iterator<Integer>[] vectors;
    int curVector;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.vectors = new Iterator[2];
        vectors[0] = v1.iterator();
        vectors[1] = v2.iterator();
        if (vectors[0].hasNext())
            curVector = 0;
        else
            curVector = 1;
    }

    public int next() {
        int res = vectors[curVector].next();
        int next = (curVector+1)%2;
        if (vectors[next].hasNext()) {
            curVector = next;
        }
        return res;
    }

    public boolean hasNext() {
        return vectors[0].hasNext() || vectors[1].hasNext();
    }
}


class GeneralZigzagIterator {
    int vectorSize;
    Iterator<Integer>[] iterators;
    int curVector;

    public GeneralZigzagIterator(List<Integer> ... vectors) {
        this.vectorSize = vectors.length;
        this.iterators = new Iterator[vectorSize];
        for (int i = 0; i<vectorSize; i++) {
            this.iterators[i] = vectors[i].iterator();
        }
        for (int i=0; i<vectorSize; i++) {
            if (iterators[i].hasNext()) {
                curVector = i;
                break;
            }
        }
    }

    public int next() {
        int res = iterators[curVector].next();

        int i = 0;
        curVector = ++curVector % vectorSize;
        while (!iterators[curVector].hasNext()) {
            curVector = ++curVector % vectorSize;
            if (++i == vectorSize)
                break;

        }
        return res;
    }

    public boolean hasNext() {
        for (Iterator itr: iterators) {
            if (itr.hasNext())
                return true;
        }
        return false;
    }

    public static void main(String args[]) {
        List<Integer> v1 = new LinkedList<>();
        v1.add(1);
        v1.add(2);
        v1.add(3);
        List<Integer> v2 = new LinkedList<>();
        v2.add(4);
        v2.add(5);
        v2.add(6);
        v2.add(7);
        List<Integer> v3 = new LinkedList<>();
        v3.add(8);
        v3.add(9);
        GeneralZigzagIterator i = new GeneralZigzagIterator(v1, v2, v3);
        while (i.hasNext()) {
            System.out.println(i.next());
        }
    }
}


/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */


