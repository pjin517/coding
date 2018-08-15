package com.jin.leet;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 759. Employee Free Time
 *
 * We are given a list schedule of employees, which represents the working time for each employee.
 *
 * Each employee has a list of non-overlapping Intervals, and these intervals are in sorted order.
 *
 * Return the list of finite intervals representing common, positive-length free time for all employees, also in sorted order.
 *
 * Example 1:
 * Input: schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * Output: [[3,4]]
 * Explanation:
 * There are a total of three employees, and all common
 * free time intervals would be [-inf, 1], [3, 4], [10, inf].
 * We discard any intervals that contain inf as they aren't finite.
 * Example 2:
 * Input: schedule = [[[1,3],[6,7]],[[2,4]],[[2,5],[9,12]]]
 * Output: [[5,6],[7,9]]
 * (Even though we are representing Intervals in the form [x, y], the objects inside are Intervals, not lists or arrays. For example, schedule[0][0].start = 1, schedule[0][0].end = 2, and schedule[0][0][0] is not defined.)
 *
 * Also, we wouldn't include intervals like [5, 5] in our answer, as they have zero length.
 *
 * Note:
 *
 * schedule and schedule[i] are lists with lengths in range [1, 50].
 * 0 <= schedule[i].start < schedule[i].end <= 10^8.
 */
public class EmployeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        int lastEndTime = -1;
        PriorityQueue<Interval> heap = new PriorityQueue<>(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                if (o1.start==o2.start)
                    return o1.end-o2.end;
                return o1.start-o2.start;
            }
        });

        for (List<Interval> l: schedule) {
            for (Interval interval: l)
                heap.offer(interval);
        }

        List<Interval> result = new LinkedList<>();
        while (!heap.isEmpty()) {
            Interval in = heap.poll();
            if (in.start>lastEndTime)
                result.add(new Interval(lastEndTime, in.start));
            lastEndTime = Math.max(lastEndTime, in.end); // there might be interval's end that's smaller than previous interval's end
        }
        result.remove(0);
        return result;
    }

}
