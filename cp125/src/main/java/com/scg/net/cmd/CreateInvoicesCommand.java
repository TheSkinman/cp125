package com.scg.net.cmd;

import java.time.LocalDate;

/**
 * The command to create invoices for a specified month, target type is LocalDate.
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public final class CreateInvoicesCommand extends AbstractCommand<LocalDate> {
    private static final long serialVersionUID = -5917364448228198875L;
    
    /**
     * Construct a CreateInvoicesCommand with a target month, which should be obtained by getting the desired month constant from LocalDate.
     * @param target the target month.
     */
    public CreateInvoicesCommand(LocalDate target) {
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
