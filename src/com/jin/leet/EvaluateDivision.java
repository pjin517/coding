package com.jin.leet;

import java.util.HashMap;

/** LeetCode399
 *
 * Equations are given in the format A / B = k, where A and B are variables represented as strings,
 * and k is a real number (floating point number). Given some queries, return the answers.
 * If the answer does not exist, return -1.0.
 *
 * Example:
 * Given a / b = 2.0, b / c = 3.0.
 * queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
 * return [6.0, 0.5, -1.0, 1.0, -1.0 ].
 *
 * The input is:
 * vector<pair<string, string>> equations,
 * vector<double>& values,
 * vector<pair<string, string>> queries ,
 * where equations.size() == values.size(), and the values are positive.
 * This represents the equations. Return vector<double>.
 *
 * According to the example above:
 *
 * equations = [ ["a", "b"], ["b", "c"] ],
 * values = [2.0, 3.0],
 * queries = [ ["a", "c"], ["b", "a"], ["a", "e"], ["a", "a"], ["x", "x"] ].
 * The input is always valid. You may assume that evaluating the queries will result in no division by zero and there is no contradiction.
 */
public class EvaluateDivision {
    HashMap<String, String> parents;
    HashMap<String, String> ranks;
    HashMap<String, HashMap<String, Double>> devisions;

    public EvaluateDivision(String[][] equations, double[] values) {
        parents = new HashMap<>();
        ranks = new HashMap<>();
        devisions = new HashMap<>();

//        for (int i=0; i<equations.length; i++) {
//            parents.put()
//        }
    }

    public String find(String x) {
        while (parents.get(x) != x) {
            String temp = x;
            parents.put(x, parents.get(parents.get(x)));
            x = parents.get(x);
            Double division = devisions.get(temp).get(parents.get(temp))*devisions.get(x).get(parents.get(x));
            HashMap<String, Double> newDevision = new HashMap<>();
            newDevision.put(parents.get(x), division);
            devisions.put(temp, newDevision);
        }
        return x;
    }

    public boolean devision(String x, String y, double value) {
        parents.put(x, y);
        HashMap<String, Double> newDevision = new HashMap<>();
        newDevision.put(y, value);
        devisions.put(x, newDevision);
        return false;
    }


    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {

        return new double[]{0.0d, 0.0d};
    }
}
