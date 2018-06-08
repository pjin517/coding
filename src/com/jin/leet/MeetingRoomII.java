package com.jin.leet;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 *
 * Example 1:
 *
 * Input: [[0, 30],[5, 10],[15, 20]]
 * Output: 2
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: 1
 *
 */
public class MeetingRoomII {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals.length == 0)
            return 0;

        // First sort the intervals
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        // a min heap to track minimum end time of overlapping intervals
        PriorityQueue<Interval> heap = new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });

        heap.offer(intervals[0]);

        for (int i=1; i<intervals.length; i++) {
            if (intervals[i].start >= heap.peek().end)
                heap.poll();
            heap.offer(intervals[i]);
        }
        return heap.size();
    }

    public static void main(String args[]) {

    }
}
