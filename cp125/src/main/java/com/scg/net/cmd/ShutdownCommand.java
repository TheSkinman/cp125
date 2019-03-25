package com.scg.net.cmd;

import org.apache.commons.lang3.NotImplementedException;

import com.scg.domain.ClientAccount;

/**
 * The command to add a ClientAccount to a list maintained by the server, target type is ClientAccount.
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public final class ShutdownCommand extends AbstractCommand<ClientAccount> {
    private static final long serialVersionUID = -5917364448228198875L;
    
    public ShutdownCommand(ClientAccount target) {
        throw new NotImplementedException("Not implemented yet.");
    }

    /**
     * Execute this Command by calling receiver.execute(this).
     */
    @Override
    public void execute() {
        throw new NotImplementedException("Not implemented yet.");
    }
}
