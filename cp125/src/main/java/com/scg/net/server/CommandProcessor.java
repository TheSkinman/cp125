package com.scg.net.server;

import java.net.Socket;
import java.util.List;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;

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

    }
}
