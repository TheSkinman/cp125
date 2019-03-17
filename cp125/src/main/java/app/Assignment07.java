package app;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.domain.ClientAccount;
import com.scg.domain.Invoice;
import com.scg.persistent.DbServer;

/**
 * Assignment 05 application.
 */
public class Assignment07 {
    /** Character encoding to use. */
    private static final String ENCODING = "ISO-8859-1";

    /** This class' logger. */
    private static final Logger log = LoggerFactory.getLogger(Assignment07.class);

    /** Setup for the database connection */
    private static String dbUrl = "jdbc:derby://localhost:1527/memory:scgDb";
    private static String username = "student";
    private static String password = "student";
    private static DbServer db = null;

    /**
     * Prevent instantiation.
     */
    private Assignment07() {
    }

    /**
     * Print the invoice to a PrintWriter.
     *
     * @param invoices
     *            the invoices to print
     * @param writer
     *            the print writer; can be System.out or a text file.
     */
    private static void printInvoices(final List<Invoice> invoices, final PrintWriter writer) {
        for (final Invoice invoice : invoices) {
            writer.println(invoice.toReportString());
        }
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
        // Connect to the database
        db = new DbServer(dbUrl, username, password);
        
        // Retrieve list of clients
        final List<ClientAccount> accounts = db.getClients();

        // Load the invoices from Database
        List<Invoice> invoices = new ArrayList<>();
        for (ClientAccount ca : accounts) {
            invoices.add(db.getInvoice(ca, Month.MARCH, 2017));
        }

        // Use the list util methods
        Console console = System.console();
        try {
            @SuppressWarnings("resource") // don't want to close console or System.out
            PrintWriter consoleWrtr = (console != null) ? console.writer()
                    : new PrintWriter(new OutputStreamWriter(System.out, ENCODING), true);

            // Print them
            consoleWrtr.println();
            consoleWrtr.println("==================================================================================");
            consoleWrtr.println("=============================== I N V O I C E S ==================================");
            consoleWrtr.println("==================================================================================");
            consoleWrtr.println();
            printInvoices(invoices, consoleWrtr);

            // Now print it to a file
            try (PrintWriter fileWriter = new PrintWriter("invoices.txt", ENCODING)) {
                printInvoices(invoices, fileWriter);
            } catch (final IOException ex) {
                log.error("Unable to print invoices to file.", ex);
            }
        } catch (UnsupportedEncodingException e) {
            log.error("Printing of invoices failed.", e);
        }
    }
}
