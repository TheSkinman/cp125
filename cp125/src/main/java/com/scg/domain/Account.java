package com.scg.domain;

import java.io.Serializable;

/**
 * Defines an account as having a name and being either billable or no-billable,
 * all accounts must implement.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public interface Account extends Serializable {
    /**
     * Getter for the name of this account.
     * 
     * @return the name of this account.
     */
    String getName();

    /**
     * Determines if this account is billable.
     * 
     * @return true if the account is billable otherwise false.
     */
    boolean isBillable();
}
