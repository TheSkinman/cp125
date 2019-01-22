package app;

import static com.scg.util.ListFactory.TEST_INVOICE_MONTH;
import static com.scg.util.ListFactory.TEST_INVOICE_YEAR;

import java.io.Console;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.Invoice;
import com.scg.domain.TimeCard;
import com.scg.util.DateRange;
import com.scg.util.ListFactory;
import com.scg.util.TimeCardListUtil;

/**
 * Assignment 04 application.
 */
public final class Assignment04 {
	/** Character encoding to use. */
	private static final String ENCODING = "ISO-8859-1";

	/** This class' logger. */
    private static final Logger log = LoggerFactory.getLogger("Assignment04");

    /**
     * Prevent instantiation.
     */
    private Assignment04() {
    }

    /**
     * Create invoices for the clients from the timecards.
     *
     * @param accounts the accounts to create the invoices for
     * @param timeCards the time cards to create the invoices from
     *
     * @return the created invoices
     */
    private static List<Invoice> createInvoices(final List<ClientAccount> accounts,
                                            final List<TimeCard> timeCards) {
        final List<Invoice> invoices = new ArrayList<>();

        final List<TimeCard> timeCardList = TimeCardListUtil
                .getTimeCardsForDateRange(timeCards, new DateRange(TEST_INVOICE_MONTH, TEST_INVOICE_YEAR));
        for (final ClientAccount account : accounts) {
            final Invoice invoice = new Invoice(account, TEST_INVOICE_MONTH, TEST_INVOICE_YEAR);
            invoices.add(invoice);
            for (final TimeCard currentTimeCard : timeCardList) {
                invoice.extractLineItems(currentTimeCard);
            }
        }

        return invoices;
    }

    /**
     * Print the invoice to a PrintStream.
     *
     * @param invoices the invoices to print
     * @param out the print stream; can be System.out or a text file.
     * @throws UnsupportedEncodingException 
     */
    private static void printInvoices(final List<Invoice> invoices, final OutputStream out)
    	throws UnsupportedEncodingException {
    	    try (PrintStream writer = new PrintStream(out, true, ENCODING)) {
            for (final Invoice invoice : invoices) {
                writer.println(invoice.toReportString());
            }
        }
    }

    /**
     * The application method.
     *
     * @param args Command line arguments.
     */
    public static void main(final String[] args) throws Exception {
        // Create lists to be populated by factory
        final List<ClientAccount> accounts = new ArrayList<>();
        final List<Consultant> consultants = new ArrayList<>();
        final List<TimeCard> timeCards = new ArrayList<>();
        ListFactory.populateLists(accounts, consultants, timeCards);
        // Print them
        ListFactory.printTimeCards(timeCards);
        
        // Use the list util methods
        Console console =  System.console();
        Consultant carl = consultants.get(0);
        final List<TimeCard> selected = TimeCardListUtil.getTimeCardsForConsultant(timeCards, carl);
        final int count = selected.size();
        console.printf("Counted %d time cards for %s%n",count, carl);
        if (count != 2) {
            log.error(String.format("Bad time card count for %s", carl));
        }

        TimeCardListUtil.sortByStartDate(timeCards);
        System.out.println("Time cards by date:");
        for (TimeCard tc : timeCards) {
            System.out.printf("  %s, %s%n", tc.getWeekStartingDay(), tc.getConsultant());
        }
        
        TimeCardListUtil.sortByConsultantName(timeCards);
        System.out.println("Time cards by consultant:");
        for (TimeCard tc : timeCards) {
            System.out.printf("  %s, %s%n", tc.getWeekStartingDay(), tc.getConsultant());
        }

        accounts.clear();
        consultants.clear();
        timeCards.clear();

        ListFactory.populateLists(accounts, consultants, timeCards);

        // Create the Invoices
        final List<Invoice> invoices = createInvoices(accounts, timeCards);
        // Print them
        System.out.println();
        System.out.println("==================================================================================");
        System.out.println("=============================== I N V O I C E S ==================================");
        System.out.println("==================================================================================");
        System.out.println();
        printInvoices(invoices, System.out);
        
        // Now print it to a file
        try (FileOutputStream out = new FileOutputStream("invoices.txt")) {
            printInvoices(invoices, out);
        } catch (final IOException ex) {
            log.error("Unable to print invoice.", ex);
        }
    }
}
