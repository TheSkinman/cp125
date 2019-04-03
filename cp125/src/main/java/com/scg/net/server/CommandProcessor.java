package com.scg.net.server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.Invoice;
import com.scg.domain.TimeCard;
import com.scg.net.cmd.AddClientCommand;
import com.scg.net.cmd.AddConsultantCommand;
import com.scg.net.cmd.AddTimeCardCommand;
import com.scg.net.cmd.Command;
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
public class CommandProcessor implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(CommandProcessor.class);
    private static final String ENCODING = "ISO-8859-1"; // found in assignment 7 for out put to screen
    private List<ClientAccount> clientList;
    private List<Consultant> consultantList;
    private Socket clientSocket;
    private String name;
    private String outputDirectoryName;
    private InvoiceServer server;
    private List<TimeCard> timeCardList;

    /**
     * Construct a CommandProcessor.
     * 
     * @param connection
     *            the Socket connecting the server to the client.
     * @param name
     *            the name assigned to this CommandProcessor by the server; mostly
     *            for logging.
     * @param clientList
     *            the ClientList to add Clients to.
     * @param consultantList
     *            the ConsultantList to add Consultants to.
     * @param server
     *            the server that created this command processor
     */
    CommandProcessor(Socket connection, String name, List<ClientAccount> clientList, List<Consultant> consultantList,
            InvoiceServer server) {
        this.clientSocket = connection;
        this.name = name;
        this.clientList = clientList;
        this.consultantList = consultantList;
        this.timeCardList = new ArrayList<>();
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
        logger.info("{}: executing " + command, name);
        timeCardList.add(command.getTarget());
    }

    /**
     * Execute an AddClientCommand.
     * 
     * @param command
     *            the command to execute.
     */
    public void execute(AddClientCommand command) {
        logger.info("{}: executing " + command, name);
        
        final ClientAccount newClientAccount = command.getTarget();
        synchronized(clientList) {
            if (!clientList.contains(newClientAccount)) {
                clientList.add(newClientAccount);
            }
        }
    }

    /**
     * Execute and AddConsultantCommand.
     * 
     * @param command
     *            the command to execute.
     */
    public void execute(AddConsultantCommand command) {
        logger.info("{}: executing " + command, name);
        
        final Consultant newConsultant = command.getTarget();
        synchronized(consultantList) {
            if (!consultantList.contains(newConsultant)) {
                consultantList.add(newConsultant);
            }
        }
    }

    /**
     * Execute a CreateInvoicesCommand.
     * 
     * @param command
     *            the command to execute.
     */
    public void execute(CreateInvoicesCommand command) {
        logger.info("{}: executing " + command, name);
        Invoice invoice = null;
        LocalDate invoiceDate = command.getTarget();
        final DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("MMMMyyyy");
        final String monthString = dtFormatter.format(invoiceDate);
        synchronized (clientList) {
            for (final ClientAccount client : this.clientList) {
                invoice = new Invoice(client, invoiceDate.getMonth(), invoiceDate.getYear());
                for (final TimeCard currentTimeCard : this.timeCardList) {
                    invoice.extractLineItems(currentTimeCard);
                }
                if (invoice.getTotalHours() > 0) {
                    final File serverDir = new File(this.outputDirectoryName);
                    if (!serverDir.exists()) {
                        if (!serverDir.mkdirs()) {
                            logger.error("Failed to create directory:" + serverDir.getAbsolutePath());
                            return;
                        }
                    }
                    final String outFileName = String.format("%s-%s-Invoice.txt", client.getName().replaceAll(" ", ""),
                            monthString);
                    final File outFile = new File(this.outputDirectoryName, outFileName);
                    try (PrintStream printOut = new PrintStream(new FileOutputStream(outFile), true, ENCODING);) {
                        printOut.println(invoice.toReportString());
                    } catch (UnsupportedEncodingException err) {
                        logger.error("Unable to write the encoding format: {}", err);
                    } catch (FileNotFoundException err) {
                        logger.error("File not found: {}", err);
                    }
                }
            }
        }
    }

    /**
     * Execute a DisconnectCommand.
     * 
     * @param command
     *            the input DisconnectCommand.
     */
    public void execute(DisconnectCommand command) {
        logger.info("{}: executing " + command, name);
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Execute a ShutdownCommand. Closes any current connections, stops listening
     * for connections and then terminates the server, without calling System.exit.
     * 
     * @param command
     *            the input ShutdownCommand.
     */
    public void execute(ShutdownCommand command) {
        logger.info("{}: executing " + command, name);
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            server.shutdown();
        }
    }

    @Override
    public void run() {
        ObjectInputStream is = null;
        try {
            logger.debug("serviceConnection - opening stream...");
            is = new ObjectInputStream(clientSocket.getInputStream());
            setOutPutDirectoryName(outputDirectoryName + "/" + this.name);

            while (!clientSocket.isClosed()) {
                logger.debug("serviceConnection - reading object...");

                final Object obj = is.readObject();

                if (obj == null) {
                    is.close();
                    clientSocket.close();
                } else {
                    final Command<?> command = (Command<?>) obj;
                    command.setReceiver(this);
                    command.execute();
                }
            }
            clientSocket.close();
        } catch (IOException | ClassNotFoundException ex) {
            logger.error("Server error: ", ex);
        } finally {
            try {
                is.close();
                clientSocket.close();
            } catch (IOException err) {
                logger.error("Problem: {}", err);
            }
        }
        
    }
}
