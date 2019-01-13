package com.scg.domain;

import java.time.LocalDate;

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
		// Company Address
		sb.append(String.format("%s%n", businessName));
		sb.append(businessAddress.toString() + "\n");
		
		// Invoice For
		sb.append("Invoice for:\n");
		sb.append(client.toString());
		sb.append("\n\n");
		
		// Invoice Dates
		sb.append(String.format("Invoice For Month of: %1$tB %1$tY%n", invoiceForMonth));
		sb.append(String.format("Invoice Date: %1$tB %1$td, %1$tY%n%n", invoiceDate));
		
		// Invoice headers
		sb.append(String.format("%-10s  %-27s  %-18s   %-5s  %-10s%n",
				"Date", "Consultant", "Skill", "Hours", "Charge"));
		sb.append(StringUtils.repeat("-", 10) + "  ");
		sb.append(StringUtils.repeat("-", 27) + "  ");
		sb.append(StringUtils.repeat("-", 18) + "   ");
		sb.append(StringUtils.repeat("-", 5) + "  ");
		sb.append(StringUtils.repeat("-", 10) + "\n");
		
		String finalString = sb.toString(); 
		return finalString;
	}
}
