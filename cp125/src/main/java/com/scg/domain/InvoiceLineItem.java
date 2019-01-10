package com.scg.domain;

import java.time.LocalDate;

/**
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class InvoiceLineItem {
	private int charge;
	private Consultant consultant;
	private LocalDate date;
	private int hours;
	private Skill skill;

	/**
	 * @param date
	 * @param consultant
	 * @param skill
	 * @param hours
	 */
	public InvoiceLineItem(LocalDate date, Consultant consultant, Skill skill, int hours) {
		super();
		this.date = date;
		this.consultant = consultant;
		this.skill = skill;
		this.hours = hours;
		charge = this.hours * this.skill.getRate();
	}

	/**
	 * @return the charge
	 */
	public int getCharge() {
		return charge;
	}

	/**
	 * @return the consultant
	 */
	public Consultant getConsultant() {
		return consultant;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InvoiceLineItem [charge=" + charge + ", consultant=" + consultant + ", date=" + date + ", hours="
				+ hours + ", skill=" + skill + "]";
	}
}
