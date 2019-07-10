package UtilitarianMarriage;

import MaximumBipartite.MaximumBipartite;
import MaximumBipartite.Model.Edge;
import MaximumBipartite.Model.Vertex;
import UtilitarianMarriage.Model.Man;
import UtilitarianMarriage.Model.Woman;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashSet;

public class UtilitarianMarriage {
    private ArrayList<Woman> women;
    private ArrayList<Man> men;
    private ArrayList<Edge> edgeList;
    private ArrayList<Vertex> vertexList;
    private HashSet<Pair<Man,Woman>> solutionSet;
    private int totalUtility;

    public UtilitarianMarriage(ArrayList<Man> men, ArrayList<Woman> women) {
        this.men = men;
        this.women = women;
        edgeList = new ArrayList<>();
        vertexList = new ArrayList<>();
        solutionSet = new HashSet<>();
        totalUtility = 0;
    }

    // REQUIRES: findMatches called previously
    // EFFECTS: returns totalUtility
    public int getTotalUtility() {
        return totalUtility;
    }

    // MODIFIES: this
    // EFFECT: Finds matches between men and women maximizing utility
    public HashSet<Pair<Man, Woman>> findMatches() {
        if (women.size() != 0) {
            convertToMaximumBipartite();
            MaximumBipartite maximumBipartite = new MaximumBipartite(vertexList, edgeList);
            HashSet<Edge> mbSolution = maximumBipartite.solveMaximumWeight();
            convertSolutionSet(mbSolution);
            totalUtility = maximumBipartite.getTotalWeight();
        }
        return solutionSet;
    }

    // MODIFIES: this
    // EFFECT: Sets up vertexList and edgeList
    private void convertToMaximumBipartite() {
        edgeList = new ArrayList<>();
        vertexList = new ArrayList<>();
        for (int i = 0; i < women.size(); i++) {
            Vertex tempW = new Vertex(women.get(i).getName(), women.get(i));
            vertexList.add(tempW);
            for (int j = 0; j < men.size(); j++) {
                Vertex tempM = null;
                if (!containsVertex(men.get(j))) {
                    tempM = new Vertex(men.get(j).getName(), men.get(j));
                    vertexList.add(tempM);
                } else {
                    tempM = getManVertex(men.get(j));
                }
                int averageWeight = (men.get(j).getPreferenceList().get(i) +
                        women.get(i).getPreferenceList().get(j)) / 2;
                Edge tempE = new Edge(tempM, tempW, averageWeight);
                edgeList.add(tempE);
            }
        }
    }

    // EFFECT: returns true if vertexList contains man in a vertex
    private Boolean containsVertex(Man man) {
        for (Vertex v : vertexList) {
            if (v.getP().equals(man)) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: containsVertex returns true
    // EFFECT: returns true if vertexList contains man in a vertex
    private Vertex getManVertex(Man man) {
        for (Vertex v : vertexList) {
            if (v.getP().equals(man)) {
                return v;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECT: converts a maximum bipartite solution set to a utilitarian marriage solution set
    private void convertSolutionSet(HashSet<Edge> mbsolution) {
        solutionSet = new HashSet<>();
        for (Edge edge : mbsolution) {
            Vertex vMan = edge.getVertex().getKey();
            Vertex vWoman = edge.getVertex().getValue();
            solutionSet.add(new Pair(vMan.getP(),vWoman.getP()));
        }
    }

    // EFFECT: Prints out the pairings and total utility
    public void printMatches() {
        for (Pair<Man,Woman> pair : solutionSet) {
            System.out.println(pair.getKey().getName() + " engaged to " + pair.getValue().getName());
        }
        System.out.println("Total Utility : " + totalUtility);
    }
}
