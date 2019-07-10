package ApplicantMarket.Model;

import java.util.ArrayList;

public class Employer extends Unit{
    private ArrayList<Student> preferenceList; // 0 index is highest preference
    private Student matchedStudent;
    private String name;
    private int preferenceIndex;


    public Employer(String name) {
        this.name = name;
        preferenceList = new ArrayList<>();
        matchedStudent = null;
        preferenceIndex = 0;
    }

    // EFFECT: returns true if employer is matched to a student
    public Boolean isMatched() {
        return matchedStudent != null;
    }

    // REQUIRES isMatched() to return true
    // EFFECT: Returns true if this prefers s1 over matchedStudent
    public Boolean prefers(Student s1) {
        for (Student s2 : preferenceList) {
            if (s2 == s1) {
                return true;
            }
            if (s2 == matchedStudent) {
                return false;
            }
        }
        return false; // Never gets here
    }

    // MODIFIES: this
    // EFFECTS: increments preferenceIndex by 1
    public void incrementIndex() {
        preferenceIndex++;
    }

    //===================SETTERS AND GETTERS==================

    // EFFECTS: returns preferenceList
    public ArrayList<Student> getPreferenceList() {
        return preferenceList;
    }

    // MODIFIES: this
    // EFFECTS: sets preferenceList
    public void setPreferenceList(ArrayList<Student> preferenceList) {
        this.preferenceList = preferenceList;
    }

    // EFFECTS: returns matchedStudent
    public Student getMatchedStudent() {
        return matchedStudent;
    }

    // EFFECTS: sets matchedStudent
    public void setMatchedStudent(Student matchedStudent) {
        this.matchedStudent = matchedStudent;
    }

    @Override
    // EFFECTS: gets name
    public String getName() {
        return name;
    }

    // EFFECTS: get preferenceIndex
    public int getPreferenceIndex() {
        return preferenceIndex;
    }

    // EFFECTS: set preferenceIndex
    public void setPreferenceIndex(int preferenceIndex) {
        this.preferenceIndex = preferenceIndex;
    }
}
