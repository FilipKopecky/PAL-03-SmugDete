package pal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Alg {
    Node[] graph;
    ArrayList<Node[]> subGraphs;

    int numNodesPack;
    int numEdgesPack;

    public Alg(Node[] graph, int numNodesPack, int numEdgesPack) {
        this.graph = graph;
        this.subGraphs = new ArrayList<>();
        this.numNodesPack = numNodesPack;
        this.numEdgesPack = numEdgesPack;
    }

    public void calculateSub() {
        kSubset(graph, graph.length - 1, new Node[numNodesPack], numNodesPack - 1);
       /**    for (Node[]subgraph:subGraphs) {
         for (Node n:subgraph) {
         System.out.print(n.index +",");
         }
         System.out.println();
         }**/
    }

    public void kSubset(Node[] set, int i_end, Node[] result, int remainingDepth) {
        if (remainingDepth < 0) {
            createSubGraph(result);
            return;
        }
        for (int i = i_end; i > remainingDepth - 1; i--) {
            result[remainingDepth] = set[i];
            kSubset(set, i - 1, result, remainingDepth - 1);
        }

    }

    public void createSubGraph(Node[] nodes) {
        Node[] copy = new Node[nodes.length];
        int discoveredRoutes = 0;
        for (int i = 0; i < nodes.length; i++) {
            copy[i] = nodes[i];

            for (Node nn : nodes) {
                if (copy[i].neighbours.contains(nn))
                {
                    discoveredRoutes++;
                }

            }

        }
        if (discoveredRoutes / 2 == numEdgesPack)
        {
            subGraphs.add(copy);
        }

    }

    public void getIsomorphisms() {
        int totalNumber=0;
        for (int i = 0; i < subGraphs.size(); i++) {
            for (int j = i + 1; j < subGraphs.size(); j++) {
                Node[] subgraph1 = subGraphs.get(i);
                Node[] subgraph2 = subGraphs.get(j);
                if (checkIso(subgraph1, subgraph2)) {
                    totalNumber++;
                }
            }


        }
        System.out.println(totalNumber);
    }

    public boolean checkIso(Node[] graph1, Node[] graph2) {
        for (int i = numNodesPack - 1; i >= 0; i--) {
            for (int j = numNodesPack - 1; j >= 0; j--) {
                if (graph1[j] == graph2[i])
                    return false;
            }
        }
        


        return true;
    }


}
