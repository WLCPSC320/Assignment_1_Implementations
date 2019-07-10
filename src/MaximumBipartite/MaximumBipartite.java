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
        int previousWeight = 99999;
        for (Edge edge : edgeList) {
            Vertex endpoint1 = edge.getVertex().getKey();
            Vertex endpoint2 = edge.getVertex().getValue();
            if (!helper2.contains(endpoint1) && !helper2.contains(endpoint2)) {
                solutionSet.add(edge);
                helper2.add(endpoint1);
                helper2.add(endpoint2);
            } else if (helper2.contains(endpoint1) && previousWeight == edge.getWeight()) { //endpoint 1 is contested
                Vertex otherContested = findVertex(endpoint1);
                if(tiebreakMaximumWeight(endpoint2, otherContested, edge.getWeight())) {
                    removeSolutionEdge(otherContested);
                    solutionSet.add(edge);
                    helper2.add(endpoint2);
                }
            } else if (helper2.contains(endpoint2) && previousWeight == edge.getWeight()) { //endpoint 2 is contested
                Vertex otherContested = findVertex(endpoint2);
                if(tiebreakMaximumWeight(endpoint1, otherContested, edge.getWeight())) {
                    removeSolutionEdge(otherContested);
                    solutionSet.add(edge);
                    helper2.add(endpoint1);
                }
            }
            previousWeight = edge.getWeight();
        }
        return solutionSet;
    }

    // EFFECT: returns the corresponding vertex in the solution set
    private Vertex findVertex(Vertex v) {
        Vertex retval = null;
        for (Edge e : solutionSet) {
            if (e.hasVertex(v)) {
                retval = e.getEndpoint(v);
            }
        }
        return retval;
    }

    // EFFECT: returns true if v1 should be preferred over v2, else false
    private Boolean tiebreakMaximumWeight(Vertex v1, Vertex v2, int eWeight) {
        int count1 = 0;
        int count2 = 0;
        for (Edge e : v1.getEdgeList()) {
            if (e.getWeight() == eWeight) {
                count1++;
            }
        }
        for (Edge e : v2.getEdgeList()) {
            if (e.getWeight() == eWeight) {
                count2++;
            }
        }
        return count2 > count1;
    }

    // EFFECT: removes otherContested's edge from solution set and otherContested from helper2
    private void removeSolutionEdge(Vertex otherContested) {
        helper2.remove(otherContested);
        Edge edge = null;
        for (Edge e : solutionSet) {
            if (e.hasVertex(otherContested)) {
                edge = e;
                break;
            }
        }
        solutionSet.remove(edge);
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
