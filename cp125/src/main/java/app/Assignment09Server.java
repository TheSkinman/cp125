package app;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;
import com.scg.net.server.InvoiceServer;
import com.scg.util.ListFactory;

/**
 * Assignment 09 application.
 */
public class Assignment09Server {
    /** This class' logger. */
    private static final Logger log = LoggerFactory.getLogger(Assignment09Server.class);
    
    /**
     * Prevent instantiation.
     */
    private Assignment09Server() {}

    /**
     * The application method.
     *
     * @param args
     *            Command line arguments.
     * @throws Exception
     *             throws on any exception
     */
    public static void main(final String[] args) throws Exception {
        
        // Create lists to be populated by factory
        final List<ClientAccount> clientList = new ArrayList<>();
        final List<Consultant> consultantList = new ArrayList<>();
        List<TimeCard> timeCards = new ArrayList<>();
        ListFactory.populateLists(clientList, consultantList, timeCards);
        timeCards = null;

        log.info("Assignment 9 Server is now starting up the server...");
        InvoiceServer is = new InvoiceServer(10888, clientList, consultantList, "target/server");
        is.run();
        log.info("Assignment 9 Server now ending.");
    }

}
