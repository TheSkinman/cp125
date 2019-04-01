package com.scg.net.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.net.cmd.AbstractCommand;
import com.scg.net.cmd.Command;

import org.apache.commons.lang3.NotImplementedException;

/**
 * The server for creating new clients, consultants and time cards as well as
 * creation of account invoices. Maintains it's own list of clients and
 * consultants, but not time cards.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class InvoiceServer {
    private static final Logger logger = LoggerFactory.getLogger(InvoiceServer.class);
    private List<ClientAccount> clientList;
    private List<Consultant> consultantList;
    private String outputDirectoryName;
    private int port;
    private ServerSocket serverSocket;
    private static int threadNumber = 1;

    /**
     * Construct an InvoiceServer with a port.
     * 
     * @param port
     *            The port for this server to listen on
     * @param clientList
     *            the initial list of clients
     * @param consultantList
     *            the initial list of consultants
     * @param outputDirectoryName
     *            the directory to be used for files output by commands
     */
    public InvoiceServer(int port, List<ClientAccount> clientList, List<Consultant> consultantList,
            String outputDirectoryName) {
        this.port = port;
        this.clientList = clientList;
        this.consultantList = consultantList;
        this.outputDirectoryName = outputDirectoryName;
        
//        Runtime runTime = Runtime.getRuntime();
//        runTime.addShutdownHook(new InvoiceServerShutdownHook(clientList, consultantList, outputDirectoryName));
    }

    /**
     * Run this server, establishing connections, receiving commands, and
     * dispatching them to the CommandProcesser.
     */
    public void run() {
        try (ServerSocket listenSocket = new ServerSocket(port);) {
            serverSocket = listenSocket;
            logger.info("Server ready on Internet address: " + serverSocket.getLocalSocketAddress() + " and port "
                    + serverSocket.getLocalPort());

            while (serverSocket != null && !serverSocket.isClosed()) {
                logger.info("listening for client...");
                try (Socket clientSocket = serverSocket.accept();) {
                    logger.info("accepted");
                    
                    String threadName = String.format("thread_%03d", threadNumber++);
                    final CommandProcessor receiver = new CommandProcessor(clientSocket, threadName, clientList, consultantList,
                            this);
                    receiver.setOutPutDirectoryName(outputDirectoryName);
                    
                    Thread thread = new Thread(receiver);
                    thread.start();
                }
            }
        } catch (IOException ex) {
            logger.error("Server error: " + ex);
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException ioex) {
                    logger.error("Error closing server socket. " + ioex);
                }
            }
        }
    }

    /**
     * Shutdown the server.
     */
    void shutdown() {
        try {
            logger.info("Server shutting down...");
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            logger.error("Server failed to shutdown, ", e);
        }
        serverSocket = null;
    }
}
