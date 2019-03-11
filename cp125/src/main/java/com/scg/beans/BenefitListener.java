package com.scg.beans;

import java.util.EventListener;

/**
 * Interface for accepting notification of consultant changes in medical and
 * dental enrollment.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public interface BenefitListener extends EventListener {
    /**
     * Invoked when a consultant is cancels dental.
     * 
     * @param event
     *            the benefit event
     */
    void dentalCancellation(BenefitEvent event);

    /**
     * Invoked when a consultant is enrolls in dental.
     * 
     * @param event
     *            the benefit event
     */
    void dentalEnrollment(BenefitEvent event);

    /**
     * Invoked when a consultant is cancels medical.
     * 
     * @param event
     *            the benefit event
     */
    void medicalCancellation(BenefitEvent event);

    /**
     * Invoked when a consultant is enrolls in medical.
     * 
     * @param event
     *            the benefit event
     */
    void medicalEnrollment(BenefitEvent event);
}
