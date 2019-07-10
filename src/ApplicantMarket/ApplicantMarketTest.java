package ApplicantMarket;

import ApplicantMarket.Model.Employer;
import ApplicantMarket.Model.Student;
import ApplicantMarket.Model.Unit;
import javafx.util.Pair;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicantMarketTest {
    private Student s1, s2, s3;
    private Employer e1, e2, e3;
    private ApplicantMarket applicantMarket;
    private ArrayList<Student> students;
    private ArrayList<Employer> employers;
    private ArrayList<Student> pL1, pL2, pL3;
    private ArrayList<Employer> pL4, pL5;

    @Before
    public void runBefore() {
        s1 = new Student("s1");
        s2 = new Student("s2");
        s3 = new Student("s3");
        e1 = new Employer("e1");
        e2 = new Employer("e2");
        e3 = new Employer("e3");
        pL1 = new ArrayList<>(); pL2 = new ArrayList<>(); pL3 = new ArrayList<>();
        pL4 = new ArrayList<>(); pL5 = new ArrayList<>();
        students = new ArrayList<>();
        employers = new ArrayList<>();
    }

    @Test
    public void testApplicantMarketTrivialEmpty() {
        System.out.println("testApplicantMarketTrivialEmpty");
        applicantMarket = new ApplicantMarket(students, employers, 0);
        HashSet<Pair<Unit, Unit>> result = applicantMarket.findMatches();
        assertEquals(result.size(), 0);
        applicantMarket.printOut();
    }

    @Test
    public void testApplicantMarketTrivialOnePairPrefer() {
        System.out.println("testApplicantMarketTrivialOnePairPrefer");
        pL1.add(s1);
        pL4.add(e1);
        s1.setPreferenceList(pL4);
        e1.setPreferenceList(pL1);
        students.add(s1);
        employers.add(e1);
        applicantMarket = new ApplicantMarket(students, employers, 1);
        HashSet<Pair<Unit, Unit>>  result = applicantMarket.findMatches();
        assertEquals(result.size(), 1);
        applicantMarket.printOut();
    }

    @Test
    public void testApplicantMarketTrivialOnePairNotPrefer() {
        System.out.println("testApplicantMarketTrivialOnePairNotPrefer");
        pL1.add(s1);
        pL4.add(e1);
        s1.setPreferenceList(pL4);
        e1.setPreferenceList(pL1);
        students.add(s1);
        employers.add(e1);
        applicantMarket = new ApplicantMarket(students, employers, 0);
        HashSet<Pair<Unit, Unit>>  result = applicantMarket.findMatches();
        assertEquals(result.size(), 2);
        applicantMarket.printOut();
    }

    @Test
    public void testApplicantMarketSimple() {
        System.out.println("testApplicantMarketTrivialOnePairNotPrefer");
        pL1.add(s1);
        pL1.add(s2);
        pL2.add(s2);
        pL2.add(s1);
        pL4.add(e1);
        pL4.add(e2);
        s1.setPreferenceList(pL4);
        s2.setPreferenceList(pL4);
        e1.setPreferenceList(pL1);
        e2.setPreferenceList(pL2);
        students.add(s1);
        students.add(s2);
        employers.add(e1);
        employers.add(e2);
        applicantMarket = new ApplicantMarket(students, employers, 2);
        HashSet<Pair<Unit, Unit>>  result = applicantMarket.findMatches();
        assertEquals(result.size(), 2);
        applicantMarket.printOut();
    }

    @Test
    public void testApplicantMarketComplex() {
        System.out.println("testApplicantMarketTrivialOnePairNotPrefer");
        pL1.add(s1); pL1.add(s2); pL1.add(s3);
        pL2.add(s3); pL2.add(s2); pL2.add(s1);
        pL3.add(s3); pL3.add(s1); pL3.add(s2);
        pL4.add(e1); pL4.add(e2); pL4.add(e3);
        pL5.add(e2); pL5.add(e1); pL5.add(e3);
        s1.setPreferenceList(pL4);
        s2.setPreferenceList(pL5);
        s3.setPreferenceList(pL4);
        e1.setPreferenceList(pL1);
        e2.setPreferenceList(pL2);
        e3.setPreferenceList(pL3);
        students.add(s1);
        students.add(s2);
        students.add(s3);
        employers.add(e1);
        employers.add(e2);
        employers.add(e3);
        applicantMarket = new ApplicantMarket(students, employers, 3);
        HashSet<Pair<Unit, Unit>>  result = applicantMarket.findMatches();
        assertEquals(result.size(), 3);
        applicantMarket.printOut();
    }

    @Test
    public void testApplicantMarketComplexWithNoPref() {
        System.out.println("testApplicantMarketTrivialOnePairNotPrefer");
        pL1.add(s1);
        pL1.add(s2);
        pL2.add(s2);
        pL2.add(s1);
        pL4.add(e1);
        pL4.add(e2);
        s1.setPreferenceList(pL4);
        s2.setPreferenceList(pL4);
        e1.setPreferenceList(pL1);
        e2.setPreferenceList(pL2);
        students.add(s1);
        students.add(s2);
        employers.add(e1);
        employers.add(e2);
        applicantMarket = new ApplicantMarket(students, employers, 2);
        HashSet<Pair<Unit, Unit>>  result = applicantMarket.findMatches();
        assertEquals(result.size(), 2);
        applicantMarket.printOut();
    }

    @Test
    public void testApplicantMarketComplexWithLimitedEmployer() {
        System.out.println("testApplicantMarketTrivialOnePairNotPrefer");
        pL1.add(s1);
        pL1.add(s2);
        pL2.add(s2);
        pL2.add(s1);
        pL4.add(e1);
        s1.setPreferenceList(pL4);
        s2.setPreferenceList(pL4);
        e1.setPreferenceList(pL1);
        students.add(s1);
        students.add(s2);
        employers.add(e1);
        applicantMarket = new ApplicantMarket(students, employers, 2);
        HashSet<Pair<Unit, Unit>>  result = applicantMarket.findMatches();
        assertEquals(result.size(), 2);
        applicantMarket.printOut();
    }

    @Test
    public void testApplicantMarketComplexWithLimitedStudent() {
        System.out.println("testApplicantMarketTrivialOnePairNotPrefer");
        pL1.add(s1);
        pL4.add(e1);
        pL4.add(e2);
        s1.setPreferenceList(pL4);
        e1.setPreferenceList(pL1);
        e2.setPreferenceList(pL1);
        students.add(s1);
        employers.add(e1);
        employers.add(e2);
        applicantMarket = new ApplicantMarket(students, employers, 2);
        HashSet<Pair<Unit, Unit>>  result = applicantMarket.findMatches();
        assertEquals(result.size(), 2);
        applicantMarket.printOut();
    }
}