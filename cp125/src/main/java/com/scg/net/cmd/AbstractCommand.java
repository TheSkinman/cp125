package com.scg.net.cmd;

import java.io.Serializable;

import com.scg.net.server.CommandProcessor;

/**
 * The superclass of all Command objects, implements the command role in the
 * Command design pattern.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 * @param <T>
 *            the target type, the type the command operates on
 */
public abstract class AbstractCommand<T> implements Serializable, Command<T> {
    private static final long serialVersionUID = 7357453670913210375L;
    private transient CommandProcessor receiver;
    private T target;

    /**
     * Construct an AbstractCommand without a target; called from subclasses.
     */
    public AbstractCommand() {
        super();
    }

    /**
     * Construct an AbstractCommand with a target; called from subclasses.
     * 
     * @param target
     *            the target
     */
    public AbstractCommand(T target) {
        super();
        this.target = target;
    }

    /**
     * Gets the CommandProcessor receiver for this Command.
     * 
     * @return the receiver for this Command.
     */
    @Override
    public final CommandProcessor getReceiver() {
        return receiver;
    }

    @Override
    public T getTarget() {
        return target;
    }

    /**
     * Set the CommandProcessor that will execute this Command.
     * 
     * @param the
     *            receiver for this Command.
     */
    @Override
    public void setReceiver(CommandProcessor receiver) {
        this.receiver = receiver;

    }

    /**
     * A string representation of this command.
     */
    @Override
    public String toString() {
        // get class and sub class
        return "[class=" + this.getClass().getName() + "]";
    }

}
