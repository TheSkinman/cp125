/**
 * 
 */
package com.scg.domain;

import java.time.LocalDate;

/**
 * @author Norman Skinner
 *
 */
public class ConsultantTime {

	private Account account;
	private LocalDate date;
	private int hours;
	private Skill skill;

	/**
	 * @param date
	 * @param account
	 * @param skill
	 * @param hours
	 */
	public ConsultantTime(LocalDate date, Account account, Skill skillType, int hours) {
		super();
		if (hours <= 0) {
			throw new IllegalArgumentException("The argument [hours] can not be less than '1'.");
		}
		this.date = date;
		this.account = account;
		this.skill = skillType;
		this.hours = hours;
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
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	/**
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @return the hours
	 */
	public int getHours() {
		return hours;
	}

	/**
	 * @return the skill
	 */
	public Skill getSkill() {
		return skill;
	}

	/**
	 * 
	 * @return The name of the Account
	 */
	public String getName() {
		return account.getName();
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

	/**
	 * Determines if the time is billable.
	 * 
	 * @return true if the time is billable otherwise false.
	 */
	public boolean isBillable() {
		return account.isBillable();
	}

	/**
	 * @param account
	 *            the account to set
	 */
	public void setAccount(Account account) {
		this.account = account;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
	 * @param hours
	 *            the hours to set
	 */
	public void setHours(int hours) {
		if (hours <= 0) {
			throw new IllegalArgumentException("The argument [hours] can not be less than '1'.");
		}
		this.hours = hours;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return String.format("%1$-27s  %2$tm/%2$td/%2$tY  %3$5s  %4$s%n", getName(), getDate(), getHours(), getSkill());
	}

}
