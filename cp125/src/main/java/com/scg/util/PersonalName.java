package com.scg.util;

import java.io.Serializable;
import java.util.Comparator;
/**
 * Encapsulates the first, middle and last name of a person.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class PersonalName implements Serializable, Comparable<PersonalName> {
    private static final long serialVersionUID = 7515511859542202652L;
    private String firstName;
    private String lastName;
    private String middleName;

    private static Comparator<PersonalName> natraulOrderComparator = Comparator.comparing(PersonalName::getLastName)
            .thenComparing(PersonalName::getFirstName).thenComparing(PersonalName::getMiddleName);

    /**
     * Public constructor for creating a Personal Name object.
     */
    public PersonalName() {
        this("", "");
    }

    /**
     * Public constructor for creating a Personal Name object.
     * 
     * @param lastName
     *            value for the last name.
     * @param firstName
     *            value for the first name.
     */
    public PersonalName(String lastName, String firstName) {
        this(lastName, firstName, "");
    }

    /**
     * Public constructor for creating a Personal Name object.
     * 
     * @param lastName
     *            value for the last name.
     * @param firstName
     *            value for the first name.
     * @param middleName
     *            value for the middle name.
     */
    public PersonalName(String lastName, String firstName, String middleName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    /**
     * Getter for firstName property.
     * 
     * @return value of firstName property
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for firstName property.
     * 
     * @param firstName
     *            new value of firstName property
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for lastName property.
     * 
     * @return value of lastName property.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for lastName property.
     * 
     * @param lastName
     *            new value of lastName property
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for middleName property.
     * 
     * @return value of middleName property.
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Setter for middleName property.
     * 
     * @param middleName
     *            new value of middleName property
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Calculate the hash code.
     * 
     * @return the hash code value
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
        return result;
    }

    /**
     * Compare names for equivalence.
     * 
     * @param other
     *            the name to compare to
     * @return true if all the name elements have the same value
     */
    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (getClass() != other.getClass())
            return false;
        PersonalName otherPN = (PersonalName) other;
        if (firstName == null) {
            if (otherPN.firstName != null)
                return false;
        } else if (!firstName.equals(otherPN.firstName))
            return false;
        if (lastName == null) {
            if (otherPN.lastName != null)
                return false;
        } else if (!lastName.equals(otherPN.lastName))
            return false;
        if (middleName == null) {
            if (otherPN.middleName != null)
                return false;
        } else if (!middleName.equals(otherPN.middleName))
            return false;
        return true;
    }

    /**
     * Create string representation of this object in the format "LastName,
     * FirstName MiddleName".
     * 
     * @return the formatted name.
     */
    @Override
    public String toString() {
        return String.format("%s, %s %s", lastName, firstName, middleName).trim();
    }

    /**
     * Compares this object with the specified object for order. Ordering by last
     * name, first name and finally middle name. Returns a negative integer, zero,
     * or a positive integer as this object is less than, equal to, or greater than
     * the specified object.
     * 
     * @param other
     *            the Object to be compared.
     * @return a negative integer, zero, or a positive integer as this object is
     *         less than, equal to, or greater than the specified object.
     */
    @Override
    public int compareTo(PersonalName other) {
        if (this == other) return 0;
        return natraulOrderComparator.compare(this, other);
    }
}
