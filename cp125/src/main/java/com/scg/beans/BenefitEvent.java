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
    private Optional<Boolean> enrolledInMendical = Optional.of(false);
    private Optional<Boolean> enrolledInDental = Optional.of(false);

    private BenefitEvent(Object source, Consultant consultant, LocalDate effectiveDate, Optional<Boolean> enrolledInMendical, Optional<Boolean> enrolledInDental) {
        super(source);
        this.consultant = consultant;
        this.effectiveDate = effectiveDate;
        this.enrolledInMendical = enrolledInMendical;
        this.enrolledInDental = enrolledInDental;
        
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
        return new BenefitEvent(source, consultant, effectiveDate, Optional.of(true), Optional.empty());
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
        return new BenefitEvent(source, consultant, effectiveDate, Optional.of(false), Optional.empty());
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
        return new BenefitEvent(source, consultant, effectiveDate, Optional.empty(), Optional.of(true));
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
        return new BenefitEvent(source, consultant, effectiveDate, Optional.empty(), Optional.of(false));
    }

    /**
     * Gets the medical enrollment status.
     * 
     * @return true if enrolled event, false if cancellation, empty if not a medical
     *         enrollment event.
     */
    public Optional<Boolean> medicalStatus() {
        return enrolledInMendical;
    }

    /**
     * Gets the dental enrollment status.
     * 
     * @return true enrolled event, false if cancellation, empty if not a dental
     *         enrollment event.
     */
    public Optional<Boolean> dentalStatus() {
        return enrolledInDental;
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
