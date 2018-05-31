package com.jin.cmp.gg;

import com.jin.graph.Graph;
import com.jin.graph.Node;

import java.util.Random;

/*
m2], [l1, l2]， 但是流出的咖啡量是随机的。 有一个杯子总容量是c2,杯子上有个刻度线c1,如果咖啡量在[c1，c2], 则视为盛满
问在进行一系列按button操作后，能不能使咖啡在[c1,c2], 盛满但不溢出,
分析时间复杂度
 */
public class SodaMachine {
    private int min;
    private int max;
    Random random;

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

    public SodaMachine(int min, int max) {
        this.min = min;
        this.max = max;
        random = new Random();
    }

    public int pressButton() {
        return random.nextInt(max-min) + min;
    }

    public static void main(String args[]) {
        getSoda(1000, 1500);
    }

    public static void sodaDFS(Graph<SodaMachine> graph, int nodeId, Cup cup) {
        int [] currentVol = new int[2];
        currentVol[0] = 0;
        currentVol[1] = 0;
        sodaDFSUtil(graph, nodeId, cup, currentVol);
    }

    public static void sodaDFSUtil(Graph<SodaMachine> graph, int nodeId, Cup cup, int[] currentVol ) {
        if (graph.getNodes()[nodeId].getValue().getMin() > cup.getMaxVol() - currentVol[1]) {
            System.out.print(nodeId + " XXX\n");
            return;
        }
        if (graph.getNodes()[nodeId].getValue().getMin() + currentVol[0] >= cup.getMinVol() &&
                currentVol[1] + graph.getNodes()[nodeId].getValue().getMax() <= cup.getMaxVol()) {
            System.out.print(nodeId + " ********\n");
            return;
        }

        for (Node n: graph.getAdj()[nodeId]) {
            System.out.print(nodeId + " ");
            currentVol[0] += graph.getNodes()[nodeId].getValue().getMin();
            currentVol[1] += graph.getNodes()[nodeId].getValue().getMax();
            sodaDFSUtil(graph, n.getId(), cup, currentVol );
        }

    }

    public static void getSoda(int min, int max) {
        SodaMachine sm0 = new SodaMachine(100, 120);
        SodaMachine sm1 = new SodaMachine(200, 250);
        SodaMachine sm2 = new SodaMachine(300, 400);

        Graph<SodaMachine> smGraph = new Graph<>(3);
        smGraph.addNode(new Node<>(0, sm0));
        smGraph.addNode(new Node<>(1, sm1));
        smGraph.addNode(new Node<>(2, sm2));
        smGraph.addEdge(0, 1);
        smGraph.addEdge(0, 2);
        smGraph.addEdge(0, 0);
        smGraph.addEdge(1, 1);
        smGraph.addEdge(1, 2);
        smGraph.addEdge(1, 0);
        smGraph.addEdge(2, 0);
        smGraph.addEdge(2, 1);
        smGraph.addEdge(2, 2);

        System.out.println("makeing soda for " + min + ", " + max);
        sodaDFS(smGraph, 0, new Cup(min, max));
        sodaDFS(smGraph, 1, new Cup(min, max));
        sodaDFS(smGraph, 2, new Cup(min, max));


    }
}

class Cup {
    private int minVol;
    private int maxVol;

    public Cup(int minVol, int maxVol) {
        this.minVol = minVol;
        this.maxVol = maxVol;
    }

    public int getMinVol() {
        return minVol;
    }

    public int getMaxVol() {
        return maxVol;
    }
}