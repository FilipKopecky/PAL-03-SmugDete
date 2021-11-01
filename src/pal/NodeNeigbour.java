package pal;

import java.util.ArrayList;

public class NodeNeigbour implements Comparable<NodeNeigbour> {
    int degree = 0;
    ArrayList<Integer> nDegrees;
    boolean checked;

    public NodeNeigbour(int degree, ArrayList<Integer> nDegrees) {
        this.degree = degree;
        this.nDegrees = nDegrees;
        this.checked = false;
    }

    @Override
    public int compareTo(NodeNeigbour o) {
        return degree-o.degree;
    }
}
