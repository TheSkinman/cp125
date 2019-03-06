package app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;
import com.scg.util.ListFactory;

/**
 * Assignment 04 application.
 */
public class InitLists {
    /** This class' logger. */
    private static final Logger log = LoggerFactory.getLogger(InitLists.class);

    /**
     * Prevent instantiation.
     */
    private InitLists() {
    }

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

        // Save the lists
        saveList(accounts, "ClientList.ser");
        saveList(timeCards, "TimeCardList.ser");
    }

    /**
     * Saves a list to the hard drive.
     * 
     * @param list
     *            The List<> to be saved.
     * @param file
     *            The name of the file to save to.
     */
    private static <T> void saveList(List<T> list, String file) {
        try (ObjectOutputStream objectOutputStram = new ObjectOutputStream(new FileOutputStream(file));) {
            objectOutputStram.writeObject(list);
            log.info("File [{}] saved successfully.", file);
        } catch (IOException ex) {
            log.error("Failded to save file [" + file + "]", ex);
        }
    }
}
