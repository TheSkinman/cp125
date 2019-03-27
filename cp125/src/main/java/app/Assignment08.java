package app;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;
import com.scg.net.client.InvoiceClient;
import com.scg.util.ListFactory;

/**
 * Assignment 04 application.
 */
public class Assignment08 {
    /** This class' logger. */
    private static final Logger log = LoggerFactory.getLogger(Assignment08.class);

    /**
     * Prevent instantiation.
     */
    private Assignment08() {}

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
        log.info("Assignment 8 is now starting up the client...");
        InvoiceClient ic = new InvoiceClient("localhost", 10888, timeCards);
        ic.run();
        ic = null;
        
        // Shut down the server.
        InvoiceClient.sendShutdown("localhost", 10888);
        log.info("Assignment 8 is now ending the client.");

    }

}
