package com.scg.domain;

import com.scg.util.Address;
import com.scg.util.PersonalName;

/**
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class ClientAccount implements Account {
	private Address address;
	private String name;
	private PersonalName contact;
	private boolean billable;

	/**
	 * @param name
	 *            Name of the Client Account.
	 * @param contact
	 *            Contact for the Client Account.
	 * @param address
	 *            Address for the Client Account.
	 */
	public ClientAccount(String name, PersonalName contact) {
		super();
		this.name = name;
		this.contact = contact;
	}

	/**
	 * @return returns the name of the Client Account.
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * @return boolean returns TRUE is Client Account is billable.
	 */
	@Override
	public boolean isBillable() {
		return true;
	}

	/**
	 * 
	 * @return contact for the Client Account.
	 */
	public PersonalName getContact() {
		return contact;
	}

	/**
	 * @param contact
	 *            sets the contact for the Client Account.
	 */
	public void setContact(PersonalName contact) {
		this.contact = contact;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
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
