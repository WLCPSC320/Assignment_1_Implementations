package UtilitarianMarriage.Model;

import java.util.ArrayList;

public class Man extends Person {
    private ArrayList<Integer> preferenceList; // 0 index is highest preference, corresponds to women 1
    private Woman engagedTo;

    public Man(String name, int index) {
        this.name = name;
        isEngaged = false;
        this.index = index;
    }

    // EFFECTS: returns preferenceList
    public ArrayList<Integer> getPreferenceList() {
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
    // EFFECT: Returns true if this prefers w1 over w2, else false
    public Boolean prefers(Woman w1) {
        int i = preferenceList.get(w1.getIndex());
        int j = preferenceList.get(engagedTo.getIndex());
        for (Integer utility : preferenceList) {
            if (utility == i) {
                return true;
            }
            if (utility == j) {
                return false;
            }
        }
        return false; // will never get here
    }

    // MODIFIES: this
    // EFFECT: sets preferenceList to pL
    public void setPrefernceList(ArrayList<Integer> pL) {
        preferenceList = pL;
    }
}