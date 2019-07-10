package UtilitarianMarriage;

import UtilitarianMarriage.Model.Man;
import UtilitarianMarriage.Model.Woman;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class UtilitarianMarriageTest {
    private Man m1, m2, m3;
    private Woman w1, w2, w3;
    private ArrayList<Integer> pL1, pL2, pL3;
    private ArrayList<Man> men;
    private ArrayList<Woman> women;
    private UtilitarianMarriage utilitarianMarriage;

    @Before
    public void runBefore() {
        m1 = new Man("m1", 0);
        m2 = new Man("m2", 1);
        m3 = new Man("m3", 2);
        w1 = new Woman("w1", 0);
        w2 = new Woman("w2", 1);
        w3 = new Woman("w3", 2);
        pL1 = new ArrayList<>(); pL2 = new ArrayList<>(); pL3 = new ArrayList<>();
        men = new ArrayList<>();
        women = new ArrayList<>();
        men.add(m1);
        women.add(w1);
    }

    @Test
    public void testFindMatchesTrivialEmpty() {
        System.out.println("testFindMatchesTrivialEmpty");
        ArrayList<Woman> womanList = new ArrayList<>();
        ArrayList<Man> manList = new ArrayList<>();
        utilitarianMarriage = new UtilitarianMarriage(manList, womanList);
        HashSet<Pair<Man,Woman>> result = utilitarianMarriage.findMatches();
        assertEquals(result.size(), 0);
        assertEquals(utilitarianMarriage.getTotalUtility(), 0);
        utilitarianMarriage.printMatches();
    }

    @Test
    public void testFindMatchesTrivialOnePair() {
        System.out.println("testFindMatchesTrivialOnePair");
        pL1.add(1);
        m1.setPrefernceList(pL1);
        w1.setPrefernceList(pL1);
        utilitarianMarriage = new UtilitarianMarriage(men, women);
        HashSet<Pair<Man,Woman>> result = utilitarianMarriage.findMatches();
        assertEquals(result.size(), 1);
        assertEquals(utilitarianMarriage.getTotalUtility(), 1);
        utilitarianMarriage.printMatches();
    }

    @Test
    public void testFindMatchesSimple() {
        System.out.println("testFindMatchesSimple");
        pL1.add(10); pL1.add(1);
        m1.setPrefernceList(pL1);
        m2.setPrefernceList(pL1);
        w1.setPrefernceList(pL1);
        w2.setPrefernceList(pL1);
        men.add(m2);
        women.add(w2);
        utilitarianMarriage = new UtilitarianMarriage(men, women);
        HashSet<Pair<Man,Woman>> result = utilitarianMarriage.findMatches();
        assertEquals(result.size(), 2);
        assertEquals(utilitarianMarriage.getTotalUtility(), 11);
        utilitarianMarriage.printMatches();
    }

    @Test
    public void testFindMatchesAdvanced() {
        System.out.println("testFindMatchesSimple");
        pL1.add(10); pL1.add(9); pL1.add(1);
        pL2.add(9); pL2.add(10); pL2.add(4);
        pL3.add(8); pL3.add(3); pL3.add(5);
        m1.setPrefernceList(pL1);
        m2.setPrefernceList(pL2);
        m3.setPrefernceList(pL3);
        w1.setPrefernceList(pL1);
        w2.setPrefernceList(pL2);
        w3.setPrefernceList(pL3);
        men.add(m2);
        men.add(m3);
        women.add(w2);
        women.add(w3);
        utilitarianMarriage = new UtilitarianMarriage(men, women);
        HashSet<Pair<Man,Woman>> result = utilitarianMarriage.findMatches();
        assertEquals(result.size(), 3);
        assertEquals(utilitarianMarriage.getTotalUtility(), 25);
        utilitarianMarriage.printMatches();
    }


}