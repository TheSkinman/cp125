package com.scg.beans;

import java.beans.PropertyVetoException;
import java.time.LocalDate;

import javax.swing.event.EventListenerList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.Consultant;

/**
 * Responsible for modifying the pay rate, sick leave and vacation hours of
 * staff consultants. Provide methods for registration of BenefitListeners, and
 * TerminationListeners.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class HumanResourceManager {
    private static final Logger log = LoggerFactory.getLogger(HumanResourceManager.class);
    
    private EventListenerList listenerList = new EventListenerList();

    public HumanResourceManager() {
    }

    /**
     * Sets the pay rate for a staff consultant and logs whether the pay rate change
     * was approved or rejected (vetoed).
     * 
     * @param c
     *            the consultant
     * @param newPayRate
     *            the new pay rate for the consultant
     */
    public void adjustPayRate(StaffConsultant c, int newPayRate) {
        double raisePercentage = (newPayRate - c.getPayRate())/ (double)c.getPayRate();
        log.info(String.format("%% change = (%1$d - %2$d)/%2$d = %3$10.6f", newPayRate, c.getPayRate(), raisePercentage));
        try {
            c.setPayRate(newPayRate);
            log.info("Approved pay adjustment for {}", c.getName());
        } catch (PropertyVetoException e) {
            log.info("Denied pay adjustment for {}", c.getName());
        }
    }

    /**
     * Sets the sick leave hours for a staff consultant.
     * 
     * @param c
     *            the consultant
     * @param newSickLeaveHours
     *            the new sick leave hours for the consultant
     */
    public void adjustSickLeaveHours(StaffConsultant c, int newSickLeaveHours) {
        c.setSickLeaveHours(newSickLeaveHours);
    }

    /**
     * Sets the vacation hours for a staff consultant.
     * 
     * @param c
     *            the consultant
     * @param newVacationHours
     *            the new vacation hours for the consultant
     */
    public void adjustVacationHours(StaffConsultant c, int newVacationHours) {
        c.setVacationHours(newVacationHours);
    }

    /**
     * Accepts the resignation of a consultant and fires a voluntary termination
     * event for the consultant.
     * 
     * @param c
     *            the consultant resigning
     */
    public synchronized void acceptResignation(Consultant c) {
        TerminationListener[] listeners;
        listeners = listenerList.getListeners(TerminationListener.class);
        for (TerminationListener listener : listeners) {
            listener.voluntaryTermination(new TerminationEvent(this, c, true));
        }
    }

    /**
     * Fires an involuntary termination event for the staff consultant. Terminates
     * the employment of a consultant and fires a forced termination event for the
     * consultant.
     * 
     * @param c
     *            the consultant being terminated
     */
    public synchronized void terminate(Consultant c) {
        TerminationListener[] listeners;
        listeners = listenerList.getListeners(TerminationListener.class);
        for (TerminationListener listener : listeners) {
            listener.forcedTermination(new TerminationEvent(this, c, false));
        }
    }

    /**
     * Adds a termination listener.
     * 
     * @param l
     *            the listener to add
     */
    public void addTerminationListener(TerminationListener l) {
        listenerList.add(TerminationListener.class, l);
    }

    /**
     * Removes a termination listener.
     * 
     * @param l
     *            the listener to remove
     */
    public void removeTerminationListener(TerminationListener l) {
        listenerList.remove(TerminationListener.class, l);
    }

    /**
     * Enroll a consultant in medical, and fires a medical enrollment event.
     * 
     * @param c
     *            the consultant enrolling in medical
     */
    public void enrollMedical(Consultant c) {
        BenefitListener[] listeners;
        listeners = listenerList.getListeners(BenefitListener.class);

        for (BenefitListener listener : listeners) {
            listener.medicalEnrollment(BenefitEvent.enrollMedical(this, c, LocalDate.now()));
        }
    }

    /**
     * Cancel a consultant's enrollment in medical, and fires a medical cancellation
     * event.
     * 
     * @param c
     *            the consultant canceling medical enrollment
     */
    public void cancelMedical(Consultant c) {
        BenefitListener[] listeners;
        listeners = listenerList.getListeners(BenefitListener.class);

        for (BenefitListener listener : listeners) {
            listener.medicalCancellation(BenefitEvent.cancelMedical(this, c, LocalDate.now()));
        }
    }

    /**
     * Enroll a consultant in dental, and fires a dental enrollment event.
     * 
     * @param c
     *            the consultant enrolling in dental
     */
    public void enrollDental(Consultant c) {
        BenefitListener[] listeners;
        listeners = listenerList.getListeners(BenefitListener.class);

        for (BenefitListener listener : listeners) {
            listener.dentalEnrollment(BenefitEvent.enrollDental(this, c, LocalDate.now()));
        }
    }

    /**
     * Cancel a consultant's enrollment in dental, and fires a dental cancellation
     * event
     * 
     * @param c
     *            the consultant canceling dental enrollment
     */
    public void cancelDental(Consultant c) {
        BenefitListener[] listeners;
        listeners = listenerList.getListeners(BenefitListener.class);

        for (BenefitListener listener : listeners) {
            listener.dentalCancellation(BenefitEvent.cancelDental(this, c, LocalDate.now()));
        }
    }

    /**
     * Adds a benefit listener.
     * 
     * @param l
     *            the listener to add
     */
    public void addBenefitListener(BenefitListener l) {
        listenerList.add(BenefitListener.class, l);
    }

    /**
     * Removes a benefit listener.
     * 
     * @param l
     *            the listener to remove
     */
    public void removeBenefitListener(BenefitListener l) {
        listenerList.remove(BenefitListener.class, l);
    }
}
