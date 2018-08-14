package com.jin.leet;

/**
 * Two Sum II Input array is sorted
 */
public class TwoSumII {

    public static int[] twoSum(int[] numbers, int target) {
        int[] result = new int[2];
        int i = 0;
        int j = numbers.length -1;
        while (i<j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                result[0] = i;
                result[1] = j;
                return  result;
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }
        return result;
    }

    public static void main(String args[]) {
        int[] numbers = new int[] {1, 7, 11, 15};
        int[] result = twoSum(numbers, 9);
        for (int v: result) {
            System.out.println(v);
        }
    }
}
