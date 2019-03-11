package com.scg.beans;

import java.util.EventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Eeoc implements TerminationListener, EventListener {
    private static final Logger log = LoggerFactory.getLogger(Eeoc.class);

    private int forcedTerminationCount;
    private int voluntaryTerminationCount;

    public Eeoc() {
    }

    /**
     * Simply prints a message indicating that the consultant was fired and adjusts
     * the forced termination count.
     * 
     * @param evt
     *            the termination event
     */
    @Override
    public void forcedTermination(TerminationEvent evt) {
        log.info("{} was fired.", evt.getConsultant().toString());
        forcedTerminationCount++;
    }

    /**
     * Simply prints a message indicating that the consultant quit and adjusts the
     * voluntary termination count.
     * 
     * @param evt
     *            the termination event
     */
    @Override
    public void voluntaryTermination(TerminationEvent evt) {
        log.info("{} quit.", evt.getConsultant().toString());
        voluntaryTerminationCount++;
    }

    /**
     * Gets the forced termination count.
     * 
     * @return the forced termination count
     */
    public int forcedTerminationCount() {
        return forcedTerminationCount;
    }

    /**
     * Gets the voluntary termination count.
     * 
     * @return the voluntary termination count
     */
    public int voluntaryTerminationCount() {
        return voluntaryTerminationCount;
    }
}
