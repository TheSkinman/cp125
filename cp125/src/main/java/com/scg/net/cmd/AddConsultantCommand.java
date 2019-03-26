package com.scg.net.cmd;

import com.scg.domain.Consultant;
import com.scg.net.server.CommandProcessor;

/**
 * The command to add a Consultant to a list maintained by the server, target type is Consultant.
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public final class AddConsultantCommand extends AbstractCommand<Consultant> {
    private static final long serialVersionUID = -5917364448228198875L;
    
    /**
     * Construct an AddConsultantCommand with a target.
     * @param target The target of this Command.
     */
    public AddConsultantCommand(Consultant target) {
        super(target);
    }

    /**
     * Execute this Command by calling receiver.execute(this).
     */
    @Override
    public void execute() {
        getReceiver().execute(this);
    }
}
