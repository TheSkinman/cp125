package com.scg.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tracks changes in employee benifits. Listens for any PropertyChangeEvent and
 * simply logs them. Additionally, Listens for any BenefitEvent and logs those
 * as well. No other actions are taken in response to any event.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class BenefitManager implements BenefitListener, PropertyChangeListener {
    private static final Logger log = LoggerFactory.getLogger(BenefitManager.class);

    public BenefitManager() {
        super();
    }

    /**
     * Logs the change.
     * 
     * @param evt
     *            a property change event for the sickLeaveHours or vacationHours,
     *            or payRate property
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        StaffConsultant sc = (StaffConsultant) evt.getSource();
        if (log.isInfoEnabled())
            log.info("{} changed from {} to {} for {}", evt.getPropertyName(), evt.getOldValue(), evt.getNewValue(),
                    sc.getName().toString());
    }

    /** {@inheritDoc} */
    @Override
    public void dentalCancellation(BenefitEvent event) {
        if (log.isInfoEnabled())
            log.info("{} cancelled in dental, {}", event.getConsultant(), event.getEffectiveDate());
    }

    /** {@inheritDoc} */
    @Override
    public void dentalEnrollment(BenefitEvent event) {
        if (log.isInfoEnabled())
            log.info("{} enrolled in dental, {}", event.getConsultant(), event.getEffectiveDate());
    }

    /** {@inheritDoc} */
    @Override
    public void medicalCancellation(BenefitEvent event) {
        if (log.isInfoEnabled())
            log.info("{} cancelled in medical, {}", event.getConsultant(), event.getEffectiveDate());
    }

    /** {@inheritDoc} */
    @Override
    public void medicalEnrollment(BenefitEvent event) {
        if (log.isInfoEnabled())
            log.info("{} enrolled in medical, {}", event.getConsultant(), event.getEffectiveDate());
    }
}
