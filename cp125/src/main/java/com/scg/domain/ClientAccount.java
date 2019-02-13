package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.PersonalName;

/**
 * A billable Account that additionally has client contact information and an address.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class ClientAccount implements Account {
    private Address address;
    private String name;
    private PersonalName contact;

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
     * Gets the account name.
     * 
     * @return returns value of name property.
     */
    @Override
    public String getName() {
        return name;
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
     * Gets the contact for this account.
     * 
     * @return contact value of contact property
     */
    public PersonalName getContact() {
        return contact;
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
     * Gets the address for this account.
     * 
     * @return value of address property.
     */
    public Address getAddress() {
        return address;
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
