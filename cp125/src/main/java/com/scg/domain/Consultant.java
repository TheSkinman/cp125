package com.scg.domain;

import com.scg.util.PersonalName;

/**
 * A consultant for the SCG, just has a PersonalName.
 * 
 * @author Norman Skinner
 *
 */
public class Consultant implements Comparable<Consultant> {

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

    /**
     * Compares this Consultant object with the specified object for order. Returns
     * a negative integer, zero, or a positive integer as this object is less than,
     * equal to, or greater than the specified object, the consultant name is used
     * to perform the comparison.
     * 
     * @param other
     *            the Object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(Consultant other) {
        int diff = 0;
        if (this != other) {
            diff = name.compareTo(other.name);
        }
        return diff;
    }
}
