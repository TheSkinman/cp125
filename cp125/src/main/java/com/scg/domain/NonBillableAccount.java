package com.scg.domain;

/**
 * Accounts which can not be billed - non-billable accounts, such as sick leave, vacation, or business development.
 * @author Norman Skinner
 *
 */
public enum NonBillableAccount implements Account {
    /** Business Development. */
    BUSINESS_DEVELOPMENT("Business Development"),
    /** Sick Leave. */
	SICK_LEAVE("Sick Leave"),
    /** Vacation. */
	VACATION("Vacation");
	
	private String friendlyName;
	
	private NonBillableAccount(String name) {
		this.friendlyName = name;
	}
	
	/**
	 * Getter for the name of this account.
	 */
	public String getName() {
		return friendlyName;
	}
	
	/**
	 * Determines if this account is billable.
	 */
	public boolean isBillable() {
		return false;
	}
	
	/**
	 * Returns the friendly name for this enumerated value.
	 */
	public String toString() {
	    return friendlyName;
	}

}
