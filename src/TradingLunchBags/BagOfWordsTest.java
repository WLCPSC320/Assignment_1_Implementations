package TradingLunchBags;

import javafx.util.Pair;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BagOfWordsTest {
    private BagOfWords bagOfWords;

    @Before
    public void beforeEach() {
        bagOfWords = new BagOfWords();
    }

    @Test
    public void testConvertToBag_BFEmpty() {
        ArrayList<Pair<String,Integer>> result = bagOfWords.ConvertToBag_BF("");
        assertEquals(0, result.size());
        System.out.println("testConvertToBag_BFEmpty");
        System.out.println(result);
    }

    @Test
    public void testConvertToBag_BFSingle() {
        ArrayList<Pair<String,Integer>> result = bagOfWords.ConvertToBag_BF("apple");
        assertEquals(1, result.size());
        assertEquals(result.get(0).getKey(), "apple");
        assertEquals(result.get(0).getValue(), 1);
        System.out.println("testConvertToBag_BFSingle");
        System.out.println(result);
    }

    @Test
    public void testConvertToBag_BFMultipleSingle() {
        ArrayList<Pair<String,Integer>> result = bagOfWords.ConvertToBag_BF("apple apple apple apple apple");
        assertEquals(1, result.size());
        assertEquals(result.get(0).getKey(), "apple");
        assertEquals(result.get(0).getValue(), 5);
        System.out.println("testConvertToBag_BFMultipleSingle");
        System.out.println(result);
    }

    @Test
    public void testConvertToBag_BFAdvanced() {
        ArrayList<Pair<String,Integer>> result = bagOfWords.ConvertToBag_BF("apple banana apple cherry");
        assertEquals(3, result.size());
        assertEquals(result.get(0).getKey(), "apple");
        assertEquals(result.get(0).getValue(), 2);
        assertEquals(result.get(1).getKey(), "banana");
        assertEquals(result.get(1).getValue(), 1);
        assertEquals(result.get(2).getKey(), "cherry");
        assertEquals(result.get(2).getValue(), 1);
        System.out.println("testConvertToBag_BFAdvanced");
        System.out.println(result);
    }
}