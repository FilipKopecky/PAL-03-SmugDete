package pal;

import java.util.ArrayList;

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
                    discoveredRoutes++;
            }
        }
        if (discoveredRoutes / 2 >= numEdgesPack)
            subGraphs.add(copy);
    }

    public void getIsomorphism()
    {

    }





  


}
