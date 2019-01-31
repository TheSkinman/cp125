/**
 * 
 */
package com.scg.domain;

/**
 * @author Norman Skinner
 *
 */
public enum NonBillableAccount implements Account {
	SICK_LEAVE("Sick Leave"),
	VACATION("Vacation"),
	BUSINESS_DEVELOPMENT("Business Development");
	
	private String friendlyName;
	
	private NonBillableAccount(String name) {
		this.friendlyName = name;
	}
	
	public String getName() {
		return friendlyName;
	}
	
	public boolean isBillable() {
		return false;
	}
	
	public String toString() {
	    return friendlyName;
	}

}
