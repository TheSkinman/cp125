/**
 * 
 */
package com.scg.domain;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.scg.util.Address;
import com.scg.util.StateCode;

/**
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class Invoice {
	private static final Logger log = LoggerFactory.getLogger(Invoice.class);
	private static final String INVOICE_PROPERTIES_FILE = "invoice.properties";
	private ClientAccount client;
	private Month invoiceMonth;
	private int invoiceYear;
	private LocalDate startDate;
	private int totalCharges;
	private int totalHours;
	private List<InvoiceLineItem> lineItems;
	private String bizName;
	private Address bizAddress;
	private static Properties invoiceProperties;
	
	/**
	 * Construct an Invoice for a client. The time period is set from the beginning to the end of the month specified.
	 * 
	 * @param client
	 * @param invoiceMonth
	 * @param invoiceYear
	 * @throws IOException 
	 */
	public Invoice(ClientAccount client, Month invoiceMonth, int invoiceYear) {
		super();
		this.client = client;
		this.invoiceMonth = invoiceMonth;
		this.invoiceYear = invoiceYear;
		lineItems = new ArrayList<>();
		loadInvoiceProperties();
	}
	
	private void loadInvoiceProperties() {
		// Clean up and get ready to load up.
		invoiceProperties = new Properties();

		// Use a "try with" so we close up the stream when we are done.
		try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(INVOICE_PROPERTIES_FILE)) {
			if (inputStream != null) {
				invoiceProperties.load(inputStream);
			} else {
				log.error("property file '" + INVOICE_PROPERTIES_FILE + "' not found in the classpath");
				log.error("Exiting application.");
				System.exit(2);
			}
		} catch (Exception err) {
			log.error(err.toString());
			log.error("Exiting application.");
			System.exit(5);
		}
		
		// Initialize bizName and bizAddress with the loaded properties.
		bizName = invoiceProperties.getProperty("business.name");
		bizAddress = new Address(
				invoiceProperties.getProperty("business.street"),
				invoiceProperties.getProperty("business.city"),
				StateCode.valueOf(invoiceProperties.getProperty("business.state")),
				invoiceProperties.getProperty("business.zip"));
		System.out.println("done");
	}

	/**
	 * @return the client
	 */
	public ClientAccount getClientAccount() {
		return client;
	}

	/**
	 * @return the invoiceMonth
	 */
	public Month getInvoiceMonth() {
		return invoiceMonth;
	}

	/**
	 * @return the invoiceMonth
	 */
	public int getInvoiceYear() {
		return invoiceYear;
	}

	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @return the totalCharges
	 */
	public int getTotalCharges() {
		return totalCharges;
	}

	/**
	 * @return the totalHours
	 */
	public int getTotalHours() {
		return totalHours;
	}
	
	/**
	 * 
	 * @param lineItem
	 */
	public void addLineItem(InvoiceLineItem lineItem) {
		lineItems.add(lineItem);
		// Increase total hours
		totalHours += lineItem.getHours();
		// Increase total charges based on skill rate
		totalCharges += lineItem.getSkill().getRate() * lineItem.getHours();
	}
	
	/**
	 * 
	 * @param timeCard
	 */
	public void extractLineItems(TimeCard timeCard) {
		Consultant consultant = timeCard.getConsultant();
		for (ConsultantTime ct : timeCard.getBillableHoursForClient(getClientAccount().getName())) {
			if (getInvoiceMonth().equals(Month.of(ct.getDate().getMonthValue()))
					&& invoiceYear == ct.getDate().getYear()) {
				addLineItem(new InvoiceLineItem(ct.getDate(), consultant, ct.getSkill(), ct.getHours()));
			}
		}
	}
	
	public String toReportString() {
		return "";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		// Invoice headers
		sb.append(String.format("%-10s  %-27s  %-18s   %5s  $%10s%n",
				"Date", "Consultant", "Skill", "Hours", "Charge"));
		sb.append(StringUtils.repeat("-", 10) + "  ");
		sb.append(StringUtils.repeat("-", 27) + "  ");
		sb.append(StringUtils.repeat("-", 18) + "   ");
		sb.append(StringUtils.repeat("-", 5) + "  ");
		sb.append(StringUtils.repeat("-", 10) + "\n");

		for(InvoiceLineItem ln : lineItems) {
			sb.append(ln.toString());
		}
		
		String finalString = sb.toString(); 
		return finalString;
	}
	
	
	


}
