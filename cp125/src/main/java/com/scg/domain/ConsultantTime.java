/**
 * 
 */
package com.scg.domain;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A consultants time, maintains date, skill, account and hours data. This
 * represent a single time entry on a time card.
 * 
 * @author Norman Skinner
 *
 */
public class ConsultantTime extends Object implements Serializable {
    private static final long serialVersionUID = 4830754727897784816L;
    private Account account;
    private LocalDate date;
    private int hours;
    private Skill skill;

    /**
     * Creates a new instance of ConsultantTime.
     * 
     * @param date
     *            The date this instance occurred.
     * @param account
     *            The account to charge the hours to; either a Client or
     *            NonBillableAccount.
     * @param skillType
     *            The skill type.
     * @param hours
     *            The number of hours, which must be positive.
     * @throws IllegalArgumentException
     *             if the hours are &lt;= 0.
     * 
     */
    public ConsultantTime(LocalDate date, Account account, Skill skillType, int hours) throws IllegalArgumentException {
        super();
        if (hours <= 0) {
            throw new IllegalArgumentException("The argument [hours] can not be less than '1'.");
        }
        this.date = date;
        this.account = account;
        this.skill = skillType;
        this.hours = hours;
    }

    /**
     * Setter for date property.
     * 
     * @return value of date property
     */
    public LocalDate getDate() {
        return date;
    }
    
    /**
     * Setter for date property.
     * 
     * @param date
     *            new value of date property
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    /**
     * Getter for account property.
     * 
     * @return value of account property
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Setter for account property.
     * 
     * @param account
     *            new value of account property
     */
    public void setAccount(Account account) {
        this.account = account;
    }
    
    /**
     * Determines if the time is billable.
     * 
     * @return true if the time is billable otherwise false.
     */
    public boolean isBillable() {
        return account.isBillable();
    }
    
    /**
     * Getter for hours property.
     * 
     * @return Value of hours property
     */
    public int getHours() {
        return hours;
    }

    /**
     * Setter for hours property.
     * 
     * @param hours
     *            New value of hours property must be &gt; 0
     * @throws IllegalArgumentException
     *             if the hours are &lt;= 0.
     */
    public void setHours(int hours) throws IllegalArgumentException {
        if (hours <= 0) {
            throw new IllegalArgumentException("The argument [hours] can not be less than '1'.");
        }
        this.hours = hours;
    }
    
    /**
     * Getter for skill property.
     * 
     * @return Value of skill property.
     */
    public Skill getSkill() {
        return skill;
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
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + hours;
        result = prime * result + ((skill == null) ? 0 : skill.hashCode());
        return result;
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConsultantTime other = (ConsultantTime) obj;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (hours != other.hours)
            return false;
        if (skill != other.skill)
            return false;
        return true;
    }

    /**
     * Creates a string representation of the consultant time.
     * 
     * @return this ConsultantTime as a formatted string.
     */
    @Override
    public String toString() {
        return String.format("%1$-27s  %2$tm/%2$td/%2$tY  %3$5s  %4$s%n", getAccount().getName(), getDate(), getHours(), getSkill());
    }

}

