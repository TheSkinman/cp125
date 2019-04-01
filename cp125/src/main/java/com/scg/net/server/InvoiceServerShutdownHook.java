package com.scg.net.server;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;

/**
 * ShutdownHook for the InvoiceServer, writes the current contents of the client
 * and consultants lists as text to separate files. Also, writes brief messages
 * to console so it is apparent it is running.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public final class InvoiceServerShutdownHook extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(InvoiceServerShutdownHook.class);
    private List<ClientAccount> clientList;
    private List<Consultant> consultantList;
    private String outputDirectoryName;

    /**
     * Construct an InvoiceServerShutDownHook.
     * 
     * @param clientList
     *            the ClientList to serialize.
     * @param consultantList
     *            the ConsultantList to serialize.
     * @param outputDirectoryName
     *            name of directory to write output to
     */
    public InvoiceServerShutdownHook(List<ClientAccount> clientList, List<Consultant> consultantList,
            String outputDirectoryName) {
        this.clientList = clientList;
        this.consultantList = consultantList;
        this.outputDirectoryName = outputDirectoryName;
    }

    /**
     * Called by the Runtime when a shutdown signal is received. This will write the
     * client and consultant lists to file, then shut down after
     * SHUTDOWN_DELAY_SECONDS seconds.
     */
    @Override
    public void run() {
        logger.info("SHUTDOWN THE BIG DOGS!!!");
    }
}
