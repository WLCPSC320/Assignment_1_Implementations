package MaximumBipartite;

import MaximumBipartite.Model.Edge;
import MaximumBipartite.Model.Vertex;

import java.util.ArrayList;
import java.util.HashSet;

public class MaximumBipartite {
    private ArrayList<Vertex> vertexList;
    private ArrayList<Edge> edgeList;
    private ArrayList<Edge> helper1; // Temporary holder for mergeSort
    private HashSet<Vertex> helper2; // Temporary holder for solveMaximumWeight
    private HashSet<Edge> solutionSet;

    public MaximumBipartite(ArrayList<Vertex> vertexList, ArrayList<Edge> edgeList) {
        this.vertexList = vertexList;
        this.edgeList = edgeList;
        helper1 = new ArrayList<>();
        helper2 = new HashSet<>();
        solutionSet = new HashSet<>();
    }

    // MODIFIES: this
    // EFFECT: returns set of edges that maximizes edge weights
    public HashSet<Edge> solveMaximumWeight() {
        solutionSet = new HashSet<>();
        helper2 = new HashSet<>();
        sortEdges();
        for (Edge edge : edgeList) {
            Vertex endpoint1 = edge.getVertex().getKey();
            Vertex endpoint2 = edge.getVertex().getValue();
            if (!helper2.contains(endpoint1) && !helper2.contains(endpoint2)) {
                solutionSet.add(edge);
                helper2.add(endpoint1);
                helper2.add(endpoint2);
            }
        }
        return solutionSet;
    }

    // MODIFIES: this
    // EFFECT: Calls mergeSort to sort edgeList
    public void sortEdges() {
        mergeSort(0, edgeList.size()-1);
    }

    // MODIFIES: this
    // EFFECT: MergeSorts edgeList
    private void mergeSort(int low, int hi) {
        if (low < hi) {
            int mid = low + (hi - low) / 2;
            mergeSort(low, mid);
            mergeSort(mid+1, hi);
            merge(low, mid, hi);
        }
    }

    // MODIFIES: this
    // EFFECT: Merges edges from low to mid and mid+1 to hi in edgeList into sorted order
    //         Adapted from : http://www.java2novice.com/java-sorting-algorithms/merge-sort/
    private void merge(int low, int mid, int hi) {
        for (int i = low; i <= hi; i++) {
            helper1.add(i, edgeList.get(i));
        }

        int i = low;
        int j = mid + 1;
        int k = low;

        while (i <= mid && j <= hi) {
            if (helper1.get(i).getWeight() >= helper1.get(j).getWeight()) {
                edgeList.set(k, helper1.get(i));
                i++;
            } else {
                edgeList.set(k, helper1.get(j));
                j++;
            }
            k++;
        }
        while (i <= mid) {
            edgeList.set(k, helper1.get(i));
            k++;
            i++;
        }
    }

    // EFFECT: returns edgeList
    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    // EFFECT: returns vertexList
    public ArrayList<Vertex> getVertexList() {
        return vertexList;
    }

    // EFFECT: returns total weight from solutionSet
    public int getTotalWeight() {
        int i = 0;
        for (Edge e : solutionSet) {
            i += e.getWeight();
        }
        return i;
    }
}
