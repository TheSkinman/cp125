package com.scg.beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.EventObject;
import java.util.Optional;

import com.scg.domain.Consultant;

/**
 * Event used to notify listeners of a Consultant's enrollment or cancellation
 * of medical or dental benefits.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class BenefitEvent extends EventObject implements Serializable {
    private static final long serialVersionUID = 6527089119809237475L;

    private Consultant consultant;
    private LocalDate effectiveDate;
    private static Optional<Boolean> medicalStatus = Optional.of(false);
    private static Optional<Boolean> dentalStatus = Optional.of(false);

    private BenefitEvent(Object source, Consultant consultant, LocalDate effectiveDate) {
        super(source);
        this.consultant = consultant;
        this.effectiveDate = effectiveDate;
    }

    /**
     * Creates a medical enrollment event.
     * 
     * @param source
     *            the event source
     * @param consultant
     *            the consultant being terminated
     * @param effectiveDate
     *            effective date of cancellation
     * @return a new event object
     */
    public static BenefitEvent enrollMedical(Object source, Consultant consultant, LocalDate effectiveDate) {
        medicalStatus = Optional.of(true);
        return new BenefitEvent(source, consultant, effectiveDate);
    }

    /**
     * Creates a medical cancellation event.
     * 
     * @param source
     *            the event source
     * @param consultant
     *            the consultant being terminated
     * @param effectiveDate
     *            effective date of cancellation
     * @return a new event object
     */
    public static BenefitEvent cancelMedical(Object source, Consultant consultant, java.time.LocalDate effectiveDate) {
        medicalStatus = Optional.of(false);
        return new BenefitEvent(source, consultant, effectiveDate);
    }

    /**
     * Creates a dental enrollment event.
     * 
     * @param source
     *            the event source
     * @param consultant
     *            the consultant being terminated
     * @param effectiveDate
     *            effective date of cancellation
     * @return a new event object
     */
    public static BenefitEvent enrollDental(Object source, Consultant consultant, java.time.LocalDate effectiveDate) {
        dentalStatus = Optional.of(true);
        return new BenefitEvent(source, consultant, effectiveDate);
    }

    /**
     * Creates a dental cancellation event.
     * 
     * @param source
     *            the event source
     * @param consultant
     *            the consultant being terminated
     * @param effectiveDate
     *            effective date of cancellation
     * @return a new event object
     */
    public static BenefitEvent cancelDental(Object source, Consultant consultant, java.time.LocalDate effectiveDate) {
        dentalStatus = Optional.of(true);
        return new BenefitEvent(source, consultant, effectiveDate);
    }

    /**
     * Gets the medical enrollment status.
     * 
     * @return true if enrolled event, false if cancellation, empty if not a medical
     *         enrollment event.
     */
    public Optional<Boolean> medicalStatus() {
        return medicalStatus;
    }

    /**
     * Gets the dental enrollment status.
     * 
     * @return true enrolled event, false if cancellation, empty if not a dental
     *         enrollment event.
     */
    public Optional<Boolean> dentalStatus() {
        return dentalStatus;
    }

    /**
     * Gets the consultant that was terminated.
     * 
     * @return the consultant that was terminated
     */
    public Consultant getConsultant() {
        return consultant;
    }

    /**
     * Gets the effective date.
     * 
     * @return the effective date
     */
    public java.time.LocalDate getEffectiveDate() {
        return effectiveDate;
    }
}
