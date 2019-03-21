package app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;
import com.scg.persistent.DbServer;
import com.scg.util.ListFactory;

/**
 * Assignment 04 application.
 */
public class InitDb {
    /** This class' logger. */
    private static final Logger log = LoggerFactory.getLogger(InitDb.class);
    
    private static String dbUrl = "jdbc:derby://localhost:1527/memory:scgDb";
    private static String username = "student";
    private static String password = "student";
    private static DbServer db = null;

    /**
     * Prevent instantiation.
     */
    private InitDb() {}

    /**
     * The application method.
     *
     * @param args
     *            Command line arguments.
     * @throws Exception
     *             throws on any exception
     */
    public static void main(final String[] args) throws Exception {
        db = new DbServer(dbUrl, username, password);
        
        // Create lists to be populated by factory
        final List<ClientAccount> accounts = new ArrayList<>();
        final List<Consultant> consultants = new ArrayList<>();
        final List<TimeCard> timeCards = new ArrayList<>();
        ListFactory.populateLists(accounts, consultants, timeCards);

        // Save the lists
        storeClientList(accounts);
        storeConsultantList(consultants);
        storeTimcardList(timeCards);
    }

    private static void storeClientList(List<ClientAccount> list) {
        for (ClientAccount clientAccount : list) {
            try {
                db.addClient(clientAccount);
            } catch (SQLException err) {
                log.error("Attempting to store a Client Account={}, Exception={}", clientAccount, err);
            }
        }
    }
    
    private static void storeConsultantList(List<Consultant> list) {
        for (Consultant consultant : list) {
            try {
                db.addConsultant(consultant);
            } catch (SQLException err) {
                log.error("Attempting to store a Consultant={}, Exception={}", consultant, err);
            }
        }
    }
    
    private static void storeTimcardList(List<TimeCard> list) {
        for (TimeCard timeCard : list) {
            try {
                db.addTimeCard(timeCard);
            } catch (SQLException err) {
                log.error("Attempting to store a Time Card={}, Exception={}", timeCard, err);
            }
        }
    }
}
