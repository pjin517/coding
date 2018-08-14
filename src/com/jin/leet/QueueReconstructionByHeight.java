package com.jin.leet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * LeetCode 406. Queue Reconstruction by Height
 * Suppose you have a random list of people standing in a queue.
 * Each person is described by a pair of integers (h, k), where h is the height of the person
 * and k is the number of people in front of this person who have a height greater than or equal to h.
 * Write an algorithm to reconstruct the queue.
 *
 * Note:
 * The number of people is less than 1,100.
 *
 *
 * Example
 *
 * Input:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 *
 * Output:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 */
public class QueueReconstructionByHeight {
    public int[][] reconstructQueue(int[][] people) {
        if (people==null||people.length == 0)
            return people;
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0])
                    return o1[1]-o2[1];
                return o2[0]-o1[0];
            }
        });
        LinkedList<int[]> list = new LinkedList<>();
        int[][] result = new int[people.length][people[0].length];
        for (int[] person: people) {
            list.add(person[1], person);
        }
        for (int i=0; i<result.length; i++)
            result[i] = list.get(i);
        return result;
    }

    public static void main(String[] args) {
        int[][] input = new int[][] {{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
        QueueReconstructionByHeight q = new QueueReconstructionByHeight();
        for (int[] a: q.reconstructQueue(input))
            System.out.println(Arrays.toString(a));
    }
}
