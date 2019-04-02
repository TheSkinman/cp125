package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;
import com.scg.net.client.InvoiceClient;
import com.scg.util.ListFactory;

/**
 * Assignment 09 application.
 */
public class Assignment09 {
    /** This class' logger. */
    private static final Logger log = LoggerFactory.getLogger(Assignment09.class);

    /**
     * Prevent instantiation.
     */
    private Assignment09() {}

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
        final List<ClientAccount> accounts = new ArrayList<>();
        final List<Consultant> consultants = new ArrayList<>();
        final List<TimeCard> timeCards = new ArrayList<>();
        ListFactory.populateLists(accounts, consultants, timeCards);
        
        // Create an invoice client and run it. 
        log.info("Assignment 9 is now starting up the client...");
        
        log.info("Creating all three threads...");        
        final List<TimeCard> unModTimeCards = Collections.unmodifiableList(timeCards);
        
        final InvoiceClient ic1 = new InvoiceClient("localhost", 10888, unModTimeCards);
        final InvoiceClient ic2 = new InvoiceClient("localhost", 10888, unModTimeCards);
        final InvoiceClient ic3 = new InvoiceClient("localhost", 10888, unModTimeCards);
        
        // Start the threads
        log.info("Starting all three threads...");
        ic1.start();
        ic2.start();
        ic3.start();
        
        // Wait for threads to complete
        log.info("Waiting on all three threads...");
        ic1.join();
        ic2.join();
        ic3.join();
        
        // Shut down the server.
        log.info("Threads joined, sending seperate shutdown command...");
        InvoiceClient.sendShutdown("localhost", 10888);
        log.info("Assignment 9 is now ending the client.");

    }

}
