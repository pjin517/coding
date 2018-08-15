package com.jin.leet;

import java.util.*;

/**
 * 815. Bus Routes
 *
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever.
 * For example if routes[0] = [1, 5, 7], this means that the first bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.
 *
 * We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only,
 * what is the least number of buses we must take to reach our destination? Return -1 if it is not possible.
 *
 * Example:
 * Input:
 * routes = [[1, 2, 7], [3, 6, 7]]
 * S = 1
 * T = 6
 * Output: 2
 * Explanation:
 * The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 * Note:
 *
 * 1 <= routes.length <= 500.
 * 1 <= routes[i].length <= 500.
 * 0 <= routes[i][j] < 10 ^ 6.
 *
 * TODO: not correct
 */
public class BusRoute {
    int[][] routes;
    Map<Integer, Set<Integer>> stopToRoutes;
    Map<Integer, Set<Integer>> routeAdj;

    public BusRoute(int[][] routes) {
        this.routes = routes;
        stopToRoutes = new HashMap<>();
        routeAdj = new HashMap<>();
        for (int i=0; i<routes.length; i++) {
            for (int s: routes[i]) {
                Set<Integer> r = stopToRoutes.get(s);
                if (r == null)
                    r = new HashSet<>();
                for (int v: r) {
                    Set<Integer> adj = routeAdj.get(i);
                    if (adj == null)
                        adj = new HashSet<>();
                    adj.add(v);
                    Set<Integer> set = routeAdj.get(v);
                    if (set == null) {
                        set = new HashSet<>();
                        routeAdj.put(v, set);
                    }
                    routeAdj.get(v).add(i);
                }
                r.add(i);
                stopToRoutes.put(s, r);
            }
        }
    }

    public int numBusesToDestination(int[][] routes, int S, int T) {
        Set<Integer> sBuses = stopToRoutes.get(S);
        Set<Integer> tBuses = stopToRoutes.get(T);
        if (sBuses == null || tBuses == null)
            return -1;
        for (int s: sBuses) {
            if (tBuses.contains(s))
                return 1;
        }

        int min = Integer.MAX_VALUE;
        for (int s: sBuses) {
            for (int t: tBuses) {
                int r = dfs(routeAdj, s, t, new HashSet<Integer>(), 1);
                if (r<min)
                    min = r;
            }
        }
        if (min == Integer.MAX_VALUE)
            return -1;
        return min;
    }

    private int dfs(Map<Integer, Set<Integer>> routeAdj, int bStart, int bEnd, Set<Integer> visited, int depth) {
        if (bStart == bEnd) {
            return depth;
        }

        int min = Integer.MAX_VALUE;
        visited.add(bStart);
        if (routeAdj.get(bStart) == null)
            return -1;
        for (int i: routeAdj.get(bStart)) {
           int r = dfs(routeAdj, i, bEnd, visited, depth+1);
           if (r>0 && r< min)
               min = r;
        }

        if (min == Integer.MAX_VALUE)
            return -1;
        return min;
    }

    public static void main(String[] args) {
        BusRoute br = new BusRoute(new int[][] {{0,1,6,16,22,23},{14,15,24,32},{4,10,12,20,24,28,33},{1,10,11,19,27,33},{11,23,25,28},{15,20,21,23,29},{29}});
        System.out.println(br.numBusesToDestination(new int[][] {{1, 2, 7}, {3, 6, 7}}, 4, 21));
    }
}
