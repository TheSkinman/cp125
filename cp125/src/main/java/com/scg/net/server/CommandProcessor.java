package com.scg.net.server;

import java.net.Socket;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;
import com.scg.net.cmd.AddClientCommand;
import com.scg.net.cmd.AddConsultantCommand;
import com.scg.net.cmd.AddTimeCardCommand;
import com.scg.net.cmd.CreateInvoicesCommand;
import com.scg.net.cmd.DisconnectCommand;
import com.scg.net.cmd.ShutdownCommand;

/**
 * The command processor for the invoice server. Implements the receiver role in
 * the Command design pattern, provides the execute method for all of the
 * supported commands. Is provided with the client and consultant lists from the
 * Invoice server, maintains its own time card list.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class CommandProcessor {
    private static final Logger logger = LoggerFactory.getLogger(CommandProcessor.class);
    private List<ClientAccount> clientList;
    private List<Consultant> consultantList;
    private Socket clientSocket;
    private String outputDirectoryName;
    private InvoiceServer server;
    private List<TimeCard> timeCardList;

    /**
     * Construct a CommandProcessor.
     * 
     * @param connection
     *            the Socket connecting the server to the client.
     * @param clientList
     *            the ClientList to add Clients to.
     * @param consultantList
     *            the ConsultantList to add Consultants to.
     * @param server
     *            the server that created this command processor
     */
    CommandProcessor(Socket connection, List<ClientAccount> clientList, List<Consultant> consultantList,
            InvoiceServer server) {
        this.clientSocket = connection;
        this.clientList = clientList;
        this.consultantList = consultantList;
        this.server = server;
    }

    /**
     * Set the output directory name.
     * 
     * @param outPutDirectoryName
     *            the output directory name.
     */
    public void setOutPutDirectoryName(String outPutDirectoryName) {
        this.outputDirectoryName = outPutDirectoryName;
    }

    /**
     * Execute and AddTimeCardCommand.
     * 
     * @param command
     *            the command to execute.
     */
    public void execute(AddTimeCardCommand command) {
        throw new NotImplementedException("Not implemented yet.");
    }

    /**
     * Execute an AddClientCommand.
     * 
     * @param command
     *            the command to execute.
     */
    public void execute(AddClientCommand command) {
        throw new NotImplementedException("Not implemented yet.");
    }

    /**
     * Execute and AddConsultantCommand.
     * 
     * @param command
     *            the command to execute.
     */
    public void execute(AddConsultantCommand command) {
        throw new NotImplementedException("Not implemented yet.");
    }

    /**
     * Execute a CreateInvoicesCommand.
     * 
     * @param command
     *            the command to execute.
     */
    public void execute(CreateInvoicesCommand command) {
        throw new NotImplementedException("Not implemented yet.");
    }

    /**
     * Execute a DisconnectCommand.
     * 
     * @param command
     *            the input DisconnectCommand.
     */
    public void execute(DisconnectCommand command) {
        throw new NotImplementedException("Not implemented yet.");
    }

    /**
     * Execute a ShutdownCommand. Closes any current connections, stops listening
     * for connections and then terminates the server, without calling System.exit.
     * 
     * @param command
     *            the input ShutdownCommand.
     */
    public void execute(ShutdownCommand command) {
        server.shutdown();
    }
}
