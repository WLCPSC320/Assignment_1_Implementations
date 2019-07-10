package UtilitarianMarriage.Model;

public abstract class Person {
    protected String name;
    protected Boolean isEngaged;

    // EFFECTS: returns name
    public String getName() {
        return name;
    }

    // EFFECTS: If engaged, return true, else false
    public abstract Boolean getIsEngaged();

    // MODIFIES: this
    // EFFECTS: sets name
    public void setName(String name) {
        this.name = name;
    }
}
