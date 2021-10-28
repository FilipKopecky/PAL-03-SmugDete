package pal;

import java.util.ArrayList;

public class Node {
    int index;
    ArrayList<Node> neighbours;

    public Node(int index) {
        this.index = index;
        neighbours = new ArrayList<>();

    }
}
