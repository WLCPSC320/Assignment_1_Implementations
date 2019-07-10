package TradingLunchBags;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

public class Hashbag {
    private Hashtable<String, Integer> hashTable;
    private ArrayList<Pair<String,Integer>> bagOfWords;

    public Hashbag() {
        bagOfWords = new ArrayList<>();
    }

    // REQUIRES: Valid string input (spaces separated by one space)
    // MODIFIES: this
    // EFFECT: Convert a list into a bag of words
    public ArrayList<Pair<String,Integer>> convertToBag_HT(String w) {
        hashTable = new Hashtable<>(countWords(w));
        bagOfWords = new ArrayList<>();
        if (w.length() != 0) {
            String[] words = w.split(" ");
            for (String word : words) {
                if (hashTable.containsKey(word)) {
                    int c_w = hashTable.get(word);
                    hashTable.replace(word, c_w + 1);
                } else {
                    hashTable.put(word, 1);
                }
            }
            setBagOfWords();
            return bagOfWords;
        } else {
            return bagOfWords;
        }
    }

    // MODIFIES: this
    // EFFECT: Sets bagOfWords
    private void setBagOfWords() {
        Set<String> keySet = hashTable.keySet();
        for (String key : keySet) {
            bagOfWords.add(new Pair(key, hashTable.get(key)));
        }
    }

    // EFFECT: Returns the number of words in w
    private int countWords(String w) {
        String[] words = w.split(" ");
        return words.length;
    }
}
