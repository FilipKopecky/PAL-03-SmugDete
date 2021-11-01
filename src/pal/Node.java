package pal;

import java.util.ArrayList;
import java.util.HashMap;

public class Node {
    int index;
    ArrayList<Node> neighbours;
    HashMap<Pack,Integer> degrees;

    public Node(int index) {
        this.index = index;
        neighbours = new ArrayList<>();
        degrees = new HashMap<>();

    }
}
