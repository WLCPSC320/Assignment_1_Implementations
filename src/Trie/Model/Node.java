package Trie.Model;

import java.util.ArrayList;

public class Node {
    private ArrayList<Node> nodes;
    private String name;
    private int value;

    public Node(String name){
        nodes = new ArrayList<>();
        this.name = name;
        value = 0;
    }

    public Node(String name, Node parent) {
        parent.nodes.add(this);
        nodes = new ArrayList<>();
        this.name = name;
        value = 0;
    }

    public Node(String name, Node parent, int value) {
        parent.nodes.add(this);
        nodes = new ArrayList<>();
        this.name = name;
        this.value = value;
    }

    // EFFECTS: Adds a node to nodes
    public void addNode(Node child) {
        nodes.add(child);
    }

    // MODIFIES: this
    // EFFECTS: sets value
    public void setValue(int i) {
        value = i;
    }

    // EFFECTS: returns value
    public int getValue() {
        return value;
    }

    // EFFECTS: return name
    public String getName() {
        return name;
    }

    // EFFECTS: return nodes
    public ArrayList<Node> getNodes() {
        return nodes;
    }
}
