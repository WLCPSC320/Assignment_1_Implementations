package TradingLunchBags;

import javafx.util.Pair;

import java.util.ArrayList;

public class BagOfWords {
    private ArrayList<Pair<String,Integer>> bagOfWords;
    private String word;

    public BagOfWords() {
        bagOfWords = new ArrayList<>();
    }

    // REQUIRES: Valid string input (spaces separated by one space)
    // MODIFIES: this
    // EFFECT: Convert a list into a bag of words
    public ArrayList<Pair<String,Integer>> ConvertToBag_BF(String w) {
        bagOfWords = new ArrayList<>();
        word = w;
        while (!word.isEmpty()) {
            String firstWord = getFirstWord(word);
            int c_w = countOccurrences(word, firstWord);
            bagOfWords.add(new Pair(firstWord, c_w));
            deleteOccurrences(firstWord);
        }
        return bagOfWords;
    }

    // MODIFIES: w
    // EFFECTS: deletes all occurrences of firstWord in w
    private void deleteOccurrences(String firstWord) {
        String retval = "";
        String a[] = word.split(" ");
        for (int i = 0; i < a.length; i++) {
            if (!firstWord.equals(a[i])) {
                retval += a[i];
                if (i != a.length - 1) {
                    retval += " ";
                }
            }
        }
        word = retval;
    }

    // EFFECT: Returns the number of times firstWord appears in w
    private int countOccurrences(String w, String firstWord) {
        int retval = 0;
        String a[] = w.split(" ");
        for (int i = 0; i < a.length; i++) {
            if (firstWord.equals(a[i])) {
                retval++;
            }
        }
        return retval;
    }

    // EFFECT: Returns the first word in a string
    private String getFirstWord(String w) {
        if (!w.contains(" ")) {
            return w;
        } else {
            String[] arrStr = w.split(" ", 2);
            return arrStr[0];
        }
    }

}
