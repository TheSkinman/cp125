package com.scg.net.cmd;

/**
 * The command to disconnect, this command has no target, so target type is Void.
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public final class DisconnectCommand extends AbstractCommand<Void> {
    private static final long serialVersionUID = -5917364448228198875L;
    
    /**
     * Construct an DisconnectCommand.
     */
    public DisconnectCommand() {
        super();
    }

    /**
     * Execute this Command by calling receiver.execute(this).
     */
    @Override
    public void execute() {
        getReceiver().execute(this);
    }
}
