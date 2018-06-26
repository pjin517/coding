package com.jin.leet;

/**
 * LeetCode134
 *
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
 *
 * Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
 *
 * Note:
 *
 * If there exists a solution, it is guaranteed to be unique.
 * Both input arrays are non-empty and have the same length.
 * Each element in the input arrays is a non-negative integer.
 * Example 1:
 *
 * Input:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 *
 * Output: 3
 *
 * Explanation:
 * Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 4. Your tank = 4 - 1 + 5 = 8
 * Travel to station 0. Your tank = 8 - 2 + 1 = 7
 * Travel to station 1. Your tank = 7 - 3 + 2 = 6
 * Travel to station 2. Your tank = 6 - 4 + 3 = 5
 * Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
 * Therefore, return 3 as the starting index.
 * Example 2:
 *
 * Input:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 *
 * Output: -1
 *
 * Explanation:
 * You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
 * Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
 * Travel to station 0. Your tank = 4 - 3 + 2 = 3
 * Travel to station 1. Your tank = 3 - 3 + 3 = 3
 * You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
 * Therefore, you can't travel around the circuit once no matter where you start.
 *
 */
public class GasStation {
    /**
     *  * The basic idea is every time we start from a station, we go as far as possible by increasing end until remaining gas is less than 0.
     *  If 'end' finally hits start we know we can travel around from 'start'. If we haven't traveled around, we know we cannot start from this station.
     *  Then we check the station before our start station if we can start from this station. Repeat until we have checked all stations.
     *
     *  Note there is a little trick that every time we try to find the next start station, we always to back to extend the distance from start to end
     *  so that we can check all stations on the circuit. Otherwise, if we move start forward to decrease the distance from start to end,
     *  we are likely to end up with only checking part of the circuit. Another trick is we start from the end of the array so as to avoid some corner cases.
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int start = gas.length-1;
        int end = 0;
        int sum = gas[start] - cost[start];
        while (start > end) {
            if (sum >= 0) {
                sum += gas[end] - cost[end];
                ++end;
            }
            else {
                --start;
                sum += gas[start] - cost[start];
            }
        }
        return sum >= 0 ? start : -1;
    }

    public static void main(String[] argv) {
        GasStation gs = new GasStation();
        int[] gas = new int[] {1,2,3,4,5};
        int[] cost = new int[] {3,4,5,1,2};
        System.out.println(gs.canCompleteCircuit(gas, cost));

        int[] gas1 = new int[] {2,3,4};
        int[] cost1 = new int[] {3,4,3};
        System.out.println(gs.canCompleteCircuit(gas1, cost1));
    }
}
