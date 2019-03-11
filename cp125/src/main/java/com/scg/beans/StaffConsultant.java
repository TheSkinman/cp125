package com.scg.beans;

import static java.util.Objects.isNull;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.Consultant;
import com.scg.util.PersonalName;

/**
 * A consultant who is kept on staff (receives benefits). The StaffConsultant
 * has bound properties for vacation hours and sick leave hours and a
 * constrained property for pay rate (allows veto).
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class StaffConsultant extends Consultant implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(StaffConsultant.class);
    private static final String BE_POSITIVE = " must be a positive value.";

    private static final long serialVersionUID = 5144982420201138358L;

    /** Pay rate property name. */
    public static final String PAY_RATE_PROPERTY_NAME = "payRate";
    /** Vacation hours property name. */
    public static final String VACATION_HOURS_PROPERTY_NAME = "vacationHours";
    /** Pay rate property name. */
    public static final String SICK_LEAVE_HOURS_PROPERTY_NAME = "sickLeaveHours";

    private PersonalName name;
    private int rate;
    private int sickLeave;
    private int vacation;

    /** PropertyChangeSupport instance. */
    private final PropertyChangeSupport pcs;

    /** PropertyChangeSupport instance. */
    private final VetoableChangeSupport vcs;

    /**
     * Creates a new instance of StaffConsultant
     * 
     * @param name
     *            the consultant's name
     * @param rate
     *            the pay rate in cents
     * @param sickLeave
     *            the sick leave hours
     * @param vacation
     *            the vacation hours
     */
    public StaffConsultant(PersonalName name, int rate, int sickLeave, int vacation) {
        super(name);
        if (isNull(name))
            throw new IllegalArgumentException("Staff Consultant's name can not be NULL.");
        if (rate < 0)
            throw new IllegalArgumentException("Pay Rate " + BE_POSITIVE);
        if (sickLeave < 0)
            throw new IllegalArgumentException("Sick Leave Hours " + BE_POSITIVE);
        if (vacation < 0)
            throw new IllegalArgumentException("Vacation Hours " + BE_POSITIVE);
        this.name = name;
        this.rate = rate;
        this.sickLeave = sickLeave;
        this.vacation = vacation;

        pcs = new PropertyChangeSupport(this);
        vcs = new VetoableChangeSupport(this);
    }

    /**
     * Adds a payRate property change listener.
     * 
     * @param l
     *            the listener
     */
    public void addPayRateListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(PAY_RATE_PROPERTY_NAME, l);
    }

    /**
     * Adds a general property change listener.
     * 
     * @param l
     *            the listener
     */
    public void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }

    /**
     * Adds a sickLeaveHours property change listener.
     * 
     * @param l
     *            the listener
     */
    public void addSickLeaveHoursListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(SICK_LEAVE_HOURS_PROPERTY_NAME, l);
    }

    /**
     * Adds a vacationHours property change listener.
     * 
     * @param l
     *            the listener
     */
    public void addVacationHoursListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(VACATION_HOURS_PROPERTY_NAME, l);
    }

    /**
     * Adds a vetoable change listener, only applicable to payRate changes.
     * 
     * @param l
     *            the listener
     */
    public void addVetoableChangeListener(VetoableChangeListener l) {
        vcs.addVetoableChangeListener(PAY_RATE_PROPERTY_NAME, l);
    }

    /**
     * Get the current pay rate.
     * 
     * @return the pay rate in cents
     */
    public int getPayRate() {
        return rate;
    }

    /**
     * Get the available sick leave.
     * 
     * @return the available sick leave hours
     */
    public int getSickLeaveHours() {
        return sickLeave;
    }

    /**
     * Get the available vacation hours.
     * 
     * @return the available vacation hours
     */
    public int getVacationHours() {
        return vacation;
    }

    /**
     * Removes a payRate property change listener.
     * 
     * @param l
     *            the listener
     */
    public void removePayRateListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(PAY_RATE_PROPERTY_NAME, l);
    }

    /**
     * Remove a general property change listener.
     * 
     * @param l
     *            the listener
     */
    public void removePropertyChangeListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(l);
    }

    /**
     * Removes a sickLeaveHours property change listener.
     * 
     * @param l
     *            the listener
     */
    public void removeSickLeaveHoursListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(SICK_LEAVE_HOURS_PROPERTY_NAME, l);
    }

    /**
     * Removes a vacationHours property change listener.
     * 
     * @param l
     *            the listener
     */
    public void removeVacationHoursListener(PropertyChangeListener l) {
        pcs.removePropertyChangeListener(VACATION_HOURS_PROPERTY_NAME, l);
    }

    /**
     * Removes a vetoable change listener.
     * 
     * @param l
     *            the listener
     */
    public void removeVetoableChangeListener(VetoableChangeListener l) {
        vcs.removeVetoableChangeListener("vetoable", l);
    }

    /**
     * Set the pay rate. Fires the VetoableChange event and if approved the
     * PropertyChange event.
     * 
     * @param payRate
     *            the pay rate in cents
     * @throws PropertyVetoException
     *             if a veto occurs
     */
    public void setPayRate(int payRate) {
        if (payRate < 0)
            throw new IllegalArgumentException("Pay Rate " + BE_POSITIVE);
    
        int oldPayRate = rate;
        try {
            vcs.fireVetoableChange(PAY_RATE_PROPERTY_NAME, oldPayRate, payRate);
        } catch (PropertyVetoException e) {
            return;
        }
        rate = payRate;
        pcs.firePropertyChange(PAY_RATE_PROPERTY_NAME, oldPayRate, payRate);
    }

    /**
     * Set the sick leave hours. Fires the ProperrtyChange event.
     * 
     * @param sickLeaveHours
     *            the available sick leave hours
     */
    public void setSickLeaveHours(int sickLeaveHours) {
        if (sickLeaveHours < 0)
            throw new IllegalArgumentException("Sick Leave Hours " + BE_POSITIVE);

        int oldsickLeaveHours = sickLeave;
        sickLeave = sickLeaveHours;
        pcs.firePropertyChange(SICK_LEAVE_HOURS_PROPERTY_NAME, oldsickLeaveHours, sickLeave);
    }

    /**
     * Set the vacation hours. Fires the ProperrtyChange event.
     * 
     * @param vacationHours
     *            the vacation hours
     */
    public void setVacationHours(int vacationHours) {
        if (vacationHours < 0)
            throw new IllegalArgumentException("Vacation Hours " + BE_POSITIVE);

        int oldVacationHours = vacation;
        vacation = vacationHours;
        pcs.firePropertyChange(VACATION_HOURS_PROPERTY_NAME, oldVacationHours, vacation);
    }

}
