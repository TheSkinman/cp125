package com.scg.domain;

import java.time.LocalDate;

/**
 * Encapsulates a single billable item to be included in an invoice. The
 * InvoiceLineItem includes:
 * <ul>
 * <li>date the service was provided,</li>
 * <li>name of consultant providing the service</li>
 * <li>the sevice/skill provided</li>
 * <li>number of hours</li>
 * </ul>
 * 
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
     * Construct an InvoiceLineItem.
     * 
     * @param date
     *            The date of this line item.
     * @param consultant
     *            Consultant for this line item.
     * @param skill
     *            Skill for this line item.
     * @param hours
     *            Hours for this line item. 
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
     * Get the date for this line item.
     * 
     * @return The date.
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Get the consultant for this line item.
     * 
     * @return The consultant.
     */
    public Consultant getConsultant() {
        return consultant;
    }

    /**
     * Get the skill for this line item.
     * 
     * @return The skill.
     */
    public Skill getSkill() {
        return skill;
    }

    /**
     * Get the hours for this line item.
     * 
     * @return The hours.
     */
    public int getHours() {
        return hours;
    }

    /**
     * Get the charge for this line item.
     * 
     * @return The charge.
     */
    public int getCharge() {
        return charge;
    }

    /**
     * Print the date, consultant, skill, hours and charge for this line item.
     * 
     * @return Formatted string.
     */
    @Override
    public String toString() {
        String returnString = String.format("%1$tm/%1$td/%1$tY  %2$-27s  %3$-19s  %4$5d  %5$,10.2f%n", getDate(),
                getConsultant(), getSkill(), getHours(), (double) getCharge());
        return returnString;
    }
}
