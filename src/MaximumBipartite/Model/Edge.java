package MaximumBipartite.Model;

import javafx.util.Pair;

public class Edge {
    private Vertex vertexA;
    private Vertex vertexB;
    private int weight;

    public Edge(Vertex a, Vertex b) {
        vertexA = a;
        vertexB = b;
        weight = 0;
        a.addEdge(this);
        a.setDegree(a.getDegree() + 1);
        b.addEdge(this);
        b.setDegree(b.getDegree() + 1);
    }

    public Edge(Vertex a, Vertex b, int weight) {
        vertexA = a;
        vertexB = b;
        this.weight = weight;
        a.addEdge(this);
        a.setDegree(a.getDegree() + 1);
        b.addEdge(this);
        b.setDegree(b.getDegree() + 1);
    }

    // EFFECTS: returns true if an endpoint contains v
    public Boolean hasVertex(Vertex v) {
        return v.equals(vertexA) || v.equals(vertexB);
    }

    // EFFECTS: returns the endpoint of the edge that is not v
    public Vertex getEndpoint(Vertex v) {
        if (v.equals(vertexA)) {
            return vertexB;
        } else {
            return vertexA;
        }
    }

    public void setWeight(int i) {
        weight = i;
    }

    public int getWeight() {
        return weight;
    }

    // EFFECTS: returns both endpoints as a pair
    public Pair<Vertex, Vertex> getVertex() {
        Pair retval = new Pair(vertexA, vertexB);
        return retval;
    }
}

