package com.jin.leet;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.
 *
 * Example 1:
 *
 * Input: [[0,30],[5,10],[15,20]]
 * Output: false
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: true
 *
 */


public class MeetingRoom {
    // sort the intervals first, and then go through the sorted array to see if there's any overlapping
    public boolean canAttendMeetings(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });

        for (int i=0; i<intervals.length-1; i++) {
            if (isOverlap(intervals[i], intervals[i+1]))
                return false;
        }
        return true;
    }

    private static boolean isOverlap(Interval o1, Interval o2) {
        return ! (Math.min(o1.end, o2.end) <= Math.max(o1.start, o2.start));
    }

    public static void main(String args[]) {

    }
}

class Interval {
    int start;
    int end;
    Interval() { start = 0; end = 0; }
    Interval(int s, int e) { start = s; end = e; }
}