package com.jin.leet;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 853. Car Fleet
 * N cars are going to the same destination along a one lane road.  The destination is target miles away.
 *
 * Each car i has a constant speed speed[i] (in miles per hour), and initial position position[i] miles towards the target along the road.
 *
 * A car can never pass another car ahead of it, but it can catch up to it, and drive bumper to bumper at the same speed.
 *
 * The distance between these two cars is ignored - they are assumed to have the same position.
 *
 * A car fleet is some non-empty set of cars driving at the same position and same speed.  Note that a single car is also a car fleet.
 *
 * If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.
 *
 *
 * How many car fleets will arrive at the destination?
 *
 *
 *
 * Example 1:
 *
 * Input: target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
 * [10,8,0,5,3]
 * [2,4,1,1,3]
 *
 * 10 8 5 3 0
 *  2 4 1 3 1
 *  1 1 7 3 12
 *
 *  10
 * [0,4,2]
 * [2,1,3]
 *
 * 4 2   0
 * 1 3   2
 * 6 2.6 5
 *
 * Output: 3
 * Explanation:
 * The cars starting at 10 and 8 become a fleet, meeting each other at 12.
 * The car starting at 0 doesn't catch up to any other car, so it is a fleet by itself.
 * The cars starting at 5 and 3 become a fleet, meeting each other at 6.
 * Note that no other cars meet these fleets before the destination, so the answer is 3.
 *
 * Note:
 *
 * 0 <= N <= 10 ^ 4
 * 0 < target <= 10 ^ 6
 * 0 < speed[i] <= 10 ^ 6
 * 0 <= position[i] < target
 * All initial positions are different.
 */
public class CarFleet {
    public int carFleet(int target, int[] position, int[] speed) {
        if (position==null || position.length == 0 || speed==null || speed.length==0)
            return 0;
        float[][] cars = new float[position.length][2];
        for (int i=0; i<cars.length; i++) {
            cars[i][0] = (float)position[i];
            cars[i][1] = (float)(target - position[i])/(float)speed[i];
        }
        Arrays.sort(cars, new Comparator<float[]>() {
            @Override
            public int compare(float[] o1, float[] o2) {
                if (o2[0] == o1[0])
                    return 0;
                return (o2[0] > o1[0])? 1:-1;
            }
        });

        int fleets = 1;
        float maxTime = cars[0][1];
        for (int i=0; i<cars.length-1; i++) {
            if (cars[i+1][1]> maxTime) {
                fleets++;
                maxTime = cars[i+1][1];
            }
        }
        return fleets;
    }

    public static void main(String args[]) {
        CarFleet c = new CarFleet();
        int a = c.carFleet(10, new int[]{0,4,2}, new int[]{2,1,3});
        System.out.println(a);
    }
}
