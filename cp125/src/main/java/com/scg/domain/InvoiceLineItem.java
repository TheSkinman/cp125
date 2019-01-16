package com.scg.domain;

import java.text.NumberFormat;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class InvoiceLineItem {
	private static final Logger log = LoggerFactory.getLogger(InvoiceLineItem.class);
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
		String returnString = String.format("%1$tm/%1$td/%1$tY  %2$-27s  %3$-18s   %4$5s  %5$,10.2f%n",
				getDate(), getConsultant(), getSkill(), getHours(), (double)getCharge());
		return returnString;
	}
}
