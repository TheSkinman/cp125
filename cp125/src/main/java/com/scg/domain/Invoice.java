/**
 * 
 */
package com.scg.domain;

import java.time.LocalDate;
import java.time.Month;

/**
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class Invoice {
	private ClientAccount client;
	private Month invoiceMonth;
	private LocalDate startDate;
	private int totalCharges;
	private int totalHours;
	
	/**
	 * Construct an Invoice for a client. The time period is set from the beginning to the end of the month specified.
	 * 
	 * @param client
	 * @param invoiceMonth
	 * @param invoiceYear
	 */
	public Invoice(ClientAccount client, Month invoiceMonth, int invoiceYear) {
		super();
		this.client = client;
		this.invoiceMonth = invoiceMonth;
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
		
	}
	
	/**
	 * 
	 * @param timeCard
	 */
	public void extractLineItems(TimeCard timeCard) {
		
	}
	
	public String toReportString() {
		return "";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Invoice [client=" + client + ", invoiceMonth=" + invoiceMonth + ", startDate=" + startDate
				+ ", totalCharges=" + totalCharges + ", totalHours=" + totalHours + "]";
	}
	
	
	


}
