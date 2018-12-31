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
		this(name, contact, true);
	}
	
	/**
	 * @param name Name of the Client Account.
	 * @param contact Contact for the Client Account.
	 * @param billable Set true if the account is billable.
	 */
	public ClientAccount(String name, PersonalName contact, boolean billable) {
		super();
		this.name = name;
		this.contact = contact;
		this.billable = billable;
	}

	/**
	 * @return returns the name of the Client Account.
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}

	/**
	 * @return boolean returns TRUE is Client Account is billable.
	 */
	@Override
	public boolean isBillable() {
		// TODO Auto-generated method stub
		return billable;
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
