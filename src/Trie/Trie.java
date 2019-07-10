package Trie;

import Trie.Model.Node;
import javafx.util.Pair;

import java.util.ArrayList;

public class Trie {
    private Node root;
    private ArrayList<Pair<String,Integer>> printOut;

    public Trie() {
        root = new Node("");
        printOut = new ArrayList<>();
    }

    // EFFECT: Sets root
    public void setRoot(Node root) {
        this.root = root;
    }

    // EFFECT: Returns root
    public Node getRoot() {
        return root;
    }

    // MODIFIES: this
    // EFFECT: Adds a word into Trie or increments it's value if it already exists
    public void addWord(String str) {
        Node currentNode = root;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i != str.length() - 1) {
                if (nodeExists(Character.toString(c), currentNode)) {
                    currentNode = getNodeEquals(Character.toString(c), currentNode);
                } else {
                    Node temp = new Node(Character.toString(c), currentNode);
                    currentNode = temp;
                }
            } else {
                if (nodeExists(Character.toString(c), currentNode)) {
                    currentNode = getNodeEquals(Character.toString(c), currentNode);
                    currentNode.setValue(currentNode.getValue() + 1);
                } else {
                    Node temp = new Node(Character.toString(c), currentNode, 1);
                }
            }
        }
    }

    // EFFECT: Returns true if node with name s is in root nodes, else false
    private Boolean nodeExists(String s, Node root) {
        for (Node node : root.getNodes()) {
            if (node.getName().equals(s)) {
                return true;
            }
        }
        return false;
    }

    // REQUIRES: nodeExists returns true on inputs
    // EFFECT: Returns node with name s in root nodes, else null
    private Node getNodeEquals(String s, Node root) {
        for (Node node: root.getNodes()) {
            if (node.getName().equals(s)) {
                return node;
            }
        }
        return null;
    }

    // MODIFIES: this
    // EFFECT: Makes an ArrayList of all strings with their respective values
    // ¯\_(ツ)_/¯ Does anyone actually read this stuff? ¯\_(ツ)_/¯
    private void makePrintTrie(Node root) {
        ArrayList<Node> nodes = root.getNodes();
        if (nodes.size() == 0) {
            return;
        } else {
            for (Node node : nodes) {
                if (root.getValue() != 0) {
                    printOut.add(new Pair(root.getName(), root.getValue()));
                }
                makePrintTrieHelper(node, "");
            }
        }
    }

    // MODIFIES: this
    // EFFECT: Makes an ArrayList of all strings with their respective values
    private void makePrintTrieHelper(Node root, String str) {
        ArrayList<Node> nodes = root.getNodes();
        for (Node node : nodes) {
            String soFar = str;
            soFar += root.getName();
            if (node.getValue() != 0) {
                soFar += node.getName();
                printOut.add(new Pair(soFar, node.getValue()));
            }
            makePrintTrieHelper(node, soFar);
        }
    }

    // EFFECT: Sets printOut
    public void setPrintTrie() {
        printOut = new ArrayList<>();
        makePrintTrie(root);
    }

    // EFFECT: Returns printOut
    public ArrayList<Pair<String, Integer>> getPrintOut() {
        return printOut;
    }
}
