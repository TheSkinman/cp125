package com.scg.domain;

import com.scg.util.PersonalName;

public class ClientAccount implements Account {

	private String name;
	private PersonalName contact;
	private boolean billable;

	/**
	 * @param name Name of the Client Account.
	 * @param contact Contact for the Client Account.
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
	 * @param contact sets the contact for the Client Account.
	 */
	public void setContact(PersonalName contact) {
		this.contact = contact;
	}
}
