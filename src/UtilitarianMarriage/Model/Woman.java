package UtilitarianMarriage.Model;

import java.util.ArrayList;

public class Woman extends Person {
    private ArrayList<Man> preferenceList; // 0 index is highest preference
    private Man engagedTo;

    public Woman(String name) {
        this.name = name;
        isEngaged = false;
    }

    // EFFECTS: returns preferenceList
    public ArrayList<Man> getPreferenceList() {
        return preferenceList;
    }

    // EFFECTS: If engaged, return true, else false
    @Override
    public Boolean getIsEngaged() {
        return engagedTo != null;
    }

    // EFFECTS: returns engagedTo
    public Man getEngaged() {
        return engagedTo;
    }

    // MODIFIES: this
    // EFFECTS: sets the engagedTo to w
    public void setEngagedTo(Man m) {
        engagedTo = m;
    }

    // REQUIRES: engagedTo != null
    // EFFECT: Returns true if this prefers w1 over engagedTo, else false
    public Boolean prefers(Man m1) {
        for (Man m2 : preferenceList) {
            if (m2 == m1) {
                return true;
            }
            if (m2 == engagedTo) {
                return false;
            }
        }
        return false; // will never get here
    }

    // MODIFIES: this
    // EFFECT: sets preferenceList to pL
    public void setPrefernceList(ArrayList<Man> pL) {
        preferenceList = pL;
    }
}
