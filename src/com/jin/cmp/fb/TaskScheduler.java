package com.jin.cmp.fb;

import java.util.Arrays;

/**
 * 621. Task Scheduler
 * Given a char array representing tasks CPU need to do.
 * It contains capital letters A to Z where different letters represent different tasks.
 * Tasks could be done without original order. Each task could be done in one interval.
 * For each interval, CPU could finish one task or just be idle.
 *
 * However, there is a non-negative cooling interval n that means between two same tasks,
 * there must be at least n intervals that CPU are doing different tasks or just be idle.
 *
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 *
 * Example 1:
 * Input: tasks = ["A","A","A","B","B","B"], n = 2
 * Output: 8
 * Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
 * Note:
 * The number of tasks is in the range [1, 10000].
 * The integer n is in the range [0, 100].
 */
public class TaskScheduler {
    public int leastInterval(char[] tasks, int n) {
        int[] num = new int[26];
        for (char c : tasks) {
            num[c-'A'] ++;
        }
        Arrays.sort(num);

        int idleSlots = (num[25] - 1)*n;

        for (int i = 24; i>=0; i--) {
            idleSlots -= Math.min(num[25]-1, num[i]);
        }

        return Math.max(tasks.length, idleSlots + tasks.length);
    }
}
