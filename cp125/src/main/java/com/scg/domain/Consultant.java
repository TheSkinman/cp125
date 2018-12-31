package com.scg.domain;

import com.scg.util.PersonalName;

/**
 * 
 * @author Norman Skinner
 *
 */
public class Consultant {
	
	private PersonalName name;

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Consultant: " + getName();
	}

	/**
	 * @param name
	 */
	public Consultant(PersonalName name) {
		super();
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public PersonalName getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(PersonalName name) {
		this.name = name;
	}
	
	

}
