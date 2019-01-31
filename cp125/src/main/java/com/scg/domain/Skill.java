package com.scg.domain;

public enum Skill {
    PROJECT_MANGER("Project Manager", 250),
    SYSTEM_ARCHITECT("System Architect", 200),
    SOFTWARE_ENGINEER("Software Engineer", 150),
    SOFTWARE_TESTER("Software Tester", 100),
    UNKNOWN_SKILL("Unknown Skill", 0);

    private String friendlyName;
    private int rate;

    private Skill(String name, int rate) {
        friendlyName = name;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return friendlyName;
    }

    public int getRate() {
        return rate;
    }

}
