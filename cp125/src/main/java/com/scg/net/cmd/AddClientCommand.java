package com.scg.net.cmd;

import com.scg.domain.ClientAccount;

/**
 * The command to add a ClientAccount to a list maintained by the server, target type is ClientAccount.
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public final class AddClientCommand extends AbstractCommand<ClientAccount> {
    private static final long serialVersionUID = -5917364448228198875L;
    
    public AddClientCommand(ClientAccount target) {
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
