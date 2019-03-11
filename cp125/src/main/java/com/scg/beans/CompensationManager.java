package com.scg.beans;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.EventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Approves or rejects compensation changes. Listens for PropertyChangeEvents on
 * the payRate property, any pay rate increase in excess of will be vetoed. The
 * rejection (veto) or acceptance of each pay rate change will be logged as will
 * any successful pay rate change.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class CompensationManager implements PropertyChangeListener, VetoableChangeListener, EventListener {
    private static final Logger log = LoggerFactory.getLogger(CompensationManager.class);
    private static final String VETOABLE_PAY_RATE = "%s pay rate change, from %d to %d for %s";

    public CompensationManager() {
    }

    /**
     * Rejects any raise over 5%.
     * 
     * @param evt
     *            a vetoable change event for the payRate property
     * @throws PropertyVetoException
     *             if the change is vetoed
     */
    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        StaffConsultant sc = (StaffConsultant)evt.getSource();
        int oldPay = (int)evt.getOldValue();
        int newPay = (int)evt.getNewValue();
        double raisePercent = (newPay - oldPay) / (double)oldPay;
        if (raisePercent > 0.05) {
            log.info(String.format(VETOABLE_PAY_RATE, "REJECTED", oldPay, newPay,  sc.getName()));
            throw new PropertyVetoException("Raises above 5% are auto rejected.", evt);
        } else {
            log.info(String.format(VETOABLE_PAY_RATE, "APPROVED", oldPay, newPay, sc.getName()));
        }
    }

    /**
     * Processes to final pay rate change.
     * 
     * @param evt
     *            a change event for the payRate property
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        log.info("Pay rate changed, from {} to {} for {}", evt.getOldValue(), evt.getNewValue(), evt.getPropertyName()); 
    }
}
