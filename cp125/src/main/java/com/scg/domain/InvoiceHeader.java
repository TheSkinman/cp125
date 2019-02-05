package com.scg.domain;

import java.time.LocalDate;
import java.util.Formatter;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.scg.util.Address;

/**
 * Header for Small Consulting Group Invoices. The printed header has the
 * following:
 * <ul>
 * <li>billing business' name</li>
 * <li>billing business' address</li>
 * <li>name of the client being billed</li>
 * <li>address of the client being billed</li>
 * <li>month being billed for</li>
 * <li>date the invoice was generated</li>
 * </ul>
 * All of this formatted in a readable fashion.
 * 
 * @author Norman Skinner (skinman@uw.edu)
 *
 */
public class InvoiceHeader {
	private String businessName;
	private Address businessAddress;
	private ClientAccount client;
	private LocalDate invoiceDate;
	private LocalDate invoiceForMonth;

	/**
	 * Construct an InvoiceHeader.
	 * 
	 * @param businessName
	 *            name of business issuing invoice
	 * @param businessAddress
	 *            address of business issuing invoice
	 * @param client
	 *            client for the invoice with this header.
	 * @param invoiceDate
	 *            date of the invoice with this header.
	 * @param invoiceForMonth
	 *            month of billable charges for invoice with this header.
	 */
	public InvoiceHeader(String businessName, Address businessAddress, ClientAccount client, LocalDate invoiceDate,
			LocalDate invoiceForMonth) {
		super();
		this.businessName = businessName;
		this.businessAddress = businessAddress;
		this.client = client;
		this.invoiceDate = invoiceDate;
		this.invoiceForMonth = invoiceForMonth;
	}

	/**
	 * Print this InvoiceHeader.
	 * 
	 * @return Formatted string of the information in this header.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		try (Formatter fmrt = new Formatter(sb, Locale.US);) {
    		// Company Address
    		fmrt.format("%s%n", businessName)
		    .format(businessAddress.toString() + "\n")
    		
    		// Invoice For
		    .format("Invoice for:%n")
		    .format(client.toString())
		    .format("%n%n")
    		
    		// Invoice Dates
		    .format("Invoice For Month of: %1$tB %1$tY%n", invoiceForMonth)
		    .format("Invoice Date: %1$tB %1$td, %1$tY%n%n", invoiceDate)
		
    		// Invoice headers
            .format("%-10s  %-27s  %-18s   %-5s  Charge%n", "Date", "Consultant", "Skill", "Hours")
		    .format("%s  %s  %s   %s  %s%n", StringUtils.repeat("-", 10), StringUtils.repeat("-", 27),
		        StringUtils.repeat("-", 18), StringUtils.repeat("-", 5), StringUtils.repeat("-", 10));
		}
		return sb.toString();
	}
	
}
