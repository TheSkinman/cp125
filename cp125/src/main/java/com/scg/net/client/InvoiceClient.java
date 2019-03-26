package com.scg.net.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
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
import com.scg.util.Address;
import com.scg.util.PersonalName;
import com.scg.util.StateCode;

/**
 * The client of the InvoiceServer. Connects to the server, sends commands to
 * add clients, consultants and time cards and then has the server create an
 * invoice.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class InvoiceClient {
    private static final Logger logger = LoggerFactory.getLogger(InvoiceClient.class);
    private Month INVOICE_MONTH;
    private int INVOICE_YEAR;
    private String host;
    private int port;
    private List<TimeCard> timeCardList;

    /**
     * Construct an InvoiceClient with a host and port for the server.
     * 
     * @param host
     *            the host for the server.
     * @param port
     *            the port for the server.
     * @param timeCardList
     *            the list of timeCards to send to the server
     */
    public InvoiceClient(String host, int port, List<TimeCard> timeCardList) {
        this.host = host;
        this.port = port;
        this.timeCardList = timeCardList;
    }

    /**
     * Runs this InvoiceClient, sending clients, consultants, and time cards to the
     * server, then sending the command to create invoices for a specified month.
     */
    public void run() {
        logger.debug("Start socket to {} on {}", host, port);
        ObjectOutputStream out = null;
        try (Socket clientSocket = new Socket(host, port);) {

            // connect to input then shut down
            // ObjectInputStream is = new ObjectInputStream(clientSocket.getInputStream());

            logger.debug("Opening streams to server...");
            out = new ObjectOutputStream(clientSocket.getOutputStream());

            // Send some consultants.
            sendConsultants(out);

            // Send some Client Accounts
            sendClients(out);

            // Send the TimeCard
            sendTimeCards(out);

            // Create the invoice
            createInvoices(out, INVOICE_MONTH, INVOICE_YEAR);
            
            // Disconnect
            sendDisconnect(out, clientSocket);

            // Shutdown the server CALL FROM MAIN

        } catch (IOException ex) {
            System.err.println("Server error: " + ex);
        }

    }

    /**
     * Send some new clients to the server.
     * 
     * @param out
     *            the output stream connecting this client to the server.
     */
    public void sendClients(ObjectOutputStream out) {
        sendCommand(out, new AddClientCommand(new ClientAccount("ABC Hole Diggers",
                new PersonalName("Hooper", "Bill", "Tim"),
                new Address("123 Alpha St.", "Seattle", StateCode.WA, "98101"))));
        sendCommand(out, new AddClientCommand(new ClientAccount("XYZ Hole Fillers",
                new PersonalName("Brown", "Mike", "P"),
                new Address("3245 SE 34 St.", "Renton", StateCode.WA, "98232"))));
        sendCommand(out, new AddClientCommand(new ClientAccount("MNLOP Hole LLC.",
                new PersonalName("Smith", "John", "K"),
                new Address("7307 7th Ave", "Kirkland", StateCode.WA, "98034"))));
    }

    /**
     * Send some new consultants to the server.
     * 
     * @param out
     *            the output stream connecting this client to the server.
     */
    public void sendConsultants(ObjectOutputStream out) {
        PersonalName name = null;

        name = new PersonalName("DeMartin", "Shelly", "R");
        sendCommand(out, new AddConsultantCommand(new Consultant(name)));

        name = new PersonalName("Skinner", "Norman", "R");
        sendCommand(out, new AddConsultantCommand(new Consultant(name)));

        name = new PersonalName("Gustafson", "Renelle", "Amberlyne");
        sendCommand(out, new AddConsultantCommand(new Consultant(name)));
    }

    /**
     * Send the time cards to the server.
     * 
     * @param out
     *            the output stream connecting this client to the server.
     */
    public void sendTimeCards(ObjectOutputStream out) {
        for (TimeCard tc : timeCardList) {
            sendCommand(out, new AddTimeCardCommand(tc));
        }
    }

    /**
     * Send the disconnect command to the server.
     * 
     * @param out
     *            the output stream connecting this client to the server.
     * @param server
     *            the connection to be closed after sending disconnect
     */
    public void sendDisconnect(ObjectOutputStream out, Socket server) {
        final DisconnectCommand disconnect = new DisconnectCommand(); 
        sendCommand(out, disconnect);
        try {
            server.close();
        } catch (IOException e) {
            logger.error("Failed to disconnect from the server.", e);
        }
    }

    /**
     * Send the command to the server to create invoices for the specified month.
     * 
     * @param out
     *            the output stream connecting this client to the server.
     * @param month
     *            the month to create invoice for
     * @param year
     *            the year to create invoice for
     */
    public void createInvoices(ObjectOutputStream out, java.time.Month month, int year) {
        sendCommand(out, new CreateInvoicesCommand(LocalDate.of(year, month, 1)));
    }

    private void sendCommand(ObjectOutputStream out, Command<?> command) {
        logger.debug("Sending the following command to the server: " + command);
        try {
            out.writeObject(command);
            out.flush();
        } catch (IOException e) {
            logger.error("Failed to send the command to the server: ", e);
        }
    }

    /**
     * Send the shutdown command to the server, this is done on a separate
     * connection to the server.
     * 
     * @param host
     *            the host for the server.
     * @param port
     *            the port for the server.
     */
    public static void sendShutdown(String host, int port) {
        // call from mMAIN
        try {
            logger.debug("Sending shutdown to {} on {}", host, port);
            Socket clientSocket = new Socket(host, port);

            // logger.debug("Opening streams to server...");
            // ObjectInputStream is = new ObjectInputStream(clientSocket.getInputStream());
            // // shutdown input
            // is.close();

            ObjectOutputStream os = new ObjectOutputStream(clientSocket.getOutputStream());

            logger.debug("Sending shutdown command to server...");
            ShutdownCommand shutdown = new ShutdownCommand();
            os.writeObject(shutdown);
            System.out.println("Envoi des informations au serveur ...");

            clientSocket.close();
        } catch (IOException e) {
            logger.error("Client shutdown failed, ", e);
        }
    }
}
