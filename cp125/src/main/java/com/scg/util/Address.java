/**
 * 
 */
package com.scg.util;

import static java.util.Objects.isNull;

import java.io.Serializable;
/**
 * A simple mailing address. Does no validity checking for things like valid
 * states or postal codes. Instances of this class are immutable.
 * 
 * @author Norman Skinner
 *
 */
public class Address implements Serializable {
    private static final long serialVersionUID = -9158943095481217014L;
    private String city;
    private String postalCode;
    private StateCode state;
    private String streetNumber;
    private Integer hashCode;

    /**
     * Construct an Address.
     * 
     * @param streetNumber
     *            the street number.
     * @param city
     *            the city.
     * @param state
     *            the state.
     * @param postalCode
     *            the postal code.
     */
    public Address(String streetNumber, String city, StateCode state, String postalCode) {
        super();
        this.streetNumber = streetNumber;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    /**
     * Get the street number number for this address.
     * 
     * @return the streetNumber the street number.
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * Gets the city for this address.
     * 
     * @return the city the city.
     */
    public String getCity() {
        return city;
    }

    /**
     * Get the state for this address.
     * 
     * @return the state the state.
     */
    public StateCode getState() {
        return state;
    }

    /**
     * Gets the postal code for this address.
     * 
     * @return the postalCode the postal code.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        if (isNull(hashCode)) {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((city == null) ? 0 : city.hashCode());
            result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
            result = prime * result + ((state == null) ? 0 : state.hashCode());
            result = prime * result + ((streetNumber == null) ? 0 : streetNumber.hashCode());
            hashCode = result;
        }
        return hashCode;
    }

    /**
     * Compares two Address object for value equality.
     * 
     * @param obj
     *            the object to compare to the object
     * @return true if all fields are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Address other = (Address) obj;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (postalCode == null) {
            if (other.postalCode != null)
                return false;
        } else if (!postalCode.equals(other.postalCode))
            return false;
        if (state != other.state)
            return false;
        if (streetNumber == null) {
            if (other.streetNumber != null)
                return false;
        } else if (!streetNumber.equals(other.streetNumber))
            return false;
        return true;
    }

    /**
     * Prints this address in the form: <br>
     * <br>
     * street number<br> 
     * city, state postal code<br>
     * 
     * @return the formatted address.
     */
    @Override
    public String toString() {
        return String.format("%s%n%s, %s %s %n", getStreetNumber(), getCity(), getState(), getPostalCode());
    }
}
