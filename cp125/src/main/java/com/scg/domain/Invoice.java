/**
 * 
 */
package com.scg.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.util.Address;
import com.scg.util.StateCode;

/**
 * Invoice encapsulates the attributes and behavior to create client invoices
 * for a given time period from time cards. The Invoice maintains are collection
 * of invoice line items; each containing date, hours and other billing
 * information, these constitute what is being billed for with this Invoice. The
 * invoice will limit the items billed on it to a single month and also has a
 * separate invoice date which reflects the date the invoice was generated. The
 * invoicing business' name and address are obtained from a properties file. The
 * name of the property file is specified by the PROP_FILE_NAME static member.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class Invoice {
    private static final Logger log = LoggerFactory.getLogger(Invoice.class);
    private static final String INVOICE_PROPERTIES_FILE = "invoice.properties";
    private static Properties invoiceProperties;
    private ClientAccount client;
    private Month invoiceMonth;
    private LocalDate startDate;
    private int totalCharges;
    private int totalHours;
    private List<InvoiceLineItem> lineItems;
    private String bizName;
    private Address bizAddress;

    /**
     * Construct an Invoice for a client. The time period is set from the beginning
     * to the end of the month specified.
     * 
     * @param client
     *            Client for this Invoice.
     * @param invoiceMonth
     *            Month for which this Invoice is being created.
     * @param invoiceYear
     *            Year for which this Invoice is being created.
     */
    public Invoice(ClientAccount client, Month invoiceMonth, int invoiceYear) {
        super();
        this.client = client;
        this.invoiceMonth = invoiceMonth;
        startDate = LocalDate.of(invoiceYear, invoiceMonth, 1);
        lineItems = new ArrayList<>();

        loadProperties(INVOICE_PROPERTIES_FILE);
    }

    // protected method to Load the properties from the resources folder. 
    protected void loadProperties(String fileName) {
        invoiceProperties = new Properties();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream != null) {
                invoiceProperties.load(inputStream);
            } else {
                String errorMessage = String.format("property file '%s' not found in the classpath", fileName);
                throw new FileNotFoundException(errorMessage);
            }
        } catch (IOException err) {
            log.error("Failed to load properties due to the following IOException...");
            log.error(err.toString());
        }

        // Set the loaded values or load with defaults.
        String name = invoiceProperties.getProperty("business.name", "The Default Company");
        String street = invoiceProperties.getProperty("business.street", "1234 Default St.");
        String city = invoiceProperties.getProperty("business.city", "Los Default");
        StateCode state = StateCode.valueOf(invoiceProperties.getProperty("business.state", "CA"));
        String zip = invoiceProperties.getProperty("business.zip", "12345");
        
        bizName = name;
        bizAddress = new Address(street, city, state, zip);
    }
    
    // protected method for testing verification
    String getBizName() {
        return bizName;
    }

    // protected method for testing verification
    Address getBizAddress() {
        return bizAddress;
    }

    /**
     * Get the start date for this Invoice, this is the earliest date a
     * ConsultantTime instance may have and still be billed on this invoice.
     * 
     * @return Start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }
    
    /**
     * Get the invoice month.
     * 
     * @return the invoiceMonth
     */
    public Month getInvoiceMonth() {
        return invoiceMonth;
    }
    
    /**
     * Get the client for this Invoice.
     * 
     * @return the client
     */
    public ClientAccount getClientAccount() {
        return client;
    }

    /**
     * Get the total hours for this Invoice.
     * 
     * @return the totalHours
     */
    public int getTotalHours() {
        return totalHours;
    }
    
    /**
     * Get the total charges for this Invoice.
     * 
     * @return the totalCharges
     */
    public int getTotalCharges() {
        return totalCharges;
    }

    /**
     * Add an invoice line item to this Invoice.
     * 
     * @param lineItem
     *            InvoiceLineItem to add.
     */
    public void addLineItem(InvoiceLineItem lineItem) {
        lineItems.add(lineItem);
        // Increase total hours
        totalHours += lineItem.getHours();
        // Increase total charges based on skill rate
        totalCharges += lineItem.getSkill().getRate() * lineItem.getHours();
    }

    /**
     * Extract the billable hours for this Invoice's client from the input TimeCard
     * and add them to the collection of line items. Only those hours for the client
     * and month unique to this invoice will be added.
     * 
     * @param timeCard
     *            the TimeCard potentially containing line items for this Invoices
     *            client.
     */
    public void extractLineItems(TimeCard timeCard) {
        Consultant consultant = timeCard.getConsultant();
        for (ConsultantTime ct : timeCard.getBillableHoursForClient(getClientAccount().getName())) {
            if (getStartDate().getMonth().equals(ct.getDate().getMonth())
                    && getStartDate().getYear() == ct.getDate().getYear()) {
                addLineItem(new InvoiceLineItem(ct.getDate(), consultant, ct.getSkill(), ct.getHours()));
            }
        }
    }
    
    /*@
     * Create a string representation of this object, suitable for printing.
     * 
     * @return string containing this invoices client name and billing start date
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        try (Formatter fmrt = new Formatter(sb, Locale.US);) {
            // Invoice headers
            fmrt.format("%-10s  %-27s  %-18s   %5s  %10s%n", "Date", "Consultant", "Skill", "Hours", "Charge")
                .format(StringUtils.repeat("-", 10) + "  ")
                .format(StringUtils.repeat("-", 27) + "  ")
                .format(StringUtils.repeat("-", 18) + "   ")
                .format(StringUtils.repeat("-", 5) + "  ")
                .format(StringUtils.repeat("-", 10) + "\n");
            
            for (InvoiceLineItem ln : lineItems) {
                fmrt.format(ln.toString());
            }
        }
        return sb.toString();
    }

    /**
     * Create a formatted string containing the printable invoice. Includes a header
     * and footer on each page.
     * 
     * @return The formatted invoice as a string.
     */
    public String toReportString() {
        StringBuilder sb = new StringBuilder();

        // Setup the header and footers
        InvoiceHeader invoiceHeader = new InvoiceHeader(bizName, bizAddress, getClientAccount(), LocalDate.now(),
                getStartDate());
        InvoiceFooter invoiceFooter = new InvoiceFooter(bizName);

        // Cycle through the invoice line items
        try (Formatter fmrt = new Formatter(sb, Locale.US);) {
            int lineCounter = 0;
            for (InvoiceLineItem lineItem : lineItems) {
                if (lineCounter == 5) {
                    fmrt.format("%n%s%n", invoiceFooter.toString());
                    invoiceFooter.incrementPageNumber();
                    lineCounter = 0;
                }
                if (lineCounter == 0) {
                    fmrt.format(invoiceHeader.toString());
                }
                fmrt.format(lineItem.toString());
                lineCounter++;
            }
            fmrt.format("%nTotal: %60d  %,10.2f%n", getTotalHours(), (float) getTotalCharges());
            fmrt.format(invoiceFooter.toString());
        }
        return sb.toString();
    }

}
