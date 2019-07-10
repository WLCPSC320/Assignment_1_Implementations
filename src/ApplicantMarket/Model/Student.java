package ApplicantMarket.Model;

import java.util.ArrayList;

public class Student extends Unit{
    private ArrayList<Employer> preferenceList; // 0 index is highest preference
    private Employer matchedEmployer;
    private String name;

    public Student(String name) {
        this.name = name;
        preferenceList = new ArrayList<>();
        matchedEmployer = null;
    }

    // EFFECT: returns true if student is matched to a employer
    public Boolean isMatched() {
        return matchedEmployer != null;
    }

    // EFFECT: Returns true if this prefers e1 over e2 or null
    public Boolean prefers(Employer e1) {
        for (Employer e2 : preferenceList) {
            if (e2 == e1) {
                return true;
            }
            if (e2 == matchedEmployer) {
                return false;
            }
        }
        return false; // Won't work for employer
    }

    // EFFECT: returns true if this will work for e
    public Boolean willWork(Employer e) {
        return preferenceList.contains(e);
    }

    //===================SETTERS AND GETTERS==================

    // EFFECTS: returns preferenceList
    public ArrayList<Employer> getPreferenceList() {
        return preferenceList;
    }

    // MODIFIES: this
    // EFFECTS: sets preferenceList
    public void setPreferenceList(ArrayList<Employer> preferenceList) {
        this.preferenceList = preferenceList;
    }

    // EFFECTS: returns matchedStudent
    public Employer getMatchedEmployer() {
        return matchedEmployer;
    }

    // EFFECTS: sets matchedStudent
    public void setMatchedEmployer(Employer matchedEmployer) {
        this.matchedEmployer = matchedEmployer;
    }

    @Override
    // EFFECTS: gets name
    public String getName() {
        return name;
    }
}
