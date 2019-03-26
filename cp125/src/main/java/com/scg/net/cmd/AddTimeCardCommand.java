package com.scg.net.cmd;

import com.scg.domain.TimeCard;
import com.scg.net.server.CommandProcessor;

/**
 * Command that adds a TimeCard to the server's list of TimeCards, target type
 * is TimeCards.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public final class AddTimeCardCommand extends AbstractCommand<TimeCard> {
    private static final long serialVersionUID = -5917364448228198875L;

    /**
     * Construct an AddTimeCardCommand with a target.
     * @param target the target of this Command.
     */
    public AddTimeCardCommand(TimeCard target) {
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
