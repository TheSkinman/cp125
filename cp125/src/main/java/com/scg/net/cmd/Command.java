package com.scg.net.cmd;

import java.io.Serializable;

import com.scg.net.server.CommandProcessor;

/**
 * The interface of all Command objects, implements the command role in the
 * Command design pattern.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 * @param <T>
 *            the target type, the type the command operates on
 */
public interface Command<T> extends Serializable {

    /**
     * The method called by the receiver. This method must be implemented by
     * subclasses to send a reference to themselves to the receiver by calling
     * receiver.execute(this), this is a key implementation detail as method
     * overloading is used to resolve the method to be invoked by the receiver
     * (CommandProcessor).
     */
    void execute();

    /**
     * Gets the CommandProcessor receiver for this Command.
     * 
     * @return the receiver for this Command.
     */
    CommandProcessor getReceiver();

    /**
     * Set the CommandProcessor that will execute this Command.
     * 
     * @param receiver
     *            the receiver for this Command.
     */
    void setReceiver(CommandProcessor receiver);

    /**
     * Return the target of this Command.
     * 
     * @return the target.
     */
    T getTarget();
}
