package com.jin.leet;

/**
 * LeetCode 837
 * Alice plays the following game, loosely based on the card game "21".
 *
 * Alice starts with 0 points, and draws numbers while she has less than K points.  During each draw, she gains an integer number of points randomly from the range [1, W], where W is an integer.  Each draw is independent and the outcomes have equal probabilities.
 *
 * Alice stops drawing numbers when she gets K or more points.  What is the probability that she has N or less points?
 *
 * Example 1:
 *
 * Input: N = 10, K = 1, W = 10
 * Output: 1.00000
 * Explanation:  Alice gets a single card, then stops.
 * Example 2:
 *
 * Input: N = 6, K = 1, W = 10
 * Output: 0.60000
 * Explanation:  Alice gets a single card, then stops.
 * In 6 out of W = 10 possibilities, she is at or below N = 6 points.
 * Example 3:
 *
 * Input: N = 21, K = 17, W = 10
 * Output: 0.73278
 * Note:
 *
 * 0 <= K <= N <= 10000
 * 1 <= W <= 10000
 * Answers will be accepted as correct if they are within 10^-5 of the correct answer.
 * The judging time limit has been reduced for this question.
 */

public class New21Game {
    public double new21Game(int N, int K, int W) {
        double[] dp = new double[N+1];
        double prob = 1.0d/W;
        // dp[x] = the answer when Alice has x points
        for (int k = K; k <= N; ++k)
            dp[k] = 1.0;

        for (int i=K-1; i>=0; i--) {
            double p = 0d;
            for (int j=1; j<=W; j++) {
                if (i+j<=N)
                    p+=dp[i+j] * prob;
                else
                    break;
            }
            dp[i] = p;
        }

        return dp[0];
    }

    public static void main(String[] args) {
        New21Game game = new New21Game();
        long start = System.currentTimeMillis();
        double p = game.new21Game(8559, 2896, 6028);
        long end = System.currentTimeMillis();
        System.out.println("Time spent: " + (end-start) + ", P="+p);
    }

}
