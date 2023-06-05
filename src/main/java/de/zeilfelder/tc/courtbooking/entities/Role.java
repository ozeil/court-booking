package de.zeilfelder.tc.courtbooking.entities;

public enum Role {
    USER("Benutzer"),
    ADMIN("Administrator"),
    TI("Touristinfo");

    private final String displayName;

    Role(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
