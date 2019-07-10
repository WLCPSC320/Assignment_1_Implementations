package MaximumBipartite;

import MaximumBipartite.Model.Edge;
import MaximumBipartite.Model.Vertex;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaximumBipartiteTest {
    private Edge e1, e2, e3, e4, e5, e6, e7, e8, e9;
    private Vertex v1, v2, v3, v4, v5, v6;
    private ArrayList<Vertex> vertexList;
    private ArrayList<Edge> edgeList;
    private MaximumBipartite maximumBipartite;

    @Before
    public void runBefore() {
        v1 = new Vertex("v1");
        v2 = new Vertex("v2");
        v3 = new Vertex("v3");
        v4 = new Vertex("v4");
        v5 = new Vertex("v5");
        v6 = new Vertex("v6");
        e1 = new Edge(v1,v4,10);
        e2 = new Edge(v1,v5,9);
        e3 = new Edge(v1,v6,8);
        e4 = new Edge(v2,v4,7);
        e5 = new Edge(v2,v5,10);
        e6 = new Edge(v2,v6,5);
        e7 = new Edge(v3,v4,4);
        e8 = new Edge(v3,v5,3);
        e9 = new Edge(v3,v6,10);
        edgeList = new ArrayList<>();
        vertexList = new ArrayList<>();
        edgeList.add(e4); edgeList.add(e3); edgeList.add(e2); edgeList.add(e1);
        edgeList.add(e9); edgeList.add(e8); edgeList.add(e7); edgeList.add(e6);
        edgeList.add(e5);
        vertexList.add(v1); vertexList.add(v2); vertexList.add(v3);
        vertexList.add(v4); vertexList.add(v5); vertexList.add(v6);
        maximumBipartite = new MaximumBipartite(vertexList,edgeList);
    }

    @Test
    public void testMergeSort() {
        maximumBipartite.sortEdges();
        ArrayList<Edge> temp = maximumBipartite.getEdgeList();
        int test = 1000000;
        for (Edge e : temp) {
            assertTrue(test >= e.getWeight());
            test = e.getWeight();
        }
    }

    @Test
    public void testSolveMaximumWeight() {
        HashSet<Edge> result = maximumBipartite.solveMaximumWeight();
        assertEquals(result.size(), 3);
        for (Edge e : result) {
            assertTrue(10 == e.getWeight());
        }
    }

    @Test
    public void testGetTotalWeight() {
        maximumBipartite.solveMaximumWeight();
        assertEquals(30, maximumBipartite.getTotalWeight());
    }

    @Test
    // Maximum Bipartite Graph in assignment 1
    public void testSolveMaximumWeightExample() {
        v1 = new Vertex("v1");
        v2 = new Vertex("v2");
        v3 = new Vertex("v3");
        v4 = new Vertex("v4");
        v5 = new Vertex("v5");
        e1 = new Edge(v1,v4,10);
        e2 = new Edge(v1,v5,2);
        e4 = new Edge(v2,v4,10);
        e5 = new Edge(v2,v5,10);
        e7 = new Edge(v3,v4,2);
        e8 = new Edge(v3,v5,2);
        edgeList = new ArrayList<>();
        vertexList = new ArrayList<>();
        edgeList.add(e4);edgeList.add(e2); edgeList.add(e1);
        edgeList.add(e8); edgeList.add(e7); edgeList.add(e5);
        vertexList.add(v1); vertexList.add(v2); vertexList.add(v3);
        vertexList.add(v4); vertexList.add(v5);
        maximumBipartite = new MaximumBipartite(vertexList,edgeList);

        maximumBipartite.solveMaximumWeight();
        assertEquals(20, maximumBipartite.getTotalWeight());
    }
}