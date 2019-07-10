package UtilitarianMarriage.Model;

import java.util.ArrayList;

public class Man extends Person {
    private ArrayList<Woman> preferenceList; // 0 index is highest preference
    private Woman engagedTo;

    public Man(String name) {
        this.name = name;
        isEngaged = false;
    }

    // EFFECTS: returns preferenceList
    public ArrayList<Woman> getPreferenceList() {
        return preferenceList;
    }

    // EFFECTS: If engaged, return true, else false
    @Override
    public Boolean getIsEngaged() {
        return engagedTo != null;
    }

    // EFFECTS: returns engagedTo
    public Woman getEngaged() {
        return engagedTo;
    }

    // MODIFIES: this
    // EFFECTS: sets the engagedTo to w
    public void setEngagedTo(Woman w) {
        engagedTo = w;
    }

    // REQUIRES: engagedTo != null
    // EFFECT: Returns true if this prefers w1 over engagedTo, else false
    public Boolean prefers(Woman w1) {
        for (Woman w2 : preferenceList) {
            if (w2 == w1) {
                return true;
            }
            if (w2 == engagedTo) {
                return false;
            }
        }
        return false; // will never get here
    }

    // MODIFIES: this
    // EFFECT: sets preferenceList to pL
    public void setPrefernceList(ArrayList<Woman> pL) {
        preferenceList = pL;
    }
}