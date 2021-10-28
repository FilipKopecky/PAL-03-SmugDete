package pal;

import java.util.ArrayList;

public class Node {
    int index;
    ArrayList<Node> neighbours;
    int interMediateDegree;

    public Node(int index) {
        this.index = index;
        neighbours = new ArrayList<>();
        this.interMediateDegree=0;
    }
}
