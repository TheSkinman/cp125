package com.scg.net.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.Month;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.Invoice;
import com.scg.domain.TimeCard;


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
//        try {
//            servSock = new ServerSocket(port);
//            System.out.println("Server ready on port " + port + "...");
//
//            while (servSock != null && !servSock.isClosed()) {
//                Socket sock = servSock.accept(); // blocks
//                process(sock);
//            }
//        } catch (IOException ex) {
//            System.err.println("Server error: " + ex);
//        } finally {
//            if (servSock != null) {
//                try {
//                    servSock.close();
//                } catch (IOException ioex) {
//                    System.err.println("Error closing server socket. " + ioex);
//                }
//            }
//        }        

    }

    /**
     * Send some new clients to the server.
     * 
     * @param out
     *            the output stream connecting this client to the server.
     */
    public void sendClients(ObjectOutputStream out) {
        throw new NotImplementedException("Not implemented yet.");
    }

    /**
     * Send some new consultants to the server.
     * 
     * @param out
     *            the output stream connecting this client to the server.
     */
    public void sendConsultants(ObjectOutputStream out) {
        throw new NotImplementedException("Not implemented yet.");
    }

    /**
     * Send the time cards to the server.
     * 
     * @param out
     *            the output stream connecting this client to the server.
     */
    public void sendTimeCards(ObjectOutputStream out) {
        throw new NotImplementedException("Not implemented yet.");
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
        throw new NotImplementedException("Not implemented yet.");
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
        throw new NotImplementedException("Not implemented yet.");
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
        try {
            logger.info("Sending shutdown to {} on {}", host, port);
            Socket clientSocket = new Socket(host, port);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            
            
            
//            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//            
//            String fromServer;
//            String fromUser;
//            while ((fromServer = in.readLine()) != null) {
//                logger.info("Server: " + fromServer);
//                if (fromServer.equals("Bye."))
//                    break;
//
//                BufferedReader stdIn;
//                fromUser = "quit";
//                if (fromUser != null) {
//                    logger.info("Client: " + fromUser);
//                    out.println(fromUser);
//                }
//            }
            
            
            
            out.println("exit");
            out.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
