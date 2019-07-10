package TradingLunchBags;

import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HashbagTest {
    private Hashbag hashbag;

    @Before
    public void runBefore() {
        hashbag = new Hashbag();
    }

    @Test
    public void testConvertToBag_HTEmpty() {
        ArrayList<Pair<String,Integer>> result = hashbag.convertToBag_HT("");
        assertEquals(0, result.size());
        System.out.println("testConvertToBag_HTEmpty");
        System.out.println(result);
    }

    @Test
    public void testConvertToBag_HTSingle() {
        ArrayList<Pair<String,Integer>> result = hashbag.convertToBag_HT("apple");
        assertEquals(1, result.size());
        assertEquals(result.get(0).getKey(), "apple");
        assertEquals(result.get(0).getValue(), 1);
        System.out.println("testConvertToBag_HTSingle");
        System.out.println(result);
    }

    @Test
    public void testConvertToBag_HTMultipleSingle() {
        ArrayList<Pair<String,Integer>> result = hashbag.convertToBag_HT("apple apple apple apple apple");
        assertEquals(1, result.size());
        assertEquals(result.get(0).getKey(), "apple");
        assertEquals(result.get(0).getValue(), 5);
        System.out.println("testConvertToBag_HTMultipleSingle");
        System.out.println(result);
    }

    @Test
    public void testConvertToBag_HTAdvanced() {
        ArrayList<Pair<String,Integer>> result = hashbag.convertToBag_HT("apple banana apple cherry");
        assertEquals(3, result.size());
        assertEquals(result.get(0).getKey(), "apple");
        assertEquals(result.get(0).getValue(), 2);
        assertEquals(result.get(1).getKey(), "cherry");
        assertEquals(result.get(1).getValue(), 1);
        assertEquals(result.get(2).getKey(), "banana");
        assertEquals(result.get(2).getValue(), 1);
        System.out.println("testConvertToBag_HTAdvanced");
        System.out.println(result);
    }

}