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
       /**     for (Node[]subgraph:subGraphs) {
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
        Node[] pack = new Node[nodes.length];
        int discoveredRoutes = 0;
        ArrayList<Node> interNeighbours;
        for (int i = 0; i < nodes.length; i++) {
            copy[i] = nodes[i];
            interNeighbours = new ArrayList<>();
            for (Node nn : nodes) {
                if (copy[i].neighbours.contains(nn)) {
                    discoveredRoutes++;
                    interNeighbours.add(nn);
                }

            }
            Node node = new Node(copy[i].index);
            node.neighbours = interNeighbours;
            if(node.neighbours.size()==0)
            {
               return;
            }
            pack[i] = node;
        }
        if (discoveredRoutes / 2 == numEdgesPack) {

            subGraphs.add(pack);
        }

    }

    public void getIsomorphisms() {
        int totalNumber = 0;
        for (int i = 0; i < subGraphs.size(); i++) {
            for (int j = i + 1; j < subGraphs.size(); j++) {
                Node[] subgraph1 = subGraphs.get(i);
                Node[] subgraph2 = subGraphs.get(j);
                if (checkIso(subgraph1, subgraph2)) {
                    totalNumber++;
                 /**   for (Node n:subgraph1) {
                        System.out.print(n.index+" ");
                    }
                    for (Node n:subgraph2) {
                        System.out.print(n.index+" ");
                    }
                    System.out.println();**/
                }
            }


        }
        System.out.println(totalNumber);
    }

    public boolean checkIso(Node[] graph1, Node[] graph2) {
        for (int i = numNodesPack - 1; i >= 0; i--) {
            for (int j = numNodesPack - 1; j >= 0; j--) {
                if (graph1[j].index == graph2[i].index)
                    return false;
            }
        }

        ArrayList<Integer> degrees1 = new ArrayList<>();
        ArrayList<Integer> degrees2 = new ArrayList<>();

        for (Node n:graph1) {
            degrees1.add(n.neighbours.size());
        }
        for (Node n:graph2) {
            degrees2.add(n.neighbours.size());
        }

        Collections.sort(degrees1);
        Collections.sort(degrees2);

        for (int i = 0; i < numNodesPack; i++) {
            if(degrees1.get(i)!=degrees2.get(i))
                return false;
        }



        



        return true;
    }


}
