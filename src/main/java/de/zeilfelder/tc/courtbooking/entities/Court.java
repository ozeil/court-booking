package de.zeilfelder.tc.courtbooking.entities;

public enum Court {
    PLATZ_1("Platz 1"),
    PLATZ_2("Platz 2"),
    PLATZ_3("Platz 3");

    private final String displayString;

    Court(String displayString) {
        this.displayString = displayString;
    }


    public String getDisplayString() {
        return displayString;
    }
}
