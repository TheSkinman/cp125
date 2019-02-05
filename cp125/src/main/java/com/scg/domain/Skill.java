package com.scg.domain;

public enum Skill {
    /** Project manager skill. */
    PROJECT_MANGER("Project Manager", 250),
    /** Engineer skill. */
    SOFTWARE_ENGINEER("Software Engineer", 150),
    /** Tester skill. */
    SOFTWARE_TESTER("Software Tester", 100),
    /** Architect skill. */
    SYSTEM_ARCHITECT("System Architect", 200),
    /** Unknown skill. */
    UNKNOWN_SKILL("Unknown Skill", 0);

    private String friendlyName;
    private int rate;

    private Skill(String name, int rate) {
        friendlyName = name;
        this.rate = rate;
    }

    /**
     * Returns the friendly name for this enumerated value.
     * 
     * @return the friendly name for this enumerated value
     */
    @Override
    public String toString() {
        return friendlyName;
    }

    /**
     * Getter for rate property.
     * 
     * @return Value of rate property.
     */
    public int getRate() {
        return rate;
    }

}
