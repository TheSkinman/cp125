package com.scg.domain;

import com.scg.util.PersonalName;

/**
 * A consultant for the SCG, just has a PersonalName.
 * 
 * @author Norman Skinner
 *
 */
public class Consultant {

    private PersonalName name;

    /**
     * Creates a new instance of Consultant.
     * 
     * @param name
     *            the consultant's name.
     */
    public Consultant(PersonalName name) {
        super();
        this.name = name;
    }

    /**
     * Getter for name property.
     * 
     * @return value of name property.
     */
    public PersonalName getName() {
        return name;
    }

    /**
     * Returns the string representation of the consultant's name.
     * 
     * @return the consultant name string
     */
    @Override
    public String toString() {
        return getName().toString();
    }
}
