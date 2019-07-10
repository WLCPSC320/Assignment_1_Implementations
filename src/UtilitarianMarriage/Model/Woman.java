package UtilitarianMarriage.Model;

import java.util.ArrayList;

public class Woman extends Person {
    private ArrayList<Integer> preferenceList; // 0 index is highest preference
    private Man engagedTo;

    public Woman(String name, int index) {
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
        int i = preferenceList.get(m1.getIndex());
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
