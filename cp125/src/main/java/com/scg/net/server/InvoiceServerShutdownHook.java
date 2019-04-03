package com.scg.net.server;

import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
    private static final String LINE_DOUBLE = "=================================================";
    private static final String LINE_SINGLE = "-------------------------------------------------";

    private static final Logger logger = LoggerFactory.getLogger(InvoiceServerShutdownHook.class);

    /** Character encoding to use. Assignment 5 */
    private static final String ENCODING = "ISO-8859-1";

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

        Console console = System.console();
        try {
            @SuppressWarnings("resource") // don't want to close console or System.out
            PrintWriter consoleWriter = (console != null) ? console.writer()
                    : new PrintWriter(new OutputStreamWriter(System.out, ENCODING), true);

            // Make sure the directory is ALIVE!
            final File serverDir = new File(this.outputDirectoryName);
            if (!serverDir.exists()) {
                if (!serverDir.mkdirs()) {
                    logger.error("Failed to create directory:" + serverDir.getAbsolutePath());
                    return;
                }
            }
            consoleWriter.println(LINE_DOUBLE);

            // Print ClientAccount List to file
            synchronized (clientList) {
                final File outClientListFile = new File(this.outputDirectoryName, "ClientAccountList.txt");
                try (PrintStream printOut = new PrintStream(new FileOutputStream(outClientListFile, false), true, ENCODING);) {
                    for (ClientAccount ca : clientList) {
                        printOut.println(ca.toString());
                        printOut.println(LINE_SINGLE);
                        consoleWriter.println(ca);
                        consoleWriter.println(LINE_SINGLE);
                    }
                } catch (UnsupportedEncodingException err) {
                    logger.error("Unable to write the encoding format: {}", err);
                } catch (FileNotFoundException err) {
                    logger.error("File not found: {}", err);
                }
            }

            consoleWriter.println(LINE_DOUBLE);
                
         // Print Consultant List to file
            synchronized (consultantList) {
                final File outConsultantListFile = new File(this.outputDirectoryName, "ConsultantList.txt");
                try (PrintStream printOut = new PrintStream(new FileOutputStream(outConsultantListFile, false), true, ENCODING);) {
                    for (Consultant c : consultantList) {
                        printOut.println(c.toString());
                        printOut.println(LINE_SINGLE);
                        consoleWriter.println(c);
                        consoleWriter.println(LINE_SINGLE);
                    }
                } catch (UnsupportedEncodingException err) {
                    logger.error("Unable to write the encoding format: {}", err);
                } catch (FileNotFoundException err) {
                    logger.error("File not found: {}", err);
                }
           }            
            
            consoleWriter.println(LINE_DOUBLE);
            consoleWriter.println("Complete! Shutting down!!!");
        
        } catch (UnsupportedEncodingException e) {
            logger.error("Printing of invoices failed.", e);
        }
    }
}
