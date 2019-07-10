package Trie;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrieTest {
    private Trie trie;

    @Before
    public void runBefore() {
        trie = new Trie();
    }

    @Test
    public void testTrieEmpty() {
        trie.setPrintTrie();
        ArrayList<Pair<String,Integer>> result = trie.getPrintOut();
        assertEquals(0, result.size());
        System.out.println("testTrieEmpty");
        System.out.println(result);
    }

    @Test
    public void testTrieSingle() {
        trie.addWord("apple");
        trie.setPrintTrie();
        ArrayList<Pair<String,Integer>> result = trie.getPrintOut();
        assertEquals(1, result.size());
        assertEquals(result.get(0).getKey(), "apple");
        assertEquals(result.get(0).getValue(), 1);
        System.out.println("testTrieSingle");
        System.out.println(result);
    }

    @Test
    public void testTrieMultipleSingle() {
        trie.addWord("apple");
        trie.addWord("apple");
        trie.addWord("apple");
        trie.setPrintTrie();
        ArrayList<Pair<String,Integer>> result = trie.getPrintOut();
        assertEquals(1, result.size());
        assertEquals(result.get(0).getKey(), "apple");
        assertEquals(result.get(0).getValue(), 3);
        System.out.println("testTrieMultipleSingle");
        System.out.println(result);
    }

    @Test
    public void testTrieMultipleAdvanced() {
        trie.addWord("apple");
        trie.addWord("banana");
        trie.addWord("apple");
        trie.addWord("cherry");
        trie.setPrintTrie();
        ArrayList<Pair<String,Integer>> result = trie.getPrintOut();
        assertEquals(3, result.size());
        assertEquals(result.get(0).getKey(), "apple");
        assertEquals(result.get(0).getValue(), 2);
        assertEquals(result.get(1).getKey(), "banana");
        assertEquals(result.get(1).getValue(), 1);
        assertEquals(result.get(2).getKey(), "cherry");
        assertEquals(result.get(2).getValue(), 1);
        System.out.println("testTrieMultipleAdvanced");
        System.out.println(result);
    }
}