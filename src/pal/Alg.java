package pal;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Alg {
    Node[] graph;
    ArrayList<Pack> packs;

    int numNodesPack;
    int numEdgesPack;

    public Alg(Node[] graph, int numNodesPack, int numEdgesPack) {
        this.graph = graph;
        this.numNodesPack = numNodesPack;
        this.numEdgesPack = numEdgesPack;


        this.packs = new ArrayList<>();
    }

    public void calculateSub() {
        /**  kSubset(graph, graph.length - 1, new Node[numNodesPack], numNodesPack - 1);

         for (Pack pack:packs) {
         for (Node n: pack.nodes) {
         System.out.print(n.index+" ");
         }
         System.out.println();
         }**/
        kSubset2(graph, 0, new Node[numNodesPack], 0);

       /** for (Pack pack : packs) {
            for (Node n : pack.nodes) {
                System.out.print(n.index + " ");
            }
            System.out.println();
        }**/

    }


    public void kSubset2(Node[] set, int i_end, Node[] result, int currentIndex) {
        if (currentIndex == numNodesPack) {
            createSubGraph(result);
            return;
        }
        for (int i = i_end; i < graph.length; i++) {
            result[currentIndex] = set[i];
            kSubset2(set, i + 1, result, currentIndex + 1);
        }

    }

    public void createSubGraph(Node[] nodes) {
        Pack pack1 = new Pack(numNodesPack);

        int allDegrees = 0;
        for (int i = 0; i < numNodesPack; i++) {
            Node n = nodes[i];
            int curr = degreeInPack(nodes, n);
            if (curr == 0)
                return;
            allDegrees += curr;
            n.degrees.put(pack1, curr);
            pack1.nodes[i] = n;
        }

        if (allDegrees / 2 == numEdgesPack) {
            packs.add(pack1);
        }

    }

    public void getIsomorphismsPacks() {
       // int totalNumber = 0;
        for (int i = 0; i < packs.size(); i++) {
            for (int j = i + 1; j < packs.size(); j++) {
                Pack p1 = packs.get(i);
                Pack p2 = packs.get(j);
                if (checkIso(p1, p2)) {
                    //totalNumber++;
                    for (int k = 0; k < numNodesPack; k++) {
                        StringBuilder sb = new StringBuilder(String.valueOf(p1.nodes[k].index));
                        System.out.print(sb.append(" "));
                    }
                    for (int k = 0; k < numNodesPack -1; k++) {
                        StringBuilder sb = new StringBuilder(String.valueOf(p2.nodes[k].index));
                        System.out.print(sb.append(" "));
                    }
                      System.out.print(p2.nodes[numNodesPack-1].index);


                     System.out.println();


                }
            }


        }
       // System.out.println(totalNumber);
    }


    public int degreeInPack(Node[] pack, Node n) {
        int degree = 0;
        for (Node node : pack) {
            if (node.neighbours.contains(n))
                degree++;
        }
        return degree;
    }

    public boolean isInPack(Node[] pack, Node n) {
        for (int i = 0; i < numNodesPack; i++) {
            if (pack[i] == n) return true;
        }
        return false;
    }

    public boolean checkIso(Pack pack1, Pack pack2) {
        for (int i = 0; i < numNodesPack; i++) {
            for (int j = 0; j < numNodesPack; j++) {
                if (pack1.nodes[j].index == pack2.nodes[i].index)
                    return false;
            }
        }


        HashMap<Integer, ArrayList<ArrayList<Integer>>> graph1 = new HashMap<>();

        for (Node n : pack1.nodes) {
            Integer nodeDegree = n.degrees.get(pack1);
            ArrayList<Integer> degrees = new ArrayList<>();
            for (Node nn : n.neighbours) {
                if (isInPack(pack1.nodes, nn)) {
                    degrees.add(degreeInPack(pack1.nodes, nn));
                }
            }
            Collections.sort(degrees);

            ArrayList<ArrayList<Integer>> gDeg = graph1.get(nodeDegree);
            if (gDeg == null)
                graph1.put(nodeDegree, new ArrayList<>());
            gDeg = graph1.get(nodeDegree);
            gDeg.add(degrees);
            graph1.put(nodeDegree, gDeg);

        }

        for (Node n : pack2.nodes) {
            Integer nodeDegree = n.degrees.get(pack2);
            ArrayList<Integer> degrees = new ArrayList<>();
            for (Node nn : n.neighbours) {
                if (isInPack(pack2.nodes, nn)) {
                    degrees.add(degreeInPack(pack2.nodes, nn));
                }
            }
            Collections.sort(degrees);


            ArrayList<ArrayList<Integer>> gDeg = graph1.get(nodeDegree);
            if (gDeg == null) return false;
            boolean removed = false;
            for (int i = gDeg.size() - 1; i >= 0; i--) {
                if (gDeg.get(i).equals(degrees)) {
                    gDeg.remove(i);
                    removed = true;
                    break;
                }
            }
            if (!removed) return false;

        }


        return true;
    }


}
