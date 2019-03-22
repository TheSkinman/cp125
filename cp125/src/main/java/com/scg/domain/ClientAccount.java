package com.scg.domain;

import java.util.Comparator;

import com.scg.util.Address;
import com.scg.util.PersonalName;

/**
 * A billable Account that additionally has client contact information and an
 * address.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class ClientAccount implements Account, Comparable<ClientAccount> {
    private static final long serialVersionUID = -5127341550616865045L;
    private Address address;
    private String name;
    private PersonalName contact;

    private static Comparator<ClientAccount> natraulOrderComparator = Comparator.comparing(ClientAccount::getName)
            .thenComparing(ClientAccount::getContact).thenComparing(ClientAccount::getAddress);

    /**
     * Creates a new instance of ClientAccount.
     * 
     * @param name
     *            Name of the Client Account.
     * @param contact
     *            Contact for the Client Account.
     * @param address
     *            Address for the Client Account.
     */
    public ClientAccount(String name, PersonalName contact, Address address) {
        super();
        this.name = name;
        this.contact = contact;
        this.address = address;
    }

    /**
     * Compares this Client Account with the specified Client Account for order,
     * ordering by name, contact, and finally address. Returns a negative integer,
     * zero, or a positive integer as this Client Account is less than, equal to, or
     * greater than the specified Client Account.
     * 
     * @param other
     *            the Client Account to be compared.
     * @return a negative integer, zero, or a positive integer as this Client
     *         Account is less than, equal to, or greater than the specified Client
     *         Account.
     */
    @Override
    public int compareTo(ClientAccount other) {
        if (this == other)
            return 0;
        return natraulOrderComparator.compare(this, other);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * 
     * @param other
     *            the reference to other object with which to compare.
     * @return true if ClientAccount has the same name as the the other object
     *         argument; false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null)
            return false;
        if (getClass() != other.getClass())
            return false;
        ClientAccount otherObj = (ClientAccount) other;
        if (address == null) {
            if (otherObj.address != null)
                return false;
        } else if (!address.equals(otherObj.address))
            return false;
        if (contact == null) {
            if (otherObj.contact != null)
                return false;
        } else if (!contact.equals(otherObj.contact))
            return false;
        if (name == null) {
            if (otherObj.name != null)
                return false;
        } else if (!name.equals(otherObj.name))
            return false;
        return true;
    }

    /**
     * Gets the address for this account.
     * 
     * @return value of address property.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Gets the contact for this account.
     * 
     * @return contact value of contact property
     */
    public PersonalName getContact() {
        return contact;
    }

    /**
     * Gets the account name.
     * 
     * @return returns value of name property.
     */
    @Override
    public String getName() {
        return name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((contact == null) ? 0 : contact.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    /**
     * Determines if this account is billable.
     * 
     * @return boolean always true
     */
    @Override
    public boolean isBillable() {
        return true;
    }

    /**
     * Setter for address property.
     * 
     * @param address
     *            new value for address property
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Setter for contact property.
     * 
     * @param contact
     *            new value for contact property
     */
    public void setContact(PersonalName contact) {
        this.contact = contact;
    }

    /**
     * String representation for this Client.
     * 
     * @return Formatted string of the form
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%s%n", getName()));
        sb.append(getAddress().toString());
        sb.append(getContact().toString());

        return sb.toString();
    }
}
