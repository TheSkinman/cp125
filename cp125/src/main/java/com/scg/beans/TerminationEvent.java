package com.scg.beans;

import java.io.Serializable;
import java.util.EventObject;

import com.scg.domain.Consultant;

/**
 * Event used to notify listeners of a Consultant's termination.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class TerminationEvent extends EventObject implements Serializable {
    private static final long serialVersionUID = -6258437135074903734L;

    private final Consultant consultant;
    private final boolean voluntary;

    /**
     * Constructor.
     * 
     * @param source
     *            the event source
     * @param consultant
     *            the consultant being terminated
     * @param voluntary
     *            was the termination voluntary
     */
    public TerminationEvent(final Object source, final Consultant consultant, final boolean voluntary) {
        super(source);
        this.consultant = consultant;
        this.voluntary = voluntary;
    }

    /**
     * Gets the voluntary termination status.
     * 
     * @return true if a voluntary termination
     */
    public boolean isVoluntary() {
        return voluntary;
    }

    /**
     * Gets the consultant that was terminated.
     * 
     * @return the consultant that was terminated
     */
    public Consultant getConsultant() {
        return consultant;
    }
}
